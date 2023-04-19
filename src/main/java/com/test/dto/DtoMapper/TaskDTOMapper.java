package com.test.dto.DtoMapper;

import com.test.dto.TaskDTO;
import com.test.entity.tasks.ManagerTask;
import com.test.entity.tasks.UserTask;
import org.springframework.scheduling.config.Task;

public class TaskDTOMapper {

    public static TaskDTO toTaskDTO(UserTask task) {
        return TaskDTO.builder()
                .id(task.getId())
                .creationDate(task.getCreationDate())
                .modificationDate(task.getModificationDate())
                .info(task.getInfo())
                .status(task.getStatus())
                .taskName(task.getTaskName())
                .build();
    }

    public static UserTask toTaskEntity(TaskDTO taskDTO) {
        return ManagerTask.builder()
                .creationDate(taskDTO.getCreationDate())
                .taskName(taskDTO.getTaskName())
                .modificationDate(taskDTO.getModificationDate())
                .info(taskDTO.getInfo())
                .status(taskDTO.getStatus())
                .build();
    }

}
