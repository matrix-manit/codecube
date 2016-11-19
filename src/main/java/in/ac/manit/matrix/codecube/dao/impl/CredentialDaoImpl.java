package in.ac.manit.matrix.codecube.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import in.ac.manit.matrix.codecube.dao.CredentialDao;
import in.ac.manit.matrix.codecube.model.Credential;
import lombok.Setter;

/**
 * Implementation of CredentialDao for Data Access at User Level
 *
 * @author Shreesha Prabhu K
 */
@Repository
public class CredentialDaoImpl implements CredentialDao {
	
	@Setter
	private SessionFactory sessionFactory;

	public void addCredential(Credential credential) {
		this.sessionFactory.getCurrentSession().persist(credential);
	}

	public void updateCredential(Credential credential) {
		this.sessionFactory.getCurrentSession().update(credential);
	}

	public void removeCredential(Long scholarNo) {
		Session session = this.sessionFactory.getCurrentSession();
		Credential credential = (Credential) session.load(Credential.class, new Long(scholarNo));
		if (credential != null) {
			session.delete(credential);
		}
	}

	public Credential getCredential(Long scholarNo) {
		Session session = this.sessionFactory.getCurrentSession();
		Credential credential = (Credential) session.load(Credential.class, new Long(scholarNo));
		return credential;
	}

}
