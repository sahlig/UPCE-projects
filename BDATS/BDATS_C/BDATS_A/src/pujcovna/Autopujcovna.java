/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pujcovna;

import auta.Auto;
import auta.OsobniAuto;
import bvs.AbstrTable;
import bvs.IAbstrTable;
import fronta.AbstrPriorQueue;
import fronta.IAbstrPriorQueue;
import generator.Generator;
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
public class Autopujcovna implements IAutopujcovna, Serializable{
    
    IAbstrDoubleList<IPobocka> pobocky;
    IAbstrPriorQueue<Auto> auta;
    IPobocka vypujcene;
    
    public Autopujcovna(){
        pobocky = new AbstrDoubleList<IPobocka>();
        //vypujcene = new AbstrTable<Auto, Auto>();
        vypujcene = new Pobocka("vyp");
        auta = new AbstrPriorQueue<Auto>();
    }
    
    public Autopujcovna(int velikost){
        pobocky = new AbstrDoubleList<IPobocka>();
        //vypujcene = new AbstrTable<Auto, Auto>();
        vypujcene = new Pobocka("vyp");
        auta = new AbstrPriorQueue<Auto>(velikost);
    }
    

    @Override
    public void vlozPobocku(IPobocka Pobocka, EnumPozice Pozice) throws Exception{
        switch(Pozice){
            case PRVNI:
                pobocky.vlozPrvni(Pobocka);
                break;
            case PREDCHUDCE:
        {
            try {
                pobocky.vlozPredchudce(Pobocka);
            } catch (Exception ex) {
                throw new Exception();
            }
        }
                break;
            case NASLEDNIK:
        {
            try {
                pobocky.vlozNaslednika(Pobocka);
            } catch (Exception ex) {
                throw new Exception();
            }
        }
                break;
            case POSLEDNI:
                pobocky.vlozPosledni(Pobocka);
                break;
            default:
                break;
        }
    }

    @Override
    public IPobocka zpristupniPobocku(EnumPozice Pozice) throws Exception{
        switch(Pozice){
            case PRVNI:
        {
            try {
                return pobocky.zpristupniPrvni();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case PREDCHUDCE:
        {
            try {
                return pobocky.zpristupniPredchudce();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case AKTUALNI:
        {
            try {
                return pobocky.zpristupniAktualni();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case NASLEDNIK:
        {
            try {
                return pobocky.zpristupniNaslednika();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case POSLEDNI:
        {
            try {
                return pobocky.zpristupniPosledni();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            default:
                return null;
        }
    }

    @Override
    public IPobocka odeberPobocku(EnumPozice Pozice) throws Exception{
    switch(Pozice){
            case PRVNI:
        {
            try {
                return pobocky.odeberPrvni();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case PREDCHUDCE:
        {
            try {
                return pobocky.odeberPredchudce();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case AKTUALNI:
        {
            try {
                return pobocky.odeberAktualni();
            } catch (Exception ex) {
                Logger.getLogger(Autopujcovna.class.getName()).log(Level.SEVERE, null, ex);
                throw new Exception();
            }
        }
            case NASLEDNIK:
        {
            try {
                return pobocky.odeberNaslednika();
            } catch (Exception ex) {
                Logger.getLogger(Autopujcovna.class.getName()).log(Level.SEVERE, null, ex);
                throw new Exception();
            }
        }
            case POSLEDNI:
        {
            try {
                return pobocky.odeberPosledni();
            } catch (Exception ex) {
                Logger.getLogger(Autopujcovna.class.getName()).log(Level.SEVERE, null, ex);
                throw new Exception();
            }
        }
            default:
                return null;
        }
    }

    @Override
    public void vlozAuto(Auto auto) throws Exception{
        try {
            auta.vloz(auto);
        } catch (Exception ex) {
            Logger.getLogger(Autopujcovna.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    @Override
    public Auto odeberAuto(Auto auto) throws Exception{
        try {
            return pobocky.zpristupniAktualni().odeberAuto(auto);
        } catch (Exception ex) {
            Logger.getLogger(Autopujcovna.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    @Override
    public Auto vypujcAuto(Auto auto) throws Exception{
        try {
            Auto tmp = auta.odeberMax();
            //vypujcene.vloz(tmp, tmp);
            vypujcene.vlozAuto(tmp);
            return tmp;
        } catch (Exception ex) {
            Logger.getLogger(Autopujcovna.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    @Override
    public Auto vratAuto(Auto auto) throws Exception{
        try {
            Auto tmp;
                    //tmp = vypujcene.odeber(auto);
                    tmp = vypujcene.odeberAuto(auto);
                    tmp.setKm(tmp.getKm() + Generator.genCel(500, 50000));
                    auta.vloz(tmp);
                    System.out.println(tmp);
                    tmp.setVyp(tmp.getVyp() + 1);
                    return tmp;
        } catch (Exception ex) {
            Logger.getLogger(Autopujcovna.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception();
        }
    }

    @Override
    public Iterator iterator(eTyp typ, eTypProhl typP, int ktery) throws Exception{
        switch(typ){
            case AUTOMOBIL:
        {
            try {
                return auta.vytvorIterator(ktery);
            } catch (Exception ex) {
                Logger.getLogger(Autopujcovna.class.getName()).log(Level.SEVERE, null, ex);
                throw new Exception();
            }
        }
            case POBOCKA:
                return pobocky.iterator();
            case VYPUJCENY:
                //return vypujcene.vytvorIterator(typP);
                try{
                return vypujcene.iterator(typP);
                }catch (Exception ex){
                    Logger.getLogger(Autopujcovna.class.getName()).log(Level.SEVERE, null, ex);
                    throw new Exception();
                }
            default:
                return null;
        }
    }

    @Override
    public void zrusPobocku() throws Exception{
        pobocky.zpristupniAktualni().zrus();
    }

    @Override
    public void zrus() {
        pobocky.zrus();
        vypujcene.zrus();
        auta.zrus();
    }
    
    @Override
    public Auto hledej(Auto auto){
        try {
            pobocky.zpristupniAktualni().hledejAuto(auto);
        } catch (Exception ex) {
            Logger.getLogger(Autopujcovna.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public void budovani(IAbstrDoubleList<Auto> seznam){
        auta.vybuduj(seznam);
    }

    @Override
    public IAbstrPriorQueue<Auto> getAuta() {
        return auta;
    }
    
}
