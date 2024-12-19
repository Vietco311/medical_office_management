package com.example.patient_service.service;

import com.example.patient_service.database.DatabaseConnection;
import com.example.patient_service.model.Patient;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    public List<Patient> getAllPatients() {
        String selectSQL = "SELECT * FROM patient";
        List<Patient> patients = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("nom");
                int age = rs.getInt("age");

                // Ajouter le patient à la liste
                patients.add(new Patient(id, fullName, age));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public Patient getPatientById(int id) {
        String selectSQL = "SELECT * FROM patient WHERE id = ?";
        Patient patient = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("nom");
                int age = rs.getInt("age");
                patient = new Patient(id, fullName, age);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient;
    }

    public void addPatient(String fullName, int age) {
        String insertSQL = "INSERT INTO patient (nom, age) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, fullName);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePatientById(int id) {
        String deleteSQL = "DELETE FROM patient WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePatient(Patient patient) {
        String updateSQL = "UPDATE patient SET nom = ?, age = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setInt(3, patient.getId());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prendreRdv(int idPatient, int idPraticien, Date date) {
        String updateSQL = "INSERT INTO rdv (idPatient, idPracticien, date) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setInt(1, idPatient);
            pstmt.setInt(2, idPraticien);
            pstmt.setDate(3, date);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
