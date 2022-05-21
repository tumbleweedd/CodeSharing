package com.example.codesharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Controller
@Validated
public class HtmlController {
    private final CodeShareController codeShareController;

    @Autowired
    public HtmlController(CodeShareController codeShareController) {
        this.codeShareController = codeShareController;
    }



    @GetMapping("/code/{id}")
    public String getCode(@PathVariable("id") int id, Model model) {
        for (Map.Entry<String, String> entry : codeShareController.getAddOtherCode().get(id).entrySet()) {
            model.addAttribute("code", entry.getKey());
            model.addAttribute("dataTime", entry.getValue());
        }
        return "getCode";
    }
    @GetMapping("/code/latest")
    public String getLatestCode(Model model) {
        Map<String, String> map = new LinkedHashMap<>();
        for (Map<String, String> stringMap : codeShareController.getLatestCodeList()) {
            map.putAll(stringMap);
        }
        model.addAttribute("latestCode", map);
        return "getLatestCode";
    }


    @GetMapping("/code/new")
    public String getNewCode() {
        return "getNewCode";
    }
}
