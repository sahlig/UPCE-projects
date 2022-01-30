/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekce;

/**
 *
 * @author Radek
 */
public class KolekceException extends Exception {
    
    public KolekceException(){
        super();
    }
    
    public KolekceException(String zprava){
        super(zprava);
    }
}
