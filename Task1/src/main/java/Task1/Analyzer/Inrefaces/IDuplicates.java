package Task1.Analyzer.Inrefaces;

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
