package com.example.codesharing.repository;

import com.example.codesharing.model.Code;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeSharingReposiroty extends CrudRepository<Code, Long> {
    Code findCodeById(Integer id);
}
