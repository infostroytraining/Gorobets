package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.ILength;
import com.beust.jcommander.Parameters;

import java.util.*;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
@Parameters(commandNames = {"length"})
public class Length implements ILength {

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

        List<String> resultWords = new ArrayList<>();
        Map<String, Integer> wordsMap = countWordsOfText(text);

        Collection<Integer> words = wordsMap.values();
        List<Integer> list = new ArrayList<>(words);
        Collections.sort(list);
        Collections.reverse(list);


        Collection<String> keyWords = wordsMap.keySet();
        for (int i = 0; i < 4; i++) {
            Integer value = list.get(i);
            for (String key : keyWords) {
                Integer val = wordsMap.get(key);
                if (key != null) {
                    if (value.equals(val) && resultWords.size() < 3) {

                        resultWords.add(key);

                    }
                }
            }
        }


        System.out.println("Three longest words are:");
        System.out.println(resultWords.get(0) + "->" + list.get(0));
        System.out.println(resultWords.get(1) + "->" + list.get(1));
        System.out.println(resultWords.get(2) + "->" + list.get(2));


    }

}
