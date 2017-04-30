package in.ac.manit.matrix.codecube.utilities;

import in.ac.manit.matrix.codecube.exception.InvalidSessionException;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;

public class HttpSessionUtil {



    private HttpSessionUtil()
    {
        //private constructor to prevent objects being created
    }


    public static void createSession(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession(true);
    }

    @Nullable
    public static Object getAttribute(HttpServletRequest httpServletRequest,String key) throws InvalidSessionException {
        if (sessionExists(httpServletRequest)) {
            Object obj = httpServletRequest.getSession(false).getAttribute(key);
            return obj;
        } else {
            throw new InvalidSessionException("Session does not exist", null);
        }
    }

    public static void setAttribute(HttpServletRequest httpServletRequest, String key, Object value) {
        httpServletRequest.getSession(false).setAttribute(key, value);
    }

    public static void invalidateSession(HttpServletRequest httpServletRequest) {
        if (sessionExists(httpServletRequest))
            httpServletRequest.getSession(false).invalidate();
    }

    public static boolean sessionExists(HttpServletRequest httpServletRequest) {
        return !(httpServletRequest.getSession(false) == null);
    }

}
