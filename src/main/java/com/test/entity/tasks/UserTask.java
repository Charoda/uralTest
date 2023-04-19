package com.test.entity.tasks;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.entity.BaseEntity;
import com.test.entity.projects.Project;
import com.test.entity.projects.SubProject;
import jakarta.persistence.*;
import lombok.*;
import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@EqualsAndHashCode(exclude = {"subProject","project"})
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
@Table(name = "usertask")
public abstract class UserTask extends BaseEntity {



    @Column(name = "name")
    protected String taskName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    protected Status status;

    @Column(name = "creation_date")
    protected ZonedDateTime creationDate;

    @Column(name = "modification_date")
    protected ZonedDateTime modificationDate;

    @Column(name = "information")
    protected String info;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "project_id")
    protected Project project;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "subproject_id")
    protected SubProject subProject;



}