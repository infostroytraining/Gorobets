package Task1.Analyzer;

import java.util.List;
import java.util.Map;

/**
 * Created by invincible_g_d on 12/8/15.
 */
public interface ITasksMethodsFrequen {

    StringBuilder textValidator();

    Map<String, Integer> countWordsOfText();

    List<String> findResultWords();
}
