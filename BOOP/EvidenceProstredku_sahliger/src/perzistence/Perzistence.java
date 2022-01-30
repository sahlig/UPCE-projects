/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perzistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;
import kolekce.Seznam;
import prostredky.Dodavka;
import prostredky.DodavkaTyp;
import prostredky.DopravniProstredek;
import prostredky.MojeBarva;
import prostredky.NakladniAutomobil;
import prostredky.OsobniAutomobil;
import prostredky.Traktor;

/**
 *
 * @author Radek
 */
public class Perzistence{

    public static <T> void zalohujTo(String jmenoSouboru, Seznam<T> seznam)
            throws IOException {
        try {
            Objects.requireNonNull(seznam);

            ObjectOutputStream vystup
                    = new ObjectOutputStream(
                            new FileOutputStream(jmenoSouboru));

            vystup.writeInt(seznam.size());

            Iterator<T> it = seznam.iterator();
            while (it.hasNext()) {
                vystup.writeObject(it.next());
            }
            vystup.close();
        } catch (IOException ex) {
            throw new IOException(ex);
        }
    }

    public static <T> Seznam<T> obnovTo(String jmenoSouboru, Seznam seznam)
            throws IOException {
        try {
            Objects.requireNonNull(seznam);
            ObjectInputStream vstup
                    = new ObjectInputStream(
                            new FileInputStream(jmenoSouboru));
            seznam.zrus();

            int pocet = vstup.readInt();
            for (int i = 0; i < pocet; i++) {
                seznam.vlozNaKonec((T) vstup.readObject());
            }
            vstup.close();
        } catch (IOException | ClassNotFoundException ex) {
            throw new IOException(ex);
        } finally {
        }
        return seznam;
    }

    public static Function<String, DopravniProstredek> mapperInput = (line) -> {
        DopravniProstredek prostredek = null;
        if (line.length() == 0) {
            return prostredek;
        }
        String[] items = line.split(",");
        String spz = items[1].trim();
        float hmotnost = Float.valueOf(items[2]);
        switch (items[0].trim().toLowerCase(Locale.US)) {
            case "oa":
                int pocetSedadel = Integer.valueOf(items[3].trim());
                MojeBarva barva = MojeBarva.decode(items[4].trim());
                prostredek = new OsobniAutomobil(pocetSedadel, barva, hmotnost, spz);
                break;
            case "do":
                DodavkaTyp typ = DodavkaTyp.decode(items[3].trim());
                prostredek = new Dodavka(typ, hmotnost, spz);
                break;
            case "na":
                long nosnost = Long.valueOf(items[3].trim());
                int pocetNaprav = Integer.valueOf(items[4].trim());
                prostredek = new NakladniAutomobil(pocetNaprav, nosnost, hmotnost, spz);
                break;
            case "tr":
                Integer tah = Integer.valueOf(items[3].trim());
                prostredek = new Traktor(tah, hmotnost, spz);
                break;
        }
        return prostredek;
    };

    public static Function<DopravniProstredek, String> mapperOutput = (prostredek) -> {
        switch (prostredek.getTyp()) {
            case OSOBNI_AUTOMOBIL:
                return String.format("oa, %s, %5.0f, %d, %s ",
                        prostredek.getPozn(), prostredek.getHmotnost(),
                        ((OsobniAutomobil) prostredek).getPocet(),
                        ((OsobniAutomobil) prostredek).getBarva());
            case DODAVKA:
                return String.format("do, %s, %5.0f, %s",
                        prostredek.getPozn(), prostredek.getHmotnost(),
                        ((Dodavka) prostredek).getDTyp());
            case NAKLADNI_AUTMOBIL:
                return String.format("na, %s, %5.0f, %d, %d",
                        prostredek.getPozn(), prostredek.getHmotnost(),
                        ((NakladniAutomobil) prostredek).getNosnost(),
                        ((NakladniAutomobil) prostredek).getPocetN());

            case TRAKTOR:
                return String.format("tr, %s, %5.0f, %d",
                        prostredek.getPozn(), prostredek.getHmotnost(),
                        prostredek.getTah());
        }
        return null;
    };

    public static Stream<DopravniProstredek> readStream(String nameFile)
            throws IOException {
        return Files.lines(Paths.get(nameFile), StandardCharsets.UTF_8)
                .filter(t -> t != null)
                .map(mapperInput);
    }

    public static void writeStream(
            Stream<DopravniProstredek> stream, String nameFile)
            throws FileNotFoundException, UnsupportedEncodingException {
        try ( PrintWriter pw = new PrintWriter(nameFile, "UTF-8")) {
            stream
                    .map(mapperOutput)
                    .forEachOrdered(pw::println);
        }
    }
    
    public static void writeStream(
            Stream<DopravniProstredek> stream, String nameFile, Function<DopravniProstredek, String> mapper)
            throws FileNotFoundException, UnsupportedEncodingException {
        try ( PrintWriter pw = new PrintWriter(nameFile, "UTF-8")) {
            stream
                    .map(mapper)
                    .forEachOrdered(pw::println);
        }
    }
    
    public static Stream<DopravniProstredek> readStream(String nameFile, Function<String, DopravniProstredek> mapper)
            throws IOException {
        return Files.lines(Paths.get(nameFile), StandardCharsets.UTF_8)
                .filter(t -> t != null)
                .map(mapper);
    }
    

}
