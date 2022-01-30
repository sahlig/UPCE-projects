package prostredky;

/**
 *
 * @author karel@simerda.cz
 */
public enum ProstredekTyp {
    OSOBNI_AUTOMOBIL("osobní automobil"),
    NAKLADNI_AUTMOBIL("nákladní automobil"),
    DODAVKA("dodávka"),
    TRAKTOR("traktor"),
    TEST("test"),
    KLIC("klíč"),
    NON_FILTER("nefiltruj");

    private final String nazev;

    private ProstredekTyp(String nazev) {
        this.nazev = nazev;
    }

    public String getNazev() {
        return nazev;
    }

    public static Enum[] getProstredky() {
        Enum[] vycet = {OSOBNI_AUTOMOBIL, NAKLADNI_AUTMOBIL, DODAVKA, TRAKTOR};
        return vycet;
    }

    public static Enum[] getProstredkyFilter() {
        Enum[] vycet = {OSOBNI_AUTOMOBIL, NAKLADNI_AUTMOBIL, DODAVKA, TRAKTOR, NON_FILTER};
        return vycet;
    }

    @Override
    public String toString() {
        return nazev;
    }

}
