package Task1.Analyzer;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
//@Parameters()   ?????????????????????????
public class TextAnalyzer {
    /*   The goal is to implement a ‘text-analyzer’. It must be a shell application so one can run it once and perform one task by another.

       Step-by-step
       1.Implement simple shell app so one can run it once and give it some arguments.
       The signature of input parameters:

               1. -i (--input) - path to the input file (e.g. C:\Program Files\Java\input.txt). Type: String, Required: true
               2. -t (--task) – task to execute. Type: Enum, Required: true, Permitted values: frequency, length, duplicates
       3. -- help –a detailed information of how to use your app

       Note! As you may have noticed, the parameters have several names.

       I suggest you using JCommander to parse input arguments, though it is not ‘a must’ and you may choose any tool you want.

               2. Your app should take file and other input parameters and be able to do several tasks on it.

       Tasks:

               1.Find the most two frequent words and print them out sorted alphabetically in a reversed order. (Task name: frequency).

       good -> 23
       allow -> 2

               2. Find first three longest words and print this words along with the their length sorted them in a descend order by the total number of letters each word contains (task name: length)

       battle -> 6
       map -> 3
       a – 1

               3. Find first three words which have duplicates and print them inversely (e.g. map -> pam) in the upper case sorted by length in ascending order. (task name: duplicates)
       PAM
               WOLLA
       STNEMUGRA

       Note! Each output should contain ‘elapsed time’ information in milliseconds. like:
       battle -> 6
       map -> 3
       a – 1
       elapsed time: 400 millis

       Expected:
               1. Good working app covered with Junit tests
       2. Do not forget to add input.txt file to your repo as well. */
    @Parameter(names = {"-t", "--task"}, description = "Task to execute", arity = 3, required = true)
    private List<String> tasks;

    @Parameter(names = {"-i", "--input"}, description = "Path to the input file", required = true)
    private String pathToFile;

    @Parameter(names = "--help", description = "A detailed information of how to use this app", help = true)
    private boolean help;


    public static void main(String[] args) {
        TextAnalyzer ta = new TextAnalyzer();
        JCommander jc = new JCommander(ta);
        jc.parse(args);
    }

    public List<Enum> getEnumTaskType() throws IllegalArgumentException {

        List<Enum> enumTaks = new ArrayList<Enum>();

        for (String task : tasks) {
            EnumTaskType tt = EnumTaskType.fromString(task);
            enumTaks.add(tt);
        }

        return enumTaks;
    }

    public StringBuilder getStringFromFile() {

        StringBuilder sb = new StringBuilder();
        File file;
        BufferedReader buff;
        try {
            buff = new BufferedReader(new FileReader(pathToFile));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

        }
        //pathToFile --/home/invincible_g_d/Programs/IDEA/Gorobets/Task1/Input.txt
// использую стримы reader с ксв файла ,запишу текст с файла в  стрингбилдер
        return sb;
    }

    private void closeStream(Closeable stream) {//метод закрытия потока вывода,если он существет
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {//ловит исключения ,если поток не закрывается и выдает причину и сообщение об этом
                e.getCause();
                e.getMessage();
            }

        }
    }
}
