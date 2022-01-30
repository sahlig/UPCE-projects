/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pujcovna;

import auta.Auto;
import bvs.AbstrTable;
import bvs.IAbstrTable;
import java.io.Serializable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import seznam.AbstrDoubleList;
import seznam.IAbstrDoubleList;

/**
 *
 * @author Radek
 */
public class Pobocka implements IPobocka, Serializable{
    
    private String jmeno;
    private int pocet;
    private IAbstrTable<Auto, Auto> seznam;
    
    public Pobocka(String jmeno){
        this.jmeno = jmeno;
        seznam = new AbstrTable<Auto, Auto>();
        pocet = 0;
    }

    @Override
    public void vlozAuto(Auto auto) throws Exception{
        seznam.vloz(auto, auto);
        }


    @Override
    public Auto odeberAuto(Auto auto) throws Exception{
        return seznam.odeber(auto);
    }

    @Override
    public void zrus() {
        seznam.zrus();
        pocet = 0;
    }

    @Override
    public Iterator iterator(eTypProhl typP) {
        return seznam.vytvorIterator(typP);
    }
    
    @Override
    public String toString(){
        return jmeno;
    }
    
    @Override
    public Auto hledejAuto(Auto auto){
        try {
            return seznam.najdi(auto);
        } catch (Exception ex) {
            Logger.getLogger(Pobocka.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
