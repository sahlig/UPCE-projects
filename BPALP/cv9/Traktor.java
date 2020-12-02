
package cv9;

public class Traktor extends Automobil {
    private int tah;
    

    public Traktor(int tah, int hmotnost, String pozn) {
        super(hmotnost, pozn);
        this.tah = tah;
    }
    
    public void setTah(int tahej){
        this.tah = tahej;
    }
    
    public int getTah(){
        return this.tah;
    }
    
    @Override
    public String toString(){
        return "Traktor vazi " + super.getHmotnost() + "  a tah " + this.getTah() + " s poznavacim cislem " + super.getPozn();
    }
}
