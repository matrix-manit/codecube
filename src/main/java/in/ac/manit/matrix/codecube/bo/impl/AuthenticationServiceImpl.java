package in.ac.manit.matrix.codecube.bo.impl;

import in.ac.manit.matrix.codecube.bo.AuthenticationService;
import in.ac.manit.matrix.codecube.constants.AuthenticationConstants;
import in.ac.manit.matrix.codecube.constants.ContactConstants;
import in.ac.manit.matrix.codecube.dao.CredentialDao;
import in.ac.manit.matrix.codecube.dao.PasswordResetRequestDao;
import in.ac.manit.matrix.codecube.dao.UserDao;
import in.ac.manit.matrix.codecube.model.Credential;
import in.ac.manit.matrix.codecube.model.PasswordResetRequest;
import in.ac.manit.matrix.codecube.model.User;
import in.ac.manit.matrix.codecube.utilities.PasswordUtil;

import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Shreesha Prabhu K
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    @Setter
    private UserDao userDao;

    @Setter
    private CredentialDao credentialDao;

    @Setter
    private PasswordResetRequestDao passwordResetRequestDao;

    /**
     * Checks the credential of the user with the credential stored in DB.
     *
     * @param scholarNo
     * @param rawPassword
     * @return <i>true</i> if credentials match else <i>false</i>
     * TODO user not present case to be handled
     */
    public boolean isCredentialValid(Long scholarNo, String rawPassword) {
        Credential credential = credentialDao.getCredential(scholarNo);
        String savedHashedPassword = userDao.getPassword(scholarNo);

        PasswordUtil passwordUtil = PasswordUtil.getDefault();
        boolean isValid = passwordUtil.isPasswordValid(rawPassword, credential.getSalt(), savedHashedPassword);
        return isValid;
    }

    /**
     * Set credentials for a user
     *
     * @param scholarNo
     * @param rawPassword
     */
    @Transactional
    public void setCredentials(Long scholarNo, String rawPassword) {
        PasswordUtil cryptUtil = PasswordUtil.getDefault();
        PasswordUtil.PasswordBundle passwordBundle = cryptUtil.generateSalt(rawPassword);

        Credential credential = new Credential();
        credential.setScholarNo(scholarNo);
        credential.setSalt(passwordBundle.getSalt());

        credentialDao.updateCredential(credential);
        userDao.setPassword(scholarNo, passwordBundle.getHashedPassword());
    }

    @Transactional
    public void resetPassword(Long scholarNo, String otp, String rawPassword) {
        PasswordResetRequest passwordResetRequest = this.passwordResetRequestDao.getPasswordResetRequest(scholarNo);
        Date date = new Date();
        Boolean otpValid, timeValid, triesValid;

        int triesRemaining = passwordResetRequest.getTriesRemaining();
        Timestamp currentTimeStamp = new Timestamp(date.getTime());  //ideally time should be taken from DB
        Timestamp otpTimeStamp = passwordResetRequest.getRequestTime();
        Timestamp permittedTimeStamp = new Timestamp(otpTimeStamp.getTime() + AuthenticationConstants.OTP_VALIDITY_TIME * 60 * 1000);


        otpValid = otp.equals(passwordResetRequest.getOneTimePassword());
        timeValid = currentTimeStamp.getTime() < permittedTimeStamp.getTime();  // hopefully no need for = operator.
        triesValid = triesRemaining > 0;
        if (otpValid && timeValid && triesValid) {
            setCredentials(scholarNo, rawPassword);
        } else {

            if (!timeValid)
                passwordResetRequestDao.deletePasswordResetRequest(passwordResetRequest.getScholarNo());

            else if (!triesValid) {
                // case when user exhausts no of tries to reset password
            } else if (!otpValid) {
                passwordResetRequest.setTriesRemaining(triesRemaining - 1);
                passwordResetRequestDao.updatePasswordResetRequest(passwordResetRequest);
            }
        }

    }

    public void sendOtp(User user, String otp) {

    }


    private String getFile(String fileName) {

        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        try {
            Scanner scanner = new Scanner(file);


            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();

    }

}
