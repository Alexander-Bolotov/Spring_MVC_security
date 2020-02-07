package ru.aibolotov.dao;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aibolotov.model.User;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<User> getListUsers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery< User > cq = cb.createQuery(User.class);
        Root< User > root = cq.from(User.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();

    }

    @Override
    public void addUser(User user) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(user);
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public void deleteUserById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("DELETE User WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public User getUser(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User WHERE name = '" + name+"'");
        List users = ((org.hibernate.query.Query) query).list();
        return (User) users.get(0);
    }


}
