package Task1.Analyzer;


import Task1.Analyzer.java8.Command;
import Task1.Analyzer.java8.TextAnalyzer;
import Task1.Analyzer.java8.duplicates.Duplicates;
import Task1.Analyzer.java8.duplicates.DuplicatesCommand;
import Task1.Analyzer.java8.duplicates.IDuplicates;
import Task1.Analyzer.java8.frequency.Frequency;
import Task1.Analyzer.java8.frequency.FrequencyCommand;
import Task1.Analyzer.java8.frequency.IFrequency;
import Task1.Analyzer.java8.length.ILength;
import Task1.Analyzer.java8.length.Length;
import Task1.Analyzer.java8.length.LengthCommand;
import com.beust.jcommander.JCommander;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
public class AppRunner {
    public static void main(String[] args) {

        TextAnalyzer ta = new TextAnalyzer();
        JCommander jc = new JCommander(ta);

        if (ta.isHelp()) {
            jc.usage();
        }

        IFrequency iFre = new Frequency();
        IDuplicates iDup = new Duplicates();
        ILength iLen = new Length();

        Command frequencyCommand = new FrequencyCommand(iFre, ta);
        Command duplicatesCommand = new DuplicatesCommand(iDup, ta);
        Command lengthCommand = new LengthCommand(iLen, ta);



        /* Создается карта имя-команда */
        Map<String, Command> map = new HashMap<>();
        map.put("frequency", frequencyCommand);
        map.put("duplicates", duplicatesCommand);
        map.put("length", lengthCommand);

        /* Разбираются аргументы запуска */

        jc.addCommand(frequencyCommand);
        jc.addCommand(duplicatesCommand);
        jc.addCommand(lengthCommand);

        jc.parse(args);

        /* Выполняется указанная в аргументах команда */
        String taskName = jc.getParsedCommand();
        map.get(taskName).executeCommand();

    }

}

