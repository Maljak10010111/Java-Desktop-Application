/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vrstaUsluge;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.VrstaUsluge;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllVrstaUsluge extends AbstractSO {

    private ArrayList<VrstaUsluge> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof VrstaUsluge)) {
            throw new Exception("Prosledjeni objekat nije instanca klase VrstaUsluge!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> listaVrstaUsluga
                = (ArrayList<AbstractDomainObject>) DBBroker.getInstance().select(ado);
        lista = (ArrayList<VrstaUsluge>) (ArrayList<?>) listaVrstaUsluga;
    }

    public ArrayList<VrstaUsluge> getLista() {
        return lista;
    }

}
