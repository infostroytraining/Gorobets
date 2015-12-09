package Task1.Analyzer.Inrefaces;

import java.util.Map;

/**
 * Created by invincible_g_d on 12/8/15.
 */
public interface IDuplicates {

    void findResultWords(StringBuilder text);

    Map<Integer, String> countWordsOfText(StringBuilder text);
}
