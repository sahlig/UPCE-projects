/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auta;

import java.io.Serializable;

/**
 *
 * @author Radek
 */
public class OsobniAuto extends Auto implements Serializable{
    private Barva bar;

    public OsobniAuto(String spz, int km, int vyp, Barva bar) {
        super(spz, km, vyp);
        this.bar = bar;
    }

    @Override
    public Barva getBar() {
        return bar;
    }

    public void setBar(Barva bar) {
        this.bar = bar;
    }
    
    
    @Override
    public String toString(){
        return ("Osobní auto  SPZ: " + super.getSpz() + " Barva: " + bar + " Stav Km: " + super.getKm() + " Počet vypůjčení: " + super.getVyp());
    }
    
    
}
