package com.test.entity.tasks;

import com.test.entity.projects.Project;
import com.test.entity.projects.SubProject;
import com.test.entity.tasks.Status;
import com.test.entity.tasks.UserTask;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;


@Entity
@NoArgsConstructor
@DiscriminatorValue("manager")
@Table(name = "usertask")
public class ManagerTask extends UserTask {



    @Builder
    public ManagerTask(String taskName,
                       Status status,
                       ZonedDateTime creationDate,
                       ZonedDateTime modificationDate,
                       String info,
                       Project project,
                       SubProject subProject) {
        super(taskName,status,creationDate,modificationDate,info, project,subProject);
    }
}
