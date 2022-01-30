/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.Scanner;

/**
 *
 * @author Radek
 */
public class Keyboard {
    private static Scanner klavesnice = new Scanner(System.in);
    
    
    public static String cekej(String prompt){
        System.out.print(prompt);
        String odpoved = klavesnice.next();
        return odpoved;
    }
}
