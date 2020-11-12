package cv7;
public class Matice {
    int[][] matice;
    
    public Matice(int velikost){
        matice = new int[velikost][velikost];
        napln(matice, 10, 50);
    }
    
    public Matice(Matice maticeN){
        matice = new int[maticeN.matice.length][maticeN.matice.length];
        for(int i = 0; i < maticeN.matice.length; i++){
            for(int j = 0; j < maticeN.matice.length; j++){
                matice[i][j] = maticeN.matice[i][j];
            }
        }
    }
    
    public Matice vymSloup(int s1, int s2){
        int pom;
        Matice maticeN = new Matice(this);
        for(int i = 0; i < matice.length; i++){
            pom = maticeN.matice[i][s1];
            maticeN.matice[i][s1] = maticeN.matice[i][s2];
            maticeN.matice[i][s2] = pom;
        }
        return maticeN;
    }
    
    public Matice vymDiag(){
        int pom;
        Matice maticeN = new Matice(this);
        for(int i = 0; i < matice.length; i++){
            for(int j = i + 1; j < matice.length; j++){
                pom = maticeN.matice[i][j];
                maticeN.matice[i][j] = maticeN.matice[j][i];
                maticeN.matice[j][i] = pom;
            }
        }
        return maticeN;
    }
    
    public Matice vynDiag(){
        Matice maticeN = new Matice(this);
        for(int i = 1; i < matice.length; i++){
            for(int j = 0; j < i; j++){
                maticeN.matice[i][j] = 0;
            }
        }
        return maticeN;
    }
    
    
    int genCel(int min, int max){
        return (int) Math.round(Math.random() * (max - min) + min);
    }
    void napln(int[][] maticeN, int min, int max){
        for(int i = 0; i < maticeN.length; i++){
            for(int j = 0; j < maticeN[i].length; j++){
                maticeN[i][j] = genCel(min, max);
            }
        }
    }
}
