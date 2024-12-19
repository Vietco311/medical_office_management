package com.example.patient_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class PatientServiceController {

    @GetMapping("/getPatient")
    public String getPatient() {
        return "Patient details";
    }

    @PostMapping("/addPatient")
    public String addPatient() {
        return "Patient added";
    }

    @DeleteMapping("/deletePatient")
    public String deletePatient() {
        return "Patient deleted";
    }

    @PutMapping("/updatePatient")
    public String updatePatient() {
        return "Patient updated";
    }

}
