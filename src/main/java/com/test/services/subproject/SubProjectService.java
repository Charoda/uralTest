package com.test.services.subproject;

import com.test.entity.projects.SubProject;

import java.util.Optional;

public interface SubProjectService {

    Optional<SubProject> createSubProject(SubProject subproject);


    Optional<SubProject> findSubProjectByNameFromProjectById(String name,Long projectId);

//    Set<UserTask> getSubProjectTaskList(String subprojectName);

    void deleteSubProjectFromProject(String subprojectName, Long id);
}
