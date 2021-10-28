
package Payments.entities;
public class payment_facture {
    private Facture Facture;
    private Payments Payments;
    public payment_facture(Facture Facture,Payments Payments){
        super();
    this.Facture=Facture;
    this.Payments=Payments;
    }

    public Facture getFacture() {
        return Facture;
    }

    public Payments getPayment() {
        return Payments;
    }

    public void setFacture(Facture facture) {
        this.Facture = facture;
    }

    public void setPayment(Payments payment) {
        this.Payments = payment;
    }

    @Override
    public String toString() {
        return "payment_facture{" + "Facture=" + Facture + ", Payments=" + Payments + '}';
    }
    
    
}
