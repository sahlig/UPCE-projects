package kolekce;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.Collections;
import java.util.Map;


/**
 * Tento interfejs deklaruje metody jednoduchého spojového seznamu. Spojový
 * seznam organizuje vkládané objekty do propojené řady prvků.
 * <p>
 * Pro lepší pochopení pricipu spojového seznamu lze použít analogii s naloženým
 * nákladním vlakem. Každý vagón je spojen s následujícím vagónem a na každém
 * vagónu je naložen náklad. V našem případě jsou nákladem objekty typu dané
 * typovým parametrem E a vagóny jsou prvky seznamu.
 *
 * <p>
 * V tomto rozhraní signatury metod pohybu po seznamu. A to proto, aby bylo
 * možné dosáhnout jakéhokoliv vloženého objektu v seznamu. Těmi metodami jsou
 * metody, která nastaví aktuální prvek seznamu na první pozici a potom další
 * metodou se posune pomyslný ukazatel na další prvek seznamu. Další důležitou
 * metodou je ta, která nastaví za aktuální prvek ten poslední, abychom mohli
 * vkládat nová data na konec seznamu. Proměnná s referencí na aktuální prvek
 * seznamu bude implementována jako skrytý atribut.
 * <p>
 * <b>Poznámka</b>: Toto rozhraní obsahuje pouze některé metody běžného
 * lineárního seznamu. S úplným rejstříkem metod se setkáte na předmětu datové
 * struktury.
 * <p>
 * Další metody zajišťují vložení, převzetí nebo vyjmutí dat do nebo ze seznamu
 * na pozici aktuálního prvku.
 * <p>
 * Další sada metod umožňuje zjistit stav seznamu v podobě aktuálního početu
 * prvků, nebo zruší všechny vložené prvky.
 * <p>
 * Poslední a to zásadní je metoda, která umožňuje procházet seznam prvků prvek
 * po prvku, aniž by hrozilo nebezpeší, že bude narušena souvislá řada prvků
 * seznamu.
 *
 * Implementace spojového seznamu může být založena na principu speciálních
 * objektů, kterým budeme říkat prvek. Tyto prvky seznam vytváří podle
 * zpráv(metod), které zasílají klienti na instance tohoto rozhraní. Tyto
 * speciální objekty/prvky jsou vytvářeny nejlépe podle staticky vnořené třídy
 * nebo podle vnitřní třídy. Která varianta je lepší je otázkou diskuze nad
 * jejími vlastnostmi. Obě dvě třídy mají v našem případě dva atributy. Jeden
 * bude určen pro odkaz na objekty datové entity dané typovým parametrem E a
 * druhý bude určen pro odkaz na další instanci trídy prvku.
 * <p>
 * Jestliže bude vkládána datová entita na konec seznamu, tak dosavadnímu
 * poslednímu prvku bude do atributu dalšího prvku vložen odkaz no nově vzniklý
 * prevek, který bude míte nastaven odkaz na objekt datové entity type E a odkaz
 * na další bude vyplne null.
 * <p>
 * Jiná, složitější, situace bude, když budeme vkládat nový prvek před nebo za
 * aktuální prvek. Složitější metody jsou ty, které vkládají před aktuální
 * prvek. Proto mají tyto metody již zde definovany defaultní těla (nová
 * vlastnost od Java 8). Tyto defaultní metody není nutné implementovat.
 * Implementaci si mohou zvolit zdatnější studenti. *
 *
 * <b>Omezení implementace</b>
 * <ol>
 * <li>Lineární seznam musí být jednosměrný. To znamená, že pomocná vnitřní
 * třída Prvek obsahuje pouze jeden propojovací atribut směrem na další instanci
 * třídy Prvek.
 * </ol>
 *
 * @author karel@simerda.cz
 *
 * @param <E>
 */
public interface Seznam<E> extends Iterable<E> {

