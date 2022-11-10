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
public class Usluga extends AbstractDomainObject implements Serializable {

    private Termin termin;
    private int rbUsluge;
    private double cenaUsluge;
    private VrstaUsluge vrstaUsluge;

    public Usluga() {
    }

    public Usluga(Termin termin, int rbUsluge, double cenaUsluge, VrstaUsluge vrstaUsluge) {
        this.termin = termin;
        this.rbUsluge = rbUsluge;
        this.cenaUsluge = cenaUsluge;
        this.vrstaUsluge = vrstaUsluge;
    }

    @Override
    public String nazivTabele() {
        return " usluga ";
    }

    @Override
    public String alijas() {
        return " usl ";
    }

    @Override
    public String join() {
        return " JOIN termin t ON (usl.terminid = t.terminid) "
                + "JOIN doktor d ON (d.doktorid = t.doktorid) "
                + "JOIN ustanova u ON (d.ustanovaid = u.ustanovaid) "
                + "JOIN pacijent p ON (p.pacijentid = t.pacijentid) "
                + "JOIN vrstaUsluge vu ON (usl.vrstaUslugeID = vu.vrstaUslugeID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Pacijent p = new Pacijent(rs.getLong("PacijentID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Email"), rs.getString("Telefon"));
            
            Ustanova u = new Ustanova(rs.getLong("UstanovaID"),
                    rs.getString("Naziv"), rs.getString("Grad"));
            
            Doktor d = new Doktor(rs.getLong("DoktorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"), u);
            
            Termin t = new Termin(rs.getLong("TerminID"),
                    rs.getTimestamp("DatumVreme"), rs.getDouble("CenaTermina"),
                    null, p, d, null);
            
            VrstaUsluge vu = new VrstaUsluge(rs.getLong("VrstaUslugeID"),
                    rs.getString(26));
            
            Usluga usluga = new Usluga(t,
                    rs.getInt("RbUsluge"), rs.getDouble("Cenausluge"), vu);

            lista.add(usluga);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (TerminID, RbUsluge, Cenausluge, VrstaUslugeID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " TerminID = " + termin.getTerminID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + termin.getTerminID() + ", " + rbUsluge + ", "
                + cenaUsluge + ", " + vrstaUsluge.getVrstaUslugeID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return " WHERE T.TERMINID = " + termin.getTerminID();
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }

    public int getRbUsluge() {
        return rbUsluge;
    }

    public void setRbUsluge(int rbUsluge) {
        this.rbUsluge = rbUsluge;
    }

    public double getCenaUsluge() {
        return cenaUsluge;
    }

    public void setCenaUsluge(double cenaUsluge) {
        this.cenaUsluge = cenaUsluge;
    }

    public VrstaUsluge getVrstaUsluge() {
        return vrstaUsluge;
    }

    public void setVrstaUsluge(VrstaUsluge vrstaUsluge) {
        this.vrstaUsluge = vrstaUsluge;
    }
}
