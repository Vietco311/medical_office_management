package com.example.dossier_medical.service;

import com.example.dossier_medical.database.DatabaseConnection;
import com.example.dossier_medical.model.DossierMedical;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DossierMedicalService {

    // Récupérer tous les dossiers médicaux
    public List<DossierMedical> getAllDossiers() {
        String selectSQL = "SELECT * FROM dossier_medical";
        List<DossierMedical> dossier_medicals = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(selectSQL);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int idPatient = rs.getInt("idPatient");
                String diagnostic = rs.getString("diagnostic");
                String traitement = rs.getString("traitement");

                // Ajouter le dossier_medical à la liste
                dossier_medicals.add(new DossierMedical(id, idPatient, traitement, diagnostic));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dossier_medicals;
    }

    // Récupérer un dossier médical par son ID
    public DossierMedical getDossierByIdPatient(int idPatient) {
        String selectSQL = "SELECT * FROM dossier_medical WHERE idPatient = ?";
        DossierMedical dossier_medical = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setInt(1, idPatient);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String diagnostic = rs.getString("diagnostic");
                String traitement = rs.getString("traitement");
                dossier_medical = new DossierMedical(id, idPatient, traitement, diagnostic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dossier_medical;
    }


    // Ajouter un dossier médical
    public void addDossier(int idPatient, String diagnostic, String traitement) {
        String insertSQL = "INSERT INTO dossier_medical(idPatient, diagnostique, traitement) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(insertSQL)) {
            statement.setInt(1, idPatient);
            statement.setString(2, diagnostic);
            statement.setString(3, traitement);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Mettre à jour un dossier médical
    public void updateDossier(int id, int idPatient, String diagnostic, String traitement) {
        String updateSQL = "UPDATE dossier_medical SET idPatient = ?, diagnostique = ?, traitement = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(updateSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}