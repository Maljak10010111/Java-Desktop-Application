/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class Termin extends AbstractDomainObject implements Serializable {

    private Long terminID;
    private Date datumVreme;
    private double cenaTermina;
    private Izvestaj izvestaj;
    private Pacijent pacijent;
    private Doktor doktor;
    private ArrayList<Usluga> listaUsluga;

    public Termin(Long terminID, Date datumVreme, double cenaTermina, Izvestaj izvestaj, Pacijent pacijent, Doktor doktor, ArrayList<Usluga> listaUsluga) {
        this.terminID = terminID;
        this.datumVreme = datumVreme;
        this.cenaTermina = cenaTermina;
        this.izvestaj = izvestaj;
        this.pacijent = pacijent;
        this.doktor = doktor;
        this.listaUsluga = listaUsluga;
    }

    public Termin() {
    }

    @Override
    public String nazivTabele() {
        return " termin ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN doktor d ON (d.doktorid = t.doktorid) "
                + "JOIN ustanova u ON (d.ustanovaid = u.ustanovaid) "
                + "JOIN pacijent p ON (p.pacijentid = t.pacijentid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Pacijent p = new Pacijent(rs.getLong("PacijentID"),
                    rs.getString(17), rs.getString(18),
                    rs.getString("Email"), rs.getString("Telefon"));

            Ustanova u = new Ustanova(rs.getLong("UstanovaID"),
                    rs.getString("Naziv"), rs.getString("Grad"));

            Doktor d = new Doktor(rs.getLong("DoktorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"), u);

            Termin t = new Termin(rs.getLong("TerminID"),
                    rs.getTimestamp("DatumVreme"), rs.getDouble("CenaTermina"),
                    null, p, d, null);

            lista.add(t);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (DatumVreme, CenaTermina, IzvestajID, PacijentID, DoktorID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " TerminID = " + terminID;
    }

    @Override
    public String vrednostiZaInsert() {
        if (izvestaj != null) {
            return " '" + new Timestamp(datumVreme.getTime()) + "', " + cenaTermina + ", "
                    + izvestaj.getIzvestajID() + ", " + pacijent.getPacijentID() + ", "
                    + doktor.getDoktorID() + " ";
        }else{
            return " '" + new Timestamp(datumVreme.getTime()) + "', " + cenaTermina + ", "
                    + null + ", " + pacijent.getPacijentID() + ", "
                    + doktor.getDoktorID() + " ";
        }
    }

    @Override
    public String vrednostiZaUpdate() {
        return " DatumVreme = '" + new Timestamp(datumVreme.getTime()) + "', "
                + "CenaTermina = " + cenaTermina + " ";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getTerminID() {
        return terminID;
    }

    public void setTerminID(Long terminID) {
        this.terminID = terminID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public double getCenaTermina() {
        return cenaTermina;
    }

    public void setCenaTermina(double cenaTermina) {
        this.cenaTermina = cenaTermina;
    }

    public Izvestaj getIzvestaj() {
        return izvestaj;
    }

    public void setIzvestaj(Izvestaj izvestaj) {
        this.izvestaj = izvestaj;
    }

    public Pacijent getPacijent() {
        return pacijent;
    }

    public void setPacijent(Pacijent pacijent) {
        this.pacijent = pacijent;
    }

    public Doktor getDoktor() {
        return doktor;
    }

    public void setDoktor(Doktor doktor) {
        this.doktor = doktor;
    }

    public ArrayList<Usluga> getListaUsluga() {
        return listaUsluga;
    }

    public void setListaUsluga(ArrayList<Usluga> listaUsluga) {
        this.listaUsluga = listaUsluga;
    }
}
