package com.test.services.usertask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.test.entity.projects.SubProject;
import com.test.entity.tasks.UserTask;

import java.util.Optional;
import java.util.Set;

public interface UserTaskService {

    void createUserTask(UserTask task);



    void deleteUserTaskByNameFromProject(String  usertaskName, Long projectId);

    void deleteTaskByNameFromSubProject(String taskName, String subProject, Long projectId) throws Exception;

    Optional<UserTask> findTaskByNameFromProject(String name, Long projectId);

    Optional<UserTask> findTaskByNameFromSubProject(String name,String subProject, Long projectId);

    void updateTask(UserTask userTask);


}
