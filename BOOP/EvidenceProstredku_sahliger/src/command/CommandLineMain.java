/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import sprava.Ovladani;
import sprava.SpravaProstredku;
import prostredky.DopravniProstredek;
import kolekce.LinSeznam;
import java.util.Comparator;
import prostredky.Dodavka;
import prostredky.DodavkaTyp;
import prostredky.DopravniProstredekKlic;
import prostredky.MojeBarva;
import prostredky.NakladniAutomobil;
import prostredky.OsobniAutomobil;
import prostredky.ProstredekTyp;
import prostredky.Traktor;


/**
 *
 * @author Radek
 */
public class CommandLineMain {

    static boolean endIt = false;

    public static void main(String[] args) {
        int kolik;
        Ovladani spravce = new SpravaProstredku();
        spravce.vytvorSeznam(LinSeznam::new);
        while (endIt == false) {
            String prikaz = Keyboard.cekej("Zadejte příkaz: ");
            switch (prikaz) {
                case "exit":
                    endIt = true;
                    break;

                case "help":
                    case "h":
                    System.out.println("help, h\nnovy, no\nnajdi, na, "
                            + "n\ndej\nedituj, edit\nvyjmi\nprvni, pr\ndalsi,"
                            + " da\nposledni, po\npocet\nobnov\nzalohuj\nvypis"
                            + "\nnactitext, nt\nuloztext, ut\ngeneruj,"
                            + " g\nzrus\nexit");
                    break;

                case "novy":
                    spravce.vlozPolozku(novyProstredek());
                    break;
                case "no":
                    spravce.vlozPolozku(novyProstredek());
                    break;

                case "n":
                case "na":
                case "najdi":
                    najdiTo(spravce);
                    break;

                case "dej": {
                        System.out.println(spravce.dejKopiiAktualniPolozky());
                }
                break;

                case "edituj":
                case "edit":{
                    try {
                        spravce.editujAktualniPolozku((DopravniProstredek t) -> editujTo(t));
                    } catch (Exception ex) {
                        spravce.nastavErrorLog(System.err::println);
                    }
                }
                break;

                case "vyjmi":  {
                    spravce.vyjmiAktualniPolozku();
                }
                break;


                case "prvni":
                case "pr":  {
                    spravce.prejdiNaPrvniPolozku();
                }
                break;


                case "dalsi":
                case "da":  {
                    spravce.prejdiNaDalsiPolozku();
                }
                break;


                case "posledni":
                case "po":  {
                    spravce.prejdiNaPosledniPolozku();
                }
                break;


                case "pocet":
                    System.out.println("Počet položek v seznamu je " + spravce.dejAktualniPocetPolozek());
                    break;

                case "obnov":  {
                    spravce.nactiZeSouboru("zaloha.bin");
                }
                break;


                case "zalohuj":  {
                    spravce.ulozDoSouboru("zaloha.bin");
                }
                break;


                case "vypis":
                    spravce.vypis((DopravniProstredek d) -> System.out.println(d));
                    break;

                case "nactitext":
                case "nt":
                    spravce.nactiTextSoubor("prostredky.txt", perzistence.Perzistence.mapperInput);
                    break;


                case "uloztext":
                case "ut":
                    spravce.ulozTextSoubor("prostredky.txt", perzistence.Perzistence.mapperOutput);
                    break;


                case "generuj":
                case "g":
                    try{
                    kolik = Integer.parseInt(Keyboard.cekej("Kolik prostředků chcete "
                            + "generovat: "));
                    spravce.generujData(kolik);
                    }catch(Exception ex){
                        spravce.nastavErrorLog(System.err::println);
                    }
                    break;

                case "zrus":
                    spravce.zrus();
                    break;

                default:
                    System.out.println("Zadejte prosím platný příkaz."
                            + " Pokud neznáte platné příkazy zadejte příkaz help.");
                    break;
            }
        }
    }


