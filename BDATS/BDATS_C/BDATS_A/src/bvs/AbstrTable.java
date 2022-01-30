/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bvs;

import java.io.Serializable;
import java.util.Iterator;
import pujcovna.eTypProhl;

/**
 *
 * @author Radek
 */
public class AbstrTable<K extends Comparable<K>, V> implements IAbstrTable<K, V>, Serializable{

    
    private Prvek root;
    private int velikost;
            
    private class Prvek<K, V> implements Serializable{

        K key;
        V value;
        Prvek<K, V> parent;
        Prvek<K, V> left;
        Prvek<K, V> right;

        public Prvek(K key, V value) throws NullPointerException{
            if(key == null || value == null) {
                throw new NullPointerException();
            }
            this.key = key;
            this.value = value;
            parent = null;
            left = null;
            right = null;
        }

    }
    
    public AbstrTable(){
        root = null;
        velikost = 0;
    }
    
    @Override
    public void zrus() {
        root = null;
        velikost = 0;
    }

    @Override
    public boolean jePrazdny() {
        return root == null;
    }

    @Override
    public V najdi(K key) throws Exception{
        if(key == null){
            throw new Exception();
        }
        Prvek<K, V> temp = root;
        while (temp != null){
            if(key.compareTo(temp.key) == 0){
                return temp.value;
            }else if(key.compareTo(temp.key) < 0){
                temp = temp.left;
            }else if(key.compareTo(temp.key) > 0){
                temp = temp.right;
            }
        }
        return null;
    }

    @Override
    public void vloz(K key, V value) throws Exception{
        if(key == null || value == null){
            throw new Exception();
        }
        Prvek novy = new Prvek(key, value);
        if(jePrazdny()){
            root = novy;
        }else{
            rekurzVloz(root, novy);
        }
    }

    @Override
    public V odeber(K key) throws Exception{
        if(key == null){
            throw new Exception();
        }
        
        if(root != null && root.left == null && root.right == null){
            Prvek<K, V> temp = root;
            zrus();
            return temp.value;
        }else{
        Prvek<K, V> vrat = rekurzOdeber(root, key);
        return vrat.value;
        }
    }

    private class sirkaIterator implements Iterator<V>{
        
        private IAbstrFIFO<Prvek> fronta;
        private Prvek<K, V> curr;

        public sirkaIterator(){
            fronta = new AbstrFIFO<Prvek>();
            if(root != null){
            fronta.vloz(root);
            }
        }
        
        @Override
        public boolean hasNext() {
            return !fronta.jePrazdny();
        }

        @Override
        public V next() {
            curr = fronta.odeber();
            if(curr.left != null){
                fronta.vloz(curr.left);
            }
            if(curr.right != null){
                fronta.vloz(curr.right);
            }
            return curr.value;
        }
        
    }
    
    private class hloubkaIterator implements Iterator<V>{
        
        private IAbstrLIFO<Prvek> zasobnik;
        private Prvek<K, V> curr;

        public hloubkaIterator(){
            zasobnik = new AbstrLIFO<Prvek>();
            if(root != null){
            zasobnik.vloz(root);
            }
            
        }
        
        @Override
        public boolean hasNext() {
            return !zasobnik.jePrazdny();
        }

        @Override
        public V next() {
            curr = zasobnik.odeber();
            if(curr.right != null){
                zasobnik.vloz(curr.right);
            }
            if(curr.left != null){
                zasobnik.vloz(curr.left);
            }
            return curr.value;
        }
        
    }

    @Override
    public Iterator vytvorIterator(eTypProhl typ) {
        switch(typ){
            case HLOUBKA:
                return new hloubkaIterator();
            case SIRKA:
                return new sirkaIterator();
            default:
                return null;
        }
    }
    
