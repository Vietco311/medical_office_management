package com.example.patient_service.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/medical_office";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Ajoutez votre mot de passe ici si nécessaire

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de connexion à la base de données !");
        }
    }
}
