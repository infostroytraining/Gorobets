package Task1.Analyzer.Inrefaces;

import java.util.List;
import java.util.Map;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
public interface IFrequency {

    Map<String, Integer> countWordsOfText( StringBuilder text);

    void findResultWords( StringBuilder text);
}
