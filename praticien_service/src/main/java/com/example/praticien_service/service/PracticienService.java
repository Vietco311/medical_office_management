package com.example.praticien_service.service;

import com.example.praticien_service.databse.DatabaseConnection;
import com.example.praticien_service.model.DossierMedical;
import com.example.praticien_service.model.Practicien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class PracticienService {

    private final RestTemplate restTemplate;

    @Autowired
    public PracticienService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Practicien> getAllPracticien() {
        List<Practicien> practicians = new ArrayList<>();
        String query = "SELECT * FROM practicien";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("nom");
                String specialty = rs.getString("specialite");

                // Ajouter le practicien à la liste
                practicians.add(new Practicien(id, fullName, specialty)); // Assuming 'name' is a column in the 'practicians' table
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving practicians");
        }
        return practicians;
    }
    
    public Practicien getPracticienById(int id) {
        String selectSQL = "SELECT * FROM practicien WHERE id = ?";
        Practicien practicien = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("nom");
                String specialite = rs.getString("specialite");
                practicien = new Practicien(id, fullName, specialite);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return practicien;
    }



    public void addPracticien(String name, String specialite) {
        String query = "INSERT INTO practicien (nom, specialite) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, specialite);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding praticien");
        }
    }

    public void deletePracticien(String name) {
        String query = "DELETE FROM practicien WHERE nom = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting praticien");
        }
    }

    public void updatePraticien(int id, String newName) {
        String query = "UPDATE practicien SET nom = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newName);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating praticien");
        }
    }

    public String processRendezVous(int idPatient, String traitement, String diagnostic) {
        String getUrl = "http://localhost:8083/dossier_medical/getDossier/" + idPatient;
        String addUrl = "http://localhost:8083/dossier_medical/addDossier";
        String updateUrl = "http://localhost:8083/dossier_medical/updateDossier";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        // Vérifier si le dossier existe
        ResponseEntity<DossierMedical> response = restTemplate.exchange(getUrl, HttpMethod.GET, entity, DossierMedical.class);
        if (response.getBody() == null) {
            // Si aucun dossier n'existe, créer un nouveau dossier
            DossierMedical newDossier = new DossierMedical(idPatient, diagnostic, traitement);
            HttpEntity<DossierMedical> addEntity = new HttpEntity<>(newDossier, headers);
            restTemplate.exchange(addUrl, HttpMethod.POST, addEntity, String.class);
            return "Dossier créé et rendez-vous ajouté avec succès !";
        } else {
            // Si un dossier existe, le mettre à jour
            DossierMedical existingDossier = response.getBody();
            existingDossier.setDiagnostic(diagnostic);
            existingDossier.setTraitement(traitement);
            HttpEntity<DossierMedical> updateEntity = new HttpEntity<>(existingDossier, headers);
            restTemplate.exchange(updateUrl, HttpMethod.PUT, updateEntity, String.class);
            return "Dossier mis à jour et rendez-vous ajouté avec succès !";
        }
    }
}