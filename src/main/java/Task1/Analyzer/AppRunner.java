package Task1.Analyzer;

import com.beust.jcommander.JCommander;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by invincible_g_d on 12/8/15.
 */
public class AppRunner {
    public static void main(String[] args) {

        TextAnalyzer ta = new TextAnalyzer();
        JCommander jc = new JCommander(ta);

//        args = new String[]{"-i", "/home/invincible_g_d/Programs/IDEA/Gorobets/Task1/Input.txt", "-t", "frequency"};//запустить с командной строки
        jc.parse(args);
        if (ta.isHelp()) {
            jc.usage();
        }

        ta.executeMethods();

    }
}
