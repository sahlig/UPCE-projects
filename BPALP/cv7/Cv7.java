package cv7;

import java.util.Scanner;

public class Cv7 {
    public static void main(String[] args) {
        Scanner zadInt = new Scanner(System.in);
        
        System.out.println("Zadejte velikost matice");
        int velikost = zadInt.nextInt();
        
        Matice mojeMat = new Matice(velikost);
        
        int kon = 0;
        while(kon == 0){
            System.out.println("Zadejte cislo pro vykonani funkce.");
            System.out.println("1: Vymena dvou sloupcu matice");
            System.out.println("2: Vymena hodnot nad/pod diagonalou");
            System.out.println("3: Vynulovani hodnot pod diagonalou");
            System.out.println("4: Ukonceni programu");
            int prog = zadInt.nextInt();
            
        switch(prog){
            case 1: 
                System.out.println("Zadejte cisla sloupcu");
                int sloup1 = zadInt.nextInt();
                int sloup2 = zadInt.nextInt();
                Matice mat2 = mojeMat.vymSloup(sloup1, sloup2);
                vypisMatici(mojeMat);
                System.out.println("");
                vypisMatici(mat2);
                break;
            case 2:
                Matice mat3 = mojeMat.vymDiag();
                vypisMatici(mojeMat);
                System.out.println("");
                vypisMatici(mat3);
                break;
            case 3:
                Matice mat4 = mojeMat.vynDiag();
                vypisMatici(mojeMat);
                System.out.println("");
                vypisMatici(mat4);
                break;
            case 4:
                kon++;
                break;
            default:
                System.out.println("Prosim zadejte platnou hodnotu");
        }
        }
    }
    
    public static void vypisMatici(Matice mat){
    for(int i = 0; i < mat.matice.length; i++){
        for(int j = 0; j < mat.matice.length; j++){
            System.out.print(mat.matice[i][j] + " ");
        }
        System.out.println("");
    }
}
}
