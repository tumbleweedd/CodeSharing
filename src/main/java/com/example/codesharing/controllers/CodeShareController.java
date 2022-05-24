package com.example.codesharing.controllers;

import com.example.codesharing.applicationLogic.CodeSnippet;
import com.example.codesharing.applicationLogic.DataTimeClass;
import com.example.codesharing.auxiliary.CodeDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Data
@RestController
public class CodeShareController {
    private final Map<Integer, Map<String, String>> addOtherCode = new LinkedHashMap<>();

    private List<Map<String, String>> latestCodeList;
    private List<Map<String, String>> latestCodeList1;

    private String code;
    private String data;


    @GetMapping("/api/code/{id}")
    @ResponseBody
    public ResponseEntity<?> justGetCode(@PathVariable("id") int id) {
        addOtherCode.get(id).forEach((key, value) -> code = key);
        addOtherCode.get(id).forEach((key, value) -> data = value);
        return new ResponseEntity<>(Map.of("code", code, "date", data), HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> putCode(@RequestBody CodeSnippet code) {
        CodeDTO.setCode(code.getCODE());
        DataTimeClass.setLocalDateTime();
        Map<String, String> getCurrentCode = new LinkedHashMap<>();

        getCurrentCode.put(CodeDTO.getCode(), DataTimeClass.getCurrentDateTime());
        addOtherCode.put(CodeDTO.inc(), getCurrentCode);

        latestCodeList = new ArrayList<>(10);
        latestCodeList1 = new ArrayList<>();
        addOtherCode.forEach((key, value) -> latestCodeList1.add(value));

        Collections.reverse(latestCodeList1);

        for (Map<String, String> stringStringMap : latestCodeList1) {
            if (latestCodeList.size() > 9) {
                break;
            }
            latestCodeList.add(stringStringMap);
        }

        return new ResponseEntity<>(Map.of("id", String.valueOf(CodeDTO.getInc())), HttpStatus.OK);
    }

    @JsonCreator
    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getLatestCodeApi() {
        Map<String, String> map = new LinkedHashMap<>();
        List<Map<String, String>> map2 = new LinkedList<>();

        for (Map<String, String> stringStringMap : latestCodeList) {
            map.putAll(stringStringMap);
        }
        map.forEach((key, value) -> map2.add(Map.of("code", key, "date", value)));

        return new ResponseEntity<>(map2, HttpStatus.OK);
    }
}

