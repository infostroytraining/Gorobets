package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.IDuplicates;
import com.beust.jcommander.Parameters;

import java.util.*;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */

public class Duplicates implements IDuplicates{


    public Duplicates() {
    }

    public Map<Integer, String> countWordsOfText(StringBuilder text) {

        Map<Integer, String> wordsMap = new HashMap<>();
        Set<String> wordsSet = new HashSet<>();
        String[] textStr = text.toString().split(" ");

        for (int j = 0; j < textStr.length; j++) {

            String word = textStr[j];

            if (word != null && !word.equals(" ")) {
                if (!wordsSet.add(word) && wordsMap.size() < 3) {
                    StringBuilder sb = new StringBuilder(word);
                    sb.reverse();
                    word = sb.toString().toUpperCase();
                    wordsMap.put(word.length(), word);
                }
            }
        }
        return wordsMap;
    }

    public void findResultWords(StringBuilder text) {
        long startTime = System.currentTimeMillis();

        Map<Integer, String> wordsMap = countWordsOfText(text);
        Collection<Integer> keyWords = wordsMap.keySet();
        List<Integer> list = new ArrayList<>(keyWords);
        Collections.sort(list);


        System.out.println("Three first duplicates are:");
        System.out.println(wordsMap.get(list.get(0)));
        System.out.println(wordsMap.get(list.get(1)));
        System.out.println(wordsMap.get(list.get(2)));

        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println("elapsed time:"+spentTime+" millis");
    }
}
