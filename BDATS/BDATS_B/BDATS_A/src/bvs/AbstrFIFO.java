/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bvs;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import seznam.AbstrDoubleList;
import seznam.IAbstrDoubleList;

/**
 *
 * @author Radek
 */
public class AbstrFIFO<T> implements IAbstrFIFO<T>{
    
    private IAbstrDoubleList<T> fronta;
    
    public AbstrFIFO(){
        this.fronta = new AbstrDoubleList<T>();
    }

    @Override
    public void zrus() {
        fronta.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return fronta.jePrazdny();
    }

    @Override
    public void vloz(T data) {
        fronta.vlozPrvni(data);
    }

    @Override
    public T odeber() {
        try {
            return fronta.odeberPosledni();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Iterator vytvorIterator() {
        return iterator();
    }

    @Override
    public Iterator<T> iterator() {
        return fronta.iterator();
    }
    
}
