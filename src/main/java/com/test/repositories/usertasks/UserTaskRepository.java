package com.test.repositories.usertasks;

import com.test.entity.tasks.UserTask;

import java.util.Optional;

public interface UserTaskRepository {

    void save(UserTask task);

    Optional<UserTask> findTaskByNameFromProject(String taskName, Long projectId);

    Optional<UserTask> findTaskByNameFromSubProject(String taskName,String subProject, Long projectId);

    void deleteUserTaskByName(String usertaskname);

    void updateTask(UserTask userTask);


    void deleleTask(UserTask userTask);

    boolean isInPersistenceContext(UserTask userTask);
}
