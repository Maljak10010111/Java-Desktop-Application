/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Ustanova extends AbstractDomainObject implements Serializable {
    
    private Long ustanovaID;
    private String naziv;
    private String grad;

    public Ustanova() {
    }

    public Ustanova(Long ustanovaID, String naziv, String grad) {
        this.ustanovaID = ustanovaID;
        this.naziv = naziv;
        this.grad = grad;
    }
    
    @Override
    public String nazivTabele() {
        return " ustanova ";
    }

    @Override
    public String alijas() {
        return " u ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Ustanova u = new Ustanova(rs.getLong("UstanovaID"),
                    rs.getString("Naziv"), rs.getString("Grad"));

            lista.add(u);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " UstanovaID = " + ustanovaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getUstanovaID() {
        return ustanovaID;
    }

    public void setUstanovaID(Long ustanovaID) {
        this.ustanovaID = ustanovaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
