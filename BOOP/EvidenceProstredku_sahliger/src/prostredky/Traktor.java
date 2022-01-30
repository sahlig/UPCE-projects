
package prostredky;

public class Traktor extends DopravniProstredek {
    private Integer tah;
    

    public Traktor(Integer tah, float hmotnost, String pozn) {
        super(ProstredekTyp.TRAKTOR, pozn, hmotnost);
        this.tah = tah;
    }
    @Override
    public void setTah(Integer tahej){
        this.tah = tahej;
    }
    @Override
    public int getTah(){
        return this.tah;
    }
    
    @Override
    public String toString(){
        return super.getId() + "    " + super.getTyp() + " váží " + super.getHmotnost() + "kg  a má tah " + this.getTah() + " kg s poznávacím číslem " + super.getPozn();
    }
}
