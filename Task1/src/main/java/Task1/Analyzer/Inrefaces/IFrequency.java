package Task1.Analyzer.Inrefaces;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface IFrequency {
    /**
     *
     * @param text
     * @return
     */
    Map<String, Integer> countWordsOfText( StringBuilder text);

    /**
     *
     * @param text
     */
    void findResultWords( StringBuilder text);
}
