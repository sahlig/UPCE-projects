package cv8;

import java.util.Scanner;

public class Sort {
 
    int[] pole;
    Scanner zadInt = new Scanner(System.in);
    
    public Sort(){
        int kolik;
        System.out.println("Zadejte velikost pole");
        kolik = zadInt.nextInt();
        pole = new int[kolik];
    }
    
    public void napln(){
        int min, max;
        System.out.println("Zadejte minimalni hodnotu prvku.");
        min = zadInt.nextInt();
        System.out.println("Zadejte maximalni hodnotu prvku.");
        max = zadInt.nextInt();
        for(int i = 0; i < pole.length; i++){
            pole[i] = genCel(min, max);
        }
    }
    
    public void vypis(){
        for(int i = 0; i < pole.length; i++){
            System.out.print(pole[i] + "  ");
        }
        System.out.println("");
        System.out.println("");
    }
    
    public void sortMe(){
        int temp = 0;
        int kont = 1;
        
        while(kont != 0){
        kont = 0;
        for(int i = 0; i < pole.length - 1; i++){
            if(pole[i] > pole[i+1]){
                temp = pole[i+1];
                pole[i+1] = pole[i];
                pole[i] = temp;
                kont++;
            }
        }
    }}
    
    
    
    private int genCel(int min, int max){
        return (int) Math.round(Math.random() * (max - min) + min);
    }
}
