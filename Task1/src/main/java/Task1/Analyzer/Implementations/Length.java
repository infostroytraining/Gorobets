package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.ILength;
import com.beust.jcommander.Parameters;

import java.util.*;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */

public class Length implements ILength {

    public Length() {
    }

    public Map<String, Integer> countWordsOfText(StringBuilder text) {


        Map<String, Integer> wordsMap = new HashMap<>();
        String[] textStr = text.toString().split(" ");

        for (int j = 0; j < textStr.length; j++) {

            String word = textStr[j];

            if (word != null && !word.equals(" ")) {

                wordsMap.put(word, word.length());

            }
        }
        return wordsMap;
    }

    public void findResultWords(StringBuilder text) {
        long startTime = System.currentTimeMillis();

        List<String> resultWords = new ArrayList<>();
        Map<String, Integer> wordsMap = countWordsOfText(text);

        Collection<Integer> words = wordsMap.values();
        List<Integer> list = new ArrayList<>(words);
        Collections.sort(list);
        Collections.reverse(list);

        Set<Map.Entry<String, Integer>> entrySet = wordsMap.entrySet();
        for (int i = 0; i < 4; i++) {
            Integer value = list.get(i);
            for (Map.Entry<String, Integer> pair : entrySet) {
                if (pair != null) {
                    if (value.equals(pair.getValue()) && resultWords.size() < 3) {
                        resultWords.add(pair.getKey());
                    }
                }
            }
        }



        System.out.println("Three longest words are:");
        System.out.println(resultWords.get(0) + "->" + list.get(0));
        System.out.println(resultWords.get(1) + "->" + list.get(1));
        System.out.println(resultWords.get(2) + "->" + list.get(2));

        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println("elapsed time:"+spentTime+" millis");
    }

}
