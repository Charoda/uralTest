package com.test.services.subproject;

import com.test.entity.projects.SubProject;
import com.test.repositories.subprojects.SubProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class SubProjectServiceImpl implements SubProjectService {

    private SubProjectRepository repository;

    public SubProjectServiceImpl(SubProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<SubProject> createSubProject(SubProject subproject) {
        repository.createSubProject(subproject);
        return Optional.of(repository.getSubProjectById(subproject.getId()));
    }

    @Override
    public Optional<SubProject> findSubProjectByNameFromProjectById(String name, Long projectId) {
        return repository.findSubProjectByNameFromProjectById(name,projectId);
    }

    @Override
    public void deleteSubProjectFromProject(String subprojectName, Long projectId) {
        Optional<SubProject> subProject = repository.findSubProjectByNameFromProjectById(subprojectName, projectId);
        repository.deleteSubProject(subProject.get().getSubProjectName());
    }
}
