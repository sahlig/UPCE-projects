/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pujcovna;

import auta.Auto;
import fronta.IAbstrPriorQueue;
import java.io.Serializable;
import java.util.Iterator;
import seznam.IAbstrDoubleList;

/**
 *
 * @author Radek
 */
public interface IAutopujcovna extends Serializable{
    void vlozPobocku(IPobocka Pobocka, EnumPozice Pozice) throws Exception;
    IPobocka zpristupniPobocku(EnumPozice Pozice) throws Exception;
    IPobocka odeberPobocku(EnumPozice Pozice) throws Exception;
    void vlozAuto(Auto auto) throws Exception;
    Auto odeberAuto(Auto auto) throws Exception;
    Auto vypujcAuto(Auto auto) throws Exception;
    Auto vratAuto(Auto auto) throws Exception;
    Iterator iterator(eTyp typ, eTypProhl typP, int ktery) throws Exception;
    void zrusPobocku() throws Exception;
    void zrus();
    Auto hledej(Auto auto);
    void budovani(IAbstrDoubleList<Auto> seznam);
    IAbstrPriorQueue<Auto> getAuta();
    
}
