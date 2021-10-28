/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produits.entities;

/**
 *
 * @author Moez
 */
public class Categories {
    	private int id;
        private String nom;
	private String description;
	private String statut;

    public Categories() {
    }

    public Categories(String nom, String description, String statut) {
        this.nom = nom;
        this.description = description;
        this.statut = statut;
    }

    public Categories(int id) {
        this.id = id;
    }

    public Categories(int id, String nom, String description, String statut) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Categories{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", statut=" + statut + '}';
    }
        
        
}
