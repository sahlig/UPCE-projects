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
public enum Barva implements Serializable{
    BILA("bílá"),
    CERNA("černá"),
    CERVENA("červená"),
    ZELENA("zelená"),
    MODRA("modrá");
    
    private String nazev;
    
    private Barva(String nazev){
        this.nazev = nazev;
    }


    @Override
    public String toString() {
        return nazev;
    }
    
    public static Barva decode(String barva){
        switch(barva){
            case "bílá":
                return BILA;
            case "černá":
                return CERNA;
            case "červená":
                return CERVENA;
            case "zelená":
                return ZELENA;
            case "modrá":
                return MODRA;
            default:
                break;
        }
        return null;
    }
    
}
