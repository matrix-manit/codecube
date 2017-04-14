package in.ac.manit.matrix.codecube.controller;

import in.ac.manit.matrix.codecube.bo.AuthenticationService;
import in.ac.manit.matrix.codecube.constants.SessionConstants;
import in.ac.manit.matrix.codecube.exception.InvalidSessionException;
import in.ac.manit.matrix.codecube.utilities.HttpSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    @Qualifier(value = "authenticationService")
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/session", method = RequestMethod.PUT)
    public Boolean createSession(HttpServletRequest request,
                                 @RequestParam(value = "scholar_number") Long scholarNo,
                                 @RequestParam(value = "raw_password") String rawPassword) {
        // TODO: handle the case if session is already established

        Boolean isCredentialValid = this.authenticationService.isCredentialValid(scholarNo, rawPassword);

        if (isCredentialValid) {
            HttpSessionUtil httpSessionUtil = new HttpSessionUtil(request);
            httpSessionUtil.invalidateSession();
            httpSessionUtil.createSession();
            httpSessionUtil.setAttribute(SessionConstants.KEY_SCHOLAR_NUMBER, scholarNo);
        }

        return isCredentialValid;
    }

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public Long getSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSessionUtil httpSessionUtil = new HttpSessionUtil(request);
        try {
            return (Long) httpSessionUtil.getAttribute(SessionConstants.KEY_SCHOLAR_NUMBER);
        } catch (InvalidSessionException e) {
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/session", method = RequestMethod.DELETE)
    public Boolean deleteSession(HttpServletRequest request) {
        HttpSessionUtil httpSessionUtil = new HttpSessionUtil(request);
        if (httpSessionUtil.sessionExists())
            httpSessionUtil.invalidateSession();

        return true;
    }
}
