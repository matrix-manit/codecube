package in.ac.manit.matrix.codecube.utilities;

import in.ac.manit.matrix.codecube.constants.AuthenticationConstants;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.Base64Utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Implementation of PasswordUtil abstract class
 * @author Shreesha Prabhu K
 */
public class BCryptPasswordUtil extends PasswordUtil {

    public PasswordBundle generatePassword() {
        // generate random password
        SecureRandom rnd = new SecureRandom();
        // Single Base64 character corresponds to 6 bits. 2^6 = 64
        Integer numberOfBits = AuthenticationConstants.GENERATED_PASSWORD_LENGTH * 6 - 1;
        BigInteger bigInt = new BigInteger(numberOfBits, rnd);
        byte[] num = bigInt.toByteArray();
        String password = Base64Utils.encodeToUrlSafeString(num);

        PasswordBundle passwordBundle = generateSalt(password);
        return passwordBundle;
    }

    public PasswordBundle generateSalt(String password) {
        PasswordBundle passwordStruct = new PasswordBundle();

        // password
        passwordStruct.setPassword(password);

        // salt
        String salt = BCrypt.gensalt();
        passwordStruct.setSalt(salt);

        // hash of password and salt
        String hashedPassword = BCrypt.hashpw(password, salt);
        passwordStruct.setHashedPassword(hashedPassword);

        return passwordStruct;
    }

    public boolean isPasswordValid(String password, String salt, String prevHashedPassword) {
        String hashedPassword = BCrypt.hashpw(password, salt);
        boolean isValid = prevHashedPassword.equals(hashedPassword);
        return isValid;
    }
}
