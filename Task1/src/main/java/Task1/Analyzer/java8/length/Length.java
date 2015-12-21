package Task1.Analyzer.java8.length;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */

public class Length implements ILength {

    public Length() {
    }

    /**
     * @param text
     * @param parallel
     */
    public void findResultWords(List<String> text, Boolean parallel) {
        long startTime = System.currentTimeMillis();

        if (parallel) {
            text = Collections.synchronizedList(text);
            List<String> wordsList = text.stream().parallel()
                    .filter(word -> !word.equals(" ") & !word.equals("-") & !word.equals(""))
                    .sorted(Comparator.comparing(String::length)).map(String::trim).distinct()
                    .skip(countWordsOfText(text) - 3).sorted(Comparator.comparing(String::length))
                    .collect(Collectors.toList());
            Collections.reverse(wordsList);
            wordsList.stream().forEach(word -> System.out.println(word + " -> " + word.length()));

        } else {
            List<String> wordsList = text.stream().filter(word -> !word.equals(" ") & !word.equals("-")
                    & !word.equals("")).sorted(Comparator.comparing(String::length)).map(String::trim).distinct()
                    .skip(countWordsOfText(text) - 3).sorted(Comparator.comparing(String::length))
                    .collect(Collectors.toList());

            Collections.reverse(wordsList);
            wordsList.stream().forEach(word -> System.out.println(word + " -> " + word.length()));

        }
        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println("elapsed time: " + spentTime + " millis");
    }

    /**
     * @param text
     * @return
     */
    private long countWordsOfText(List<String> text) {

        return text.stream().filter(word -> !word.equals(" ") && !word.equals("-") && !word.equals(""))
                .map(String::trim).distinct().count();

    }
}


