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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author PC
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Doktor login(Doktor doktor) throws Exception {
        return (Doktor) sendRequest(Operation.LOGIN, doktor);
    }

    public void addIzvestaj(Izvestaj izvestaj) throws Exception {
        sendRequest(Operation.ADD_IZVESTAJ, izvestaj);
    }
    
    public void addPacijent(Pacijent pacijent) throws Exception {
        sendRequest(Operation.ADD_PACIJENT, pacijent);
    }
    
    public void addTermin(Termin termin) throws Exception {
        sendRequest(Operation.ADD_TERMIN, termin);
    }
    
    public void addUsluga(Usluga usluga) throws Exception {
        sendRequest(Operation.ADD_USLUGA, usluga);
    }

    public void deleteIzvestaj(Izvestaj izvestaj) throws Exception {
        sendRequest(Operation.DELETE_IZVESTAJ, izvestaj);
    }
    
    public void deletePacijent(Pacijent pacijent) throws Exception {
        sendRequest(Operation.DELETE_PACIJENT, pacijent);
    }
    
    public void deleteTermin(Termin termin) throws Exception {
        sendRequest(Operation.DELETE_TERMIN, termin);
    }
    
    public void deleteUsluga(Usluga usluga) throws Exception {
        sendRequest(Operation.DELETE_USLUGA, usluga);
    }

    public void updatePacijent(Pacijent pacijent) throws Exception {
        sendRequest(Operation.UPDATE_PACIJENT, pacijent);
    }
    
    public void updateTermin(Termin termin) throws Exception {
        sendRequest(Operation.UPDATE_TERMIN, termin);
    }

    public ArrayList<Doktor> getAllDoktor() throws Exception {
        return (ArrayList<Doktor>) sendRequest(Operation.GET_ALL_DOKTOR, null);
    }
    
    public ArrayList<Izvestaj> getAllIzvestaj(Termin t) throws Exception {
        return (ArrayList<Izvestaj>) sendRequest(Operation.GET_ALL_IZVESTAJ, t);
    }
    
    public ArrayList<Pacijent> getAllPacijent() throws Exception {
        return (ArrayList<Pacijent>) sendRequest(Operation.GET_ALL_PACIJENT, null);
    }
    
    public ArrayList<Termin> getAllTermin() throws Exception {
        return (ArrayList<Termin>) sendRequest(Operation.GET_ALL_TERMIN, null);
    }
    
    public ArrayList<Usluga> getAllUsluga(Termin t) throws Exception {
        return (ArrayList<Usluga>) sendRequest(Operation.GET_ALL_USLUGA, t);
    }
    
    public ArrayList<VrstaUsluge> getAllVrstaUsluge() throws Exception {
        return (ArrayList<VrstaUsluge>) sendRequest(Operation.GET_ALL_VRSTA_USLUGE, null);
    }
    
    private Object sendRequest(int operation, Object data) throws Exception {
        Request req = new Request(operation, data);
        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);
        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res = (Response) in.readObject();
        if (res.getResponseStatus().equals(ResponseStatus.Error)) {
            throw res.getError();
        } else {
            return res.getData();
        }
    }
}
