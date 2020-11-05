package cv5;

import java.util.Scanner;

public class Cv5 {
    public static void main(String[] args) {
        Scanner zadCis = new Scanner(System.in);
        
        int velikostPole = genCel(25, 50);
        int[] polePuvodni = new int[velikostPole];
        int dolniHranice = genCel(-100, -50);
        int horniHranice = genCel(50, 100);
        
        for(int i = 0; i < polePuvodni.length; i++){
            polePuvodni[i] = genCel(dolniHranice, horniHranice);
            System.out.print(polePuvodni[i] + " ");
        }
        System.out.println();
        
        int[] poleNove = polePuvodni;
        System.out.println("Zadejte cislo.");
        int poziceZmeny = zadCis.nextInt();
        
        for (int i = 0; i < poleNove.length; i++) {
            if(poleNove[i] % poziceZmeny == 0){
                poleNove[i] = 0;
            }
        }
        
        for(int vypis : poleNove){
            System.out.print(vypis + " ");
        }
    }
    static int genCel(int min, int max){
        return (int) Math.round(Math.random() * (max - min) + min);
    }
}
