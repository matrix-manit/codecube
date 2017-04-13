package in.ac.manit.matrix.codecube.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.ac.manit.matrix.codecube.utilities.SingletonObjects;
import in.ac.manit.matrix.codecube.bo.UserService;
import in.ac.manit.matrix.codecube.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tanay khemani on 28-Feb-17.
 */
@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;



    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean addUser(@RequestBody User user)
    {
        // TODO validate User object.
        // TODO How to verify whether User persisted.


        try {
           
            userService.addUser(user);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;

    }

    @RequestMapping(value = "/{scholarNumber}", method = RequestMethod.PUT )
    public User updateUser(@RequestBody User user, @PathVariable Long scholarNumber)
    {
        userService.updateUser(user);
        return null;
    }
}
