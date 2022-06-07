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
    private List<Map<String, String>> helpForLatestCodeList;

    private String code;
    private String date;

    @GetMapping("/api/code/{id}")
    @ResponseBody
    public ResponseEntity<?> justGetCode(@PathVariable("id") int id) {
        addOtherCode.get(id).forEach((key, value) -> code = key);
        addOtherCode.get(id).forEach((key, value) -> date = value);
        return new ResponseEntity<>(Map.of("code", code, "date", date), HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> putCode(@RequestBody CodeSnippet code) {
        CodeDTO.setCode(code.getCODE());
        DataTimeClass.setLocalDateTime();
        Map<String, String> getCurrentCode = new LinkedHashMap<>();

        getCurrentCode.put(CodeDTO.getCode(), DataTimeClass.getCurrentDateTime());
        addOtherCode.put(CodeDTO.inc(), getCurrentCode);

        latestCodeList = new ArrayList<>(10);
        helpForLatestCodeList = new ArrayList<>();
        addOtherCode.forEach((key, value) -> helpForLatestCodeList.add(value));

        Collections.reverse(helpForLatestCodeList);

        helpForLatestCodeList.stream()
                .takeWhile(stringStringMap -> latestCodeList.size() <= 9)
                .forEach(stringStringMap -> latestCodeList.add(stringStringMap));

        return new ResponseEntity<>(Map.of("id", String.valueOf(CodeDTO.getInc())), HttpStatus.OK);
    }

    @JsonCreator
    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getLatestCodeApi() {
        Map<String, String> map = new LinkedHashMap<>();
        List<Map<String, String>> map2 = new LinkedList<>();

        latestCodeList.forEach(map::putAll);
        map.forEach((key, value) -> map2.add(Map.of("code", key, "date", value)));

        return new ResponseEntity<>(map2, HttpStatus.OK);
    }
}

