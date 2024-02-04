/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Ari
 */
public class ServerskiOdgovor implements Serializable {

    private Object odgovor;
    private String poruka;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, String poruka) {
        this.odgovor = odgovor;
        this.poruka = poruka;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

}
