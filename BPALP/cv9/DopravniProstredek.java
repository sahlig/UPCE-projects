package cv9;

public class DopravniProstredek {
    private String poznavaciCislo;

    public DopravniProstredek(String poznavaciCislo) {
        this.poznavaciCislo = poznavaciCislo;
    }
    
    public void setPozn(String pozn){
        this.poznavaciCislo = pozn;
    }
    
    public String getPozn(){
        return this.poznavaciCislo;
    }
}
