package com.example.praticien_service.service;

import com.example.praticien_service.databse.DatabaseConnection;
import com.example.praticien_service.model.Practicien;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class PracticienService {

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
}