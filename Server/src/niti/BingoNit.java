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
public class BingoNit extends Thread {

    private JLabel lblPrviBroj;
    private JLabel lblDrugiBroj;
    private JLabel lblTreciBroj;
    private JLabel lblCetvrtiBroj;
    private boolean aktivna;

    public BingoNit(JLabel lblPrviBroj, JLabel lblDrugiBroj, JLabel lblTreciBroj, JLabel lblCetvrtiBroj) {
        this.lblPrviBroj = lblPrviBroj;
        this.lblDrugiBroj = lblDrugiBroj;
        this.lblTreciBroj = lblTreciBroj;
        this.lblCetvrtiBroj = lblCetvrtiBroj;
        this.aktivna = true;
    }

    @Override
    public void run() {
        while (aktivna) {

            long prviBroj = Math.round(Math.random() * 5);
            long drugiBroj = Math.round(Math.random() * 5);
            long treciBroj = Math.round(Math.random() * 5);
            long cetvrtiBroj = Math.round(Math.random() * 5);

            lblPrviBroj.setText(String.valueOf(prviBroj));
            lblDrugiBroj.setText(String.valueOf(drugiBroj));
            lblTreciBroj.setText(String.valueOf(treciBroj));
            lblCetvrtiBroj.setText(String.valueOf(cetvrtiBroj));

            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(BingoNit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void stopirajNit() {
        this.aktivna = false;
    }

    public boolean isAktivna() {
        return aktivna;
    }

}
