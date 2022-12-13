package ru.detmir.sap.job;

import com.google.gson.Gson;
import com.sap.scheduler.runtime.JobContext;
import com.sap.scheduler.runtime.mdb.MDBJobImplementation;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Message-Driven Bean implementation class for: TelegramAlertingJob
 */
@MessageDriven(activationConfig = {@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "JobDefinition='TelegramAlertingJob'"), @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")})
public class TelegramAlertingJob extends MDBJobImplementation {
    private static final long serialVersionUID = 1L;

    @Override
    public void onJob(JobContext jobContext) throws Exception {
        int maxAlertCount;
        Logger logger = jobContext.getLogger();
        maxAlertCount = jobContext.getJobParameter("maxAlertCount").getIntegerValue();
        AlertsQueue queue = new AlertsQueue();
        ArrayList<String> consumedAlerts = new ArrayList<>();
        try {
            queue.open(jobContext);
            for (int i = 0; i < maxAlertCount; i++) {
                String alert = queue.nextAlert();
                if (alert == null) {
                    break;
                }
                logger.info("logged alert: " + alert);
                consumedAlerts.add(alert);
            }
        } catch (NamingException | JMSException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            queue.close();
        }


        //sending messages to tg
        try {
            if (consumedAlerts.size() > 0) {
                sendToTelegram(consumedAlerts, jobContext);
            } else {
                logger.info("No alerts consumed");
            }
        } catch (IOException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException();
        }

    }

    private void sendToTelegram(ArrayList<String> consumedAlerts, JobContext jobContext) throws IOException {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s&parse_mode=HTML";
        String apiToken = jobContext.getJobParameter("apiToken").getStringValue();
        String chatId = jobContext.getJobParameter("chatId").getStringValue();
        Logger logger = jobContext.getLogger();
        Gson gson = new Gson();
        String alertJsonString;
        for (int i = 0; i < consumedAlerts.size(); i++) {
            alertJsonString = consumedAlerts.get(i);
            Alert alert = gson.fromJson(alertJsonString, Alert.class);
            String alertText = URLEncoder.encode(alert.formatAlertText(), StandardCharsets.UTF_8.toString());
            //logger.info(alertText);
            String AlertUrlString = String.format(urlString, apiToken, chatId, alertText);
            //logger.info(AlertUrlString);
            URL url = new URL(null, AlertUrlString, new sun.net.www.protocol.https.Handler()); //using sun https handler for miss the casting to DSRHttpsURLConnection inside SAP JVM
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            InputStream is = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            String response = sb.toString();
            if (!response.contains("\"ok\":true")) {
                logger.severe("Error sending to tg channel");
                logger.severe(response);
            }
        }
    }
}