/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Ari
 */
public class DrugaNit extends Thread {

    private JLabel lblDrugiBroj;
    private boolean aktivna;

    public DrugaNit(JLabel lblDrugiBroj) {
        this.lblDrugiBroj = lblDrugiBroj;
        this.aktivna = true;
    }

    @Override
    public void run() {
        while (aktivna) {
            lblDrugiBroj.setText(String.valueOf(Math.round(Math.random() * 5)));
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(DrugaNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isAktivna() {
        return aktivna;
    }

    public void stopiraj() {
        this.aktivna = false;
    }

}
