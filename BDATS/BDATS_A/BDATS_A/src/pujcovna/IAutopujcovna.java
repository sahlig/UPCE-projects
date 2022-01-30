/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pujcovna;

import auta.Auto;
import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author Radek
 */
public interface IAutopujcovna extends Serializable{
    void vlozPobocku(IPobocka Pobocka, EnumPozice Pozice) throws Exception;
    IPobocka zpristupniPobocku(EnumPozice Pozice) throws Exception;
    IPobocka odeberPobocku(EnumPozice Pozice) throws Exception;
    void vlozAuto(Auto auto, EnumPozice Pozice) throws Exception;
    Auto zpristupniAuto(EnumPozice Pozice) throws Exception;
    Auto odeberAuto(EnumPozice Pozice) throws Exception;
    Auto vypujcAuto(EnumPozice Pozice) throws Exception;
    Auto vratAuto(EnumPozice Pozice) throws Exception;
    Auto zpristupniVypujceneAuto(EnumPozice Pozice) throws Exception;
    Iterator iterator(eTyp typ) throws Exception;
    void zrusPobocku() throws Exception;
    void zrus();
    
}
