package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.IFrequency;
import com.beust.jcommander.Parameters;

import java.util.*;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */

public class Frequency implements IFrequency {

    public Frequency() {
    }

    /**
     * This method find result most frequency words using a Map
     * which it get from a "countWordOfText" method.
     * There is this Map sorts and get necessary words
     * @param text -it's validated
     */
    public void findResultWords(StringBuilder text) {
        long startTime = System.currentTimeMillis();

        List<String> resultWords = new ArrayList<>();
        Map<String, Integer> words = countWordsOfText(text);
        Collection<Integer> tr = words.values();
        List<Integer> list = new ArrayList<>(tr);
        Collections.sort(list);

        Set<Map.Entry<String, Integer>> entrySet = words.entrySet();
        for (int i = list.size() - 1; i > list.size() - 3; i--) {
            Integer value = list.get(i);
            for (Map.Entry<String, Integer> pair : entrySet) {
                if (pair != null) {
                    if (value.equals(pair.getValue())) {
                        resultWords.add(pair.getKey());
                    }
                }
            }
        }

        Collections.reverse(resultWords);
        System.out.println("Two most frequent words are:");
        System.out.println(resultWords.get(0) + "->" + words.get(resultWords.get(0)));
        System.out.println(resultWords.get(1) + "->" + words.get(resultWords.get(1)));

        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println("elapsed time:" + spentTime + " millis");
    }

    /**
     * @return
     */
    public Map<String, Integer> countWordsOfText(StringBuilder text) {

        Map<String, Integer> words = new HashMap<>();

        String[] textStr = text.toString().split(" ");

        for (int j = 0; j < textStr.length; j++) {

            String word = textStr[j];
            Integer value = words.get(word);

            if (value == null) {
                words.put(word, 1);
            } else {
                words.put(word, value + 1);
            }
        }

        return words;
    }

}



