/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronta;

import java.io.Serializable;
import java.util.Iterator;
import seznam.IAbstrDoubleList;

/**
 *
 * @author Radek
 */
public interface IAbstrPriorQueue<T extends Comparable<T>>{
    void zrus();
    boolean jePrazdny();
    void vloz(T data);
    T odeberMax();
    T zpristupni();
    Iterator vytvorIterator(int ktery);
    void vybuduj(IAbstrDoubleList<T> seznam);
}
