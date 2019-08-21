package ru.eltex.testsystem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestAPI {
    @GetMapping(value ="/index")
    public String index(){
        return "bootstrap4/file_upload";}
}
