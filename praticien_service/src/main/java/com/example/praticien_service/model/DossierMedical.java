package com.example.praticien_service.model;

public class DossierMedical {
    private int id;
    private int idPatient;
    private String diagnostic;
    private String traitement;

    public DossierMedical(int idPatient, String diagnostic, String traitement) {

        this.idPatient = idPatient;
        this.diagnostic = diagnostic;
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
