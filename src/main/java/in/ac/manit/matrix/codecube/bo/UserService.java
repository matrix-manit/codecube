package in.ac.manit.matrix.codecube.bo;

import in.ac.manit.matrix.codecube.constants.UserConstants;
import in.ac.manit.matrix.codecube.enumerator.DataRowAccessLimit;
import in.ac.manit.matrix.codecube.enumerator.Gender;
import in.ac.manit.matrix.codecube.enumerator.OrderBy;
import in.ac.manit.matrix.codecube.model.User;

import java.util.List;

/**
 * Service Interface for operations on User data.
 *
 * @author Mohit Godwani
 */
public interface UserService {

    /**
     * Creates a User
     * @param user
     */
    public void addUser(User user);

    /**
     * Returns user corresponding to given <code>scholarNo</code>
     * @param scholarNo
     * @return User having given scholarNo
     */
    public User getUser(Long scholarNo);

    /**
     * Returns user corresponding to given <code>emailAddress</code>
     * @param emailAddress
     * @return User having given emailAddress
     */
    public User getUserByEmail(String emailAddress);

    /**
     * Returns user corresponding to given <code>handle</code>
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
     * @param sortColumn
     * @param offset
     * @param limit
     * @return
     */
    public List<User> getUsers(List<String> branches, List<Integer> joiningYears, Gender gender, OrderBy order, UserConstants.SortableFields sortColumn, Integer offset, DataRowAccessLimit limit);

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
}