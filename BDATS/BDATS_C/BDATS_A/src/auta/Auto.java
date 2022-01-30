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
public abstract class Auto implements Serializable, Comparable<Auto>{
    private String spz;
    private int km;
    private int vyp;
    
    public Auto(String spz, int km, int vyp){
        this.spz = spz;
        this.km = km;
        this.vyp = vyp;
}

    public String getSpz() {
        return spz;
    }

    public int getKm() {
        return km;
    }

    public int getVyp() {
        return vyp;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public void setVyp(int vyp) {
        this.vyp = vyp;
    }
    
    public Barva getBar(){
        return null;
    }
    
    public int getNos(){
        return 0;
    }
    
    @Override
    public int compareTo(Auto dalsi){
        return this.getKm() - dalsi.getKm();
    }
   
    
}
