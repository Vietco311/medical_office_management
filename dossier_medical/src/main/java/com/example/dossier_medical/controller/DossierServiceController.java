package com.example.dossier_medical.controller;

import com.example.dossier_medical.model.DossierMedical;
import com.example.dossier_medical.service.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dossier_medical")
public class DossierServiceController {

    @Autowired
    private DossierMedicalService dossierService;

        @GetMapping("/getAllDossier")
        public List<DossierMedical> getAllDossier() {
            return dossierService.getAllDossiers();
        }

        @GetMapping("/getDossier/{idPatient}")
        public DossierMedical getDossier(@PathVariable int idPatient) {
            return dossierService.getDossierByIdPatient(idPatient);
        }

        @PostMapping("/addDossier")
        public String addDossier(@RequestBody DossierMedical dossier) {
            dossierService.addDossier(dossier.getIdPatient(), dossier.getDiagnostic(), dossier.getTraitement());
            return "Dossier added";
        }


        @PutMapping("/updateDossier")
        public String updateDossier(@RequestBody DossierMedical dossier) {
            dossierService.updateDossier(dossier.getId(), dossier.getIdPatient(), dossier.getDiagnostic(), dossier.getTraitement());

            return "Dossier updated";
        }

}
