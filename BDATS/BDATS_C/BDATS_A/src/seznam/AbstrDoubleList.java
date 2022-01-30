package seznam;


import java.io.Serializable;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Radek
 */
public class AbstrDoubleList<T> implements IAbstrDoubleList<T>, Serializable{
    private Prvek<T> prvni;
    private Prvek<T> aktualni;
    private Prvek<T> posledni;
    private int velikost;
    
    public AbstrDoubleList() {
        this.prvni = null;
        this.aktualni = null;
        this.posledni = null;
        velikost = 0;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iteration = new Iterator<T>() {

            Prvek<T> current = prvni;
            int i = 0;

            @Override
            public boolean hasNext() {
                /*if (i < velikost) {
                    return true;
                } else {
                    return false;
                }*/
                return current != null;
            }

            @Override
            public T next() {
                Prvek<T> tmp = current;
                current = current.dalsi;
                i++;
                return tmp.data;
            }
        };
        return iteration;
    }

    @Override
    public void zrus() {
        prvni = null;
        posledni = null;
        aktualni = null;
        velikost = 0;
    }

    @Override
    public boolean jePrazdny() {
        if (prvni == null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void vlozPrvni(T data) {
        Prvek<T> novy = new Prvek<>(data);
        novy.dalsi = prvni;
        if(prvni == null){
            posledni = novy;
        }else{
            prvni.predchozi = novy;
        }
        prvni = novy;
        velikost++; 
    }

    @Override
    public void vlozPosledni(T data) {
        Prvek<T> novy = new Prvek<>(data);
        novy.predchozi = posledni;
        if(prvni == null){
            prvni = novy;
        }else{
            posledni.dalsi = novy;
        }
        posledni = novy;
        velikost++;
    }

    @Override
    public void vlozNaslednika(T data) throws Exception{
        Prvek<T> novy = new Prvek<>(data);
        if(aktualni == null){
            throw new Exception();
        }else{
            if(aktualni == posledni){
                vlozPosledni(data);
            }else{
            novy.predchozi = aktualni;
            novy.dalsi = aktualni.dalsi;
            aktualni.dalsi = novy;
            novy.dalsi.predchozi = novy;
            if(prvni == posledni){
                posledni = novy;
            }
            velikost++;
            }
        }
        
    }

    @Override
    public void vlozPredchudce(T data) throws Exception{
        Prvek<T> novy = new Prvek<>(data);
        if(aktualni == null){
            throw new Exception();
        }else{
            if(aktualni == prvni){
                vlozPrvni(data);
            }else{
            novy.predchozi = aktualni.predchozi;
            novy.dalsi = aktualni;
            aktualni.predchozi = novy;
            novy.predchozi.dalsi = novy;
            if(prvni == posledni){
                prvni = novy;
            }
            velikost++;
            }
        }
    }

    @Override
    public T zpristupniAktualni() throws Exception{
        if(aktualni == null){
            throw new Exception();
        }
        return aktualni.data;
    }

    @Override
    public T zpristupniPrvni() throws Exception{
        if(prvni == null){
            throw new Exception();
        }
        aktualni = prvni;
        return aktualni.data;
    }

    @Override
    public T zpristupniPosledni() throws Exception{
        if(posledni == null){
            throw new Exception();
        }
        aktualni = posledni;
        return aktualni.data;
    }

    @Override
    public T zpristupniNaslednika() throws Exception{
        if(aktualni == null /*|| aktualni.dalsi == null*/){
            throw new Exception();
        }
        aktualni = aktualni.dalsi;
        return aktualni.data;
    }

    @Override
    public T zpristupniPredchudce() throws Exception{
        if(aktualni == null || aktualni.predchozi == null){
            throw new Exception();
        }
        aktualni = aktualni.predchozi;
        return aktualni.data;
    }

    @Override
    public T odeberAktualni() throws Exception{
        if(aktualni == null){
            throw new Exception();
        }
        Prvek<T> tmp = aktualni;
        if(aktualni == prvni){
            if(prvni == posledni){
                zrus();
            }else{
                prvni = prvni.dalsi;
                prvni.predchozi = null;
                aktualni.dalsi = null;
                aktualni = prvni;
            }
        }else if(aktualni == posledni){
            if(prvni == posledni){
                zrus();
            }else{
                posledni = posledni.predchozi;
                posledni.dalsi = null;
                aktualni.predchozi = null;
                aktualni = prvni;
            }
        }else{
            aktualni.predchozi.dalsi = aktualni.dalsi;
            aktualni.dalsi.predchozi = aktualni.predchozi;
            aktualni.dalsi = aktualni.predchozi = null;
            aktualni = prvni;
        }
        velikost--;
        return tmp.data;
    }

    @Override
    public T odeberPrvni() throws Exception{
        if(prvni == null){
            throw new Exception();
        }
        Prvek<T> tmp = prvni;
        if(prvni == posledni){
            zrus();
        }else{
            if(prvni == aktualni){
                return odeberAktualni();
            }else{
                prvni = prvni.dalsi;
                prvni.predchozi = null;
                tmp.dalsi = null;
            }
        }
        velikost--;
        return tmp.data;
    }

    @Override
    public T odeberPosledni() throws Exception{
        if(prvni == null){
            throw new Exception();
        }
        Prvek<T> tmp = posledni;
        if(prvni == posledni){
            zrus();
        }else{
            if(posledni == aktualni){
                return odeberAktualni();
            }else{
                posledni = posledni.predchozi;
                posledni.dalsi = null;
                tmp.predchozi = null;
            }
        }
        velikost--;
        return tmp.data;
    }

    @Override
    public T odeberNaslednika() throws Exception{
        if(aktualni == null){
            throw new Exception();
        }
        Prvek<T> tmp = aktualni.dalsi;
        if(tmp == null){
            throw new Exception();
        }else{
            if(tmp == posledni){
            return odeberPosledni();
            }else{
                aktualni.dalsi = tmp.dalsi;
                tmp.dalsi.predchozi = aktualni;
                tmp.dalsi = tmp.predchozi = null;
            }
        }
        velikost--;
        return tmp.data;
    }

    @Override
    public T odeberPredchudce() throws Exception{
        if(aktualni == null){
            throw new Exception();
        }
        Prvek<T> tmp = aktualni.predchozi;
        if(tmp == null){
            throw new Exception();
        }else{
            if(tmp == prvni){
            return odeberPrvni();
            }else{
                aktualni.predchozi = tmp.predchozi;
                tmp.predchozi.dalsi = aktualni;
                tmp.dalsi = tmp.predchozi = null;
            }
        }
        velikost--;
        return tmp.data;
    }
    
    @Override
    public int getVelikost(){
        return velikost;
    }
    
    private class Prvek<T> implements Serializable{

        T data;
        Prvek<T> dalsi;
        Prvek<T> predchozi;

        public Prvek(T novy) throws NullPointerException {
            if (novy == null) {
                throw new NullPointerException();
            }
            data = novy;
            dalsi = null;
            predchozi = null;
        }

    }
}
