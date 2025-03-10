package com.bella.ppmtool.services;

import com.bella.ppmtool.domain.Project;
import com.bella.ppmtool.exceptions.ProjectIdException;
import com.bella.ppmtool.repositories.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);
    @Autowired
    private ProjectRepository projectRepository;


    public Project saveOrUpdate(Project project) {
        // Logic here
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }
        catch(Exception e){
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project getProjectById(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null) {
            throw new ProjectIdException("Project Identifier '" + projectId.toUpperCase() + "' doesn't exists");
        }
        return project;
    }

    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteById(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project != null) {
            projectRepository.delete(project);
        }else{
            throw new ProjectIdException("Project Identifier '" + projectId.toUpperCase() + "' doesn't exists");
        }
    }
}
