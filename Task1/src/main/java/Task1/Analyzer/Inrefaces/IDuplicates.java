package Task1.Analyzer.Inrefaces;

import java.util.Map;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
public interface IDuplicates {

    void findResultWords(StringBuilder text);

    Map<Integer, String> countWordsOfText(StringBuilder text);
}
