package in.ac.manit.matrix.codecube.utilities.emailImpl;

import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import in.ac.manit.matrix.codecube.utilities.EmailMessageFormat;
import in.ac.manit.matrix.codecube.utilities.EmailUtil;
import in.ac.manit.matrix.codecube.utilities.SingletonObjects;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by tanay khemani on 01-Mar-17.
 */
public class GmailImpl implements EmailUtil{

    private static MimeMessage createEmail(EmailMessageFormat emailMessageFormat)
            throws javax.mail.MessagingException{
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(emailMessageFormat.getFrom()));
        email.addRecipient(javax.mail.Message.RecipientType.TO,
                new InternetAddress(emailMessageFormat.getTo()));
        email.setSubject(emailMessageFormat.getSubject());
        email.setText(emailMessageFormat.getBody());
        return email;
    }

    private static Message createMessageWithEmail(MimeMessage emailContent)
            throws javax.mail.MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }
    private static Message sendMimeMessage(Gmail service,
                                           String userId,
                                           MimeMessage emailContent)
            throws MessagingException, IOException {
        Message message = createMessageWithEmail(emailContent);
        message = service.users().messages().send(userId, message).execute();


        return message;
    }

    public  boolean sendMail(EmailMessageFormat emailMessageFormat)
    {
        try {
            MimeMessage mimeMessage = createEmail(emailMessageFormat);
            Message message = sendMimeMessage(SingletonObjects.getGmailService(), "me", mimeMessage);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }



}
