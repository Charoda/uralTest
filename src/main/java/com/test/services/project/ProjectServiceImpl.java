package com.test.services.project;

import com.test.entity.projects.Project;
import com.test.entity.projects.SubProject;
import com.test.entity.tasks.UserTask;
import com.test.repositories.projects.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository repository;

    public ProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createProject(Project project) {
        repository.createProject(project);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Project> getProjectList() {
        return repository.getProjectList();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findProjectById(Long projectId) {
        return repository.findProjectById(projectId);
    }


    @Override
    @Transactional(readOnly = true)
    public Set<UserTask> getProjectTaskList(Long projectId) {
        return repository.getProjectTaskList(projectId);
    }


    @Override
    @Transactional(readOnly = true)
    public Set<SubProject> getSubProjectList(Long projectId) {
        return repository.getSubProjectList(projectId);
    }

    @Override
    public void deleteProject(Long projectId) {
        repository.deleteProject(projectId);
    }
}
