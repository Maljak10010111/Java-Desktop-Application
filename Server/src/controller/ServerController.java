/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Doktor;
import domain.Izvestaj;
import domain.Pacijent;
import domain.Termin;
import domain.Usluga;
import domain.VrstaUsluge;
import java.util.ArrayList;
import so.AbstractSO;
import so.doktor.SOGetAllDoktor;
import so.izvestaj.SOAddIzvestaj;
import so.izvestaj.SODeleteIzvestaj;
import so.izvestaj.SOGetAllIzvestaj;
import so.pacijent.SOAddPacijent;
import so.pacijent.SODeletePacijent;
import so.pacijent.SOGetAllPacijent;
import so.pacijent.SOUpdatePacijent;
import so.termin.SOAddTermin;
import so.termin.SODeleteTermin;
import so.termin.SOGetAllTermin;
import so.termin.SOUpdateTermin;
import so.usluga.SOAddUsluga;
import so.usluga.SODeleteUsluga;
import so.usluga.SOGetAllUsluga;
import so.vrstaUsluge.SOGetAllVrstaUsluge;

/**
 *
 * @author PC
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void addIzvestaj(Izvestaj izvestaj) throws Exception {
        AbstractSO aso = new SOAddIzvestaj();
        aso.templateExecute(izvestaj);
    }
    
    public void addPacijent(Pacijent pacijent) throws Exception {
        AbstractSO aso = new SOAddPacijent();
        aso.templateExecute(pacijent);
    }
    
    public void addTermin(Termin termin) throws Exception {
        AbstractSO aso = new SOAddTermin();
        aso.templateExecute(termin);
    }
    
    public void addUsluga(Usluga usluga) throws Exception {
        AbstractSO aso = new SOAddUsluga();
        aso.templateExecute(usluga);
    }

    public void deleteIzvestaj(Izvestaj izvestaj) throws Exception {
        AbstractSO aso = new SODeleteIzvestaj();
        aso.templateExecute(izvestaj);
    }
    
    public void deletePacijent(Pacijent pacijent) throws Exception {
        AbstractSO aso = new SODeletePacijent();
        aso.templateExecute(pacijent);
    }
    
    public void deleteTermin(Termin termin) throws Exception {
        AbstractSO aso = new SODeleteTermin();
        aso.templateExecute(termin);
    }
    
    public void deleteUsluga(Usluga usluga) throws Exception {
        AbstractSO aso = new SODeleteUsluga();
        aso.templateExecute(usluga);
    }

    public void updatePacijent(Pacijent pacijent) throws Exception {
        AbstractSO aso = new SOUpdatePacijent();
        aso.templateExecute(pacijent);
    }
    
    public void updateTermin(Termin termin) throws Exception {
        AbstractSO aso = new SOUpdateTermin();
        aso.templateExecute(termin);
    }

    public ArrayList<Doktor> getAllDoktor() throws Exception {
        SOGetAllDoktor so = new SOGetAllDoktor();
        so.templateExecute(new Doktor());
        return so.getLista();
    }
    
    public ArrayList<Izvestaj> getAllIzvestaj(Termin t) throws Exception {
        SOGetAllIzvestaj so = new SOGetAllIzvestaj();
        
        Izvestaj i = new Izvestaj();
        i.setTermin(t);
        
        so.templateExecute(i);
        return so.getLista();
    }
    
    public ArrayList<Pacijent> getAllPacijent() throws Exception {
        SOGetAllPacijent so = new SOGetAllPacijent();
        so.templateExecute(new Pacijent());
        return so.getLista();
    }
    
    public ArrayList<Termin> getAllTermin() throws Exception {
        SOGetAllTermin so = new SOGetAllTermin();
        so.templateExecute(new Termin());
        return so.getLista();
    }
    
    public ArrayList<Usluga> getAllUsluga(Termin t) throws Exception {
        SOGetAllUsluga so = new SOGetAllUsluga();
        
        Usluga u = new Usluga();
        u.setTermin(t);
        
        so.templateExecute(u);
        return so.getLista();
    }
    
    public ArrayList<VrstaUsluge> getAllVrstaUsluge() throws Exception {
        SOGetAllVrstaUsluge so = new SOGetAllVrstaUsluge();
        so.templateExecute(new VrstaUsluge());
        return so.getLista();
    }

}
