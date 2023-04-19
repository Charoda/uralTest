package com.test.entity.tasks;

import com.test.entity.projects.Project;
import com.test.entity.projects.SubProject;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@NoArgsConstructor
@DiscriminatorValue("techspec")
public class TechSpecialist extends UserTask {


    @Builder
    public TechSpecialist(String taskName,
                       Status status,
                       ZonedDateTime creationDate,
                       ZonedDateTime modificationDate,
                       String info,
                       Project project,
                       SubProject subProject) {
        super(taskName,status,creationDate,modificationDate,info, project,subProject);
    }

}
