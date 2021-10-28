/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author user
 */
public class Reclamation {
    
    private int idReclamation;
    private int idPersonne;
    private String description;
    private int typeReclamation;

    public Reclamation(int idPersonne, String description, int typeReclamation) {
        this.idPersonne = idPersonne;
        this.description = description;
        this.typeReclamation = typeReclamation;
    }
    
    public Reclamation(){
        
    }

    
    
    
    public int getIdReclamation() {
        return idReclamation;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public String getDescription() {
        return description;
    }

    public int getTypeReclamation() {
        return typeReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypeReclamation(int typeReclamation) {
        this.typeReclamation = typeReclamation;
    }
    
    
    
    
    
}
