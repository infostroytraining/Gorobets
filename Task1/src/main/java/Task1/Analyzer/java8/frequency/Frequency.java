package Task1.Analyzer.java8.frequency;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 *
 */
public class Frequency implements IFrequency {

    public Frequency() {
    }

    /**
     * This method find result most frequency words .
     * There is the Map sorts and gets necessary words
     *
     * @param text     -it's validated text
     * @param parallel
     */

    public void findResultWords(List<String> text, Boolean parallel) {

        long startTime = System.currentTimeMillis();

        Map<String, Long> words;


        if (parallel) {
            text = Collections.synchronizedList(text);
            words = text.stream().parallel().filter(word -> !word.equals(" ") & !word.equals(""))
                    .map(String::trim).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            Set<Map.Entry<String, Long>> set = words.entrySet();

            List<Map.Entry<String, Long>> listOfMap = set.stream().parallel()
                    .sorted(Comparator.comparing(Map.Entry::getValue))
                    .skip(set.size() - 2).sorted(Comparator.comparing(Map.Entry::getKey))
                    .collect(Collectors.toList());

            Collections.reverse(listOfMap);
            listOfMap.stream().parallel().forEach(el -> System.out.println(el.getKey() + " -> " + el.getValue()));

        } else {

            words = text.stream().filter(word -> !word.equals(" ") & !word.equals("")).map(String::trim)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            Set<Map.Entry<String, Long>> set = words.entrySet();

            List<Map.Entry<String, Long>> listOfMap = set.stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue))
                    .skip(set.size() - 2).sorted(Comparator.comparing(Map.Entry::getKey))
                    .collect(Collectors.toList());

            Collections.reverse(listOfMap);
            listOfMap.stream().forEach(el -> System.out.println(el.getKey() + " -> " + el.getValue()));

        }
        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println("elapsed time: " + spentTime + " millis");
    }

}



