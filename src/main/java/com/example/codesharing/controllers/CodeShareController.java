package com.example.codesharing.controllers;

import com.example.codesharing.applicationLogic.CodeSnippet;
import com.example.codesharing.applicationLogic.DataTimeClass;
import com.example.codesharing.auxiliary.CodeDTO;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@RestController
public class CodeShareController {
    private final Map<Integer, Map<String, String>> addOtherCode = new LinkedHashMap<>();
    private String code;
    private String data;


    @GetMapping("/api/code/{id}")
    @ResponseBody
    public ResponseEntity<?> justGetCode(@PathVariable("id") int id) {
        addOtherCode.get(id).forEach((key, value) -> code = key);
        addOtherCode.get(id).forEach((key, value) -> data = value);
        return new ResponseEntity<>(Map.of("cod", code, "data", data), HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> putCode(@RequestBody CodeSnippet code) {
        CodeDTO.setCode(code.getCODE());
        DataTimeClass.setLocalDateTime();
        Map<String, String> getCurrentCode = new LinkedHashMap<>();

        getCurrentCode.put(CodeDTO.getCode(), DataTimeClass.getCurrentDateTime());
        addOtherCode.put(CodeDTO.inc(), getCurrentCode);


        return new ResponseEntity<>(Map.of("id", CodeDTO.getInc()), HttpStatus.OK);
    }
}
