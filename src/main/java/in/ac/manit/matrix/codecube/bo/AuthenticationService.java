package in.ac.manit.matrix.codecube.bo;

/**
 * 
 * @author Shreesha Prabhu K
 *
 */
public interface AuthenticationService {
	/**
	 * Tests whether the credential entered by user is valid.
	 * @param scholarNo
	 * @param rawPassword
	 * @return <i>true</i> if credentials match else <i>false</i>
	 */
	public boolean isCredentialValid(Long scholarNo, String rawPassword);

	/**
	 * Updates credentials.
	 * @param scholarNo
	 * @param rawPassword
	 */
	public void setCredentials(Long scholarNo, String rawPassword);
	

	public void resetPassword(Long scholarNo, String otp, String rawPassword);
}
