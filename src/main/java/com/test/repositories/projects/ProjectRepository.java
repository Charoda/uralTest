package com.test.repositories.projects;

import com.test.entity.projects.Project;
import com.test.entity.projects.SubProject;
import com.test.entity.tasks.UserTask;

import java.util.Optional;
import java.util.Set;

public interface ProjectRepository {

    void createProject(Project project);

    Set<Project> getProjectList();


    Optional<Project> findProjectById(Long projectId);

    Set<UserTask> getProjectTaskList(Long projectId);

    Set<SubProject> getSubProjectList(Long projectId);

    void deleteProject(Long projectId);

}
