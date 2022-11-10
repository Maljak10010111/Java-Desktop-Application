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
public class Izvestaj extends AbstractDomainObject implements Serializable {

    private Long izvestajID;
    private String opis;
    private Termin termin;

    public Izvestaj(Long izvestajID, String opis, Termin termin) {
        this.izvestajID = izvestajID;
        this.opis = opis;
        this.termin = termin;
    }

    public Izvestaj() {
    }

    @Override
    public String nazivTabele() {
        return " izvestaj ";
    }

    @Override
    public String alijas() {
        return " i ";
    }

    @Override
    public String join() {
        return " join termin t on (t.terminid = i.terminid)"
                + "JOIN doktor d ON (d.doktorid = t.doktorid) "
                + "JOIN ustanova u ON (d.ustanovaid = u.ustanovaid) "
                + "JOIN pacijent p ON (p.pacijentid = t.pacijentid) ";

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

            Izvestaj i = new Izvestaj(rs.getLong("IzvestajID"),
                    rs.getString("Opis"), t);

            lista.add(i);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Opis, TerminID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " IzvestajID = " + izvestajID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + opis + "', " + termin.getTerminID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return " WHERE T.TERMINID = " + termin.getTerminID();
    }

    public Long getIzvestajID() {
        return izvestajID;
    }

    public void setIzvestajID(Long izvestajID) {
        this.izvestajID = izvestajID;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }
}
