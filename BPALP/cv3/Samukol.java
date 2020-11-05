
package samukol;

import java.util.Scanner;

public class Samukol {

    public static void main(String[] args) {
        Scanner zadDoub = new Scanner(System.in);
        double a, b, c;
        
        System.out.println("Zadejte prvni stranu");
        a = zadDoub.nextDouble();
        System.out.println("Zadejte druhou stranu");
        b = zadDoub.nextDouble();
        System.out.println("Zadejte treti stranu");
        c = zadDoub.nextDouble();
        
        System.out.println();
        System.out.println();
        
        if(a > 0 && b > 0 && c > 0){
            if((a+b) > c){
                if((b+c) > a){
                    if((a+c) > b){
                        System.out.println("Obvod trojhuelniku je " + (a+b+c));
                    }else{
                        System.out.println("Trojulenik neni sestrojitelny.");
                    }
                }else{
                    System.out.println("Trojulenik neni sestrojitelny.");
                }
            }else{
                System.out.println("Trojulenik neni sestrojitelny.");
            }
        }else{
            System.out.println("Trojuhelnik neni sestrojitelny.");
        }
    }
    
}
