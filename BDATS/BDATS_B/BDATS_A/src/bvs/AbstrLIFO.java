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
public class AbstrLIFO<T> implements IAbstrLIFO<T>{
    
    private IAbstrDoubleList<T> zasobnik;
    
    public AbstrLIFO(){
        this.zasobnik = new AbstrDoubleList<T>();
    }

    @Override
    public void zrus() {
        zasobnik.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return zasobnik.jePrazdny();
    }

    @Override
    public void vloz(T data) {
        zasobnik.vlozPrvni(data);
    }

    @Override
    public T odeber() {
        try {
            return zasobnik.odeberPrvni();
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
        return zasobnik.iterator();
    }
    
}
