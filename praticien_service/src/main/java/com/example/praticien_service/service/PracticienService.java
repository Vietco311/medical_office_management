package com.example.praticien_service.service;

import com.example.praticien_service.databse.DatabaseConnection;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class PracticienService {

    public List<String> getAllPraticien() {
        List<String> praticiens = new ArrayList<>();
        String query = "SELECT * FROM praticiens";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                praticiens.add(resultSet.getString("name")); // Assuming 'name' is a column in the 'praticiens' table
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving praticiens");
        }
        return praticiens;
    }

    public String addPraticien(String name) {
        String query = "INSERT INTO praticiens (name) VALUES (?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding praticien");
        }
        return "Praticien added";
    }

    public String deletePraticien(String name) {
        String query = "DELETE FROM praticiens WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting praticien");
        }
        return "Praticien deleted";
    }

    public String updatePraticien(String oldName, String newName) {
        String query = "UPDATE praticiens SET name = ? WHERE name = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newName);
            statement.setString(2, oldName);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating praticien");
        }
        return "Praticien updated";
    }
}