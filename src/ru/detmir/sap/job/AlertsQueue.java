package ru.detmir.sap.job;

import com.sap.scheduler.runtime.JobContext;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Logger;


public class AlertsQueue {

    private Logger logger;
    private QueueConnection connection;
    private QueueSession session;
    private Queue queue;
    private QueueReceiver receiver;
    private InitialContext context;


    public void open(JobContext jobContext) throws NamingException, JMSException {
        this.context = new InitialContext();
        QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("jmsfactory/alertingVP/AlertingConsumerConnectionFactory");
        this.connection = factory.createQueueConnection();
        this.session = this.connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        this.queue = (Queue) context.lookup("jmsqueues/alertingVP/jms/queue/xi/monitoring/alert/" + jobContext.getJobParameter("alertConsumer").getStringValue());
        this.receiver = this.session.createReceiver(queue);
        this.connection.start();
    }

    public void close() {
        if (this.receiver != null) {
            try {
                this.receiver.close();
            } catch (JMSException e) {

            }
        }
        if (this.session != null) {
            try {
                this.session.close();
            } catch (JMSException e) {

            }
        }
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (JMSException e) {

            }
        }
        if (this.context != null) {
            try {
                this.context.close();
            } catch (NamingException e) {

            }
        }
    }

    public String nextAlert() throws JMSException {
        TextMessage message = (TextMessage) this.receiver.receiveNoWait();
        return message != null ? message.getText() : null;
    }

}
