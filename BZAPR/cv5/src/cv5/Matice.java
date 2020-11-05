package cv5;
public class Matice{
    int[][] maticeA;
    int[][] maticeVys;
    
    public Matice(){
        maticeA = new int[2][2];
        napln(maticeA, 0, 10);
        vypisMat(maticeA);
    }
    public Matice(int x, int y){
        maticeA = new int[x][y];
        napln(maticeA, 0, 10);
        vypisMat(maticeA);
    }
    public Matice(int x, int y, int a, int b){
        maticeA = new int[x][y];
        napln(maticeA, a, b);
        vypisMat(maticeA);
    }
    
    
    public void nasobeniKon(int kons){
        for(int i = 0; i < maticeA.length; i++){
            for(int j = 0; j < maticeA[i].length; j++){
                maticeA[i][j] = maticeA[i][j] * kons;
            }
        }
        vypisMat(maticeA);
    }
    
    public void scitaniMat(int[][] matice2){
        if(maticeA.length == matice2.length){
            if(maticeA[0].length == matice2[0].length){
                for(int i = 0; i < maticeA.length; i++){
                    for(int j = 0; j < maticeA[i].length; j++){
                        maticeA[i][j] = maticeA[i][j] + matice2[i][j];
                    }
                }
                vypisMat(maticeA);
            }else{
                System.out.println("Matice musi byt stejne velke!");
            }
        }else{
            System.out.println("Matice musi byt stejne velke!");
        }
    }
    
    public void odecitaniMat(int[][] matice2){
        if(maticeA.length == matice2.length){
            if(maticeA[0].length == matice2[0].length){
                for(int i = 0; i < maticeA.length; i++){
                    for(int j = 0; j < maticeA[i].length; j++){
                        maticeA[i][j] = maticeA[i][j] - matice2[i][j];
                    }
                }
                vypisMat(maticeA);
            }else{
                System.out.println("Matice musi byt stejne velke!");
            }
        }else{
            System.out.println("Matice musi byt stejne velke!");
        }
    }
    
    public void nasobeniMat(int[][] matice2){
        if(maticeA[0].length == matice2.length){
                    maticeVys = new int[maticeA.length][matice2[0].length];
            for (int i = 0; i < maticeA.length; i++) {
            for (int j = 0; j < matice2[i].length; j++) {
                int pom = 0;
                for (int s = 0; s < matice2.length; s++) {
                    pom += (maticeA[i][s] * matice2[s][j]);
                }
                maticeVys[i][j] = pom;
            }
        }
            vypisMat(maticeVys);
        }else{
            System.out.println("Matice nemaji pozadovanou velikost!");
        }
    }
    //-----------------Pomocne fce --------------------------------------//
    
    static void vypisMat(int[][] matice){
        System.out.println("Matice");
        for (int[] pole : matice) {
            for (int prvek : pole) {
                System.out.print(prvek + " ");
            }
            System.out.println();
        }
    }
    static int genCel(int min, int max){
        return (int) Math.round(Math.random() * (max - min) + min);
    }
    static void napln(int[][] matice, int min, int max){
        for(int i = 0; i < matice.length; i++){
            for(int j = 0; j < matice[i].length; j++){
                matice[i][j] = genCel(min, max);
            }
        }
    }
}
