/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bvs;

import java.util.Iterator;
import pujcovna.eTypProhl;

/**
 *
 * @author Radek
 */
public interface IAbstrTable<K extends Comparable<K>, V>{
    void zrus();
    boolean jePrazdny();
    V najdi(K key) throws Exception;
    void vloz(K key, V value) throws Exception;
    V odeber(K key) throws Exception;
    Iterator vytvorIterator(eTypProhl typ);
}
