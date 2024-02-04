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
public class PrvaNit extends Thread {

    private JLabel lblPrviBroj;
    private boolean aktivna;

    public PrvaNit(JLabel lblPrviBroj) {
        this.lblPrviBroj = lblPrviBroj;
        this.aktivna = true;
    }

    @Override
    public void run() {
        while (aktivna) {
            lblPrviBroj.setText(String.valueOf(Math.round(Math.random() * 5)));
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(PrvaNit.class.getName()).log(Level.SEVERE, null, ex);
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
