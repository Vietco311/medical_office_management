package com.example.dossier_medical.model;

public class DossierMedical {
    private int id;
    private int idPatient;
    private int idPraticien;
    private String diagnostic;
    private String traitement;

    public DossierMedical(int id, int idPatient, String diagnostic, String traitement, int idPraticien) {
        this.id = id;
        this.idPatient = idPatient;
        this.diagnostic = diagnostic;
        this.idPraticien = idPraticien;
        this.traitement = traitement;
    }

    public int getId() {
        return id;
    }

    public int getIdPatient() {
        return idPatient;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }
}
