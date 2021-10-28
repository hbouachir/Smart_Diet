
package java4eme;

import PaymentService.CRUDFacture;
import PaymentService.CRUDPayments;
import Payments.entities.Facture;
import Payments.entities.Payments;
import java.sql.SQLException;

public class Java4eme {

    public static void main(String[] args) throws SQLException {
     
        Payments p=new Payments("java","java","java");
         Facture a =new Facture("java","java","java");
CRUDPayments ps =new CRUDPayments();
CRUDFacture pt =new CRUDFacture();
//ajout
//ps.insertpayement(p);
//afficher
//System.out.println(ps.findAll().toString());
//delete
ps.DeletePayement("bb");

//modifier

//facture
pt.insertFacture(a );
System.out.println(pt.findAll().toString());

    }
    
}
