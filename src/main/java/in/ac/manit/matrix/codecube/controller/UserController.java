package in.ac.manit.matrix.codecube.controller;

import in.ac.manit.matrix.codecube.bo.UserService;
import in.ac.manit.matrix.codecube.constants.UserConstants.ModelFields;
import in.ac.manit.matrix.codecube.enumerator.DataRowAccessLimit;
import in.ac.manit.matrix.codecube.enumerator.Gender;
import in.ac.manit.matrix.codecube.enumerator.OrderBy;
import in.ac.manit.matrix.codecube.exception.CodecubeRuntimeException;
import in.ac.manit.matrix.codecube.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static in.ac.manit.matrix.codecube.constants.UserConstants.SortableFields;

/**
 * Controller for User API
 *
 * @author Mohit Godwani
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping("/")
    public List<User> listUsers(@RequestParam(defaultValue = "0") Integer offset,
                                @RequestParam(defaultValue = "SMALL") DataRowAccessLimit limit,
                                @RequestParam(required = false) List<String> branches,
                                @RequestParam(required = false) List<Integer> joiningYears,
                                @RequestParam(required = false) Gender gender,
                                @RequestParam(defaultValue = "Ascending") OrderBy orderBy,
                                @RequestParam(defaultValue = ModelFields.scholarNumber) SortableFields sortableFields) {


        List<User> listUsers =
                this.userService.getUsers(branches, joiningYears, gender, orderBy, sortableFields, offset, limit);
        removeUnauthorizedFields(listUsers);
        return listUsers;
    }

    @RequestMapping(value = "/{scholarNumber}", method = {RequestMethod.GET})
    public User getUserByScholarNumber(@PathVariable Long scholarNumber, HttpServletRequest request) {
        HttpSession session = getSession(request);
        boolean removeAuthorizedFields = (session == null) || (scholarNumber == null)
                || !scholarNumber.toString().equals(session.getAttribute("scholarNumber"));

        User user = this.userService.getUser(scholarNumber);
        if (removeAuthorizedFields)
            removeUnauthorizedFields(user);
        return user;
    }

    @RequestMapping(value = "/{scholarNumber}", method = {RequestMethod.PATCH})
    public User updateUser(@PathVariable Long scholarNumber, HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/{scholarNumber}", method = {RequestMethod.PUT})
    public User updateUserPassword(@PathVariable Long scholarNumber, HttpServletRequest request) {
        return null;
    }

    private HttpSession getSession(HttpServletRequest request) {
        return request.getSession(false);
    }

    private void verifySession(HttpServletRequest request) {
        if (getSession(request) == null)
            throw new CodecubeRuntimeException("Session not set.", null);
    }

    private void removeUnauthorizedFields(List<User> users) {
        for (User user : users) {
            removeUnauthorizedFields(user);
        }
    }

    private void removeUnauthorizedFields(User user) {
        user.setPhoneNumber(null);
        user.setEmailAddress(null);
    }
}
