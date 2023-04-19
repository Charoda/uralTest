package com.test.repositories.projects;


import com.test.entity.projects.Project;
import com.test.entity.projects.SubProject;
import com.test.entity.tasks.UserTask;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createProject(Project project) {
        entityManager.persist(project);
    }

    @Override
    public Set<Project> getProjectList() {
    TypedQuery<Project> query =  entityManager.createQuery("select p from Project p",Project.class);
        return query.getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Optional<Project> findProjectById(Long projectId) {
        return Optional.ofNullable(entityManager.find(Project.class,projectId));
    }

    @Override
    public Set<UserTask> getProjectTaskList(Long projectId) {
        TypedQuery<UserTask> query = entityManager.createQuery("select u from UserTask u WHERE u.projectTask.id = :projId",UserTask.class
                );
        query.setParameter("projId",projectId);
        return query.getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Set<SubProject> getSubProjectList(Long projectId) {
        TypedQuery<SubProject> query = entityManager.createQuery("select s from SubProject s where s.project.id =: projId", SubProject.class);
        query.setParameter("projId",projectId);
        return query.getResultStream().collect(Collectors.toSet());
    }

    @Override
    public void deleteProject(Long projectId) {
        Project project = entityManager.find(Project.class, projectId);
        entityManager.remove(project);
    }
}
