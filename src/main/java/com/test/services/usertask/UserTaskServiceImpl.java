package com.test.services.usertask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.test.dto.DtoMapper.TaskDTOMapper;
import com.test.dto.TaskDTO;
import com.test.entity.tasks.UserTask;
import com.test.repositories.usertasks.UserTaskRepository;
import com.test.restcontroller.PatchUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserTaskServiceImpl implements UserTaskService{

    private UserTaskRepository repository;

    public UserTaskServiceImpl(UserTaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUserTask(UserTask task) {
        repository.save(task);
    }

    @Override
    public void updateTask(UserTask userTask) {
        repository.isInPersistenceContext(userTask);
        repository.updateTask(userTask);
    }

//    public UserTask userUserTask(String taskname,Long projectId, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
//
//        Optional<UserTask> task = repository.findTaskByNameFromProject(taskname, projectId);
//        TaskDTO taskDTO = TaskDTOMapper.toTaskDTO(task.get());
//        TaskDTO userTask = PatchUtil.applyPatchToCustomer(patch, taskDTO);
//        System.out.println("Index is: " + userTask.getId());
//        UserTask userTask1 = TaskDTOMapper.toTaskEntity(userTask);
//        System.out.println("Index is: " + userTask1.getId());
//        userTask1.setId(userTask.getId());
//        System.out.println("Index is: " + userTask1.getId());
//        return repository.updateTask(userTask1);
//    }

    @Override
    public void deleteUserTaskByNameFromProject(String usertaskName, Long projectId) {
        Optional<UserTask> task = repository.findTaskByNameFromProject(usertaskName, projectId);
        repository.deleleTask(task.get());
    }

    @Override
    public void deleteTaskByNameFromSubProject(String name, String subProject, Long projectId) throws Exception {
        Optional<UserTask> task = repository.findTaskByNameFromSubProject(name, subProject, projectId);
        repository.deleleTask(task.orElseThrow(Exception::new));
    }



    @Override
    @Transactional(readOnly = true)
    public Optional<UserTask> findTaskByNameFromProject(String name, Long projectId) {
        return repository.findTaskByNameFromProject(name,projectId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserTask> findTaskByNameFromSubProject(String taskName, String subProject, Long projectId) {
        return repository.findTaskByNameFromSubProject(taskName,subProject,projectId);
    }

}

