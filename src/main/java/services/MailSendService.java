package services;

import org.apache.log4j.Logger;
import test.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class MailSendService {
    private static final Logger LOGGER = Logger.getLogger(MailSendService.class);


    public void sendEmail(String userEmail, String subject, String text) {
        try {
            final Properties properties = new Properties();
            properties.load(Objects.requireNonNull(Test.class.getClassLoader().getResourceAsStream("mail.properties")));

            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("usertest0103@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject(subject);
            message.setText(text);


            Transport transport = session.getTransport();
            transport.connect("Usertest0103", "Usertest01032020");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (IOException e) {
            LOGGER.error("IO exception occurred", e);
        } catch (MessagingException e) {
            LOGGER.error("Messaging exception occurred", e);
        }
    }

}
