package Task1.Analyzer.Inrefaces;

import java.util.Map;

/**
 *
 */
public interface ILength {
    /**
     *
     * @param text
     * @return
     */
    Map<String, Integer> countWordsOfText(StringBuilder text);

    /**
     *
     * @param text
     */
    void findResultWords(StringBuilder text);

}
