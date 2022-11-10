/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Doktor;
import domain.Izvestaj;
import domain.Pacijent;
import domain.Termin;
import domain.Usluga;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author PC
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request req = (Request) in.readObject();
                Response res = handleRequest(req);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request req) {
        Response res = new Response(null, null, ResponseStatus.Success);
        try {
            switch (req.getOperation()) {
                case Operation.ADD_IZVESTAJ:
                    ServerController.getInstance().addIzvestaj((Izvestaj) req.getData());
                    break;
                case Operation.ADD_PACIJENT:
                    ServerController.getInstance().addPacijent((Pacijent) req.getData());
                    break;
                case Operation.ADD_TERMIN:
                    ServerController.getInstance().addTermin((Termin) req.getData());
                    break;
                case Operation.ADD_USLUGA:
                    ServerController.getInstance().addUsluga((Usluga) req.getData());
                    break;
                case Operation.DELETE_IZVESTAJ:
                    ServerController.getInstance().deleteIzvestaj((Izvestaj) req.getData());
                    break;
                case Operation.DELETE_PACIJENT:
                    ServerController.getInstance().deletePacijent((Pacijent) req.getData());
                    break;
                case Operation.DELETE_TERMIN:
                    ServerController.getInstance().deleteTermin((Termin) req.getData());
                    break;
                case Operation.DELETE_USLUGA:
                    ServerController.getInstance().deleteUsluga((Usluga) req.getData());
                    break;
                case Operation.UPDATE_PACIJENT:
                    ServerController.getInstance().updatePacijent((Pacijent) req.getData());
                    break;
                case Operation.UPDATE_TERMIN:
                    ServerController.getInstance().updateTermin((Termin) req.getData());
                    break;
                case Operation.GET_ALL_DOKTOR:
                    res.setData(ServerController.getInstance().getAllDoktor());
                    break;
                case Operation.GET_ALL_IZVESTAJ:
                    res.setData(ServerController.getInstance().getAllIzvestaj((Termin) req.getData()));
                    break;
                case Operation.GET_ALL_PACIJENT:
                    res.setData(ServerController.getInstance().getAllPacijent());
                    break;
                case Operation.GET_ALL_TERMIN:
                    res.setData(ServerController.getInstance().getAllTermin());
                    break;
                case Operation.GET_ALL_USLUGA:
                    res.setData(ServerController.getInstance().getAllUsluga((Termin) req.getData()));
                    break;
                case Operation.GET_ALL_VRSTA_USLUGE:
                    res.setData(ServerController.getInstance().getAllVrstaUsluge());
                    break;
                case Operation.LOGIN:
                    ArrayList<Doktor> lista = ServerController.getInstance().getAllDoktor();
                    Doktor d = (Doktor) req.getData();
                    for (Doktor doktor : lista) {
                        if (doktor.getUsername().equals(d.getUsername())
                                && doktor.getPassword().equals(d.getPassword())) {
                            res.setData(doktor);
                        }
                    }
                    if (res.getData() == null) {
                        throw new Exception("Nisu tacni username/password.");
                    } else {
                        break;
                    }
                default:
                    return null;
            }
        } catch (Exception e) {
            res.setError(e);
            res.setData(null);
            res.setResponseStatus(ResponseStatus.Error);
        }
        return res;
    }

}
