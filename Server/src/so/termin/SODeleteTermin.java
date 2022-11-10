/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.termin;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Termin;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SODeleteTermin extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        
        Termin t = (Termin) ado;
        
        // prvo moramo da obrisemo slab objekat da bismo obrisali jak
        // ova linija koda brise sve usluge za taj termin upitom
        // DELETE FROM USLUGA WHERE TERMINID = (tvojTerminID)
        DBBroker.getInstance().delete(t.getListaUsluga().get(0));
        
        // posle toga brisemo termin
        DBBroker.getInstance().delete(ado);
    }

}
