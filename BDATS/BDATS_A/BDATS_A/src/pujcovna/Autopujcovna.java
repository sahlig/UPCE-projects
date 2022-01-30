/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pujcovna;

import auta.Auto;
import auta.OsobniAuto;
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
    IAbstrDoubleList<Auto> vypujcene;
    
    public Autopujcovna(){
        pobocky = new AbstrDoubleList<IPobocka>();
        vypujcene = new AbstrDoubleList<Auto>();
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
                throw new Exception();
            }
        }
            case NASLEDNIK:
        {
            try {
                return pobocky.odeberNaslednika();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case POSLEDNI:
        {
            try {
                return pobocky.odeberPosledni();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            default:
                return null;
        }
    }

    @Override
    public void vlozAuto(Auto auto, EnumPozice Pozice) throws Exception{
        try {
            pobocky.zpristupniAktualni().vlozAuto(auto, Pozice);
        } catch (Exception ex) {
            throw new Exception();
        }
    }

    @Override
    public Auto zpristupniAuto(EnumPozice Pozice) throws Exception{
        try {
            return pobocky.zpristupniAktualni().zpristupniAuto(Pozice);
        } catch (Exception ex) {
            throw new Exception();
        }
    }

    @Override
    public Auto odeberAuto(EnumPozice Pozice) throws Exception{
        try {
            return pobocky.zpristupniAktualni().odeberAuto(Pozice);
        } catch (Exception ex) {
            throw new Exception();
        }
    }

    @Override
    public Auto vypujcAuto(EnumPozice Pozice) throws Exception{
        try {
            Auto tmp = pobocky.zpristupniAktualni().odeberAuto(Pozice);
            vypujcene.vlozPrvni(tmp);
            return tmp;
        } catch (Exception ex) {
            throw new Exception();
        }
    }

    @Override
    public Auto vratAuto(EnumPozice Pozice) throws Exception{
        try {
            Auto tmp;
            
            switch(Pozice){
                case PRVNI:
                    tmp = vypujcene.odeberPrvni();
                    pobocky.zpristupniAktualni().vlozAuto(tmp, EnumPozice.PRVNI);
                    tmp.setVyp(tmp.getVyp() + 1);
                    return tmp;
                case PREDCHUDCE:
                    tmp = vypujcene.odeberPredchudce();
                    pobocky.zpristupniAktualni().vlozAuto(tmp, EnumPozice.PRVNI);
                    tmp.setVyp(tmp.getVyp() + 1);
                    return tmp;
                case AKTUALNI:
                    tmp = vypujcene.odeberAktualni();
                    pobocky.zpristupniAktualni().vlozAuto(tmp, EnumPozice.PRVNI);
                    tmp.setVyp(tmp.getVyp() + 1);
                    return tmp;
                case NASLEDNIK:
                    tmp = vypujcene.odeberNaslednika();
                    pobocky.zpristupniAktualni().vlozAuto(tmp, EnumPozice.PRVNI);
                    tmp.setVyp(tmp.getVyp() + 1);
                    return tmp;
                case POSLEDNI:
                    tmp = vypujcene.odeberPosledni();
                    pobocky.zpristupniAktualni().vlozAuto(tmp, EnumPozice.PRVNI);
                    tmp.setVyp(tmp.getVyp() + 1);
                    return tmp;
                default:
                    return null;
            }
        } catch (Exception ex) {
            throw new Exception();
        }
    }

    @Override
    public Auto zpristupniVypujceneAuto(EnumPozice Pozice) throws Exception{
        switch(Pozice){
                case PRVNI:
                    return vypujcene.zpristupniPrvni();
                case PREDCHUDCE:
                    return vypujcene.zpristupniPredchudce();
                case AKTUALNI:
                    return vypujcene.zpristupniAktualni();
                case NASLEDNIK:
                    return vypujcene.zpristupniNaslednika();
                case POSLEDNI:
                    return vypujcene.zpristupniPosledni();
                default:
                    return null;
            }
    }

    @Override
    public Iterator iterator(eTyp typ) throws Exception{
        switch(typ){
            case AUTOMOBIL:
        {
            try {
                return pobocky.zpristupniAktualni().iterator();
            } catch (Exception ex) {
                throw new Exception();
            }
        }
            case POBOCKA:
                return pobocky.iterator();
            case VYPUJCENY:
                return vypujcene.iterator();
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
    }
    
}
