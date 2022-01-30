package prostredky;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DopravniProstredek implements Serializable, Cloneable{
    private int id;
    private String poznavaciCislo;
    private ProstredekTyp typ;
    private float hmotnost;

    public DopravniProstredek(ProstredekTyp typ, String poznavaciCislo, float hmotnost){
        this.hmotnost = hmotnost;
        this.poznavaciCislo = poznavaciCislo;
        this.typ = typ;
    }
    
    public DopravniProstredek(ProstredekTyp typ, int id, String spz){
        this.id = id;
        this.poznavaciCislo = spz;
        this.typ = typ;
    }
    public DopravniProstredek(ProstredekTyp typ, int id){
        this.id = id;
        this.typ = typ;
    }
    public DopravniProstredek(ProstredekTyp typ, String spz){
        this.poznavaciCislo = spz;
        this.typ = typ;
    }
    
    public void setPozn(String pozn){
        this.poznavaciCislo = pozn;
    }
    
    public String getPozn(){
        return this.poznavaciCislo;
    }

    public ProstredekTyp getTyp() {
        return this.typ;
    }

    public void setTyp(ProstredekTyp typ) {
        this.typ = typ;
    }

    public float getHmotnost() {
        return hmotnost;
    }

    public void setHmotnost(float hmotnost) {
        this.hmotnost = hmotnost;
    }
    
    @Override
    public String toString(){
        return "Nefunguje";
    }
    
    public void setPocet(Integer pocet){
        
    }
    
    public int getPocet(){
        return 0;
    }
    
    public void setBarva(MojeBarva barva){
        
    }
    
    public MojeBarva getBarva(){
        return null;
    }
    
    public void setPocetN(Integer pocet){
        
    }
    
    public int getPocetN(){
        return 0;
    }
    
    public void setNosnost(long nosnost){
        
    }
    
    public long getNosnost(){
        return 0;
    }
    
    public void setTah(Integer tahej){
        
    }
    
    public int getTah(){
        return 0;
    }
    
    public void setDTyp(DodavkaTyp dTyp){
        
    }
    
    public DodavkaTyp getDTyp(){
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public DopravniProstredek clone(){
        try {
            return (DopravniProstredek) super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(DopravniProstredek.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
}
