/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekce;

import java.util.Iterator;


/**
 *
 * @author Radek
 */
public class LinSeznam<E> implements Seznam<E> {

    private Prvek<E> prvni;
    private Prvek<E> aktualni;
    private Prvek<E> posledni;
    private int velikost;

    public LinSeznam() {
        this.prvni = null;
        this.aktualni = null;
        this.posledni = null;
        velikost = 0;
    }

    @Override
    public void nastavPrvni() throws KolekceException {
        if (prvni == null) {
            throw new KolekceException();
        } else {
            aktualni = prvni;
        }
    }

    @Override
    public void nastavPosledni() throws KolekceException {
        if (prvni == null) {
            throw new KolekceException();
        } else {
            aktualni = posledni;
            
        }
    }

    @Override
    public boolean dalsi() throws KolekceException {
        if (aktualni == null) {
            throw new KolekceException();
        } else {
            if (aktualni.dalsi != null) {
                aktualni = aktualni.dalsi;
                return true;
            } else {
                aktualni = aktualni.dalsi;
                return false;
            }
        }
    }
 
    @Override
    public void vlozPrvni(E data) {
        Prvek<E> novy = new Prvek<>(data);
        novy.dalsi = prvni;
        if (prvni == null) {
            posledni = novy;
        }
        prvni = novy;
        velikost++;
    }

    @Override
    public void vlozNaKonec(E data) {
        Prvek<E> novy = new Prvek<>(data);
        if (prvni == null) {
            prvni = novy;
            posledni = prvni;
        } else {
            posledni.dalsi = novy;
            posledni = posledni.dalsi;
        }
        velikost++;

    }

    @Override
    public void vlozZaAktualni(E data) throws KolekceException {
        Prvek<E> novy = new Prvek<>(data);
        if (aktualni == null) {
            throw new KolekceException();
        } else {
            Prvek<E> tmp = aktualni.dalsi;
            aktualni.dalsi = novy;
            novy.dalsi = tmp;
            velikost++;
            if (aktualni == posledni) {
                posledni = aktualni.dalsi;
            }
        }

    }

    @Override
    public boolean jePrazdny() {
        return prvni == null;
    }

    @Override
    public E dejPrvni() throws KolekceException {
        if (prvni == null) {
            throw new KolekceException();
        } else {
            return prvni.data;
        }
    }

    @Override
    public E dejPosledni() throws KolekceException {
        if (prvni == null) {
            throw new KolekceException();
        } else {
            return posledni.data;
        }
    }

    @Override
    public E dejAktualni() throws KolekceException {
        if (prvni == null || aktualni == null) {
            throw new KolekceException();
        } else {
            return aktualni.data;
        }
    }

    @Override
    public E dejZaAktualnim() throws KolekceException {
        if (prvni == null || aktualni == null) {
            throw new KolekceException();
        } else {
            if (aktualni == posledni) {
                return null;
            } else {
                return aktualni.dalsi.data;
            }
        }
    }

    @Override
    public E odeberAktualni() throws KolekceException {
        if (prvni == null || aktualni == null) {
            throw new KolekceException();
        } else {
            Prvek<E> tmp = aktualni;
            Prvek<E> tmp2 = prvni;
            if (velikost == 1) {
                zrus();
                return tmp2.data;
            }
            if (prvni != aktualni) {
                while (tmp2.dalsi != aktualni) {
                    tmp2 = tmp2.dalsi;
                }
            }else{
                prvni = prvni.dalsi;
            }
            if (posledni == aktualni) {
                tmp2.dalsi = null;
                posledni = tmp2;
            } else {
                tmp2.dalsi = aktualni.dalsi;
            }
            aktualni = null;
            velikost--;
            return tmp.data;
        }
    }

    @Override
    public E odeberZaAktualnim() throws KolekceException {
        if (prvni == null || aktualni == null || aktualni.dalsi == null) {
            throw new KolekceException();
        } else {
            Prvek<E> tmp;
            if (aktualni.dalsi == posledni) {
                tmp = aktualni.dalsi;
                posledni = aktualni;
                aktualni.dalsi = null;
                velikost--;
                return tmp.data;
            } else if (velikost == 1) {
                return null;
            } else if (aktualni == posledni) {
                return null;
            } else {
                tmp = aktualni.dalsi;
                aktualni.dalsi = tmp.dalsi;
                velikost--;
                return tmp.data;
            }
        }
    }

    @Override
    public int size() {
        return velikost;
    }

    @Override
    public void zrus() {
        prvni = null;
        aktualni = null;
        posledni = null;
        velikost = 0;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iteration = new Iterator<E>() {

            Prvek<E> current = prvni;
            int i = 0;

            @Override
            public boolean hasNext() {
                if (i < velikost) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public E next() {
                Prvek<E> tmp = current;
                current = current.dalsi;
                i++;
                return tmp.data;
            }
        };
        return iteration;
    }

    private class Prvek<E> {

        E data;
        Prvek<E> dalsi;

        public Prvek(E novy) throws NullPointerException {
            if (novy == null) {
                throw new NullPointerException();
            }
            data = novy;
            dalsi = null;
        }

    }
}
