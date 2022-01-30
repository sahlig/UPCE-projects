/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;
import kolekce.LinSeznam;
import prostredky.DopravniProstredek;
import prostredky.DopravniProstredekTypy;
import prostredky.Dodavka;
import prostredky.DodavkaTyp;
import prostredky.MojeBarva;
import prostredky.OsobniAutomobil;
import prostredky.NakladniAutomobil;
import prostredky.Traktor;


/**
 *
 * @author Radek
 */
public class Generator {
    
    private static DopravniProstredekTypy gererujTyp(){
        DopravniProstredekTypy typ = null;
        int t = genCel(1, 4);
        switch(t){
            case 1:
                typ = DopravniProstredekTypy.OSOBNI;
                break;
            case 2:
                typ = DopravniProstredekTypy.NAKLADNI;
                break;
            case 3:
                typ = DopravniProstredekTypy.TRAKTOR;
                break;
            case 4:
                typ = DopravniProstredekTypy.DODAVKA;
                break;
            default:
                break;
        }
        
        return typ;
    }
    
    private static MojeBarva generujBarvu(){
        int b = genCel(1, 5);
        
        switch(b){
            case 1:
                return MojeBarva.BILA;
            case 2:
                return MojeBarva.CERNA;
            case 3:
                return MojeBarva.CERVENA;
            case 4:
                return MojeBarva.MODRA;
            case 5:
                return MojeBarva.ZELENA;
            default:
                break;
                
        }
        return null;
    }
    
    private static DodavkaTyp generujDTyp(){
        int b = genCel(1, 3);
        
        switch(b){
            case 1:
                return DodavkaTyp.DVOJKABINA;
            case 2:
                return DodavkaTyp.NASTAVBA;
            case 3:
                return DodavkaTyp.VALNIK;
            default:
                break;
                
        }
        return null;
    }
    
    private static String generujZnacku(){
        String znacka = "";
        int pismena;
        int cisla;
        for (int i = 0; i < 4; i++) {
            pismena = genCel(1, 26);
            znacka = znacka + "" + (char)(64 + pismena);
        }
        for (int i = 0; i < 4; i++) {
            cisla = genCel(1, 9);
            znacka = znacka + cisla;
        }
        
        return znacka;
    }
    
    public static DopravniProstredek generujJeden(){
        DopravniProstredekTypy typ = gererujTyp();
        switch(typ){
            case OSOBNI:
                return new OsobniAutomobil(genCel(1, 5), generujBarvu(), (float) genCel(1500, 5000), generujZnacku());
            case NAKLADNI:
                return new NakladniAutomobil(genCel(3, 6), (long) genCel(10000, 20000), (float) genCel(10000, 20000), generujZnacku());
            case TRAKTOR:
                return new Traktor(genCel(1000, 10000), (float) genCel(3000, 5000), generujZnacku());
            case DODAVKA:
                return new Dodavka(generujDTyp(), (float) genCel(3000, 5000), generujZnacku());
            default:
                break;
        }
        return null;
    }
    
    public static void generujSeznam(LinSeznam seznam){
        for (int i = 0; i < 5; i++) {
            seznam.vlozNaKonec(generujJeden());
        }
    }
    
    private static int genCel(int min, int max){
        return (int) Math.round(Math.random() * (max - min) + min);
    }
    
}
