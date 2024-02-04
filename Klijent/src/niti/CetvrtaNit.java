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
public class CetvrtaNit extends Thread {

    private JLabel lblCetvrtiBroj;
    private boolean aktivna;

    public CetvrtaNit(JLabel lblCetvrtiBroj) {
        this.lblCetvrtiBroj = lblCetvrtiBroj;
        this.aktivna = true;
    }

    @Override
    public void run() {
        while (aktivna) {
            lblCetvrtiBroj.setText(String.valueOf(Math.round(Math.random() * 5)));
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(CetvrtaNit.class.getName()).log(Level.SEVERE, null, ex);
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
