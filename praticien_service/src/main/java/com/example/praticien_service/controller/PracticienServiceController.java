package com.example.praticien_service.controller;

import com.example.praticien_service.model.DossierMedical;
import com.example.praticien_service.model.Practicien;
import com.example.praticien_service.service.PracticienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/practicien")
public class PracticienServiceController {

    @Autowired
    private PracticienService praticienService;


    // Route pour obtenir tous les praticiens
    @GetMapping("/getAllPracticien")
    public List<Practicien> getAllPracticien() {
        return praticienService.getAllPracticien();
    }

    @GetMapping("/getPracticien/{id}")
    public Practicien getPracticien(@PathVariable int id) {
        return praticienService.getPracticienById(id);
    }

    // Route pour ajouter un praticien avec un ID
    @PostMapping("/addPracticien")
    public String addPraticien(@RequestBody Practicien practicien) {
        if (practicien == null) {
            System.out.println("practicien est null !");
        } else {
            System.out.println("Nom: " + practicien.getName());
            System.out.println("Specialiite: " + practicien.getSpeciality());
        }
        praticienService.addPracticien(practicien.getName(), practicien.getSpeciality());
        return "Practicien ajouté avec succès !";
    }

    // Route pour supprimer un praticien
    @DeleteMapping("/deletePraticien")
    public String deletePraticien(@RequestBody String name) {
        praticienService.deletePracticien(name);
        return "Practicien supprimé avec succès !";
    }

    // Route pour mettre à jour un praticien
    @PutMapping("/updatePraticien")
    public String updatePraticien(@RequestBody int id, String name) {
        praticienService.updatePraticien(id, name);
        return "Practicien mis à jour avec succès !";
    }

    @PostMapping("/processRendezVous")
    public String processRendezVous(@RequestBody DossierMedical dossierMedical) {
        return praticienService.processRendezVous(dossierMedical.getIdPatient(), dossierMedical.getTraitement(), dossierMedical.getDiagnostic());
    }
}
