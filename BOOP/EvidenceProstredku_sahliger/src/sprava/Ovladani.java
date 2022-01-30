package sprava;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import kolekce.KolekceException;
import kolekce.Seznam;
import prostredky.DopravniProstredek;
import prostredky.DopravniProstredekKlic;
//import prostredky.Klon;

/**
 * Rozhraní {@code Ovladani} deklaruje celou řadu různých metod, jejichž
 * implementace zajistí celkovou požadovanou funkčnost aplikace.
 * <p>
 * Toto rozhraní je navrženo tak, aby si různá uživatelská rozhraní mohla
 * připojit různé implementace tohoto rozhraní. V našem případě se bude nejříve
 * jednat o uživatelské rozhraní příkazového řádku a poté se budeme věnovat
 * grafickému uživatelskému rozhraní (GUI). A na druhou stranu můžeme v instanci
 * implementační třídy tohoto rozhraní si zvolit implementaci vnitřního seznamu,
 * zda bude na poli nebo zda se bude jednat o spojový seznam.
 * <p>
 * Toto rozhraní je navrženo tak, aby implimentace byla podle návrhového vzoru
 * fasada (viz
 * https://cs.wikipedia.org/wiki/Fas%C3%A1da_(n%C3%A1vrhov%C3%BD_vzor) ). Fasáda
 * sjednocuje ovládání skupiny tříd do jednoho místa.
 * <p>
 * Požadovanou funkčnost fasády je správa seznamu generických objektů/položek
 * těmito skupinami funkcí:
 * <ol>
 * <li> konfigurační funkce </li>
 * <li> funkce přístupu k datovým položkám seznamu a změny v seznamu </li>
 * <li> funkce pohybu po datových položkách v seznamu  </li>
 * <li> funkce uložení stavu datových položek seznamu a jejich obnova </li>
 * <li> skupina s pomocnými nástroji </li>
 * </ol>
 *
 *
 * @author karel@simerda.cz
 *
 *
 * @author karel@simerda.cz
 * @param <E>
 */
public interface Ovladani extends Iterable<DopravniProstredek> {

    
    
    
// ----- konfigurace -----------------------------------------------------------   
    /**
     * Vytvoření objektu seznamu jako komponenty v rámci instanční třídy se
     * správou datových položek. Tím, že se konstrukce vnitřního seznamu provede
     * pomocí zabaleného konstruktoru v parametru supplier se vytvoří seznam
     * zvolené implementace. Tou implementací může být seznam na poli nebo
     * spojový seznam. Takto vytvořený seznam je zcela skryt před ostatními
     * objekty programu.
     * <p>
     * Příklad použití:
     * <pre>{@code
     *   Ovladani<DopravniProstredek> spravce = new SpravaProstredku(); *
     *   spravce.vytvorSeznam(SpojovySeznam::new);
     * nebo
     *   spravce.vytvorSeznam(SeznamNaPoli::new);
     * }
     * </pre>
     *
     * @param supplier dodavatel instance s rozhraním Seznam
     */
    void vytvorSeznam(Supplier<Seznam<DopravniProstredek>> supplier);

    /**
     * Tato přetížená metoda má stejný úkol jako předchozí, vytvořit si skrytou
     * instanci s rozhraním Seznam, jen s tím rozdílem, že možné pomocí druhého
     * parametru definovat počet položek v seznamu.Proto se více hodí na
 vytvoření seznamu na poli.<p>
     * Příklad použití:
     * <pre>{@code
     *    Ovladani<DopravniProstredek> spravce = new SpravaProstredku();
     *    spravce.vytvorSeznam((t) -> new SeznamNaPoli<>(t), 10);
     * }
     * </pre> V parametru t se předává velikost pole nebo délka spojového
     * seznamu.
     *
     * @param function
     * @param size mezní počet položek v seznamu.
     * @throws kolekce.KolekceException
     */
    void vytvorSeznam(Function<Integer, Seznam<DopravniProstredek>> function, int size)
            throws KolekceException;

