/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pujcovna;

import java.io.Serializable;

/**
 *
 * @author Radek
 */
public enum EnumPozice implements Serializable{
    PRVNI("První"),
    PREDCHUDCE("Předchůdce"),
    AKTUALNI("Aktuální"),
    NASLEDNIK("Nasledník"),
    POSLEDNI("Poslední");
    
    private String nazev;
    
    private EnumPozice(String nazev){
        this.nazev = nazev;
    }


    @Override
    public String toString() {
        return nazev;
    }
    
    public static EnumPozice decode(String pozice){
        switch(pozice){
            case "První":
                return PRVNI;
            case "Předchozí":
                return PREDCHUDCE;
            case "Aktuální":
                return AKTUALNI;
            case "Následující":
                return NASLEDNIK;
            case "Poslední":
                return POSLEDNI;
            default:
                break;
        }
        return null;
    }
    
}
