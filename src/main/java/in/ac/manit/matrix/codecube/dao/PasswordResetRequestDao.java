package in.ac.manit.matrix.codecube.dao;

import in.ac.manit.matrix.codecube.model.PasswordResetRequest;

/**
 * @author Shreesha Prabhu K
 */
public interface PasswordResetRequestDao {

    public void createResetRequest(PasswordResetRequest passwordResetRequest);

    public PasswordResetRequest getPasswordResetRequest(Long scholarNo);

    public void deletePasswordResetRequest(Long scholarNo);

    public void updatePasswordResetRequest(PasswordResetRequest passwordResetRequest);
}
