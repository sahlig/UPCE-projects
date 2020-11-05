package cv6;

import java.util.Scanner;

public class Cv6 {

    public static void main(String[] args) {
        Scanner zadBoard = new Scanner(System.in);
        System.out.println("Zadejte velikost pole.");
        int vel = zadBoard.nextInt();
        System.out.println("Zadejte pocet lodi.");
        int pocetLodi = zadBoard.nextInt();
        System.out.println("Chcete velikost lodi 1 nebo 3?");
        int lode = zadBoard.nextInt();
        
        if (pocetLodi > (vel * vel / lode) || lode >= vel-lode) {
            System.out.println(vel*vel/lode);
            System.out.println("Tato sestava neni z duvodu velikosti pole mozna!");
            System.exit(0);
        }
        System.out.println(vel*vel/lode);
        
        int[][] board = new int[vel][vel];
        if (lode == 1) {            
            for (int i = 0; i < pocetLodi;) {
                int x = (int) Math.round(Math.random() * ((vel - 1)));
                int y = (int) Math.round(Math.random() * ((vel - 1)));
                if (board[x][y] == 0) {
                    board[x][y] = 1;
                    i++;
                }
            }
        } else if (lode == 3) {
            
            for (int i = 0; i < pocetLodi;) {
                int x = (int) Math.round(Math.random() * ((vel - 1)));
                int y = (int) Math.round(Math.random() * ((vel - 1)));
                if (board[x][y] == 0) {
                    if (y == 0 && board[x][y + 1] == 0 && board[x][y + 2] == 0) {
                        board[x][y] = 1;
                        board[x][y + 1] = 1;
                        board[x][y + 2] = 1;
                        i++;
                    } else if (y == (vel - 1) && board[x][y - 1] == 0 && board[x][y - 2] == 0) {
                        board[x][y] = 1;
                        board[x][y - 1] = 1;
                        board[x][y - 2] = 1;
                        i++;
                    } else if (y > 0 && y < (vel - 1) && board[x][y - 1] == 0 && board[x][y + 1] == 0) {
                        board[x][y] = 1;
                        board[x][y - 1] = 1;
                        board[x][y + 1] = 1;
                        i++;
                    }
                }
            }
        } else {
            System.out.println("Prosim zadejte platnou velikost lodi!");
            System.exit(0);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    System.out.print("X");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }
    
}
