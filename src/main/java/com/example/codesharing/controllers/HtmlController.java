package com.example.codesharing.controllers;

import com.example.codesharing.exceptionClass.CodeNotFoundException;
import com.example.codesharing.model.Code;
import com.example.codesharing.service.CodeSharingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Controller
@Validated
public class HtmlController {
    @Autowired
    private CodeSharingServiceImpl codeSharingService;

    @GetMapping("/code/{id}")
    public String findCodeDB(@PathVariable("id") String id, Model model) {
        LocalDateTime currentTime = LocalDateTime.now();
        Code codes = codeSharingService.findCodeById(id);

        if (!codeSharingService.existsCodeById(id)) throw new CodeNotFoundException();
        if (codeSharingService.checkExpired(codes, currentTime)) {
            throw new CodeNotFoundException();
        }

        model.addAttribute("code", codes);


        return "showCode";
    }


    @GetMapping("/code/latest")
    public String getLatestCode(Model model) {
        Map<String, String> map = new LinkedHashMap<>();
        List<Code> latestCode = codeSharingService.readAll();
        Collections.reverse(latestCode);
        latestCode.removeIf(code -> code.isTimeRestricted() || code.isViewsRestricted());
        for (Code code : latestCode.stream().limit(10).collect(Collectors.toList())) {
            map.put(code.getDate(), code.getCode());
        }
        model.addAttribute("latestCode", map);
        return "getLatestCode";
    }

    @GetMapping("/code/new")
    public String getNewCode() {
        return "getNewCode";
    }
}
