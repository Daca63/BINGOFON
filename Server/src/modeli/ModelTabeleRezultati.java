/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Igra;
import domen.RezultatIgre;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import logika.Kontroler;

/**
 *
 * @author Ari
 */
public class ModelTabeleRezultati extends AbstractTableModel {

    ArrayList<RezultatIgre> lista;
    String[] kolone = {"Datum i vreme", "Zadata kombinacija", "Broj pokusaja", "Rezultat"};

    public ModelTabeleRezultati() {
        lista = Kontroler.getInstance().vratiRezultate();
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
    public String getColumnName(int columnIndex) {
        return kolone[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RezultatIgre ri = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        switch (columnIndex) {
            case 0:
                return sdf.format(ri.getDatumVreme());
            case 1:
                return ri.getZadataKombinacija();
            case 2:
                return ri.getBrojPokusaja();
            case 3:
                return ri.getRezultat();

            default:
                return "return!";
        }
    }

    public ArrayList<Igra> vratiIgre(int row) {
        return lista.get(row).getIgre();
    }

}
