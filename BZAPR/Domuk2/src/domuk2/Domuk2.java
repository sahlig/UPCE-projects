
package domuk2;

import java.util.Scanner;

public class Domuk2 {

    public static void main(String[] args) {
    Scanner zadInt = new Scanner(System.in);

    /*int min, max, del;
    min = max = del = 0;
    min = zadInt.nextInt();
    max = zadInt.nextInt();
    del = zadInt.nextInt();
    
    for(int i = min; i <= max; i++){
        if((i%del) == 0){
            System.out.print(i);
            if((i%del == 0) && (i + del <= max)){
                System.out.print(", ");
        }
    }
    }
        System.out.println();*/
    
    

   /* int min, max;
    min = zadInt.nextInt();
    max = zadInt.nextInt();
    
    for(int i = 0; i < max+1; i++){
        for(int j = 0; j < (max - min)+1; j++){
            if(i == 0 && j == 0){
                System.out.print("  ");
            }else if(i == 0 && j != 0){
                    System.out.print(j + " ");
            }else if(i != 0 && j == 0){
                System.out.print(i + " ");
            }else{
                System.out.print(i*j + " ");
            }
            
        }
        System.out.println("");
        if(i == 0 && min != 0){
            i = min-1;
        }
    }*/


       /* int rad;
        rad = zadInt.nextInt();
        for (int i = 0; i < rad; i++) {
            for (int j = 0; j < rad-i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i+1; j++) {
                System.out.print("*");
                if(j == i && j > 0){
                    for(int k = 0; k < i; k++){
                        System.out.print("*");
                    }
                }
            }
            System.out.println();
        }*/


        /*int a, b, c;
        a = zadInt.nextInt();
        b = zadInt.nextInt();
        while(b != 0){
            c = a%b;
            a = b;
            b = c;
        }
        System.out.println(a + " Je nejvetsi spolecny delitel.");*/

    }
    
}
