package com.example.patient_service.service;

import com.example.patient_service.database.DatabaseConnection;
import com.example.patient_service.model.Patient;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    public static List<Patient> getAllPatients() {
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

    public static Patient getPatientById(int id) {
        String selectSQL = "SELECT * FROM patient WHERE id = ?";
        Patient patient = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("full_name");
                int age = rs.getInt("age");
                patient = new Patient(id, fullName, age);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient;
    }

    public static void addPatient(String fullName, int age) {
        String insertSQL = "INSERT INTO patient (nom, age) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            System.out.println("fullName: " + fullName);
            pstmt.setString(1, fullName);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletePatientById(int id) {
        String deleteSQL = "DELETE FROM patient WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updatePatient(Patient patient) {
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


}
