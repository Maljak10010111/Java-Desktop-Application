/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.termin;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Termin;
import domain.Usluga;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdateTermin extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }

        Termin t = (Termin) ado;
        
        if(t.getListaUsluga().isEmpty()){
            throw new Exception("Termin mora imati bar jednu uslugu!");
        }

        ArrayList<Termin> termini
                = (ArrayList<Termin>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Termin termin : termini) {
            if (!termin.getDoktor().getDoktorID().equals(t.getDoktor().getDoktorID()) &&
                    t.getDatumVreme().equals(termin.getDatumVreme())
                    && t.getDoktor().getDoktorID().equals(termin.getDoktor().getDoktorID())) {
                throw new Exception("Doktor vec ima termin za uneto vreme!");
            }
            if (!termin.getPacijent().getPacijentID().equals(t.getPacijent().getPacijentID()) &&
                    t.getDatumVreme().equals(termin.getDatumVreme())
                    && t.getPacijent().getPacijentID().equals(termin.getPacijent().getPacijentID())) {
                throw new Exception("Pacijent vec ima termin za uneto vreme!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {

        // updatujemo termin
        DBBroker.getInstance().update(ado);

        Termin t = (Termin) ado;

        // da bismo updatovali usluge za termin, moramo prvo da 
        // obrisemo sve postojece i onda dodamo te nove
        DBBroker.getInstance().delete(t.getListaUsluga().get(0));

        t.setTerminID(t.getTerminID());

        for (Usluga usluga : t.getListaUsluga()) {
            usluga.setTermin(t);
            DBBroker.getInstance().insert(usluga);
        }

    }

}
