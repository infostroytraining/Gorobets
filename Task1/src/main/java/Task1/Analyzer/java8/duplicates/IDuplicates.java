package Task1.Analyzer.java8.duplicates;

import java.util.Map;

/**
 *
 */
public interface IDuplicates {
    /**
     *
     * @param text
     */
    void findResultWords(StringBuilder text);

    /**
     *
     * @param text
     * @return
     */
    Map<Integer, String> countWordsOfText(StringBuilder text);
}
