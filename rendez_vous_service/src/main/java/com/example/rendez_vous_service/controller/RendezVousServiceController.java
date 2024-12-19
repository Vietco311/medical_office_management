package com.example.rendez_vous_service.controller;

import com.example.rendez_vous_service.model.RendezVous;
import com.example.rendez_vous_service.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/rendez_vous")
public class RendezVousServiceController {

    @Autowired
    private RendezVousService rendezVousService;

    @GetMapping("/getAllRendezVous")
    public List<RendezVous> getRendezVous() {
        return rendezVousService.getAllRendezVous();
    }

    @GetMapping("/getRendezVous/{id}")
    public RendezVous getRendezVous(@PathVariable int id) {
        return rendezVousService.getRendezVousById(id);
    }

    @PostMapping("/addRendezVous")
    public String addRendezVous(@RequestBody RendezVous rendezVous) {
        rendezVousService.addRendezVous(rendezVous.getIdPatient(), rendezVous.getIdPraticien(), rendezVous.getDate());
        return "RendezVous added";
    }

    @DeleteMapping("/deleteRendezVous")
    public String deleteRendezVous(@RequestBody int id) {
        rendezVousService.deleteRendezVousById(id);
        return "RendezVous deleted";
    }

    @PutMapping("/updateRendezVous")
    public String updateRendezVous(@RequestBody int id, Date date) {
        rendezVousService.updateRendezVous(id, date);
        return "RendezVous updated";
    }
}
