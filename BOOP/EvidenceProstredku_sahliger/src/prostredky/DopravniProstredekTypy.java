
package prostredky;

public enum DopravniProstredekTypy {
    OSOBNI("osobní automobil"),
    NAKLADNI("nákladní automobil"),
    TRAKTOR("traktor"),
    DODAVKA("dodávka");
    
    private String nazev;
    
    private DopravniProstredekTypy(String nazev){
     this.nazev = nazev;   
    }

    @Override
    public String toString() {
       return this.nazev;
    }
    
    
}
