package com.openclassrooms.safetinet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class hello {
	
	@GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
