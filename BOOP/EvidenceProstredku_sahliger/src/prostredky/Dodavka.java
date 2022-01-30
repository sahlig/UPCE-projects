package prostredky;

public class Dodavka extends DopravniProstredek {
    private DodavkaTyp dTyp;
    

    public Dodavka(DodavkaTyp dTyp, float hmotnost, String pozn) {
        super(ProstredekTyp.DODAVKA, pozn, hmotnost);
        this.dTyp = dTyp;
    }
    @Override
    public void setDTyp(DodavkaTyp dTyp){
        this.dTyp = dTyp;
    }
    @Override
    public DodavkaTyp getDTyp(){
        return this.dTyp;
    }
    
    @Override
    public String toString(){
        return super.getId() + "    " + super.getTyp() + " váží " + super.getHmotnost() + "kg a je typu " + this.getDTyp() + " s poznávacím číslem " + super.getPozn();
    }
}
