package com.example.codesharing.controllers;

import com.example.codesharing.model.Code;
import com.example.codesharing.service.CodeSharingServiceImpl;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
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
    public ResponseEntity<?> justGetCode(@PathVariable("id") String id) {
        LocalDateTime currentTime = LocalDateTime.now();
        Map<String, Object> getCodeByIdMap = new LinkedHashMap<>();
        Code codes = codeSharingService.findCodeById(id);

        if (codeSharingService.checkExpired(codes, currentTime)) {
            codeSharingService.delete(codes);
            return new ResponseEntity<>(Map.of("error", "Code snippet not found"), HttpStatus.NOT_FOUND);
        }

        getCodeByIdMap.put("code", codes.getCode());
        getCodeByIdMap.put("date", codes.getDate());
        getCodeByIdMap.put("time", codes.getTime());
        getCodeByIdMap.put("views", codes.getViews());
        return new ResponseEntity<>(getCodeByIdMap, HttpStatus.OK);
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<?> putCodeBD(@RequestBody Code code) {
        String id = codeSharingService.save(code);
        return new ResponseEntity<>(Map.of("id", id), HttpStatus.OK);
    }

    @JsonCreator
    @GetMapping("/api/code/latest")
    public ResponseEntity<?> getLatestCodeApiBD() {
        List<Code> latestCode = codeSharingService.readAll();
        Collections.reverse(latestCode);
        latestCode.removeIf(code -> code.isTimeRestricted() || code.isViewsRestricted());
        if (!latestCode.isEmpty())
            return new ResponseEntity<>(latestCode.stream().limit(10).collect(Collectors.toList()), HttpStatus.OK);
        return new ResponseEntity<>(Map.of("error", "404 Not Found"), HttpStatus.NOT_FOUND);
    }

}

