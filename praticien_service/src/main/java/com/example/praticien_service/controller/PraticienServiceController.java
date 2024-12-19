package com.example.praticien_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class PraticienServiceController {

    @GetMapping("/getAllPraticien")
    public String getPraticien() {
        return "Praticien details";
    }

    @PostMapping("/addPraticien/{id}")
    public String addPraticien(@PathVariable String id) {
        return "Praticien added";
    }

    @DeleteMapping("/deletePraticien")
    public String deletePraticien() {
        return "Praticien deleted";
    }

    @PutMapping("/updatePraticien")
    public String updatePraticien() {
        return "Praticien updated";
    }
}
