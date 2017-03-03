package in.ac.manit.matrix.codecube.controller;

import in.ac.manit.matrix.codecube.bo.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    @Qualifier(value = "authenticationService")
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/session", method = RequestMethod.PUT)
    public Boolean createSession(@RequestParam(value = "scholar_number") Long scholarNo,
                                 @RequestParam(value = "raw_password") String rawPassword) {
        Boolean isCredentialValid = this.authenticationService.isCredentialValid(scholarNo, rawPassword);

        return isCredentialValid;
    }

    @RequestMapping(value = "/session", method = RequestMethod.DELETE)
    public Boolean deleteSession(@RequestParam(value = "scholar_number") Long scholarNo) {
        return true;
    }
}
