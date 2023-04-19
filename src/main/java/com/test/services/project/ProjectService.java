package com.test.services.project;

import com.test.entity.projects.Project;
import com.test.entity.projects.SubProject;
import com.test.entity.tasks.UserTask;
import org.apache.catalina.User;

import java.util.Optional;
import java.util.Set;

public interface ProjectService {

    void createProject(Project project);

    Set<Project> getProjectList();

    Optional<Project> findProjectById(Long projectId);

    Set<UserTask> getProjectTaskList(Long projectId);

    Set<SubProject> getSubProjectList(Long projectId);

    void deleteProject(Long projectId);


}
