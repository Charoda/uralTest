package com.test.repositories.usertasks;
import com.test.entity.tasks.ManagerTask;
import com.test.entity.tasks.UserTask;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsertaskRepositoryImpl implements UserTaskRepository {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void save(UserTask task) {
        System.out.println("Hello Akhmed");
        entityManager.persist(task);
    }

//    @Override
//    public UserTask updateTask(UserTask userTask) {
//        return entityManager.merge(userTask);
//    }



    @Override
    public void updateTask(UserTask userTask) {
        Query query = entityManager.createQuery("UPDATE UserTask  SET " +
                "taskName =: taskName," +
                "status =: status," +
                "creationDate =: creationDate ," +
                "modificationDate =: modificationDate," +
                "info =: info WHERE id =: taskId");
        System.out.println("Query1");
        query.setParameter("taskId",userTask.getId());
        System.out.println("Query2");
        query.setParameter("taskName",userTask.getTaskName());
        System.out.println("Query4");
        query.setParameter("status",userTask.getStatus());
        System.out.println("Query5");
        query.setParameter("creationDate",userTask.getCreationDate());
        System.out.println("Query6");
        query.setParameter("modificationDate",userTask.getModificationDate());
        System.out.println("Query7");
        query.setParameter("info",userTask.getInfo());
        System.out.println("Query8");
        query.executeUpdate();
        System.out.println("Query3");
    }


    public boolean isInPersistenceContext(UserTask userTask) {
        boolean contains = entityManager.contains(userTask);
        System.out.println("Is Entity in PC: " + contains);
        return contains;
    }

    @Override
    public void deleteUserTaskByName(String usertaskname) {
        TypedQuery<UserTask> query = entityManager.createQuery("select t from UserTask t WHERE t.taskName =:name", UserTask.class);
        query.setParameter("name",usertaskname);
        UserTask userTask = query.getResultList().get(0);
        entityManager.remove(userTask);
    }

    @Override
    public Optional<UserTask> findTaskByNameFromProject(String taskName, Long projectId) {
        TypedQuery<UserTask> query = entityManager.createQuery("select u FROM UserTask u" +
                " JOIN u.project p" +
                " WHERE p.id =: projectId AND u.taskName =:taskName", UserTask.class);
        query.setParameter("projectId",projectId);
        query.setParameter("taskName",taskName);
        return query.getResultStream().findFirst();

    }

    @Override
    public void deleleTask(UserTask userTask) {
        entityManager.remove(userTask);
    }

    @Override
    public Optional<UserTask> findTaskByNameFromSubProject(String taskName,String subProjectName, Long projectId) {
        TypedQuery<UserTask> query = entityManager.createQuery("select u from UserTask u" +
                " JOIN u.subProject s" +
                " JOIN s.project p " +
                "WHERE p.id =:projectId " +
                "AND s.subProjectName =:subProjectName AND u.taskName =:taskName", UserTask.class);
        query.setParameter("projectId",projectId);
        query.setParameter("taskName",taskName);
        query.setParameter("subProjectName",subProjectName);
        return query.getResultStream().findFirst();
    }
}
