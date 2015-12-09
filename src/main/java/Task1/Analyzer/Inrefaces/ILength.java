package Task1.Analyzer.Inrefaces;

import java.util.Map;

/**
 * Created by invincible_g_d on 12/8/15.
 */
public interface ILength {

    Map<String, Integer> countWordsOfText(StringBuilder text);

    void findResultWords(StringBuilder text);

}
