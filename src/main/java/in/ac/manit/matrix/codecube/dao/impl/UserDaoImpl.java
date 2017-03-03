package in.ac.manit.matrix.codecube.dao.impl;

import in.ac.manit.matrix.codecube.dao.UserDao;
import in.ac.manit.matrix.codecube.enumerator.DataRowAccessLimit;
import in.ac.manit.matrix.codecube.enumerator.Gender;
import in.ac.manit.matrix.codecube.enumerator.OrderBy;
import in.ac.manit.matrix.codecube.model.User;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static in.ac.manit.matrix.codecube.constants.UserConstants.ModelFields;
import static in.ac.manit.matrix.codecube.constants.UserConstants.SortableFields;

/**
 * Implementation of UserDao for Data Access from User Table
 *
 * @author Mohit Godwani
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Setter
    private SessionFactory sessionFactory;

    private final static String PASSWORD_UPDATE_QUERY_TEMPLATE =
            String.format("UPDATE TABLE User SET password=:password WHERE {} = :{}", ModelFields.scholarNumber);

    private final static String PASSWORD_QUERY_TEMPLATE =
            String.format("FROM User WHERE {} = :{}", ModelFields.scholarNumber);

    public void addUser(User user) {
        this.sessionFactory.getCurrentSession().persist(user);
    }

    public User getUser(Long scholarNo) {
        return getUserByField(ModelFields.scholarNumber, scholarNo);
    }

    public User getUserByEmail(String emailAddress) {
        return getUserByField(ModelFields.emailAddress, emailAddress);
    }

    public User getUserByHandle(String handle) {
        return getUserByField(ModelFields.handle, handle);
    }

    public List<User> getUsers(List<String> branches, List<Integer> joiningYears, Gender gender, OrderBy order, SortableFields sortableFields, Integer offset, DataRowAccessLimit limit) {
        Criteria criteria = this.sessionFactory
                .getCurrentSession()
                .createCriteria(User.class);

        // Add conjunctive criteria
        Conjunction andCondition = Restrictions.conjunction();
        if (branches != null)
            andCondition.add(Restrictions.in(ModelFields.branch, branches));
        if (joiningYears != null)
            andCondition.add(Restrictions.in(ModelFields.joiningDate, joiningYears));
        if (gender != null) {
            andCondition.add(Restrictions.eq(ModelFields.gender, gender));
        }
        criteria.add(andCondition);

        // Add order-by criteria
        if (OrderBy.Ascending.equals(order))
            criteria.addOrder(Order.asc(sortableFields.getValue()));
        else if ((OrderBy.Descending.equals(order)))
            criteria.addOrder(Order.desc(sortableFields.getValue()));

        //Add limit criteria
        criteria.setFirstResult(offset);
        criteria.setMaxResults(limit.getValue());

        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers(Integer offset, DataRowAccessLimit limit) {
        return this.sessionFactory
                .getCurrentSession()
                .createCriteria(User.class)
                .setFirstResult(offset)
                .setMaxResults(limit.getValue())
                .list();
    }

    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    public void removeUser(Long scholarNumber) {
        Session session = this.sessionFactory.getCurrentSession();
        User entryInDatabase = (User) session.load(User.class, new Long(scholarNumber));
        if (entryInDatabase != null) {
            session.delete(entryInDatabase);
        }
    }

    public String getPassword(Long scholarNumber) {
        return (String) this.sessionFactory
                .getCurrentSession()
                .createQuery(PASSWORD_QUERY_TEMPLATE)
                .setParameter(ModelFields.scholarNumber, scholarNumber)
                .uniqueResult();
    }

    public void setPassword(Long scholarNumber, String password) {
        this.sessionFactory
                .getCurrentSession()
                .createSQLQuery(PASSWORD_QUERY_TEMPLATE)
                .setParameter(ModelFields.scholarNumber, scholarNumber)
                .executeUpdate();

    }

    private <T> User getUserByField(String fieldName, T value) {
        return (User) this.sessionFactory
                .getCurrentSession()
                .createCriteria(User.class)
                .add(Restrictions.eq(fieldName, value))
                .uniqueResult();
    }

}
