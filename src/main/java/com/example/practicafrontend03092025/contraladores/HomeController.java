package com.example.practicafrontend03092025.contraladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
    @GetMapping("/hola")
    public String getMethodName() {
        return "hola mundo";
    }
    
}