/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Termin;
import domain.Usluga;
import domain.VrstaUsluge;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelUsluge extends AbstractTableModel {

    private ArrayList<Usluga> lista;
    private String[] kolone = {"Rb", "Cena", "Vrsta usluge"};
    int rb = 0;

    public TableModelUsluge() {
        lista = new ArrayList<>();
    }

    public TableModelUsluge(Termin t) {
        try {
            lista = ClientController.getInstance().getAllUsluga(t);
        } catch (Exception ex) {
            Logger.getLogger(TableModelUsluge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Usluga u = lista.get(row);

        switch (column) {
            case 0:
                return u.getRbUsluge();
            case 1:
                return u.getCenaUsluge();
            case 2:
                return u.getVrstaUsluge();

            default:
                return null;
        }
    }

    public Usluga getSelectedUsluga(int row) {
        return lista.get(row);
    }

    public void dodajUslugu(Usluga u) {
        u.setRbUsluge(++rb);
        lista.add(u);
        fireTableDataChanged();
    }

    public void obrisiUslugu(int row) {
        lista.remove(row);
        
        rb = 0;
        for (Usluga usluga : lista) {
            usluga.setRbUsluge(++rb);
        }
        
        fireTableDataChanged();
    }

    public ArrayList<Usluga> getLista() {
        return lista;
    }

    public Usluga getUsluga(int row) {
        return lista.get(row);
    }

    public boolean postojiVrstaUsluge(VrstaUsluge vu) {
        for (Usluga usluga : lista) {
            if(usluga.getVrstaUsluge().getVrstaUslugeID().equals(vu.getVrstaUslugeID()))
                return true;
        }
        return false;
    }

    public void dodajUsluguZaIzmenu(Usluga u) {
        for (Usluga usluga : lista) {
            rb++;
        }
        u.setRbUsluge(++rb);
        lista.add(u);
        fireTableDataChanged();
    }

}
