package com.test.repositories.subprojects;
import com.test.entity.projects.SubProject;
import com.test.entity.tasks.UserTask;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class SubProjectRepositoryImpl implements SubProjectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createSubProject(SubProject subproject) {
        entityManager.persist(subproject);
    }

    @Override
    public SubProject getSubProjectById(Long subPrId) {
        return entityManager.find(SubProject.class, subPrId);
    }

    @Override
    public Optional<SubProject> findSubProjectByNameFromProjectById(String name, Long projectId) {
        TypedQuery<SubProject> query = entityManager.createQuery("select s from SubProject s JOIN s.project p WHERE p.id =:projectId AND s.subProjectName =:subprojectName", SubProject.class);
        query.setParameter("subprojectName",name);
        query.setParameter("projectId", projectId);
        return query.getResultStream().findFirst();
    }

    @Override
    public void deleteSubProject(String subprojectName) {
        TypedQuery<SubProject> query = entityManager.createQuery("select s from SubProject s WHERE s.subProjectName =:subprojectName",SubProject.class);
        query.setParameter("subprojectName",subprojectName);
        SubProject subProject = query.getResultList().get(0);
        entityManager.remove(subProject);
    }
}
