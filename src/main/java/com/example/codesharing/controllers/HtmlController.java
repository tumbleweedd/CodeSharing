package com.example.codesharing.controllers;

import com.example.codesharing.model.Code;
import com.example.codesharing.service.CodeSharingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Controller
@Validated
public class HtmlController {
    @Autowired
    private CodeSharingServiceImpl codeSharingService;

    @GetMapping("/code/{id}")
    public String findCodeDB(@PathVariable("id") int id, Model model) {
        var codes = codeSharingService.findCodeById(id);
        model.addAttribute("codes", codes.getCode());
        model.addAttribute("date", codes.getDate());
        return "showCode";
    }


    @GetMapping("/code/latest")
    public String getLatestCode(Model model) {
        Map<String, String> map = new LinkedHashMap<>();
        var latestCode = codeSharingService.readAll();
        Collections.reverse(latestCode);
        for (Code code : latestCode.stream().limit(10).collect(Collectors.toList())) {
            map.put(code.getCode(), code.getDate());
        }
        model.addAttribute("latestCode", map);
        return "getLatestCode";
    }

    @GetMapping("/code/new")
    public String getNewCode() {
        return "getNewCode";
    }
}