    private void rekurzVloz(Prvek<K, V> odkud, Prvek<K, V> novy){
        if(novy.key.compareTo(odkud.key) < 0){
            if(odkud.left == null){
                odkud.left = novy;
                novy.parent = odkud;
            }else{
                rekurzVloz(odkud.left, novy);
            }
        }else if(novy.key.compareTo(odkud.key) > 0){
            if(odkud.right == null){
                odkud.right = novy;
                novy.parent = odkud;
            }else{
                rekurzVloz(odkud.right, novy);
            }
        }
    }
    
    
    private Prvek<K, V> rekurzOdeber(Prvek<K, V> odkud, K key){
        Prvek<K, V> temp = odkud;
        if(odkud != null){
        if(key.compareTo(odkud.key) == 0){
            if(odkud.left != null){
                temp = levoPravo(odkud);
                temp.left = null;
                temp.right = null;
                temp.parent = null;
                return temp;
            }else if(odkud.right != null && odkud.left == null){
                temp = pravoLevo(odkud);
                temp.left = null;
                temp.right = null;
                temp.parent = null;
                return temp;
            }else if(odkud.left == null && odkud.right == null){
                if(temp.key.compareTo(temp.parent.key) < 0){
                    temp.parent.left = null;
                }else if(temp.key.compareTo(temp.parent.key) > 0){
                    temp.parent.right = null;
                }
                temp.parent = null;
                return temp;
            }
        }else if(key.compareTo(odkud.key) < 0){
            return rekurzOdeber(odkud.left, key);
        }else if(key.compareTo(odkud.key) > 0){
            return rekurzOdeber(odkud.right, key);
        }
        return odkud;
    }
    return odkud;
    }
    
    private Prvek<K, V> levoPravo(Prvek<K, V> co){
        Prvek temp = co.left;
        if(temp.right == null){
            temp.parent = co.parent;
            if(co == root){
                root = temp;
            }else{
            if(co.key.compareTo(co.parent.key) < 0){
                co.parent.left = temp;
            }else if(co.key.compareTo(co.parent.key) > 0){
                co.parent.right = temp;
            }
            }
            temp.right = co.right;
            if(co.right != null){
            co.right.parent = temp;
            }
        }else{
            while(temp.right != null){
                temp = temp.right;
            }
            if(temp.left != null){
                temp.parent.right = temp.left;
                temp.left.parent = temp.parent;
            }else{
                temp.parent.right = null;
            temp.parent = co.parent;
            if(co == root){
                root = temp;
                temp.left = co.left;
                if(co.left != null){
                 temp.left.parent = temp;   
                }
                temp.right = co.right;
                if(co.right != null){
                    co.right.parent = temp;
                }
            }else{
            if(co.key.compareTo(co.parent.key) < 0){
                co.parent.left = temp;
            }else if(co.key.compareTo(co.parent.key) > 0){
                co.parent.right = temp;
            }
            }
            temp.right = co.right;
            if(co.right != null){
            co.right.parent = temp;
            }
            temp.left = co.left;
            if(co.left != null){
                co.left.parent = temp;
            }
            }
        }
        return co;
    }
    
    private Prvek<K, V> pravoLevo(Prvek<K, V> co){
        Prvek<K, V> temp = co.right;
        if(temp.left == null){
            temp.parent = co.parent;
            if(co == root){
                root = temp;
            }else{
            if(co.key.compareTo(co.parent.key) < 0){
                co.parent.left = temp;
            }else if(co.key.compareTo(co.parent.key) > 0){
                co.parent.right = temp;
            }
            }
            temp.left = co.left;
            if(co.left != null){
            co.left.parent = temp;
            }
        }else{
            while(temp.left != null){
                temp = temp.left;
            }
            if(temp.right != null){
                temp.parent.left = temp.right;
                temp.right.parent = temp.parent;
            }else{
                temp.parent.left = null;
            temp.parent = co.parent;
            if(co == root){
                root = temp;
                temp.right = co.right;
                if(co.right != null){
                temp.right.parent = temp;
                }
                temp.left = co.left;
                if(co.left != null){
                    co.left.parent = temp;
                }
            }else{
            if(co.key.compareTo(co.parent.key) < 0){
                co.parent.left = temp;
            }else if(co.key.compareTo(co.parent.key) > 0){
                co.parent.right = temp;
            }
            }
            temp.left = co.left;
            if(co.left != null){
            co.left.parent = temp;
            }
            temp.right = co.right;
            if(co.right != null){
                co.right.parent = temp;
            }
            }
        }
        return co;
    }
    
}
