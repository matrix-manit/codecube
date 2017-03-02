package in.ac.manit.matrix.codecube.utilities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * This abstract util class is used to generate password and salt.
 * Also hashing the combination of password and salt.
 *
 * @author Tanay Khemani
 * @author Shreesha Prabhu K
 */

public abstract class PasswordUtil {

    /**
     * This class is used to bundle password, salt
     * and hash of password-salt combination
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    public class PasswordBundle {
        private String password;
        private String salt;
        private String hashedPassword;
    }

    /**
     * This method returns the default PasswordUtil subclass.
     * Currently default is BCryptPasswordUtil
     * @return default <i>PasswordUtil</i> object
     * NOTE: Only BCrypt encryption implemented. Use switch case in case of multiple implementations to
     *       return appropriate implementation object
     */
    public static PasswordUtil getDefault() {
        return new BCryptPasswordUtil();
    }

    /**
     * This method generates password and salt.
     * Hash of password, salt combination is also calculated.
     * @return <i>PasswordBundle</i> object enclosing password, salt and hash.
     */
    public abstract PasswordBundle generatePassword();

    /**
     * This method generates salt.
     * Hash of given password and generated salt is calculated.
     * @param password
     * @return <i>PasswordBundle</i> object enclosing password, salt and hash.
     */
    public abstract PasswordBundle generateSalt(String password);

    /**
     * This method tests whether hash of given password and salt matches with previous hash.
     * @param password
     * @param salt
     * @param prevHashedPassword
     * @return <i>true</i> if there is match else <i>false</i>
     */
    public abstract boolean isPasswordValid(String password, String salt, String prevHashedPassword);
}