    /**
     * Metoda pohybu nastavPrvni() nastaví vnitřní aktuální ukazatel na první
     * prvek seznamu.
     *
     * @throws KolekceException Výjimka se vystaví, když je seznam prázdný.
     */
    void nastavPrvni() throws KolekceException;

    /**
     * Metoda pohybu nastavPosledni() nastaví vnitřní aktuální ukazatel na
     * posledni prvek seznamu.
     *
     * @throws KolekceException Výjimka se vystaví, když je seznam prázdný.
     */
    void nastavPosledni() throws KolekceException ;

    /**
     * Metoda pohybu dalsi() přestaví vnitřní aktuální ukazatel na další prvek
     * seznamu.V případě, že metoda zjistí, že nenásleduje žádný prvek v
     * seznamu, tak to indikuje hodnotou false při návratu.
     *
     *
     * @return Vrací hodnotu true, když se přesun podařil, jinak false.
     *
     * @throws KolekceException Výjimka se vystaví v případě, že nebyl nastaven
     * aktuální prvek.
     */
    boolean dalsi() throws KolekceException;

    /**
     * Vkládací metoda vlozPrvni() vloží prvek s datovou entitou jako
     * první.Přechozí první prvek bude po zavolání metody druhým revekm v
     * seznamu.
     *
     *
     * @param data Datová entita typu E
     */
    void vlozPrvni(E data);

    /**
     * Vkládací metoda vlozNaKonec() vloží nový prvek na konec seznamu
     *
     * Jestliže je seznam prázdný, tak se prvek zařadí jako první.
     *
     * @param data Datová entita typu E
     */
    void vlozNaKonec(E data);

    /**
     * Vkládací metoda vlozZaAktualni() vloží nový prvek s entitami type E za
     * aktuální prvek.Metoda musí rozpojit seznam a spojit ho zpět přes nově
     * vytvořený prvek.
     *
     * U analogie s vlakem by musela druhá část po rozpojení odtažena na jinou
     * kolej. Nový vagón s nákladem by se připojil k první část a druhá část by
     * se zpětně připojila za nově přidaný vagón.
     *
     * @param data Datová entita typu E
     *
     * @throws kolekce.KolekceException
     */
    void vlozZaAktualni(E data) throws KolekceException;

    /**
     * Vkládací metoda vlozPredAktualni() vloží nový prvek s entitiami E před
     * aktuální prvek.
     *
     * Tato metoda je složitější na implementaci, protože jsme omezeni
     * jednosměrným řazením prvků seznamu. Proto je tato metoda deklarována jako
     * "default" a tím pádem má definováneé tělo již v rozhraní. To umožňuje
     * tuto metodu vynechat v implementační třídě.
     *
     * @param data
     */
    default void vlozPredAktualni(E data) {
        throw new UnsupportedOperationException();
    }

    /**
     * Zjišťovací metoda jePrazdny() zjistí, zda seznam neobsahuje žádný prvek.
     *
     * @return Vrací true, když je seznam prázdný, jinak false
     */
    boolean jePrazdny();

    /**
     * Přístupová metoda dejPrvni() vrátí odkaz na datovou entitu typu E prvního
     * prvku seznamu.
     *
     * @return Odkaz na datovou entitu typu E
     *
     * @throws KolekceException Výjimka se vystaví, když je seznam prázdný.
     */
    E dejPrvni() throws KolekceException;

    /**
     * Přístupová metoda dejPoslední() vrátí odkaz na datovou entitu typu E
     * posledního prvku seznamu.
     *
     * @return Odkaz na datovou entitu typu E
     * @throws KolekceException Výjimka se vystaví, když je seznam prázdný.
     */
    E dejPosledni() throws KolekceException;

