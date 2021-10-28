/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author Nour
 */
public class Consultation {
    private int idConsultation;
    private int idRdv; 
    private String ordonnance;
    
    public Consultation(){
        
    }

    public Consultation(int idConsultation, String ordonnance) {
        this.idConsultation = idConsultation;
        this.ordonnance = ordonnance;
    }

    public Consultation(String ordonnance) {
        this.ordonnance = ordonnance;
    }

    public Consultation(int idConsultation, int idRdv, String ordonnance) {
        this.idConsultation = idConsultation;
        this.idRdv = idRdv;
        this.ordonnance = ordonnance;
    }

    
    public int getIdConsultation() {
        return idConsultation;
    }

    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
    }

    public int getIdRdv() {
        return idRdv;
    }

    public void setIdRdv(int idRdv) {
        this.idRdv = idRdv;
    }

    public String getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(String ordonnance) {
        this.ordonnance = ordonnance;
    }

    @Override
    public String toString() {
        return "Consultation{" + "idConsultation=" + idConsultation + ", idRdv=" + idRdv + ", ordonnance=" + ordonnance + '}';
    }
    
    
    
}
