package Task1.Analyzer.Inrefaces;

import java.util.List;
import java.util.Map;

/**
 * Created by invincible_g_d on 12/8/15.
 */
public interface IFrequency {

    StringBuilder textValidator();

    Map<String, Integer> countWordsOfText();

    List<String> findResultWords();
}
