package seznam;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Radek
 */
public interface IAbstrDoubleList<T> extends Iterable<T>{
    
    void zrus();
    boolean jePrazdny();
    int getVelikost();
    
    void vlozPrvni(T data);
    void vlozPosledni(T data);
    void vlozNaslednika(T data) throws Exception;
    void vlozPredchudce(T data) throws Exception;
    
    T zpristupniAktualni() throws Exception;
    T zpristupniPrvni() throws Exception;
    T zpristupniPosledni() throws Exception;
    T zpristupniNaslednika() throws Exception;
    T zpristupniPredchudce() throws Exception;
    
    T odeberAktualni() throws Exception;
    T odeberPrvni() throws Exception;
    T odeberPosledni() throws Exception;
    T odeberNaslednika() throws Exception;
    T odeberPredchudce() throws Exception;
    
}
