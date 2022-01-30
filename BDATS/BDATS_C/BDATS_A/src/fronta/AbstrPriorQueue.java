/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronta;

import generator.Generator;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import seznam.AbstrDoubleList;
import seznam.IAbstrDoubleList;

/**
 *
 * @author Radek
 */
public class AbstrPriorQueue<T extends Comparable<T>> implements IAbstrPriorQueue<T>, Serializable{
    
    int velikostCel;
    int velikostSet;
    IAbstrDoubleList<T> setrideny;
    IAbstrDoubleList<T> nesetrideny;
    
    public AbstrPriorQueue(){
        velikostCel = 0;
        velikostSet = Generator.genCel(5, 10);
        setrideny = new AbstrDoubleList<T>();
        nesetrideny = new AbstrDoubleList<T>();
    }
    
    public AbstrPriorQueue(int vel){
        velikostCel = vel;
        velikostSet = (int) Math.sqrt(vel);
        setrideny = new AbstrDoubleList<T>();
        nesetrideny = new AbstrDoubleList<T>();
    }

    @Override
    public void zrus() {
        setrideny.zrus();
        nesetrideny.zrus();
        velikostCel = 0;
        velikostSet = 0;
    }

    @Override
    public boolean jePrazdny() {
        return velikostCel == 0;
    }

    @Override
    public void vloz(T data) {
        T tmp;
        if(setrideny.jePrazdny()){
            setrideny.vlozPosledni(data);
        }else if(setrideny.getVelikost() < velikostSet){
            try {
                tmp = setrideny.zpristupniPosledni();
                if (data.compareTo(tmp) >= 0){
                    setrideny.vlozPosledni(data);
                }else{
                    vlozSet(data);
                }
            } catch (Exception ex) {
                Logger.getLogger(AbstrPriorQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(setrideny.getVelikost() == velikostSet){
            try {
                tmp = setrideny.zpristupniPosledni();
                if (data.compareTo(tmp) >= 0){
                    nesetrideny.vlozPrvni(data);
                }else{
                    vlozSet(data);
                    nesetrideny.vlozPrvni(setrideny.odeberPosledni());
                }
            } catch (Exception ex) {
                Logger.getLogger(AbstrPriorQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public T odeberMax() {
        if(setrideny.jePrazdny() && nesetrideny.getVelikost() != 0){
            mojeVybuduj();
            return odeberMax();
        }else if(setrideny.jePrazdny() && nesetrideny.getVelikost() == 0){
            return null;
        }else{
            try {
                return setrideny.odeberPrvni();
                
            } catch (Exception ex) {
                Logger.getLogger(AbstrPriorQueue.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public T zpristupni() {
        try {
            return setrideny.zpristupniPrvni();
        } catch (Exception ex) {
            Logger.getLogger(AbstrPriorQueue.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Iterator vytvorIterator(int ktery) {
        if(ktery == 0){
            return setrideny.iterator();
        }else{
            return nesetrideny.iterator();
        }
    }

    
    private void mojeVybuduj() {
        if(nesetrideny.getVelikost() != 0){
            T tmp;
            T tmpNext;
            for (int i = 0; i < velikostSet; i++) {
                try {
                    tmp = null;
                    tmpNext = null;
                    try{
                    tmp = nesetrideny.zpristupniPrvni();
                    }catch(Exception ex){
                        tmp = null;
                    }
                    for (int j = 0; j < nesetrideny.getVelikost(); j++) {
                    try{
                    tmpNext = nesetrideny.zpristupniNaslednika();
                    }catch(Exception ex){
                        tmpNext = null;
                    }
                    
                    if(tmpNext != null){
                        if(tmp.compareTo(tmpNext) >= 0){
                            tmp = tmpNext;
                        }
                    }
                    }
                    
                    /*Iterator ite = nesetrideny.iterator();
                    while(ite.hasNext()){
                    if(tmp == null){
                    tmpNext = (T) ite.next();
                    tmp = tmpNext;
                    }else{
                    tmpNext = (T) ite.next();
                    if(tmp.compareTo(tmpNext) >= 0){
                    tmp = tmpNext;
                    }
                    }
                    }*/
                    if(tmp != null){
                        tmpNext = null;
                        tmpNext = nesetrideny.zpristupniPrvni();
                        while(tmpNext != tmp){
                            //System.out.println("Dolni While" + tmp);
                            if(tmpNext != tmp){
                               // System.out.println("zase" + tmpNext);
                                tmpNext = nesetrideny.zpristupniNaslednika();
                            }
                        }
                        setrideny.vlozPosledni(tmp);
                        nesetrideny.odeberAktualni();
                    }
                } catch (Exception ex) {
                    Logger.getLogger(AbstrPriorQueue.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public void vybuduj(IAbstrDoubleList<T> seznam){
        long startDate = System.nanoTime();
        nesetrideny = seznam;
        mojeVybuduj();
        long endDate = System.nanoTime();
        System.out.println("Čas pomocí vybuduj");
        System.out.println((endDate - startDate) + " ms");
    }
    
    private void vlozSet(T data) throws Exception{
        setrideny.zpristupniPosledni();
        if(setrideny.getVelikost() == 1){
            setrideny.vlozPrvni(data);
        }else{
            for (int i = 0; i < setrideny.getVelikost(); i++){
                if(i == (setrideny.getVelikost() - 1)){
                setrideny.vlozPrvni(data);
                return;
                }
            T tmp = setrideny.zpristupniPredchudce();
            if(data.compareTo(tmp) >= 0){
            setrideny.vlozNaslednika(data);
            return;
            }
        }
    }}
}