    private static DopravniProstredek editujTo(DopravniProstredek dop) {
        boolean again = false;
        do{
            again = false;
        try{
        if (dop.getTyp() == ProstredekTyp.OSOBNI_AUTOMOBIL) {
            System.out.println("1) Počet sedadel\n"
                    + "2) Barva\n"
                    + "3) Hmotnost\n"
                    + "4) SPZ");
            String which = Keyboard.cekej("Zadejte který atribut chcete upravit: ");
            String sed;
            switch (which) {
                case "1":
                    sed = Keyboard.cekej("Zadejte pocet sedadel: ");
                    int kolik = Integer.parseInt(sed);
                    dop.setPocet(kolik);
                    break;
                case "2":
                    System.out.println("1) Bílá\n"
                            + "2) Černá\n"
                            + "3) Červená\n"
                            + "4) Zelená\n"
                            + "5) Modrá");
                    sed = Keyboard.cekej("Zadejte číslo barvy: ");
                    boolean bar = false;
                    do{
                    switch (sed) {
                        case "1":
                            bar = true;
                            dop.setBarva(MojeBarva.BILA);
                            break;
                        case "2":
                            bar = true;
                            dop.setBarva(MojeBarva.CERNA);
                            break;
                        case "3":
                            bar = true;
                            dop.setBarva(MojeBarva.CERVENA);
                            break;
                        case "4":
                            bar = true;
                            dop.setBarva(MojeBarva.ZELENA);
                            break;
                        case "5":
                            bar = true;
                            dop.setBarva(MojeBarva.MODRA);
                            break;
                        default:
                            bar = false;
                            System.out.println("\nProsím zadejte jednu z nabízených možností.\n");
                            break;
                    }}while(bar == false);
                    break;
                case "3":
                    sed = Keyboard.cekej("Zadejte vahu: ");
                    float vaha = Float.parseFloat(sed);
                    dop.setHmotnost(vaha);
                    break;
                case "4":
                    sed = Keyboard.cekej("Zadejte SPZ: ");
                    dop.setPozn(sed);
                    break;
                default:
                    System.out.println("\nProsím zadejte jednu z nabízených možností.\n");
                    again = true;
                    break;
            }
        } else if (dop.getTyp() == ProstredekTyp.NAKLADNI_AUTMOBIL) {
            System.out.println("1) Počet náprav\n"
                    + "2) Nosnost\n"
                    + "3) Hmotnost\n"
                    + "4) SPZ");
            String which = Keyboard.cekej("Zadejte který atribut chcete upravit: ");
            String sed;
            switch (which) {
                case "1":
                    sed = Keyboard.cekej("Zadejte počet náprav: ");
                    int nap = Integer.parseInt(sed);
                    dop.setPocetN(nap);
                    break;
                case "2":
                    sed = Keyboard.cekej("Zadejte nosnost: ");
                    long nos = Long.parseLong(sed);
                    dop.setNosnost(nos);
                    break;
                case "3":
                    sed = Keyboard.cekej("Zadejte vahu: ");
                    float vaha = Float.parseFloat(sed);
                    dop.setHmotnost(vaha);
                    break;
                case "4":
                    sed = Keyboard.cekej("Zadejte SPZ: ");
                    dop.setPozn(sed);
                    break;
                default:
                    System.out.println("\nProsím zadejte jednu z nabízených možností.\n");
                    again = true;
                    break;
            }
        } else if (dop.getTyp() == ProstredekTyp.TRAKTOR) {
            System.out.println("1) Tah\n"
                    + "2) Hmotnost\n"
                    + "3) SPZ");
            String which = Keyboard.cekej("Zadejte který atribut chcete upravit: ");
            String sed;
            switch (which) {
                case "1":
                    sed = Keyboard.cekej("Zadejte tah: ");
                    int tah = Integer.parseInt(sed);
                    dop.setTah(tah);
                    break;
                case "2":
                    sed = Keyboard.cekej("Zadejte vahu: ");
                    float vaha = Float.parseFloat(sed);
                    dop.setHmotnost(vaha);
                    break;
                case "3":
                    sed = Keyboard.cekej("Zadejte SPZ: ");
                    dop.setPozn(sed);
                    break;
                default:
                    System.out.println("\nProsím zadejte jednu z nabízených možností.\n");
                    again = true;
                    break;
            }
        } else {
            System.out.println("1) Typ dodávky\n"
                    + "2) Hmotnost\n"
                    + "3) SPZ");
            String which = Keyboard.cekej("Zadejte který atribut chcete upravit: ");
            String sed;
            switch (which) {
                case "1":
                    boolean dtyp = false;
                    do{
                    System.out.println("1) Valník\n"
                            + "2) Dvojkabina\n"
                            + "3) Nástavba");
                    sed = Keyboard.cekej("Zadejte číslo typu dodávky: ");
                    switch (sed) {
                        case "1":
                            dtyp = true;
                            dop.setDTyp(DodavkaTyp.VALNIK);
                            break;
                        case "2":
                            dtyp = true;
                            dop.setDTyp(DodavkaTyp.DVOJKABINA);
                            break;
                        case "3":
                            dtyp = true;
                            dop.setDTyp(DodavkaTyp.NASTAVBA);
                            break;
                        default:
                            dtyp = false;
                            System.out.println("\nProsím zadejte jednu z nabízených možností.\n");
                            break;
                    }}while(dtyp == false);
                    break;
                case "2":
                    sed = Keyboard.cekej("Zadejte vahu: ");
                    float vaha = Float.parseFloat(sed);
                    dop.setHmotnost(vaha);
                    break;
                case "3":
                    sed = Keyboard.cekej("Zadejte SPZ: ");
                    dop.setPozn(sed);
                    break;
                default:
                    System.out.println("\nProsím zadejte jednu z nabízených možností.\n");
                    again = true;
                    break;
            }
            return dop;
        }}catch(Exception ex){
            System.out.println("\nNebylo možné editovat prostředek prosím zkontrolujte,"
                    + "zda zadáváte správné hodnoty.\n");
            again = true;
        }}while(again == true);
        return null;
    }
    
    
        public static DopravniProstredek novyProstredek(){
        boolean again = false;
       do{
           again = false;
        try {
            System.out.println("1) Osobní automobil\n"
                    + "2) Nákladní automobil\n"
                    + "3) Traktor\n"
                    + "4) Dodávka");
            String which = Keyboard.cekej("Zadejte číslo typu nového přestředku: ");
            
            if (which.equals("1")) {
                    String sed;
                    MojeBarva bar = null;
                    float vaha;
                    String spz;
                    sed = Keyboard.cekej("Zadejte pocet sedadel: ");
                    int koliksed = Integer.parseInt(sed);
                    

                    
                    do{
                        System.out.println("1) Bílá\n"
                            + "2) Černá\n"
                            + "3) Červená\n"
                            + "4) Zelená\n"
                            + "5) Modrá");
                    sed = Keyboard.cekej("Zadejte číslo barvy: ");
                    switch (sed) {
                        case "1":
                            bar = MojeBarva.BILA;
                            break;
                        case "2":
                            bar = MojeBarva.CERNA;
                            break;
                        case "3":
                            bar = MojeBarva.CERVENA;
                            break;
                        case "4":
                            bar = MojeBarva.ZELENA;
                            break;
                        case "5":
                            bar = MojeBarva.MODRA;
                            break;
                        default:
                            System.out.println("\nProsím zadejte jednu z nabízených možností.\n");
                            break;
                    }}while(bar == null);
                    
                    sed = Keyboard.cekej("Zadejte vahu: ");
                    vaha = Float.parseFloat(sed);
                    
                    sed = Keyboard.cekej("Zadejte SPZ: ");
                    spz = sed;
                    
                    DopravniProstredek novy = new OsobniAutomobil(koliksed, bar, vaha, spz);
                    return novy;
            
        } else if(which.equals("2")) {
            String sed;
            int nap;
            long nos;
            float vaha;
            String spz;
            
                    sed = Keyboard.cekej("Zadejte počet náprav: ");
                    nap = Integer.parseInt(sed);

                    sed = Keyboard.cekej("Zadejte nosnost: ");
                    nos = Long.parseLong(sed);

                    sed = Keyboard.cekej("Zadejte vahu: ");
                    vaha = Integer.parseInt(sed);

                    sed = Keyboard.cekej("Zadejte SPZ: ");
                    spz = sed;
                    
                    DopravniProstredek novy = new NakladniAutomobil(nap, nos, vaha, spz);
                    return novy;
                    
        } else if (which.equals("3")) {
            int tah;
            float vaha;
            String spz;
            String sed;
            
                    sed = Keyboard.cekej("Zadejte tah: ");
                    tah = Integer.parseInt(sed);

                    sed = Keyboard.cekej("Zadejte vahu: ");
                    vaha = Float.parseFloat(sed);
                    

                    sed = Keyboard.cekej("Zadejte SPZ: ");
                    spz = sed;
                    
                    DopravniProstredek novy = new Traktor(tah, vaha, spz);
                    return novy;
            
        } else if (which.equals("4")){
            DodavkaTyp dtyp = null;
            float vaha;
            String spz;
            String sed;

                    
                    do{
                        System.out.println("1) Valník\n"
                            + "2) Dvojkabina\n"
                            + "3) Nástavba");
                    sed = Keyboard.cekej("Zadejte číslo typu dodávky: ");
                    switch (sed) {
                        case "1":
                            dtyp = DodavkaTyp.VALNIK;
                            break;
                        case "2":
                            dtyp = DodavkaTyp.DVOJKABINA;
                            break;
                        case "3":
                            dtyp = DodavkaTyp.NASTAVBA;
                            break;
                        default:
                            System.out.println("\nProsím zadejte jednu z nabízených možností.\n");
                            break;
                    }}while(dtyp == null);

                    sed = Keyboard.cekej("Zadejte vahu: ");
                    vaha = Float.parseFloat(sed);

                    sed = Keyboard.cekej("Zadejte SPZ: ");
                    spz = sed;
                    
                    DopravniProstredek novy = new Dodavka(dtyp, vaha, spz);
                    return novy;

            }else{
                System.out.println("\nProsím zadejte jednu z nabízených možností.\n");
                again = true;
        }
            
        } catch (Exception ex) {
            System.out.println("\nProstředek nebylo možné zařadit do seznamu, "
                    + "ujistěte se, že zadáváte správné hodnoty!\n");
            again = true;
        }}while(again == true);
       return null;
        }
        
