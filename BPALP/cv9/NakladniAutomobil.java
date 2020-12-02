/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv9;

/**
 *
 * @author Radek
 */
public class NakladniAutomobil extends Automobil {
    int pocetNaprav;
    int nosnost;

    public NakladniAutomobil(int pocetNaprav, int nosnost, int hmotnost, String pozn) {
        super(hmotnost, pozn);
        this.pocetNaprav = pocetNaprav;
        this.nosnost = nosnost;
    }
    
    public void setPocetN(int pocet){
        this.pocetNaprav = pocet;
    }
    
    public int getPocetN(){
        return this.pocetNaprav;
    }
    
    public void setNosnost(int nosnost){
        this.nosnost = nosnost;
    }
    
    public int getNosnost(){
        return this.nosnost;
    }

    

    @Override
    public String toString(){
        return "Nakladni automobil vazi " + super.getHmotnost() + " ma pocet naprav " + this.getPocetN() + " a nosnost " + this.getNosnost() + " s poznavacim cislem " + super.getPozn();
    }
    
}
