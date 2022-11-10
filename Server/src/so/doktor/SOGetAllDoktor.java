/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.doktor;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Doktor;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllDoktor extends AbstractSO {

    private ArrayList<Doktor> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Doktor)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Doktor!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> listaDoktora
                = (ArrayList<AbstractDomainObject>) DBBroker.getInstance().select(ado);
        lista = (ArrayList<Doktor>) (ArrayList<?>) listaDoktora;
    }

    public ArrayList<Doktor> getLista() {
        return lista;
    }

}