    /**
     * Přístupová metoda dejAktualni() vrátí v návratové hodnotě odkaz na
     * objekt, datovou entitu typu E, aktuálně nastaveného prvku metodami pohybu
     * po seznamu.
     *
     * @return vrací odkaz na object/datovou entitu typu E z aktuálního prvku
     * seznamu.
     *
     *
     * @throws KolekceException Tato výjimka se vystaví, když je seznam prázdný
     * nebo když není nastaven aktualní prvek.
     */
    E dejAktualni() throws KolekceException;

    /**
     * Přístupová metoda dejZaAktualnim() vrátí v návratové hodnotě odkaz na
     * objekt, datovou entitu typu E, za aktuálně nastaveného prvku metodami
     * pohybu po seznamu.
     *
     * @return vrací odkaz na object/datovou entitu typu E za aktuálním prvkem
     * seznamu.
     *
     *
     * @throws KolekceException Tato výjimka se vystaví, když je seznam prázdný
     * nebo když není nastaven aktuální prvek.
     */
    E dejZaAktualnim() throws KolekceException;

    /**
     * Přístupová metoda dejPředktualnim() vrátí v návratové hodnotě odkaz na
     * objekt, datovou entitu typu E, který je před aktuálně nastaveném prvku
     * metodami pohybu po seznamu.
     *
     * @return vrací odkaz na object/datovou entitu typu E za aktuálním prvkem
     * seznamu.
     *
     * Tato metoda je složitější na implementaci, protože jsme omezeni
     * jednosměrným řazením prvků seznamu. Proto je tato metoda deklarována jako
     * "default" a tím pádem má definováneé tělo již v rozhraní. To umožňuje
     * tuto metodu vynechat v implementační třídě.
     *
     * @throws KolekceException Tato výjimka se vystaví, když je seznam prázdný
     * nebo když není nastaven aktuální prvek.
     */
    default E dejPredAktualnim() throws KolekceException {
        throw new UnsupportedOperationException();
    }

    /**
     * Metoda odeberAktualni() odebere aktuální prvek ze seznamu a zajistí
     * souvislé propojení zbylých prvků seznamu.Metoda mění aktuální počet
     * objektů v seznamu.
     *
     *
     * @return Vrací odkaz na odebíraný objekt, datovou entitu typu E
     *
     * @throws kolekce.KolekceException tato výjimka se vystaváí, když nebyl
     * nastaven aktuální prvek nebo byl seznam prázdný.
     */
    E odeberAktualni() throws KolekceException;

    /**
     * Metoda odeberZaAktualnim() odebere prvek ze seznamu za aktuálním prvek a
     * zajistí souvislé propojení zbylých prvků seznamu. Metoda mění počet
     * objektů v seznamu.
     *
     *
     * @return Vrací odkaz na odebíraný objekt, datovou entitu typu E
     *
     * @throws kolekce.KolekceException tato výjimka se vystaváí, když nebyl
     * nastaven aktuální prvek nebo byl seznam prázdný.
     */
    E odeberZaAktualnim() throws KolekceException;

    /**
     * Metoda odeberPredAktualnim() odebere prvek ze seznamu, který je před
     * aktuálním a zajistí souvislé propojení zbylých prvků seznamu. Metoda mění
     * počet objektů v seznamu.
     *
     *
     * @return Vrací odkaz na odebíraný objekt, datovou entitu typu E
     *
     * @throws kolekce.KolekceException tato výjimka se vystaváí, když nebyl
     * nastaven aktuální prvek nebo byl seznam prázdný.
     */
    default E odeberPredAktualnim() throws KolekceException {
        throw new UnsupportedOperationException();
    }

    /**
     * Metoda size() vrací aktuální počet objektů v seznamu
     *
     * @return Vrací hodnotu s počtem objektů v seznamu.
     */
    int size();

    /**
     * Metoda zrus() odebere všechny prvky v seznamu.
     */
    void zrus();

    /**
     * Metoda prevede obsah seznamu na datovy proud, ktery preda pri navratu.
     * Tato metoda se nepřetěžuje.
     *
     * @return datovy proud
     */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

}
