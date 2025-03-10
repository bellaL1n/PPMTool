package com.bella.ppmtool.repositories;

import com.bella.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    Iterable<Project> findAll();

    Project findByProjectIdentifier(String projectIdentifier);

    @Override
    void delete(Project entity);

}
