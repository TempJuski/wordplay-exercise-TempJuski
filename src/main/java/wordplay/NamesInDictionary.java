package wordplay;

//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.openjdk.jmh.annotations.Benchmark;
import java.util.TreeSet;

//import wordplay.benchmark.BenchmarkConfig;
import wordplay.io.DictionaryReader;
import wordplay.io.NamesReader;

public class NamesInDictionary /*extends BenchmarkConfig*/{

    // Tässä on nyt vähän turhaa juoksua eri tietotyyppien välillä, mutta jätän sen nyt näin.
    private static List<String> finnishNames = NamesReader.readFirstNames();
    private static List<String> finnishWords = DictionaryReader.readFinnishWords().stream().map(x -> x.toLowerCase()).toList(); // laitetaan kaikki lowercase
    private static HashSet<String> hashNames = new HashSet<>(finnishNames);
    private static HashSet<String> hashWords = new HashSet<>(finnishWords);
    private static TreeSet<String> treeSet = new TreeSet<>(hashNames);

    @Benchmark
    public static void benchHash() { 
        int i = 0;
        for (String name : treeSet) {
            if (hashWords.contains(name.toLowerCase())) {
                i++;
                System.out.printf("%d -- %s\n", i, name);
            }
        }
    }

//    @Benchmark
//    public static void benchList() { // Tämä tuottaa 654 tulosta. Set poistaa tuplat, jota tässä ei ole tehty. Poistamalla ne tulisi 654 - 75 = 579 tulosta
//        for (String name : finnishNames) {
//            if (finnishWords.contains(name.toLowerCase())) {
//                System.out.println(name);
//            }
//        }
//    }

// Ekan COMMITIN AIKAINEN TESTI: Yllä olevien benchmarkkien tulokset printit kommentoituna ulos ja ilman rivien laskua. Listalla on selkeästi hitaampi vauhti. Toki aikaa saisi pois ottamalla tuplat pois,
// mutta listan nopeus ei riittäisi siltikään voittamaan HashSettiä.
//     Benchmark                    Mode  Cnt   Score    Error  Units
//     NamesInDictionary.benchHash  avgt    5   0.001 ±  0.001   s/op
//     NamesInDictionary.benchList  avgt    5  12.044 ±  7.205   s/op

// Tässä uusi testi treesetillä
//     Benchmark                    Mode  Cnt  Score    Error  Units
//     NamesInDictionary.benchHash  avgt    5  0.001 ±  0.001   s/op


//public static List<String> readNames() {
//    Path nimet = Path.of("data", "nimet.txt");
//    try {
//        return Files.readAllLines(nimet, StandardCharsets.UTF_8);
//    } catch (IOException e) {
//        throw new RuntimeException(e);
//    }
//
//}

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
 
        // Manuaalisesti poistin EOF newlinen names.txt tiedostosta ja sitten ajoin diff -i data/names.txt data/nimet.txt jonka tulostus oli > jussi
        // ELikkä jussi puuttui. Tämä johtui siitä, että jussi oli kaikkisanat.txt tiedostossa isolla eli Jussi
//        try {
//            FileWriter writer = new FileWriter("data/names.txt");
//            for (String name : treeSet) {
//                if (hashWords.contains(name.toLowerCase())) {
//                    writer.append(name + "\n");
//                }
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.printf("Sanat: %d, Nimet: %d\n", finnishWords.size(), finnishNames.size());                        Tämä tulostaa -> Sanat: 93086, Nimet: 15665
//        System.out.printf("Sanat ilman tuplia: %d, Nimet ilman tuplia: %d\n", hashWords.size(), hashNames.size());    Tämä tulostaa -> Sanat ilman tuplia: 93086, Nimet ilman tuplia: 15169
    }
}
