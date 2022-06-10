package com.example.codesharing.controllers;

import com.example.codesharing.applicationLogic.CodeSnippet;
import com.example.codesharing.applicationLogic.DataTimeClass;
import com.example.codesharing.model.Code;
import com.example.codesharing.service.CodeSharingServiceImpl;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@RestController
public class CodeShareController {
    private Map<String, String> latestCode = new LinkedHashMap<>();
    @Autowired
    private CodeSharingServiceImpl codeSharingService;


    @GetMapping("/api/code/{id}")
    @ResponseBody
    public ResponseEntity<?> justGetCode(@PathVariable("id") int id) {
        var codes = codeSharingService.findCodeById(id);
        Map<String, String> getCodeById = new LinkedHashMap<>();

        getCodeById.put("code", codes.getCode());
        getCodeById.put("date", codes.getDate());
        return new ResponseEntity<>(getCodeById, HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> putCodeBD(@RequestBody CodeSnippet code) {
        Code addCode = new Code();
        DataTimeClass.setLocalDateTime();
        addCode.setCode(code.getCODE());
        addCode.setDate(DataTimeClass.getCurrentDateTime(DataTimeClass.getLocalDateTime()));
        codeSharingService.save(addCode);
        return new ResponseEntity<>(Map.of("id", String.valueOf(addCode.getId())), HttpStatus.OK);
    }


    @JsonCreator
    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getLatestCodeApiBD() {
        var latestCode = codeSharingService.readAll();
        Collections.reverse(latestCode);
        return new ResponseEntity<>(latestCode.stream().limit(10).collect(Collectors.toList()), HttpStatus.OK);
    }

}

