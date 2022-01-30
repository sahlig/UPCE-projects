
package prostredky;

public enum MojeBarva {
    BILA("bílá"),
    CERNA("černá"),
    CERVENA("červená"),
    ZELENA("zelená"),
    MODRA("modrá");
    
    private String nazev;
    
    private MojeBarva(String nazev){
        this.nazev = nazev;
    }


    @Override
    public String toString() {
        return nazev;
    }
    
    public static MojeBarva decode(String barva){
        switch(barva){
            case "bílá":
                return BILA;
            case "černá":
                return CERNA;
            case "červená":
                return CERVENA;
            case "zelená":
                return ZELENA;
            case "modrá":
                return MODRA;
            default:
                break;
        }
        return null;
    }
    
}
