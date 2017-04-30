package in.ac.manit.matrix.codecube.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.gmail.Gmail;
import in.ac.manit.matrix.codecube.utilities.emailImpl.GmailImpl;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by tanay khemani on 01-Mar-17.
 */
public class SingletonObjects {

    private static ObjectMapper objectMapper;

    private static Gmail gmailService;

    private static EmailUtil emailUtil;

    private static GmailUtil gmailUtil;


    public static ObjectMapper getObjectMapper()
    {
        if(objectMapper == null)
        {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public static Gmail getGmailService()throws IOException
    {
        if(gmailService == null)
        {
                GmailUtil locGmailUtil = getGmailUtil();
                gmailService = locGmailUtil.getGmailService();

        }
        return gmailService;
    }

    public static EmailUtil getEmailUtil()
    {
        if(emailUtil == null)
        {
            emailUtil = new GmailImpl();
        }
        return emailUtil;
    }

    private static GmailUtil getGmailUtil()
    {
        if(gmailUtil == null)
        {
            gmailUtil = new GmailUtil();
        }
        return gmailUtil;
    }


}
