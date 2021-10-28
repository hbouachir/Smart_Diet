/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author HAMZA
 */
public class lignecommande {
    private int idLigne;
    private int idCommande;
    private int codeProduit;
    private int quantite;
    private float prix;

    public lignecommande() {
    }

    public lignecommande( int idCommande, int codeProduit, int quantite, float prix) {
        
        this.idCommande = idCommande;
        this.codeProduit = codeProduit;
        this.quantite = quantite;
        this.prix = prix;
    }

    public int getIdLigne() {
        return idLigne;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public int getCodeProduit() {
        return codeProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setIdLigne(int idLigne) {
        this.idLigne = idLigne;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setCodeProduit(int codeProduit) {
        this.codeProduit = codeProduit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    
    
    
    
    
    
}
