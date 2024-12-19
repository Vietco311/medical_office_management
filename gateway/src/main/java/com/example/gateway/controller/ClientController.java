package com.example.gateway.controller;

import com.example.gateway.model.RendezVous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

@RestController
public class ClientController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/patientDetails/{idPatient}", method = RequestMethod.GET)
    public String getPatient(@PathVariable int idPatient) {
        System.out.println("Getting Patient details for " + idPatient);

        String response = this.restTemplate.exchange("http://patient_service/getPatient/{idPatient}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, idPatient).getBody();

        System.out.println("Response Body " + response);

        return "Employee Id -  " + idPatient + " [ Employee Details " + response + " ]";
    }

    @RequestMapping(value = "/prendreRdv", method = RequestMethod.POST)
    public String prendreRdv(@RequestBody int idPatient, int idPracticien, Date date) {

        HttpEntity<RendezVous> addRdv = new HttpEntity<>(new RendezVous(idPatient, idPracticien, date));
        String response = this.restTemplate.exchange("http://patient_service/prendreRdv",
                HttpMethod.POST, addRdv, String.class).getBody();

        System.out.println("Response Body " + response);

        return "Employee Id -  " + idPatient + " [ Employee Details " + response + " ]";
    }

    @RequestMapping(value = "/practicienDetails/{idPracticien}", method = RequestMethod.GET)
    public String getPractician(@PathVariable int idPracticien) {
        System.out.println("Getting Employee details for " + idPracticien);

        String response = this.restTemplate.exchange("http://employee_service/getPracticien/{idPracticien}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, idPracticien).getBody();

        System.out.println("Response Body " + response);

        return "Employee Id -  " + idPracticien + " [ Employee Details " + response + " ]";
    }




    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
