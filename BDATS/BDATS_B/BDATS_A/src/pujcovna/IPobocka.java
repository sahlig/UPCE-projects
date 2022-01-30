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
public interface IPobocka extends Serializable{
    void vlozAuto(Auto auto) throws Exception;
    Auto odeberAuto(Auto auto) throws Exception;
    void zrus();
    Iterator iterator(eTypProhl typP);
    Auto hledejAuto(Auto auto);
}
