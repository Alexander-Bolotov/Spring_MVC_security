package ru.aibolotov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aibolotov.model.Role;
import ru.aibolotov.model.User;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
@Transactional
public class RolesDaoImpl implements RolesDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Role> getListRoles() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);
        Root< Role > root = cq.from(Role.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public boolean roleIsExist(String role) {
        List<Role> roles = getListRoles();
        int size = roles.size();
        for (int i = 0; i < size; i++) {
            if(roles.get(i).getRole().equals(role)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Role getRoleByName(String roleName) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Role WHERE role = '" + roleName+"'");
        List roles = ((org.hibernate.query.Query) query).list();
        return (Role) roles.get(0);
    }
}
