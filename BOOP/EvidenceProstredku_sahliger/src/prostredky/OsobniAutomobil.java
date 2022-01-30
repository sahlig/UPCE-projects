/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prostredky;

/**
 *
 * @author Radek
 */
public class OsobniAutomobil extends DopravniProstredek {
    private Integer pocetSedadel;
    private MojeBarva barva;

    public OsobniAutomobil(Integer pocetSedadel, MojeBarva barva, float hmotnost, String pozn) {
        super(ProstredekTyp.OSOBNI_AUTOMOBIL, pozn, hmotnost);
        this.pocetSedadel = pocetSedadel;
        this.barva = barva;
    }
    @Override
    public void setPocet(Integer pocet){
        this.pocetSedadel = pocet;
    }
    @Override
    public int getPocet(){
        return this.pocetSedadel;
    }
    @Override
    public void setBarva(MojeBarva barva){
        this.barva = barva;
    }
    @Override
    public MojeBarva getBarva(){
        return this.barva;
    }

    
    @Override
    public String toString(){
        return super.getId() + "    " + super.getTyp() + " váží " + super.getHmotnost() + "kg,  má počet sedadel " + this.getPocet() + " a má tuto barvu " + this.getBarva() + " s poznávacím číslem " + super.getPozn();
    }
    
}
