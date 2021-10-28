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
public class commande {
    private int idCommande;
    private int idClient;
    private int montantPanier;

    public commande() {
    }

    public commande(int idClient, int montantPanier) {
        
        this.idClient = idClient;
        this.montantPanier = montantPanier;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getMontantPanier() {
        return montantPanier;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
}

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setMontantPanier(int montantPanier) {
        this.montantPanier = montantPanier;
    }

    @Override
    public String toString() {
        return "commande{" + "idCommande=" + idCommande + ", idClient=" + idClient + ", montantPanier=" + montantPanier + '}';
    }
    
    
    
}
