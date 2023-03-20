package ru.detmir.sap.job;

import com.google.gson.Gson;
import com.sap.scheduler.runtime.JobContext;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Logger;

class TelegramAlertClient {
    JobContext jobContext;
    String urlString;
    String apiToken;
    String chatId;
    Logger logger;

    public TelegramAlertClient(JobContext jobContext) {
        this.jobContext = jobContext;
        this.urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s&parse_mode=HTML";
        this.apiToken = jobContext.getJobParameter("apiToken").getStringValue();
        this.chatId = jobContext.getJobParameter("chatId").getStringValue();
        this.logger = jobContext.getLogger();
    }

    public void sendToTelegram(ArrayList<String> consumedAlerts) throws IOException {

        String alertJsonString;
        for (int i = 0; i < consumedAlerts.size(); i++) {
            alertJsonString = consumedAlerts.get(i);
            String AlertUrlString = encodeURLAlertString(alertJsonString);
            //using sun https handler for miss the casting to DSRHttpsURLConnection inside SAP JVM
            URL url = new URL(null, AlertUrlString, new sun.net.www.protocol.https.Handler());
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

    private String encodeURLAlertString(String alertJsonString) throws UnsupportedEncodingException {
        Gson gson = new Gson();
        Alert alert = gson.fromJson(alertJsonString, Alert.class);
        String alertText = URLEncoder.encode(alert.formatAlertText(), StandardCharsets.UTF_8.toString());
        return String.format(urlString, apiToken, chatId, alertText);
    }


}