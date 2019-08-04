package com.stone.stoneproject.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        return new Date().toString();
    }
}
