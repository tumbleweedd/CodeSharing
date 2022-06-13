package com.example.codesharing.service;

import com.example.codesharing.model.Code;

import java.time.LocalDateTime;
import java.util.List;

public interface CodeSharingService {
    List<Code> readAll();

    boolean checkExpired(Code code, LocalDateTime currentTime);

    void delete(Code code);

    String save(Code toSave);

    Code findCodeById(String id);




    boolean update(Code code, String id);
}
