package cv4;

import java.util.Scanner;

public class Cv4 {
    private static int MAX_VOZIK = 200;
    private static int KRABICE_VAHA = 30;
    public static void main(String[] args) {
        
        Scanner zadKra = new Scanner(System.in);
        int vozikVaha = 0;
        int konec = 0;
        int kolikaty = 0;
        
        System.out.print("Kolik krabic bude vyskladneno: ");
        int krabice = zadKra.nextInt();
        System.out.println();
        
        while(true){
            vozikVaha = 0;
            while(krabice > 0 && (vozikVaha + KRABICE_VAHA) <= MAX_VOZIK){
                vozikVaha = vozikVaha + KRABICE_VAHA;
                krabice--;
            }
            kolikaty++;
            System.out.println("Odjizdi " + kolikaty + ". vozik a vazi " + vozikVaha + " kg.");
            if(krabice == 0){
                System.out.println("Konec, vyskladneno.");
                break;
            }
        }
        
    }
}
