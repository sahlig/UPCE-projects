/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv9;

import java.awt.Color;

/**
 *
 * @author Radek
 */
public class OsobniAutomobil extends Automobil {
    private int pocetSedadel;
    private Color barva;

    public OsobniAutomobil(int pocetSedadel, Color barva, int hmotnost, String pozn) {
        super(hmotnost, pozn);
        this.pocetSedadel = pocetSedadel;
        this.barva = barva;
    }
    
    public void setPocet(int pocet){
        this.pocetSedadel = pocet;
    }
    
    public int getPocet(){
        return this.pocetSedadel;
    }
    
    public void setBarva(Color barva){
        this.barva = barva;
    }
    
    public Color getBarva(){
        return this.barva;
    }

    
    @Override
    public String toString(){
        return "Osobni automobil vazi " + super.getHmotnost() + "  ma pocet sedadel " + this.getPocet() + " a ma tuto barvu " + this.getBarva() + " s poznavacim cislem " + super.getPozn();
    }
    
}
