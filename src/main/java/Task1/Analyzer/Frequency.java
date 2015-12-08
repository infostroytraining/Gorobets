package Task1.Analyzer;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.*;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
@Parameters(commandNames = {"frequency"})
public class Frequency implements Runnable {

    private StringBuilder text;

    public Frequency(StringBuilder text) {
        this.text = text;
    }

    /**
     *
     * @return
     */
    public List<String> findTwoFrequentWords() {
        List<String> resultWords = new ArrayList<>() ;

        Map<String, Integer> words = countWordsOfText();
        Collection<Integer> tr = words.values();
        List<Integer> list = new ArrayList<>(tr);
        Collections.sort(list);


        Collection<String> keyWords = words.keySet();
        for (int i = list.size()-1; i >list.size()-3 ; i--) {
            Integer value =list.get(i) ;
            for (String key : keyWords) {
                Integer val = words.get(key);
                if (key != null) {
                    if (value.equals(val)) {

                            resultWords.add(key);

                    }
                }
            }
        }
        Collections.reverse(resultWords);
        System.out.println("Two most frequent words are:");
        System.out.println(resultWords.get(0)+"->"+ words.get(resultWords.get(0)));
        System.out.println(resultWords.get(1)+"->"+ words.get(resultWords.get(1)));


        return resultWords;
    }

    /**
     *
     */
    @Override
    public void run() {
         findTwoFrequentWords();
    }

    /**
     *
     * @return
     */
    public Map<String, Integer> countWordsOfText() {

        Map<String, Integer> words = new HashMap<>();
        StringBuilder text = textValidator();


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

    /**
     *
     * @return
     */
    public StringBuilder textValidator() {

        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '.' || text.charAt(i) == ',' || text.charAt(i) == '!'
                    || text.charAt(i) == '?' || text.charAt(i) == '—'
                    || text.charAt(i) == '(' || text.charAt(i) == ')' || text.charAt(i) == '"'
                    || text.charAt(i) == ';' || text.charAt(i) == ':') {
                text.deleteCharAt(i);
            }
            if (text.charAt(i) == '\"' || text.charAt(i) == '\"') {
                text.deleteCharAt(i);
            }
        }

        if (text.charAt(text.length() - 1) == '.') {
            text.deleteCharAt(text.length() - 1);
        }

        return text;
    }
}



