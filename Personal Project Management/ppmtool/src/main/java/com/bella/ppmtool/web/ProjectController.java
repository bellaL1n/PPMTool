package com.bella.ppmtool.web;

import com.bella.ppmtool.domain.Project;
import com.bella.ppmtool.services.ProjectService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@Slf4j
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorServices mapValidationErrorServices;

    @PostMapping("")
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        log.info("Creating project {}", project);
        ResponseEntity<?> errorMap = mapValidationErrorServices.MapValidationService(bindingResult);
        if (errorMap != null) return errorMap;

        projectService.saveOrUpdate(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        log.info("Getting project {}", projectId);
        Project project = projectService.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        log.info("Getting all projects");
        return projectService.getAllProjects();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable String projectId) {
        log.info("Deleting project {}", projectId);
        projectService.deleteById(projectId);
        return new ResponseEntity<>("Project deleted", HttpStatus.OK);
    }

}
