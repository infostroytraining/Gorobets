package Task1.Analyzer.Implementations;


import Task1.Analyzer.Inrefaces.Command;
import Task1.Analyzer.Inrefaces.IDuplicates;
import Task1.Analyzer.Inrefaces.IFrequency;
import Task1.Analyzer.Inrefaces.ILength;
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

