package in.ac.manit.matrix.codecube.controller;

import in.ac.manit.matrix.codecube.bo.UserService;
import in.ac.manit.matrix.codecube.enumerator.DataRowAccessLimit;
import in.ac.manit.matrix.codecube.enumerator.Gender;
import in.ac.manit.matrix.codecube.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Controller for Greeting message
 */
@RestController
public class HelloWorldController {

    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/get")
    public List<User> showMessage(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        List<User> list = this.userService.getUsers(0, DataRowAccessLimit.VERY_LARGE);
        StringBuilder sb = new StringBuilder("");
        for (User user : list) {
            sb.append(user.toString() + "\n");
        }
        return list;
    }


    @RequestMapping("/user")
    public String addUser(@RequestParam(value = "scholar_number", required = true) Long scholarNumber) {
        User user = new User();
        user.setScholarNumber(scholarNumber);
        user.setEmailAddress(Long.toString(System.currentTimeMillis()));
        user.setGender(Gender.Male);
        user.setJoiningDate(Date.valueOf("2016-11-1"));
        this.userService.addUser(user);

        return "{\'Success\':\'true\'}";
    }

    @RequestMapping("/hello")
    public String doIt() {
        return "{\'Success\':\'true\'}";
    }

    /*@Autowired
    ApplicationContext applicationContext;

    @RequestMapping("/beans")
    public List<String> getBeans() {
        return java.util.Arrays.asList(applicationContext.getBeanDefinitionNames());
    }*/
}
