package cv9;

import java.awt.Color;

public class Cv9 {

    public static void main(String[] args) {
        OsobniAutomobil auto1 = new OsobniAutomobil(5, Color.gray, 1500, "123456");
        OsobniAutomobil auto2 = new OsobniAutomobil(4, Color.pink, 2000, "123AH456");
        NakladniAutomobil nak1 = new NakladniAutomobil(6, 50000, 6000, "8522486");
        NakladniAutomobil nak2 = new NakladniAutomobil(7, 60000, 7000, "8SDUHS486");
        Traktor trak1 = new Traktor(5000, 3000, "ABC");
        Traktor trak2 = new Traktor(6000, 4000, "ABC123");
        
        System.out.println(auto1);
        System.out.println(auto2);
        System.out.println(nak1);
        System.out.println(nak2);
        System.out.println(trak1);
        System.out.println(trak2);
        
        System.out.println("");
        
        System.out.println(nak2);
        nak2.setNosnost(70000);
        nak2.setPozn("ADVAHS45658");
        nak2.setPocetN(20);
        System.out.println(nak2);
        
        System.out.println("");
        
        System.out.println("Osobni automobil ma barvu " + auto1.getBarva());
        System.out.println("Osobni automobil ma tento pocet sedadel " + auto1.getPocet());
        auto1.setBarva(Color.red);
        auto1.setPocet(2);
        System.out.println("Osobni automobil ma barvu " + auto1.getBarva());
        System.out.println("Osobni automobil ma tento pocet sedadel " + auto1.getPocet());
        
        System.out.println("");
        
        System.out.println("Traktor ma tah " + trak2.getTah() + " a vazi " + trak2.getHmotnost());
        trak2.setTah(20000);
        trak2.setHmotnost(5000000);
        System.out.println("Traktor ma tah " + trak2.getTah() + " a vazi " + trak2.getHmotnost());
    }
    
}