    /**
     * Metoda nastaví, jak se budou porovnávat datové položky v seznamu. Novým
     * zavoláním metody se může vyměnit způsob, jak se budou datové položky
     * seznamu porovávat. Funkční je vždy poslední verze komparátoru. Komparátor
     * se použije v metodách vyhledání položky v seznamu nebo při její odebrání
     * podle klíče. Klíč musí být stejného typu jako datové položka seznamu a
     * musí mít hodnotu nebo hodnoty hledaného datové položky.
     * <p>
     * Příklad použití:
     * <pre>{@code
     *    Ovladani<DopravniProstredek> spravce = new SpravaProstredku();
     *    spravce.nastavKomparator(DopravniProstredek::compareTo);
     * }
     * </pre>
     *
     * @param comparator reference na zabalený komparátor
     */
    void nastavKomparator(Comparator<? super DopravniProstredek> comparator);

    /**
     * Metoda nastaví chybový výpis.
     * <p>
     * Příklad použití:
     * <pre>{@code
     *    Ovladani<DopravniProstredek> spravce = new SpravaProstredku();
     * pro příkazový řádek
     *    spravce.nastavErrorLog(System.err::println);
     * nebo pro GUI
     *     Consumer<String> alert =
     *        t -> new Alert(Alert.AlertType.ERROR, t).showAndWait();
     *     spravce.nastavErrorLog(alert);
     * }
     * </pre>
     *
     * @param errorLog reference na zabalený objekt s výpisem chybového hlášení.
     */
    default void nastavErrorLog(Consumer<String> errorLog) {
        errorLog.accept("");
    }

// ----- přístup a změny  prostredků v seznamu ---------------------------------
    /**
     * Metoda vloží datovou položku do seznamu na konec seznamu.
     *
     * @param data reference na instanci položky
     *
     * @throws NullPointerException vystavení výjimky při hodnotě null v
     * parametru data
     */
    void vlozPolozku(DopravniProstredek data) throws NullPointerException;

    /**
     * Metoda vrátí referenci na vyhledanou datovou položku v seznamu podle
     * hodnoty klíče.Hodnotu určuje implementace komparátoru .<p>
     * Příklad použití:
     * <pre>{@code
     *    Ovladani<DopravniProstredek> spravce = new SpravaProstredku();
     *    spravce.nastavKomparator(DopravniProstredek::compareTo);
     *    DopravniProstredek data = new TestClass(1);
     *    spravce.vlozPolozku(data);
     *    DopravniProstredek klic = new TestClass(1);
     *    DopravniProstredek result = spravce.najdiPolozku(klic);
     * }
     * </pre>
     *
     * @param <error>
     * @param klic reference na objekt hodnotou klíče
     *
     * @return reference na nalezenou datovou položku při shodě s klíčem, v
     * případě, že nedošlo ke shodě podle klíče vrací se <code>null</code>
     */
    DopravniProstredek najdiPolozku(DopravniProstredekKlic klic);

// ----- pohyb po seznamu, nastavení aktuálního prostředku ---------------------    
    /**
     * Metoda nastaví prvni položku v seznamu jako aktuální.
     */
    void prejdiNaPrvniPolozku();

    /**
     * Metoda nastaví poslední položku v seznamu jako aktuální.
     * <p>
     */
    void prejdiNaPosledniPolozku();

    /**
     * Metoda nastaví další prvek jako aktuální.
     * <p>
     * Nutným předpokladem změny je, že již je nastaven aktuální prvek, když ne
     * je vydáno chybové hlášení pomocí objektu zadaného metodou
     * {@link #nastavErrorLog(java.util.function.Consumer)}.
     * <p>
     */
    void prejdiNaDalsiPolozku();
    
     /**
     * Metoda nastaví předchozi prvek jako aktuální.
     * <p>
     * Nutným předpokladem změny je, že již je nastaven aktuální prvek, když ne
     * je vydáno chybové hlášení pomocí objektu zadaného metodou
     * {@link #nastavErrorLog(java.util.function.Consumer)}.
     * <p>
     */
    void prejdiNaPredchoziPolozku();
   

    /**
     * Metoda nastaví aktuální položku podle klíče v parametru.
     * <p>
     * Klíč bude stejného typu jako datové položky seznamu.
     *
     * @param klic odkaz na objekt s hodnotou klíče.
     * @return 
     */
    DopravniProstredek nastavAktualniPolozku(DopravniProstredekKlic klic);

    /**
     * Metoda odstraní aktuální datovou položku ze seznamu, která byla před tím
     * vybrána posloupností pohybových metod.Seznam musí zůstat po odebrání
 položky stále konzistentní. To znamená, že se seznam odebráním položky
 nesmí přerušit nebo nahradit prázdnou položkou.
 <p>
     * Po odstranění položky ze seznamu dojde ke zrušení nastavení aktuální
     * položky a k opětovnému nastavení dojde pouze použitím pohybových metod.
     *
     * @return vrací referenci na aktuální datovou položku
     */
    DopravniProstredek vyjmiAktualniPolozku() ;

