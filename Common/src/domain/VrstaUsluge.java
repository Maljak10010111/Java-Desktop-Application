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
public class VrstaUsluge extends AbstractDomainObject implements Serializable{
    
    private Long vrstaUslugeID;
    private String naziv;

    public VrstaUsluge() {
    }

    public VrstaUsluge(Long vrstaUslugeID, String naziv) {
        this.vrstaUslugeID = vrstaUslugeID;
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    @Override
    public String nazivTabele() {
        return " vrstaUsluge ";
    }

    @Override
    public String alijas() {
        return " vu ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            VrstaUsluge vu = new VrstaUsluge(rs.getLong("VrstaUslugeID"),
                    rs.getString("Naziv"));

            lista.add(vu);
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
        return " VrstaUslugeID = " + vrstaUslugeID;
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

    public Long getVrstaUslugeID() {
        return vrstaUslugeID;
    }

    public void setVrstaUslugeID(Long vrstaUslugeID) {
        this.vrstaUslugeID = vrstaUslugeID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
