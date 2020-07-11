package com.gmail.robertosrjr.authenticate.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("companies")
public class CompanyController {

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().build();
    }
}
