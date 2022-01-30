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
public class NakladniAutomobil extends DopravniProstredek {
    private Integer pocetNaprav;
    private long nosnost;

    public NakladniAutomobil(Integer pocetNaprav, long nosnost, float hmotnost, String pozn) {
        super(ProstredekTyp.NAKLADNI_AUTMOBIL, pozn, hmotnost);
        this.pocetNaprav = pocetNaprav;
        this.nosnost = nosnost;
    }
    @Override
    public void setPocetN(Integer pocet){
        this.pocetNaprav = pocet;
    }
    @Override
    public int getPocetN(){
        return this.pocetNaprav;
    }
    @Override
    public void setNosnost(long nosnost){
        this.nosnost = nosnost;
    }
    @Override
    public long getNosnost(){
        return this.nosnost;
    }

    

    @Override
    public String toString(){
        return super.getId() + "    " + super.getTyp() + " váží " + super.getHmotnost() + "kg, má počet náprav " + this.getPocetN() + " a nosnost " + this.getNosnost() + " s poznávacím číslem " + super.getPozn();
    }
    
}
