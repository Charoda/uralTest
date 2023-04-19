package com.test.repositories.secure;
import com.test.entity.secure.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;



    @Override
    public List<User> index() {
        Query query = em.createQuery("from User");
        return query.getResultList();
    }

    @Override
    public Optional<User> show(Long id) {
        return Optional.of(em.find(User.class,id));
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }




    @Override
    public void update(Long id, User user) {
        User userforUpdate = em.find(User.class,id);
        userforUpdate.setUsername(user.getUsername());
        userforUpdate.setPassword(user.getPassword());
        userforUpdate.setEmail(user.getEmail());
        em.merge(userforUpdate);
    }

    @Override
    public void newupdate(User user) {
        em.merge(user);
    }

    @Override
    public void delete(Long id) {
        em.remove(em.find(User.class,id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Query query = em.createQuery("select u from User u join fetch u.roles where u.username =: username ");
        query.setParameter("username",username);
        return query.getResultList().stream().findFirst();
    }
}