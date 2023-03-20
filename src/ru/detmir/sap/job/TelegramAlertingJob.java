package ru.detmir.sap.job;

import com.sap.scheduler.runtime.JobContext;
import com.sap.scheduler.runtime.mdb.MDBJobImplementation;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Message-Driven Bean implementation class for: TelegramAlertingJob
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "JobDefinition='TelegramAlertingJob'"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")})
public class TelegramAlertingJob extends MDBJobImplementation {
    private static final long serialVersionUID = 1L;

    @Override
    public void onJob(JobContext jobContext) throws Exception {
        int maxAlertCount;
        Logger logger = jobContext.getLogger();
        //consume alerts from queue
        ArrayList<String> consumedAlerts = consumeAlerts(jobContext, logger);
        //sending messages to tg
        try {
            if (consumedAlerts.size() > 0) {
                TelegramAlertClient client = new TelegramAlertClient(jobContext);
                client.sendToTelegram(consumedAlerts);
            } else {
                logger.info("No alerts consumed");
            }
        } catch (IOException e) {
            logger.severe(e.getMessage());
            throw new RuntimeException();
        }
    }

    private static ArrayList<String> consumeAlerts(JobContext jobContext, Logger logger) {
        AlertsQueue queue = new AlertsQueue();
        int maxAlertCount = jobContext.getJobParameter("maxAlertCount").getIntegerValue();
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
        return consumedAlerts;
    }
}