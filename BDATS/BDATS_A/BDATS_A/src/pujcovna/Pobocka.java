/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pujcovna;

import auta.Auto;
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
    private IAbstrDoubleList<Auto> seznam;
    
    public Pobocka(String jmeno){
        this.jmeno = jmeno;
        seznam = new AbstrDoubleList<Auto>();
        pocet = 0;
    }

    @Override
    public void vlozAuto(Auto auto, EnumPozice Pozice) throws Exception{
        switch(Pozice){
            case PRVNI:
                seznam.vlozPrvni(auto);
                pocet++;
                break;
            case PREDCHUDCE:
                seznam.vlozPredchudce(auto);
                pocet++;
                break;
            case NASLEDNIK:
                seznam.vlozNaslednika(auto);
                pocet++;
                break;
            case POSLEDNI:
                seznam.vlozPosledni(auto);
                pocet++;
                break;
            default:
                break;
        }
    }

    @Override
    public Auto zpristupniAuto(EnumPozice Pozice) throws Exception{
        switch(Pozice){
            case PRVNI:
        {
            try {
                return seznam.zpristupniPrvni();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case PREDCHUDCE:
        {
            try {
                return seznam.zpristupniPredchudce();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case AKTUALNI:
        {
            try {
                return seznam.zpristupniAktualni();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case NASLEDNIK:
        {
            try {
                return seznam.zpristupniNaslednika();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case POSLEDNI:
        {
            try {
                return seznam.zpristupniPosledni();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            default:
                return null;
        }
    }

    @Override
    public Auto odeberAuto(EnumPozice Pozice) throws Exception{
        Auto tmp;
        switch(Pozice){
            case PRVNI:
        {
            try {
                tmp = seznam.odeberPrvni();
                pocet--;
                return tmp;
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case PREDCHUDCE:
        {
            try {
                tmp = seznam.odeberPredchudce();
                pocet--;
                return tmp;
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case AKTUALNI:
        {
            try {
                tmp = seznam.odeberAktualni();
                pocet--;
                return tmp;
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case NASLEDNIK:
        {
            try {
                tmp = seznam.odeberNaslednika();
                pocet--;
                return tmp;
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case POSLEDNI:
        {
            try {
                tmp = seznam.odeberPosledni();
                pocet--;
                return tmp;
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            default:
                return null;
        }
    }

    @Override
    public void zrus() {
        seznam.zrus();
        pocet = 0;
    }

    @Override
    public Iterator iterator() {
        return seznam.iterator();
    }
    
    @Override
    public String toString(){
        return jmeno;
    }
    
}
