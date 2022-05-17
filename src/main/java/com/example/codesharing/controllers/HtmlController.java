package com.example.codesharing.controllers;

import com.example.codesharing.applicationLogic.DataTimeClass;
import com.example.codesharing.auxiliary.CodeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Validated
public class HtmlController {

    @GetMapping("/code")
    public String getCode(Model model) {
        String code = CodeDTO.getCode();
        String dataTime = DataTimeClass.getCurrentDateTime();
        model.addAttribute("code", code);
        model.addAttribute("dataTime",dataTime);
        return "getCode";
    }


    @GetMapping("/code/new")
    public String getNewCode() {
        return "getNewCode";
    }
}
