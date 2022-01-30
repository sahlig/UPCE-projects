package prostredky;

/**
 *
 * @author karel@simerda.cz
 */
public class DopravniProstredekKlic extends DopravniProstredek {

    public DopravniProstredekKlic(int id, String spz) {
        super(ProstredekTyp.KLIC, id, spz);
    }

    public DopravniProstredekKlic(String spz) {
        super(ProstredekTyp.KLIC, 0, spz);
    }

    public DopravniProstredekKlic(int id) {
        super(ProstredekTyp.KLIC, id, "");
    }
}
