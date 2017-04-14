package in.ac.manit.matrix.codecube.utilities;

import in.ac.manit.matrix.codecube.exception.InvalidSessionException;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;

public class HttpSessionUtil {

    private HttpServletRequest httpServletRequest;

    public HttpSessionUtil(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    public void createSession() {
        this.httpServletRequest.getSession(true);
    }

    @Nullable
    public Object getAttribute(String key) throws InvalidSessionException {
        if (this.sessionExists()) {
            Object obj = this.httpServletRequest.getSession(false).getAttribute(key);
            return obj;
        } else {
            throw new InvalidSessionException("Session does not exist", null);
        }
    }

    public void setAttribute(String attribute, String key) {
        this.httpServletRequest.getSession(false).setAttribute(attribute, key);
    }

    public void invalidateSession() {
        if (sessionExists())
            this.httpServletRequest.getSession(false).invalidate();
    }

    public boolean sessionExists() {
        return !(httpServletRequest.getSession(false) == null);
    }

}
