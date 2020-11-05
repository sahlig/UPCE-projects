package cv5;
public class Main {
    public static void main(String[] args) {
        Matice moje1 = new Matice();
        Matice moje2 = new Matice(5, 6);
        Matice moje3 = new Matice(5, 6);
        Matice moje4 = new Matice(6, 8, 5, 60);
        Matice moje5 = new Matice(20, 6);
        
        moje1.nasobeniKon(5);
        System.out.println();
        moje2.scitaniMat(moje3.maticeA);
        System.out.println();
        moje2.odecitaniMat(moje3.maticeA);
        System.out.println();
        moje2.scitaniMat(moje4.maticeA);
        System.out.println();
        moje2.nasobeniMat(moje4.maticeA);
        System.out.println();
        moje2.nasobeniMat(moje5.maticeA);
    }
}
