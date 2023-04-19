package com.test.restcontroller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.test.dto.DtoMapper.TaskDTOMapper;
import com.test.dto.TaskDTO;
import com.test.entity.projects.Project;
import com.test.entity.tasks.ManagerTask;
import com.test.entity.tasks.Status;
import com.test.entity.tasks.TechSpecialist;
import com.test.entity.tasks.UserTask;
import com.test.services.project.ProjectService;
import com.test.services.subproject.SubProjectService;
import com.test.services.usertask.UserTaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;

@Log4j2
@RestController
public class ProjectRestController {

    public ProjectService projectService;
    public SubProjectService subProjectService;
    public UserTaskService userTaskService;

    public ProjectRestController(ProjectService projectService, SubProjectService subProjectService, UserTaskService userTaskService) {
        this.projectService = projectService;
        this.subProjectService = subProjectService;
        this.userTaskService = userTaskService;
    }


    /**
     * Create new project
     */
    @PostMapping("/project/new")
    public ResponseEntity<HttpStatus> createProject(@RequestBody Project project) {
        log.info("New project ({}) will be created", project.getProjectName());
        projectService.createProject(project);
        log.info("New project ({}) was created", project.getProjectName());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    /**
     * Create subProject for manager by project`s id.
     */
    @PostMapping("project/usertask/manager/new")
    public ResponseEntity<HttpStatus> createUserTaskInProjectForManager(@RequestParam(name = "projectId") Long projectId, @RequestBody ManagerTask managerTask) {
        log.info("New task named {} for manager will be created", managerTask.getTaskName());
        Project project = projectService.findProjectById(projectId).orElseThrow(() -> new RuntimeException());
        managerTask.setCreationDate(ZonedDateTime.now());
        managerTask.setStatus(Status.NEW);
        managerTask.setProject(project);
        userTaskService.createUserTask(managerTask);
        log.info("New task {} for manager was created", managerTask.getTaskName());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    /**
     * Create subProject for technical specialist by project`s id.
     */
    @PostMapping("project/usertask/techspec/new")
    public ResponseEntity<HttpStatus> createUserTaskInProjectForTechSpec(@RequestParam(name = "projectId") Long projectId, @RequestBody TechSpecialist techSpecialist) {
        log.info("New task named {} for technical specialist will be created", techSpecialist.getTaskName());
        Project project = projectService.findProjectById(projectId).orElseThrow(() -> new RuntimeException());
        techSpecialist.setCreationDate(ZonedDateTime.now());
        techSpecialist.setStatus(Status.NEW);
        techSpecialist.setProject(project);
        userTaskService.createUserTask(techSpecialist);
        log.info("New task {} for technical specialist was created", techSpecialist.getTaskName());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    /**
     * Используйте json-patch стандарт для посылки patch запроса c телом формата json
     * ВВедите только:
     * 1. Id проекта
     * 2. Имя таски проекта
     * 3. тело патч запроса по стандарту patch-json
     */
    @PatchMapping(value = "/project/usertask/edit")
    public ResponseEntity<TaskDTO> updateTaskOfProject(@RequestParam(name = "projectId") Long projectId, @RequestParam(name = "taskName") String taskname, @RequestBody JsonPatch patch) {
        Optional<UserTask> task = userTaskService.findTaskByNameFromProject(taskname, projectId);
        TaskDTO taskDTO = TaskDTOMapper.toTaskDTO(task.get());
        try {
            TaskDTO userTask = PatchUtil.applyPatchToCustomer(patch, taskDTO);
            UserTask userTask1 = TaskDTOMapper.toTaskEntity(userTask);
            userTask1.setId(userTask.getId());
            userTask1.setModificationDate(ZonedDateTime.now());
            userTaskService.updateTask(userTask1);
            return ResponseEntity.ok(userTask);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Return project by id.
     */
    @GetMapping("/getProject")
    public Project getProject(@RequestParam Long projectId) {
        Optional<Project> projectById = projectService.findProjectById(projectId);
        Project project = projectById.orElseThrow(() -> new RuntimeException());
        return project;
    }


    @GetMapping("/")
    public String getString() {
        return "Hello world";
    }

    /**
     * Return project list from DB.
     */
    @GetMapping("/getProjectList")
    public ResponseEntity<Set<Project>> getProjectList() {
        Set<Project> projectList = projectService.getProjectList();
        return ResponseEntity.ok(projectList);
    }

    /**
     * Return project list from DB.
     */
    @DeleteMapping("/project")
    public ResponseEntity<HttpStatus> deleteProjectById(@RequestParam(name = "projectId") Long projectid) {
        log.info("Project with ID= {} will be deleted from structure", projectid);
        projectService.deleteProject(projectid);
        log.info("Project with ID= {} was deleted from structure", projectid);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("/project/usertask")
    public ResponseEntity<HttpStatus> deleteTaskByNameFromProject(@RequestParam(name = "taskName") String taskName, @RequestParam(name = "projectId") Long projectid) {
        log.info("Task {} will be deleted from project with ID ={}", taskName, projectid);
        userTaskService.deleteUserTaskByNameFromProject(taskName, projectid);
        log.info("Task {} was deleted from project with ID ={}", taskName, projectid);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

