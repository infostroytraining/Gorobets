package Task1.Analyzer.Inrefaces;

import java.util.List;
import java.util.Map;

/**
 * Created by invincible_g_d on 12/8/15.
 */
public interface IFrequency {

    StringBuilder textValidator( StringBuilder text);

    Map<String, Integer> countWordsOfText( StringBuilder text);

    void findResultWords( StringBuilder text);
}
