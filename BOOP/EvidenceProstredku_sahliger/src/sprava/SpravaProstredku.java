
package sprava;
import perzistence.Perzistence;
import generator.Generator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import kolekce.KolekceException;
import kolekce.Seznam;
import kolekce.LinSeznam;
import prostredky.Dodavka;
import prostredky.DodavkaTyp;
import prostredky.DopravniProstredek;
import prostredky.DopravniProstredekKlic;
import prostredky.MojeBarva;
import prostredky.NakladniAutomobil;
import prostredky.OsobniAutomobil;
import prostredky.Traktor;
/**
 *
 * @author Radek
 */
public class SpravaProstredku implements Ovladani{
   private int idcount = 0;
   private Seznam<DopravniProstredek> seznam;
   private Comparator<? super DopravniProstredek> komparator;

    @Override
    public void vytvorSeznam(Supplier<Seznam<DopravniProstredek>> supplier) {
        seznam = supplier.get();
    }

    @Override
    public void vytvorSeznam(Function<Integer, Seznam<DopravniProstredek>> function, int size) throws KolekceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nastavKomparator(Comparator<? super DopravniProstredek> comparator) {
        komparator = comparator;
    }

    @Override
    public void vlozPolozku(DopravniProstredek data) throws NullPointerException {
        data.setId(idcount);
        seznam.vlozNaKonec(data);
        idcount++;
    }

    @Override
    public DopravniProstredek najdiPolozku(DopravniProstredekKlic klic) {
        Iterator<DopravniProstredek> ite = seznam.iterator();
        DopravniProstredek tmp;
        while(ite.hasNext()){
            tmp = ite.next();
            if(komparator.compare(klic, tmp) == 0){
            return tmp;
        }
        }
        return null;
    }

    @Override
    public void prejdiNaPrvniPolozku() {
       try {
           seznam.nastavPrvni();
       } catch (KolekceException ex) {
           nastavErrorLog(System.err::println);
       }
    }

    @Override
    public void prejdiNaPosledniPolozku() {
       try {
           seznam.nastavPosledni();
       } catch (KolekceException ex) {
           nastavErrorLog(System.err::println);
       }
    }

    @Override
    public void prejdiNaDalsiPolozku() {
       try {
           seznam.dalsi();
       } catch (KolekceException ex) {
           nastavErrorLog(System.err::println);
       }
    }

    @Override
    public void prejdiNaPredchoziPolozku() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DopravniProstredek nastavAktualniPolozku(DopravniProstredekKlic klic) {
        try{
        Iterator<DopravniProstredek> it = seznam.iterator();
           seznam.nastavPrvni();
           while(it.hasNext()){
               DopravniProstredek tmp = it.next();
               if(komparator.compare(klic, tmp) != 0){
                   seznam.dalsi();
               }else{
                   return it.next();
               }
           }
           return null;
        }catch(KolekceException ex){
        nastavErrorLog(System.err::println);
        return null;
    }
    }

    @Override
    public DopravniProstredek vyjmiAktualniPolozku() {
       try {
          DopravniProstredek tmp = seznam.odeberAktualni();
          return tmp;
       } catch (KolekceException ex) {
           nastavErrorLog(System.err::println);
           return null;
       }
    }

    @Override
    public DopravniProstredek dejKopiiAktualniPolozky() {
       try {
           return seznam.dejAktualni().clone();
       } catch (KolekceException ex) {
           nastavErrorLog(System.err::println);
       }
       return null;
    }

    @Override
    public void editujAktualniPolozku(Function<DopravniProstredek, DopravniProstredek> editor) {
       try {
           editor.apply(seznam.dejAktualni());
       } catch (KolekceException ex) {
           nastavErrorLog(System.err::println);
       }
    }

    @Override
    public void ulozDoSouboru(String soubor) {
       try {
           Perzistence.zalohujTo(soubor, seznam);
       } catch (IOException ex) {
           nastavErrorLog(System.err::println);
       }
    }

    @Override
    public void nactiZeSouboru(String soubor) {
       try {
           idcount = 0;
           seznam = Perzistence.obnovTo(soubor, seznam);
           Iterator<DopravniProstredek> it = seznam.iterator();
           while(it.hasNext()){
               it.next().setId(idcount);
               idcount++;
           }
       } catch (IOException ex) {
           nastavErrorLog(System.err::println);
       }
    }

    @Override
    public void vypis(Consumer<DopravniProstredek> writer) {
        Iterator<DopravniProstredek> it = seznam.iterator();
        while(it.hasNext()){
            writer.accept(it.next());
        }
    }

    @Override
    public void nactiTextSoubor(String soubor, Function<String, DopravniProstredek> mapper) {
        seznam.zrus();
        idcount = 0;
        Iterator<DopravniProstredek> ite;
       try {
           ite = perzistence.Perzistence.readStream(soubor, mapper).iterator();
           while(ite.hasNext()){
           seznam.vlozNaKonec(ite.next());
           }
       } catch (IOException ex) {
           nastavErrorLog(System.err::println);
        }
       ite = seznam.iterator();
       while(ite.hasNext()){
           ite.next().setId(idcount);
           idcount++;
       }
    }

    @Override
    public void ulozTextSoubor(String soubor, Function<DopravniProstredek, String> mapper) {
       try {
           perzistence.Perzistence.writeStream(seznam.stream(), soubor, mapper);
       } catch (FileNotFoundException ex) {
           nastavErrorLog(System.err::println);
       } catch (UnsupportedEncodingException ex) {
           nastavErrorLog(System.err::println);
       }
    }

    @Override
    public void generujData(int pocetProstredku) {
        for (int i = 0; i < pocetProstredku; i++) {
            DopravniProstredek tmp = Generator.generujJeden();
            tmp.setId(idcount);
            seznam.vlozNaKonec(tmp);
            idcount++;
            
        }
    }

    @Override
    public int dejAktualniPocetPolozek() {
        return seznam.size();
    }

    @Override
    public void zrus() {
        idcount = 0;
        seznam.zrus();
    }

    @Override
    public Iterator<DopravniProstredek> iterator() {
        return seznam.iterator();
    }
    
    
    
}
