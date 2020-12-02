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
public class Automobil extends DopravniProstredek {

    private int hmotnost;
    public Automobil(int hmotnost, String pozn) {
        super(pozn);
        this.hmotnost = hmotnost;
    }
    
    public void setHmotnost(int hmotnost){
        this.hmotnost = hmotnost;
    }
    
    public int getHmotnost(){
        return this.hmotnost;
    }
    
}
