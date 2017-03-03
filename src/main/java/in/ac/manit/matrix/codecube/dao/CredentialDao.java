package in.ac.manit.matrix.codecube.dao;

import in.ac.manit.matrix.codecube.model.Credential;

/**
 * The interface for Data Access Object for Credential Table.
 *
 * @author Shreesha Prabhu K
 * @author Tanay Khemani
 */
public interface CredentialDao {
	/**
	 * 
	 * @param credential
	 */
	public void addCredential(Credential credential);

	/**
	 * 
	 * @param credential
	 */
	public void updateCredential(Credential credential);

	/**
	 * 
	 * @param credential
	 */
	public void removeCredential(Long scholarNo);
	
	/**
	 * 
	 * @param scholarNo
	 */
	public Credential getCredential(Long scholarNo);
}
