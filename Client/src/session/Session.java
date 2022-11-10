/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domain.Doktor;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author PC
 */
public class Session {

    private static Session instance;
    private Socket socket;
    private Doktor ulogovani;

    public Session() {
        try {
            socket = new Socket("localhost", 10000);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public Doktor getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Doktor ulogovani) {
        this.ulogovani = ulogovani;
    }


}
