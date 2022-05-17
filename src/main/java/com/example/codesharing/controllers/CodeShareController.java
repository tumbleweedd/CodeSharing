package com.example.codesharing.controllers;

import com.example.codesharing.applicationLogic.CodeSnippet;
import com.example.codesharing.applicationLogic.DataTimeClass;
import com.example.codesharing.auxiliary.CodeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class CodeShareController {

    @GetMapping("/api/code")
    public ResponseEntity<?> justGetCode() {
        Map<String, String> getCodeWithDate = new LinkedHashMap<>();
        getCodeWithDate.put("code", CodeDTO.getCode());
        getCodeWithDate.put("date", DataTimeClass.getCurrentDateTime());

        return new ResponseEntity<>(getCodeWithDate, HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> putCode(@RequestBody CodeSnippet code) {
        DataTimeClass.setLocalDateTime();
        CodeDTO.setCode(code.getCODE());

        return new ResponseEntity<>(new CodeShareController(), HttpStatus.OK);
    }
}
