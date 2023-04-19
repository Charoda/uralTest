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
@Table(name = "subproject")
@ToString(exclude = {"project","userTaskSet"})
public class SubProject extends BaseEntity {

    @Column(name = "name")
    private String subProjectName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "subProject",cascade = CascadeType.ALL)
    private Set<UserTask> userTaskSet = new HashSet<>();

    @JsonIgnore
    public void addUserTask(UserTask userTask) {
        userTaskSet.add(userTask);
    }

}
