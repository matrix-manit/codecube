package in.ac.manit.matrix.codecube.dao.impl;

import in.ac.manit.matrix.codecube.dao.PasswordResetRequestDao;
import in.ac.manit.matrix.codecube.model.PasswordResetRequest;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author Mohit Godwani
 *         Created on 3/3/17.
 */
@Repository
public class PasswordResetRequestDaoImpl implements PasswordResetRequestDao {

    @Setter
    private SessionFactory sessionFactory;

    public void createResetRequest(PasswordResetRequest passwordResetRequest) {
        this.sessionFactory.getCurrentSession().persist(passwordResetRequest);
    }

    public PasswordResetRequest getPasswordResetRequest(Long scholarNo) {
        return (PasswordResetRequest)this.sessionFactory
                .getCurrentSession()
                .createCriteria(PasswordResetRequest.class)
                .add(Restrictions.eq("user.scholarNumber", scholarNo))
                .uniqueResult();
    }

    public void deletePasswordResetRequest(Long scholarNo) {
        Session currentSession = this.sessionFactory.getCurrentSession();
        PasswordResetRequest resetRequest = (PasswordResetRequest) currentSession.get(PasswordResetRequest.class, scholarNo);

        if (resetRequest != null) {
            currentSession.delete(resetRequest);
        }
    }

    public void updatePasswordResetRequest(PasswordResetRequest passwordResetRequest) {
        this.sessionFactory.getCurrentSession().update(passwordResetRequest);
    }
}
