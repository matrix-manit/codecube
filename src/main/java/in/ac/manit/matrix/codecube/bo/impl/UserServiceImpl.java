package in.ac.manit.matrix.codecube.bo.impl;

import in.ac.manit.matrix.codecube.bo.UserService;
import in.ac.manit.matrix.codecube.constants.UserConstants;
import in.ac.manit.matrix.codecube.dao.UserDao;
import in.ac.manit.matrix.codecube.enumerator.DataRowAccessLimit;
import in.ac.manit.matrix.codecube.enumerator.Gender;
import in.ac.manit.matrix.codecube.enumerator.OrderBy;
import in.ac.manit.matrix.codecube.model.User;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of User Service
 *
 * @author Mohit Godwani
 */
@Service
public class UserServiceImpl implements UserService {

    @Setter
    private UserDao userDao;

    @Transactional
    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    @Transactional
    public User getUser(Long scholarNo) {
        return this.userDao.getUser(scholarNo);
    }

    @Transactional
    public User getUserByEmail(String emailAddress) {
        return this.userDao.getUserByEmail(emailAddress);
    }

    @Transactional
    public User getUserByHandle(String handle) {
        return this.userDao.getUserByHandle(handle);
    }

    @Transactional
    public List<User> getUsers(List<String> branches, List<Integer> joiningYears, Gender gender, OrderBy order, UserConstants.SortableFields sortColumn, Integer offset, DataRowAccessLimit limit) {
        return this.userDao.getUsers(branches, joiningYears, gender, order, sortColumn, offset, limit);
    }

    @Transactional
    public List<User> getUsers(Integer offset, DataRowAccessLimit limit) {
        return this.userDao.getUsers(offset, limit);
    }

    @Transactional
    public void updateUser(User user) {
        this.userDao.addUser(user);
    }

    @Transactional
    public void removeUser(Long scholarNumber) {
        this.userDao.removeUser(scholarNumber);
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
