package com.test.entity.projects;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.entity.BaseEntity;
import com.test.entity.tasks.UserTask;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "project")
public class Project extends BaseEntity {

    @Column(name = "name")
    private String projectName;


    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<SubProject> subProjectSet = new HashSet<>();


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<UserTask> userTaskSet = new HashSet<>();

    @JsonIgnore
    public void addSubproject(SubProject subProject) {
        subProjectSet.add(subProject);
    }
    @JsonIgnore
    public void addUserTask(UserTask userTask) {
        userTaskSet.add(userTask);
    }

}
