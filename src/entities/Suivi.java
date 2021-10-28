/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author cyrine belhssan
 */
public class Suivi {
     private int idSuivi;
    private int idUser;
     private Personne p;
    private String dateSuivi;
    private float taille;
    private float poid;
  //  private float bmi;
    private float glycemie;
    private String petitDej;
    private String repas;
    private String diner;
    private String notes;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Suivi() {
    }

    public Suivi(int idSuivi, float taille, float poid, float glycemie, String petitDej, String repas, String diner, String notes) {
        this.idSuivi = idSuivi;
        this.taille = taille;
        this.poid = poid;
        this.glycemie = glycemie;
        this.petitDej = petitDej;
        this.repas = repas;
        this.diner = diner;
        this.notes = notes;
    }

    public Suivi(int idSuivi, String dateSuivi, float taille, float poid, float glycemie, String petitDej, String repas, String diner, String notes) {
        this.idSuivi = idSuivi;
        this.dateSuivi = dateSuivi;
        this.taille = taille;
        this.poid = poid;
        this.glycemie = glycemie;
        this.petitDej = petitDej;
        this.repas = repas;
        this.diner = diner;
        this.notes = notes;
    }
    

    public Suivi(String dateSuivi, float taille, float poid, float glycemie, String petitDej, String repas, String diner, String notes) {
        this.dateSuivi = dateSuivi;
        this.taille = taille;
        this.poid = poid;
        this.glycemie = glycemie;
        this.petitDej = petitDej;
        this.repas = repas;
        this.diner = diner;
        this.notes = notes;
    }
    

    public Suivi(int idSuivi, Personne p, String dateSuivi, float taille, float poid, float glycemie) {
        this.idSuivi = idSuivi;
        this.p = p;
        this.dateSuivi = dateSuivi;
        this.taille = taille;
        this.poid = poid;
        this.glycemie = glycemie;
    }
    

    public Suivi(float taille, float poid, float glycemie, String petitDej, String repas, String diner, String notes) {
        this.taille = taille;
        this.poid = poid;
        this.glycemie = glycemie;
        this.petitDej = petitDej;
        this.repas = repas;
        this.diner = diner;
        this.notes = notes;
    }
    
    

    public Suivi(int idSuivi, Personne p, String dateSuivi, float taille, float poid, float glycemie, String petitDej, String repas, String diner, String notes) {
        this.idSuivi = idSuivi;
        this.p = p;
        this.dateSuivi = dateSuivi;
        this.taille = taille;
        this.poid = poid;
        this.glycemie = glycemie;
        this.petitDej = petitDej;
        this.repas = repas;
        this.diner = diner;
        this.notes = notes;
    }

    public Suivi(Personne p, String dateSuivi, float taille, float poid, float glycemie, String petitDej, String repas, String diner, String notes) {
        this.p = p;
        this.dateSuivi = dateSuivi;
        this.taille = taille;
        this.poid = poid;
        this.glycemie = glycemie;
        this.petitDej = petitDej;
        this.repas = repas;
        this.diner = diner;
        this.notes = notes;
    }

    public int getIdSuivi() {
        return idSuivi;
    }

    public Personne getP() {
        return p;
    }

    public String getDateSuivi() {
        return dateSuivi;
    }

    public float getTaille() {
        return taille;
    }

    public float getPoid() {
        return poid;
    }

    public float getGlycemie() {
        return glycemie;
    }

    public String getPetitDej() {
        return petitDej;
    }

    public String getRepas() {
        return repas;
    }

    public String getDiner() {
        return diner;
    }

    public String getNotes() {
        return notes;
    }

    public void setIdSuivi(int idSuivi) {
        this.idSuivi = idSuivi;
    }

    public void setP(Personne p) {
        this.p = p;
    }

    public void setDateSuivi(String dateSuivi) {
        this.dateSuivi = dateSuivi;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public void setPoid(float poid) {
        this.poid = poid;
    }

    public void setGlycemie(float glycemie) {
        this.glycemie = glycemie;
    }

    public void setPetitDej(String petitDej) {
        this.petitDej = petitDej;
    }

    public void setRepas(String repas) {
        this.repas = repas;
    }

    public void setDiner(String diner) {
        this.diner = diner;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Suivi{" + "idSuivi=" + idSuivi + ", p=" + p + ", dateSuivi=" + dateSuivi + ", taille=" + taille + ", poid=" + poid + ", glycemie=" + glycemie + ", petitDej=" + petitDej + ", repas=" + repas + ", diner=" + diner + ", notes=" + notes + '}';
    }

   

   
    
}
