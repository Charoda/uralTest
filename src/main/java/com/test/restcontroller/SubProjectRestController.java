package com.test.restcontroller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.test.dto.DtoMapper.TaskDTOMapper;
import com.test.dto.TaskDTO;
import com.test.entity.projects.Project;
import com.test.entity.projects.SubProject;
import com.test.entity.tasks.ManagerTask;
import com.test.entity.tasks.Status;
import com.test.entity.tasks.TechSpecialist;
import com.test.entity.tasks.UserTask;
import com.test.services.project.ProjectService;
import com.test.services.subproject.SubProjectService;
import com.test.services.usertask.UserTaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Optional;

@Log4j2
@RestController
public class SubProjectRestController {
    public ProjectService projectService;
    public SubProjectService subProjectService;
    public UserTaskService userTaskService;

    public SubProjectRestController(ProjectService projectService, SubProjectService subProjectService, UserTaskService userTaskService) {
        this.projectService = projectService;
        this.subProjectService = subProjectService;
        this.userTaskService = userTaskService;
    }

    /**
     * Create new subproject in DB for the project.
     */
    @PostMapping("subproject/new")
    public ResponseEntity<HttpStatus> createNewSubProject(@RequestParam(name = "projectId") Long projectId, @RequestBody SubProject subProject) {
        log.info("New subproject ({}) will be created", subProject.getSubProjectName());
        Project project = projectService.findProjectById(projectId).orElseThrow(() -> new RuntimeException());
        subProject.setProject(project);
        subProjectService.createSubProject(subProject);
        log.info("New project ({}) was created", subProject.getSubProjectName());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    /**
     * Create task in subproject for technical specialist by project`s id and subproject`s name.
     */
    @PostMapping("subproject/usertask/techspec/new")
    public ResponseEntity<HttpStatus> createUserTaskInProjectForTechSpecForTechSpec(@RequestParam(name = "subprojectName") String name, @RequestParam(name = "projectId") Long projectId, @RequestBody TechSpecialist techSpecialist) {
        log.info("New task named {} for technical specialist will be created", techSpecialist.getTaskName());
        SubProject subProject = subProjectService.findSubProjectByNameFromProjectById(name, projectId).orElseThrow(() -> new RuntimeException());
        techSpecialist.setCreationDate(ZonedDateTime.now());
        techSpecialist.setStatus(Status.NEW);
        techSpecialist.setSubProject(subProject);
        userTaskService.createUserTask(techSpecialist);
        log.info("New task {} for technical specialist was created", techSpecialist.getTaskName());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    /**
     * Create task in subproject for manager by project`s id and subproject`s name.
     */
    @PostMapping("subproject/usertask/manager/new")
    public ResponseEntity<HttpStatus> createUserTaskInProjectForTechSpecForManager(@RequestParam(name = "subprojectName") String name, @RequestParam(name = "projectId") Long projectId, @RequestBody ManagerTask managerTask) {
        log.info("New task named {} for technical specialist will be created", managerTask.getTaskName());
        SubProject subProject = subProjectService.findSubProjectByNameFromProjectById(name, projectId).orElseThrow(() -> new RuntimeException());
        managerTask.setCreationDate(ZonedDateTime.now());
        managerTask.setStatus(Status.NEW);
        managerTask.setSubProject(subProject);
        userTaskService.createUserTask(managerTask);
        log.info("New task {} for technical specialist was created", managerTask.getTaskName());
        return ResponseEntity.ok(HttpStatus.CREATED);
    }


    @DeleteMapping("/subproject")
    public ResponseEntity<HttpStatus> deleteSubProjectById(@RequestParam(name = "name") String subprojectName, @RequestParam(name = "projectId") Long subProjectid) {
        log.info("SubProject with ID= {} will be deleted from structure", subProjectid);
        subProjectService.deleteSubProjectFromProject(subprojectName, subProjectid);
        log.info("SubProject with ID= {} was deleted from structure", subProjectid);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/subproject/usertask")
    public ResponseEntity<HttpStatus> deleteTaskByNameFromSubProject(@RequestParam(name = "taskName") String taskName, @RequestParam(name = "subProjectName") String subProjectName, @RequestParam(name = "projectId") Long projectid) throws Exception {
        log.info("Task {} will be deleted from project with ID ={}", taskName, projectid);
        userTaskService.deleteTaskByNameFromSubProject(taskName, subProjectName, projectid);
        log.info("Task {} was deleted from project with ID ={}", taskName, projectid);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    /**
     * Используйте json-patch стандарт для посылки patch запроса (пример приложил в коллекции json ) c телом формата json
     * ВВедите только:
     * 1. Id проекта
     * 2. Имя подпроекта
     * 3. Имя таски проекта
     * 4. тело патч запроса по стандарту patch-json
     */
    @PatchMapping(value = "/subproject/usertask/edit")
    public ResponseEntity<TaskDTO> updateTaskOfSubProject(@RequestParam(name = "projectId") Long projectId, @RequestParam(name = "subProjectName") String subProjectName, @RequestParam(name = "taskName") String taskname, @RequestBody JsonPatch patch) {
        Optional<UserTask> task = userTaskService.findTaskByNameFromSubProject(taskname, subProjectName, projectId);
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


}
