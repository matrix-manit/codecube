package in.ac.manit.matrix.codecube.dao;

import in.ac.manit.matrix.codecube.constants.UserConstants;
import in.ac.manit.matrix.codecube.enumerator.DataRowAccessLimit;
import in.ac.manit.matrix.codecube.enumerator.Gender;
import in.ac.manit.matrix.codecube.enumerator.OrderBy;
import in.ac.manit.matrix.codecube.model.User;

import java.util.List;

/**
 * The interface for Data Access Object for User Table.
 *
 * @author Mohit Godwani
 */
public interface UserDao {

    /**
     * Creates a User
     */
    public void addUser(User user);

    /**
     * Returns user corresponding to given <code>scholarNo</code>
     *
     * @param scholarNo
     * @return User having given scholarNo
     */
    public User getUser(Long scholarNo);

    /**
     * Returns user corresponding to given <code>emailAddress</code>
     *
     * @param emailAddress
     * @return User having given emailAddress
     */
    public User getUserByEmail(String emailAddress);

    /**
     * Returns user corresponding to given <code>handle</code>
     *
     * @param handle
     * @return User having given handle
     */
    public User getUserByHandle(String handle);

    /**
     * Returns user according to parameters (conditions defined by parameters are conjunctive)
     *
     * @param branches
     * @param joiningYears
     * @param gender
     * @param order
     * @param sortableFields
     * @param offset
     * @param limit
     * @return
     */
    public List<User> getUsers(List<String> branches, List<Integer> joiningYears, Gender gender,
                               OrderBy order, UserConstants.SortableFields sortableFields, Integer offset, DataRowAccessLimit limit);

    /**
     * Obtains a list of all the users
     *
     * @return list of users
     */
    public List<User> getUsers(Integer offset, DataRowAccessLimit limit);


    /**
     * Updates an entry in the table
     *
     * @param user the user to be updated
     */
    public void updateUser(User user);

    /**
     * Removes the entry from table
     *
     * @param user the user to be deleted from table
     */
    public void removeUser(Long scholarNumber);

    /**
     * Returns the password column for a given user
     *
     * @param scholarNumber
     * @return password
     */
    public String getPassword(Long scholarNumber);

    /**
     * Sets the password for user with given scholar number
     *
     * @param scholarNumber
     * @param password
     */
    public void setPassword(Long scholarNumber, String password);
}