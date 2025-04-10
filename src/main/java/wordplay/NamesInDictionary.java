package wordplay;

import java.util.HashSet;
import java.util.List;
//import org.openjdk.jmh.annotations.Benchmark;

import wordplay.io.DictionaryReader;
import wordplay.io.NamesReader;

public class NamesInDictionary {

    private static List<String> finnishNames = NamesReader.readFirstNames();
    private static List<String> finnishWords = DictionaryReader.readFinnishWords();
    private static HashSet<String> hashNames = new HashSet<>(finnishNames);
    private static HashSet<String> hashWords = new HashSet<>(finnishWords);

//    @Benchmark
    public static void benchHash() { // Tämä tuottaa 578 tulosta. Yksi nimi jää siis puuttumaan..
        int i = 0;
        for (String name : hashNames) {
            if (hashWords.contains(name.toLowerCase())) {
                i++;
                System.out.printf("%d -- %s\n", i, name);
            }
        }
    }

//    @Benchmark
//    public static void benchList() { // Tämä tuottaa 653 tulosta. Set poistaa tuplat, jota tässä ei ole tehty. Poistamalla ne tulisi 653 - 75 = 578 tulosta
//        for (String name : finnishNames) {
//            if (finnishWords.contains(name.toLowerCase())) {
//                System.out.println(name);
//            }
//        }
//    }

// Yllä olevien benchmarkkien tulokset printit kommentoituna ulos ja ilman rivien laskua. Listalla on selkeästi hitaampi vauhti. Toki aikaa saisi pois ottamalla tuplat pois,
// mutta listan nopeus ei riittäisi siltikään voittamaan HashSettiä.
//     Benchmark                    Mode  Cnt   Score    Error  Units
//     NamesInDictionary.benchHash  avgt    5   0.001 ±  0.001   s/op
//     NamesInDictionary.benchList  avgt    5  12.044 ±  7.205   s/op

    public static void main(String[] args) {
        /*
         * Implement a program that prints all Finnish names that are also
         * found in the Finnish dictionary.
         *
         * You can use the finnishNames and finnishWords variables defined above
         * as the data set you need to process. Notice that the words are in lower case
         * but the names are not. The names can contain duplicates, as there are several
         * unisex names in the data set.
         *
         * Try to make your program as efficient as possible. A good goal is to have
         * the program run in tenths of a second.
         *
         * Good luck!
         */
        benchHash();
//        System.out.printf("Sanat: %d, Nimet: %d\n", finnishWords.size(), finnishNames.size());                        Tämä tulostaa -> Sanat: 93086, Nimet: 15665
//        System.out.printf("Sanat ilman tuplia: %d, Nimet ilman tuplia: %d\n", hashWords.size(), hashNames.size());    Tämä tulostaa -> Sanat ilman tuplia: 93086, Nimet ilman tuplia: 15169

// Elikkä python testissä (Oikea määrä sanoja tuloksessa) -> Nimet: 15667 ja nimet ilman tuplia: 15170
// Java testissä (Yksi nimi puuttuu lopputuloksesta) -> Nimet: 15665 ja nimet ilman tuplia: 15169
// SYY TÄHÄN ON SE, PYTHON TESTISSÄ OTAMME MUKAAN ENSIMMÄISEN RIVIN JOSSA ON VAIN ETUNIMI. NamesReader SKIPPAA TÄMÄN JA SIITÄ SYYSTÄ JAVA SAA YHDEN NIMEN VÄHEMMÄN (ETUNIMI)
// VOISIMME SIIS VAIHTAA NamesReaderiin Skip(0) JOLLOIN SE EI SKIPPAA EKAA RIVIÄ JA SAAMME 579 JOISTA YKSI ON ETUNIMI. PYTHONISSA VOISIMME MYÖS SKIPATA EKAN RIVIN JOLLOIN SE SAISI 578
    }
}
