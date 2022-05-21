/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tresenraya;

import javax.swing.JButton;

/**
 *
 * @author Easyklk
 */
public class Caja extends JButton {

    private int xH;
    private int yV;
    private int propiedad;

    public Caja() {
        this.xH = 0;
        this.yV = 0;
        this.propiedad = 0;
    }

    public Caja(int x, int y, int propiedad) {
        this.xH = x;
        this.yV = y;
        this.propiedad = propiedad;
    }

    public int getxH() {
        return xH;
    }

    public void setxH(int xH) {
        this.xH = xH;
    }

    public int getyV() {
        return yV;
    }

    public void setyV(int yV) {
        this.yV = yV;
    }

    public int getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(int propiedad) {
        this.propiedad = propiedad;
    }

}
