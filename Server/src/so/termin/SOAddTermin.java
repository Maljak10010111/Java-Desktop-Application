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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddTermin extends AbstractSO {

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
            if (t.getDatumVreme().equals(termin.getDatumVreme())
                    && t.getDoktor().getDoktorID().equals(termin.getDoktor().getDoktorID())) {
                throw new Exception("Doktor vec ima termin za uneto vreme!");
            }
            if (t.getDatumVreme().equals(termin.getDatumVreme())
                    && t.getPacijent().getPacijentID().equals(termin.getPacijent().getPacijentID())) {
                throw new Exception("Pacijent vec ima termin za uneto vreme!");
            }
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        // PreparedStatement nam je vratio kljuc koji je baza generisala
        // taj kljuc izvlacimo u ove 3 linije koda
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long terminID = tableKeys.getLong(1);
        
        // setujemo taj ID koji je baza generisala na nas termin da bismo dodali usluge (slab objekat)
        Termin t = (Termin) ado;
        t.setTerminID(terminID);
        
        // dodajemo usluge za taj termin
        for (Usluga usluga : t.getListaUsluga()) {
            usluga.setTermin(t);
            DBBroker.getInstance().insert(usluga);
        }
        
    }

}
