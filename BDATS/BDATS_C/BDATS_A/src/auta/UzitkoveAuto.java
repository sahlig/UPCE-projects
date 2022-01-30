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
public class UzitkoveAuto extends Auto implements Serializable{
    private int nos;

    public UzitkoveAuto(String spz, int km, int vyp, int nos) {
        super(spz, km, vyp);
        this.nos = nos;
    }

    @Override
    public int getNos() {
        return nos;
    }

    public void setNos(int nos) {
        this.nos = nos;
    }
    
    
    @Override
    public String toString(){
        return ("Užitkové auto  SPZ: " + super.getSpz() + " Nosnost: " + nos + " Stav Km: " + super.getKm() + " Počet vypůjčení: " + super.getVyp());
    }
}
