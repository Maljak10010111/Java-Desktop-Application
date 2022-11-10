/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.pacijent;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Pacijent;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddPacijent extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Pacijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Pacijent!");
        }
        
        Pacijent p = (Pacijent) ado;
        
        ArrayList<Pacijent> pacijenti 
                = (ArrayList<Pacijent>)(ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Pacijent pacijent : pacijenti) {
            if(pacijent.getEmail().equals(p.getEmail()))
                throw new Exception("Vec postoji pacijent sa tim emailom!");
            if(pacijent.getTelefon().equals(p.getTelefon()))
                throw new Exception("Vec postoji pacijent sa tim telefonom!");
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().insert(ado);
    }

}
