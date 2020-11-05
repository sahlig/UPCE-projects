
package domuk;

import java.util.Scanner;

public class Domuk {

    public static void main(String[] args) {

    /*int sek, min, hod, den;
    min = hod = den = 0;

    Scanner zadcas = new Scanner(System.in);
        System.out.print("Zadejte pocet vterin:");
    sek = zadcas.nextInt();
    if ((sek/60) >= 1){
        min = (sek/60);
        sek = (sek - (min*60));
        if((min/60) >= 1){
            hod = (min/60);
            min = (min - (hod*60));
            if((hod/24) >= 1){
                den = (hod/24);
                hod = (hod - (den*24));
            }
        }
    }
        System.out.println(den + " d, " + hod + " h, " + min + " m, " + sek + " s");*/
    


       /* Scanner zadSour = new Scanner(System.in);
        double aX, aY, bX, bY = 0.0;
        
        System.out.print("Zadejte aX: ");
        aX = zadSour.nextDouble();
        System.out.print("Zadejte aY: ");
        aY = zadSour.nextDouble();
        System.out.print("Zadejte bX: ");
        bX = zadSour.nextDouble();
        System.out.print("Zadejte bY: ");
        bY = zadSour.nextDouble();
        
        double vz = Math.sqrt(((bX - aX)*(bX - aX)) + ((bY - aY)*(bY - aY)));
        vz = Math.round(vz*1000)/1000d;
        
        System.out.println("Vysledna vzdalenost je " + vz);*/


        /*double a, b, c = 0.0;
        double[] koreny = {0.0, 0.0};
        Scanner zadBod = new Scanner(System.in);
        System.out.print("Zadejte prvni promennou: ");
        a = zadBod.nextDouble();
        System.out.print("Zadejte druhou promennou: ");
        b = zadBod.nextDouble();
        System.out.print("Zadejte treti promennou: ");
        c = zadBod.nextDouble();
        
        double d = ((b*b) - (4*a*c));
        if(d == 0){
            System.out.println("Rovnice ma jeden koren.");
            koreny[0] = Math.round((-b/2*a)*1000)/1000d;
            System.out.println("Koren rovnice je roven: " + koreny[0]);
        }else if(d > 0){
            System.out.println("Rovnice ma dva koreny");
            koreny[0] = Math.round((-b + Math.sqrt(d)/2*a)*1000)/1000d;
            koreny[1] = Math.round((-b - Math.sqrt(d)/2*a)*1000)/1000d;
            System.out.println("Prvni koren rovnice je roven: " + koreny[0]);
            System.out.println("Druhy koren rovnice je roven: " + koreny[1]);
        }else{
            System.out.println("Rovnice nema koren v oboru realnych cisel.");
        }*/

        
        /*Scanner zadKal = new Scanner(System.in);
        double a, b, vy = 0.0;
        String op;
        System.out.print("Zadejte prvni cislo: ");
        a = zadKal.nextDouble();
        System.out.print("Zadejte operaci: ");
        op = zadKal.next();
        
        if(!op.equals("+") && !op.equals("-") && !op.equals("*") && !op.equals("/")){
            System.out.println("Prosim zadejte platny znak. (+, -, *, /)");
            System.exit(0);
        }
        
        System.out.print("Zadejte druhe cislo: ");
        b = zadKal.nextDouble();
        
        if((b == 0) && (op.equals("/"))){
            System.out.println("Nelze delit nulou");
            System.exit(0);
        }
        
        switch(op){
            case "+": vy = (double) (a + b);
            break;
            case "-": vy = (double) (a - b);
            break;
            case "*": vy = (double) (a * b);
            break;
            case "/": vy = (double) (a / b);
            default: break;
        }
        
        System.out.println("Vypocet " + a + " " +  op + " " + b + " = " + vy);*/
        
        
    }
}
