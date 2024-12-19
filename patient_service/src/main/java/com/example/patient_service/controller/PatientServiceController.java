package com.example.patient_service.controller;

import com.example.patient_service.model.Patient;
import com.example.patient_service.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientServiceController {

    @GetMapping("/getAllPatient")
    public List<Patient> getAllPatient() {
        return PatientService.getAllPatients();
    }

    @GetMapping("/getPatient/{id}")
    public Patient getPatient(@PathVariable int id) {
        return PatientService.getPatientById(id);
    }

    @PostMapping("/addPatient")
    public String addPatient(@RequestBody Patient patient) {
        if (patient == null) {
            System.out.println("Patient est null !");
        } else {
            System.out.println("Nom: " + patient.getName());
            System.out.println("Âge: " + patient.getAge());
        }
        PatientService.addPatient(patient.getName(), patient.getAge());
        return "Patient ajouté avec succès !";
    }

    @DeleteMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable int id) {
        PatientService.deletePatientById(id);
        return "Patient supprimé avec succès !";
    }

    @PutMapping("/updatePatient")
    public String updatePatient(@RequestBody Patient patient) {
        PatientService.updatePatient(patient);
        return "Patient mis à jour avec succès !";
    }

}
