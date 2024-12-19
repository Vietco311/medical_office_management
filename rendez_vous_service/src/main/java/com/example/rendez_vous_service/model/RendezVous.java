package com.example.rendez_vous_service.model;

import java.sql.Date;

public class RendezVous {
    private int id;
    private int idPatient;
    private int idPraticien;
    private Date date;

    public RendezVous(int id, int idPatient, int idPraticien, Date date) {
        this.id = id;
        this.idPatient = idPatient;
        this.idPraticien = idPraticien;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public int getIdPraticien() {
        return idPraticien;
    }

    public Date getDate() {
        return date;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public void setIdPraticien(int idPraticien) {
        this.idPraticien = idPraticien;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
