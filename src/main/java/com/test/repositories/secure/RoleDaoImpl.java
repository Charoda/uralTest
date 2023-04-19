package com.test.repositories.secure;
import com.test.entity.secure.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> index() {
        Query query = em.createQuery("from Role");
        return query.getResultList();
    }


    @Override
    public Role getRole(Long id) {
        return em.find(Role.class,id);
    }


    @Override
    public List<Role> getRolesListById(List<Long> roles) {
        TypedQuery<Role> q = em.createQuery("select r from Role r where r.id in :role", Role.class);
        q.setParameter("role", roles);
        return new ArrayList<>(q.getResultList());
    }
}
