package cv6;

import java.util.Scanner;

public class Cv6 {
    public static void main(String[] args) {
        Scanner zadBoard = new Scanner(System.in);
        System.out.println("Zadejte velikost pole.");
        int vel = zadBoard.nextInt();
        System.out.println("Zadejte pocet lodi.");
        int pocetLodi = zadBoard.nextInt();
        
        int lode = 0;
        int[][] board = new int[vel][vel];
        
        for(int i = 0; i < pocetLodi;){
            int x = (int) Math.round(Math.random()*((vel - 1) - 1)) + 1;
            int y = (int) Math.round(Math.random()*((vel - 1) - 1)) + 1;
            if(board[x][y] == 0){
                board[x][y] = 1;
                i++;
            }
        }
        
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == 1){
                    System.out.print("X");
                }else{
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }
    
}
