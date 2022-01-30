package generator;

import auta.Auto;
import auta.Barva;
import auta.OsobniAuto;
import auta.UzitkoveAuto;
import seznam.AbstrDoubleList;
import seznam.IAbstrDoubleList;


/**
 *
 * @author Radek
 */
public class Generator {
    
    
    private static Barva generujBarvu(){
        int b = genCel(1, 5);
        
        switch(b){
            case 1:
                return Barva.BILA;
            case 2:
                return Barva.CERNA;
            case 3:
                return Barva.CERVENA;
            case 4:
                return Barva.MODRA;
            case 5:
                return Barva.ZELENA;
            default:
                break;
                
        }
        return null;
    }
    
    private static String generujZnacku(){
        String znacka = "";
        int pismena;
        int cisla;
        for (int i = 0; i < 3; i++) {
            pismena = genCel(1, 26);
            znacka = znacka + "" + (char)(64 + pismena);
        }
        for (int i = 0; i < 4; i++) {
            cisla = genCel(1, 9);
            znacka = znacka + cisla;
        }
        
        return znacka;
    }
    
    public static Auto generujJeden(){
        int typ = genCel(1, 2);
        switch(typ){
            case 1:
                return new OsobniAuto(generujZnacku(), genCel(50000, 120000), 0, generujBarvu());
            case 2:
                return new UzitkoveAuto(generujZnacku(), genCel(50000, 120000), 0, genCel(5000, 10000));
            default:
                break;
        }
        return null;
    }
    
    public static IAbstrDoubleList<Auto> dejList(int kolik){
        IAbstrDoubleList<Auto> tmp = new AbstrDoubleList<Auto>();
        for (int i = 0; i < kolik; i++) {
            tmp.vlozPosledni(generujJeden());
        }
        return tmp;
    }
    
    public static int genCel(int min, int max){
        return (int) Math.round(Math.random() * (max - min) + min);
    }
    
}
