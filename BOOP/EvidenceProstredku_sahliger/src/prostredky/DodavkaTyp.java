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
public enum DodavkaTyp {
    VALNIK("valník"),
    DVOJKABINA("dvojkabina"),
    NASTAVBA("nástavba");
    
    private String nazev;
    
    private DodavkaTyp(String nazev){
     this.nazev = nazev;   
    }

    @Override
    public String toString() {
       return this.nazev;
    }
    
    public static DodavkaTyp decode(String typ){
        switch(typ){
            case "valník":
                return VALNIK;
            case "dvojkabina":
                return DVOJKABINA;
            case "nástavba":
                return NASTAVBA;
            default:
                break;
        }
        return null;
    }
}
