/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.izvestaj;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Izvestaj;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllIzvestaj extends AbstractSO {

    private ArrayList<Izvestaj> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Izvestaj)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Izvestaj!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> listaIzvestaja
                = (ArrayList<AbstractDomainObject>) DBBroker.getInstance().select(ado);
        lista = (ArrayList<Izvestaj>) (ArrayList<?>) listaIzvestaja;
    }

    public ArrayList<Izvestaj> getLista() {
        return lista;
    }

}
