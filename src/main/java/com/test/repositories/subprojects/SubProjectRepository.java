package com.test.repositories.subprojects;

import com.test.entity.projects.SubProject;
import com.test.entity.tasks.UserTask;

import java.util.Optional;
import java.util.Set;

public interface SubProjectRepository {

    void createSubProject(SubProject subproject);

    SubProject getSubProjectById(Long subPrId);

    Optional<SubProject> findSubProjectByNameFromProjectById(String name, Long projectId);

//    Set<UserTask> getSubProjectTaskList(String subprojectName);


    void deleteSubProject(String subprojectName);
}
