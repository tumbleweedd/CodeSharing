package com.example.codesharing.service;

import com.example.codesharing.model.Code;

import java.util.List;

public interface CodeSharingService {
    List<Code> readAll();

    boolean delete(Long id);

    Code save(Code toSave);

    Code findCodeById(Integer id);




    boolean update(Code code, int id);
}
