package Task1.Analyzer;

import com.beust.jcommander.JCommander;

/**
 * Created by invincible_g_d on 12/8/15.
 */
public class AppRunner {
    public static void main(String[] args) {
        TextAnalyzer ta = new TextAnalyzer();
        JCommander jc = new JCommander(ta);
        jc.parse(args);

        ta.getStringFromFile();
    }
}
