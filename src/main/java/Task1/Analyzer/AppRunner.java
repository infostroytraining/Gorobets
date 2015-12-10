package Task1.Analyzer;

import Task1.Analyzer.Implementations.*;
import Task1.Analyzer.Inrefaces.Command;
import Task1.Analyzer.Inrefaces.IDuplicates;
import Task1.Analyzer.Inrefaces.IFrequency;
import Task1.Analyzer.Inrefaces.ILength;
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

//       args = new String[]{"-i", "/home/invincible_g_d/Programs/IDEA/Gorobets/Task1/Input.txt", "-t", "frequency"};//запустить с командной строки
//        jc.parse(args);
        if (ta.isHelp()) {
            jc.usage();
        }

        IFrequency iFre = new Frequency();
        IDuplicates iDup = new Duplicates();
        ILength iLen  = new Length();

        Command switchFreq = new FrequencyCommand(iFre);
        Command switchDuplic = new DuplicatesCommand(iDup);
        Command switchLength = new LengthCommand(iLen);

//        CommandSwitcher switcher = new CommandSwitcher(switchFreq,switchLength,switchDuplic);
//
//        switcher.duplicatesDo();
//        switcher.frequencyDo();
//        switcher.lengthDo();
//        ta.executeMethods();
 /* Создается карта имя-команда */
        Map<String, Command> map = new HashMap<>();
        map.put("frequency", switchFreq);
        map.put("duplicates", switchDuplic);
        map.put("length", switchLength);

        /* Разбираются аргументы запуска */

        jc.addCommand(switchFreq);
        jc.addCommand(switchDuplic);
        jc.addCommand(switchLength);

        jc.parse(args);

        /* Выполняется указанная в аргументах команда */
        String taskName = jc.getParsedCommand();
        System.out.println(taskName);
        map.get("frequency").executeCommand();

    }

}

