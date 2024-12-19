package com.example.dossier_medical.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class DossierServiceController {

        @GetMapping("/getDossier")
        public String getDossier() {
            return "Dossier details";
        }

        @PostMapping("/addDossier")
        public String addDossier() {
            return "Dossier added";
        }

        @DeleteMapping("/deleteDossier")
        public String deleteDossier() {
            return "Dossier deleted";
        }

        @PutMapping("/updateDossier")
        public String updateDossier() {
            return "Dossier updated";
        }
}
