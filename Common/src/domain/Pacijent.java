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
public class Pacijent extends AbstractDomainObject implements Serializable{
    
    private Long pacijentID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;

    public Pacijent() {
    }

    public Pacijent(Long pacijentID, String ime, String prezime, String email, String telefon) {
        this.pacijentID = pacijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
    @Override
    public String nazivTabele() {
        return " pacijent ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Pacijent p = new Pacijent(rs.getLong("PacijentID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Email"), rs.getString("Telefon"));

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Email, Telefon) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " PacijentID = " + pacijentID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + email + "', '" + telefon + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Email = '" + email + "', Telefon = '" + telefon + "' ";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getPacijentID() {
        return pacijentID;
    }

    public void setPacijentID(Long pacijentID) {
        this.pacijentID = pacijentID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