        private static void najdiTo(Ovladani spravce){
            String which;
            int wh = 0;
            int id;
            
            do{
                try{
            System.out.println("1) ID\n"
                            + "2) SPZ\n"
                            + "3) ID a SPZ");;
                    which = Keyboard.cekej("Zadejte číslo parametru, "
                            + "podle kterého chcete vyhledávat: ");
            wh = Integer.parseInt(which);
            
            switch(wh){
                case 1:
                    id = Integer.parseInt(Keyboard.cekej("Zadejte ID pro vyhledávání: "));
                    spravce.nastavKomparator(Comparator.comparing(DopravniProstredek::getId));
                    System.out.println(spravce.najdiPolozku(new DopravniProstredekKlic(id)));
                    break;
                case 2:
                    which = Keyboard.cekej("Zadejte hledanou SPZ: ");
                    spravce.nastavKomparator(Comparator.comparing(DopravniProstredek::getPozn));
                    System.out.println(spravce.najdiPolozku(new DopravniProstredekKlic(which)));
                    break;
                case 3:
                    id = Integer.parseInt(Keyboard.cekej("Zadejte ID pro vyhledávání: "));
                    which = Keyboard.cekej("Zadejte hledanou SPZ: ");
                    spravce.nastavKomparator(Comparator.comparing(DopravniProstredek::getId)
                    .thenComparing(DopravniProstredek::getPozn));
                    System.out.println(spravce.najdiPolozku(new DopravniProstredekKlic(id, which)));
                    break;
                default:
                    wh = 0;
                    break;
            }
            }catch(Exception ex){
                    System.out.println("\nZkontrolujte zda zadáváte správné hodnoty.\n");
                    wh = 0;
                    }
        }while(wh == 0);
        }

    

    }
            
