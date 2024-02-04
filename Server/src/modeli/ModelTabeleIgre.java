/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Igra;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ari
 */
public class ModelTabeleIgre extends AbstractTableModel {

    ArrayList<Igra> lista;
    String[] kolone = {"RB", "Kombinacija", "Pogodjenih NA MESTU", "Pogodjenih NISU NA MESTU"};
    int rb = 0;

    public ModelTabeleIgre() {
        lista = new ArrayList<>();
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
        Igra r = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return r.getRb();
            case 1:
                return r.getKombinacija();
            case 2:
                return r.getPogodjenihNaMestu();
            case 3:
                return r.getPogodjenihNisuNaMestu();

            default:
                return "return!";
        }
    }

    public void dodajIgre(ArrayList<Igra> igre) {
        lista = igre;
        fireTableDataChanged();
    }

}