    /**
     * Tato metoda vrátí kopii aktuální datové položky seznamu. Po vykonání
     * metody zůstane stav instance implementační třídy nezměněn.
     * <p>
     * Kopie datové položky byla zvolena z důvodu pročvičení klonování objektů.
     * Kopírování datových položek je důležitý u seznamů, u kterých jsou řazeny
     * položky podle jejího obsahu/stavu.
     * <p>
     * Příkladem mohou být například prioritní fronty. Tam by mohlo dojít k
     * tomu, že při změně stavu datové položky po zjistění jejího odkazu by
     * seřazení položek neodpovídalo prioritnímu řazení. Takže klonování by nám
     * zajistilo, že nedojde k porušení řazení datových položek v seznamu, pokud
     * poskytneme na ně odkaz.
     * <p>
     * Další technikou zajištění správného řazení položek je používání neměnných
     * objektů. Když stav objektu bude neměnný, můžeme poskytovat odkazy na
     * položky seznamu bez obav, že by někdo mohl tento stav objektu změnit a
     * tím porušit řazení v seznamu.
     *
     * @return reference na kopii objektu aktuální datové položky.
     */
    DopravniProstredek dejKopiiAktualniPolozky();

    /**
     * Tato metoda zajistí úpravu obsahu aktuální položky v seznamu.
     * <p>
     * Metoda byla zařazena do tohoto rozhraní z důvodu procvičení, že lze,
     * pomocí lambda výrazu v podobě funkce, editovat stav objektu nezávisle na
     * typu uživatelského rozhraní.
     * <p>
     * V našem případě to bude v jednom případě uživatelské rozhraní tzv.
     * příkazového řádku nebo v druhém případě to bude GUI.
     * <p>
     * Příklad třídy s editorem datové položky pro příkazový řádek
     * <pre>{@code
     *public class Editor implements Function<DopravniProstredek, DopravniProstredek> {
     *  public DopravniProstredek apply(DopravniProstredek prostredek) {
     *    prostredek.setSpz(getStringItem("SPZ (" + prostredek.getSpz()+"):"));
     *    prostredek.setHmotnost(getFloatItem("Hmotnost:",prostredek.getHmotnost()));
     *    switch (prostredek.getType()) {
     *       case OSOBNI_AUTOMOBIL:
     *         OsobniAuto auto = (OsobniAuto) prostredek;
     *           auto.setPocetSedadel(getIntItem("Pocet sedadel:", auto.getPocetSedadel()));
     *           auto.setBarva(getBarvaItem("Barva:", auto.getBarva()));
     *           break;
     *       case DODAVKA:
     *           break;
     *       case NAKLADNI_AUTMOBIL:
     *           break;
     *       case TRAKTOR:
     *           break;
     *       default:
     *       }
     *    return prostredek;
     *   }
     *}
     *}</pre>
     *
     *
     *
     * @param editor odkaz na objek s editorem jedné datové položky
     */
    void editujAktualniPolozku(Function<DopravniProstredek, DopravniProstredek> editor);

// ----- perzistence serializací -----------------------------------------------  
    /**
     * Metoda provede lehkou perzistenci seznamu polozek do souboru, jehož
     * umístění a název budou dány v parametru metody.
     * <p>
     * Požaduje se serializovat jednotlivé datové entity seznamu. Je zakázano
     * serializovat celý objekt seznamu.
     *
     * @param soubor název a cesta k vytvožení souboru
     */
    void ulozDoSouboru(String soubor);

    /**
     * Metoda obnoví seznam polozek ze souboru, jehož umístění a název budou
     * dány v parametru metody.
     * <p>
     * Předpokládá se, že soubor bude obsahovat serializavené jednotlivé
     * dopravní prostředky. Před načtením souboru se požaduje vyprázdnění obsahu
     * seznamu s dopravnímími prostředky.
     *
     * @param soubor název a cesta k souboru
     */
    void nactiZeSouboru(String soubor);

// ----- nástroje --------------------------------------------------------------    
    /**
     * Metoda vypíše seznam polozek konzumentem, který bude předán v parametru..
     * <p>
     * Metoda je nezávislá na formě výpisu i výstupních zařízeních. Tyto
     * informace budou obsaženy v objektu, který bude odkazován v parametru
     * metody.
     *
     * @param writer odkaz na objekt, který zajistí výpis jedné datové položky
     *
     */
    void vypis(Consumer<DopravniProstredek> writer);

