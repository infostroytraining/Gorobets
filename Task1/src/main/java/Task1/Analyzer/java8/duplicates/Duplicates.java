package Task1.Analyzer.java8.duplicates;

import java.util.*;

/**
 *
 */

public class Duplicates implements IDuplicates {


    public Duplicates() {
    }

    /**
     * @param text
     * @return
     */
    public void findResultWords(List<String> text, Boolean parallel) {

        long startTime = System.currentTimeMillis();

        Set<String> wordsSet = new HashSet<>();


        if (parallel) {
            text = Collections.synchronizedList(text);
            text.stream().parallel().filter(word -> !word.equals(" ") & !word.equals("") & !word.equals("-"))
                    .filter(word -> !wordsSet.add(word)).map(String::trim).distinct().limit(3)
                    .map(word -> new StringBuilder(word).reverse().toString()).map(String::toUpperCase)
                    .sorted(Comparator.comparing(String::length)).forEach(System.out::println);
        } else {

            text.stream().filter(word -> !word.equals(" ") & !word.equals("") & !word.equals(""))
                    .filter(word -> !wordsSet.add(word)).map(String::trim).distinct().limit(3)
                    .map(word -> new StringBuilder(word).reverse().toString()).map(String::toUpperCase)
                    .sorted(Comparator.comparing(String::length)).forEach(System.out::println);

        }
        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println("elapsed time: " + spentTime + " millis");
    }


}
