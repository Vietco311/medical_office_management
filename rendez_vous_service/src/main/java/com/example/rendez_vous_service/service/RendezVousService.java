package com.example.rendez_vous_service.service;

import com.example.rendez_vous_service.database.DatabaseConnection;
import com.example.rendez_vous_service.model.RendezVous;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Service
public class RendezVousService {

    public List<RendezVous> getAllRendezVous() {
        String selectSQL = "SELECT * FROM rdv";
        List<RendezVous> rdvs = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int idPatient = rs.getInt("idPatient");
                int idPracticien = rs.getInt("idPracticien");
                Date date = rs.getDate("date");

                // Ajouter le rdv à la liste
                rdvs.add(new RendezVous(id, idPatient, idPracticien, date));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rdvs;
    }

    public RendezVous getRendezVousById(int id) {
        String selectSQL = "SELECT * FROM rdv WHERE id = ?";
        RendezVous rdv = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int idPatient = rs.getInt("idPatient");
                int idPracticien = rs.getInt("idPracticien");
                Date date = rs.getDate("date");
                rdv = new RendezVous(id, idPatient, idPracticien, date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rdv;
    }

    public void addRendezVous(int idPatient, int idPracticien, Date date) {
        String insertSQL = "INSERT INTO rdv (idPatient, idPracticien, date) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, idPatient);
            pstmt.setInt(2, idPracticien);
            pstmt.setDate(3, date);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRendezVousById(int id) {
        String deleteSQL = "DELETE FROM rdv WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRendezVous(int idRdv, Date date) {
        String updateSQL = "UPDATE rdv SET date = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

            pstmt.setString(1, String.valueOf(date));
            pstmt.setInt(2, idRdv);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