    /**
     * Metoda načte textový soubor s popisem jednotlivých typů dopravních
     * prostředků.Umístění a název souboru budou dány parametrem metody.
     * <p>
     * Přiklad formátu textového souboru:
     * <pre>
     * na, 8I11359, 12500, 6500
     * na, 5V51425,  7500, 18000
     * do, 5K85694,  2000, nástavba
     * na, 0G66105,  5000, 26000
     * do, 3J48990,  2000, dvojkabina
     * tr, 5X34682,  5500, 1860
     * oa, 2T16951,  2500, 4, bílá
     * oa, 4T38122,  1500, 7, modrá
     * na, 6P77538,  8000, 32000
     * do, 5S97103,  2500, valník
     * oa, 8I78723,  1500, 6, modrá
     * do, 5C50440,  3000, dvojkabina
     * oa, 0A71302,  2500, 4, bílá
     * </pre>
     * <p>
     * kde
     * <ol>
     * <li>v první položce je uveden typ dopravního prostředku</li>
     * <ul>
     * <li>oa - osobní automobil</li>
     * <li>na - nakladní automobil</li>
     * <li>do - dodávka</li>
     * <li>tr - traktor</li>
     * </ul>
     * <li>v druhé položce je státní poznávací značka</li>
     * <li>v třetí položce je hmotnost </li>
     * <li>od čtvrté položky jsou volitelné parametry podle typu dopravního
     * prostředku </li>
     * </ol>
     * <p>
     * Převod textu na objekty zajistí mapovací objekt. napříkla
     * <pre>
     * public static final Function<String, DopravniProstredek> mapperInput = (line) -> {
     *  DopravniProstredek prostredek = null;
     *  if (line.length() == 0) { return prostredek; }
     *  String[] items = line.split(",");
     *  String spz = items[1].trim();
     *  float hmotnost = Float.valueOf(items[2]);
     *  switch (items[0].trim().toLowerCase(Locale.US)) {
     *     case "oa":
     *       int pocetSedadel = Integer.valueOf(items[3].trim());
     *       Barva barva = Barva.decode(items[4].trim());
     *       prostredek = new OsobniAuto(spz, pocetSedadel, barva, hmotnost);
     *       break;
     *     case "do":
     *       DodavkaTyp typ = DodavkaTyp.decode(items[3].trim());
     *       prostredek = new Dodavka(spz, hmotnost, typ);
     *       break;
     *     case "na":
     *       long nosnost = Long.valueOf(items[3].trim());
     *       prostredek = new NakladniAuto(spz, hmotnost, nosnost);
     *       break;
     *     case "tr":
     *       int tah = Integer.valueOf(items[3].trim());
     *       prostredek = new Traktor(spz, hmotnost, tah);
     *       break;
     *    }
     *   return prostredek;
     * };
     * </pre>
     *
     * @param soubor název a cesta k souboru
     * @param mapper odkaz na mapovací objekt
     */
    void nactiTextSoubor(String soubor, Function<String, DopravniProstredek> mapper);

    /**
     * Metoda uloží do textového souboru popis jednotlivých typů dopravních
     * prostředků ze seznamu.Umístění a název souboru budou dany parametrem
     * metody.
     * <p>
     * Formát textového souboru je shodný s formátem, který je uveden u metody
     * {@code nactiTextSoubor}.
     *
     * @param soubor název a cesta k souboru
     * @param mapper odkaz na mapovací objekt
     */
    void ulozTextSoubor(String soubor, Function<DopravniProstredek, String> mapper);

    /**
     * Metoda provede automatické generování obsahu seznamu dopravních
     * prostředků o počtu, který je dám parametrem metody.
     *
     * @param pocetProstredku
     */
    void generujData(int pocetProstredku);

    /**
     * Metoda vrátí aktuální počet vložených datových položek seznamu
     * @return
     */
    int dejAktualniPocetPolozek();


    public void zrus();
/**
     * Metoda prevede obsah seznamu na datovy proud, ktery preda pri navratu.
     * Tato metoda se nepřetěžuje.
     *
     * @return datovy proud
     */
    default Stream<DopravniProstredek> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
