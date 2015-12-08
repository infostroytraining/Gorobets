package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.IFrequency;
import Task1.Analyzer.Inrefaces.ITextAnalyzer;
import com.beust.jcommander.Parameter;

import java.io.*;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
//@Parameters()   ?????????????????????????
public class TextAnalyzer implements ITextAnalyzer {

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


    @Parameter(names = {"-t", "--task"}, description = "Task to execute", arity = 1, required = true)
    private String tasks;

    @Parameter(names = {"-i", "--input"}, description = "Path to the input file", required = true)
    private String pathToFile;

    @Parameter(names = {"-h", "--help"}, description = "A detailed information of how to use this app", help = true)
    private boolean help;

    public String getTasks() {
        return tasks;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public boolean isHelp() {
        return help;
    }

    public Enum getEnumTaskType() throws IllegalArgumentException {

        EnumTaskType tt = EnumTaskType.fromString(tasks);
        System.out.println(tt);
        return tt;
    }


    public void executeMethods() throws IllegalArgumentException {
        long startTume = System.currentTimeMillis();

        StringBuilder sb = getStringFromFile();
        IFrequency it = new Frequency(sb);
        Duplicates du = new Duplicates();

        Length leng = new Length();

        if (getEnumTaskType().equals(EnumTaskType.FREQUENCY)) {

            it.findResultWords();
        }
        if (getEnumTaskType().equals(EnumTaskType.LENGTH)) {

            leng.findResultWords(it.textValidator());

        }
        if (getEnumTaskType().equals(EnumTaskType.DUPLICATES)) {

            du.findResultWords(it.textValidator());

        }
        long spentTime = System.currentTimeMillis() - startTume;
        System.out.println(spentTime);
    }

    public StringBuilder getStringFromFile() {

        StringBuilder sb = new StringBuilder();
        BufferedReader buff = null;

        try {
            buff = new BufferedReader(new FileReader(pathToFile));

            for (; ; ) {
                String fileContent = buff.readLine();

                if (fileContent == null) {
                    break;
                }
                sb.append(fileContent).append("\n");
            }

        } catch (IOException e) {
            e.getMessage();
        } finally {
            closeStream(buff);
        }

        return sb;
    }

    private void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.getMessage();
            }

        }
    }
}
