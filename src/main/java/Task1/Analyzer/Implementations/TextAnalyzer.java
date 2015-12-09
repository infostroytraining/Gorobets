package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.*;
import com.beust.jcommander.Parameter;

import java.io.*;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */

public class TextAnalyzer implements ITextAnalyzer {

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
        return tt;
    }


    public void executeMethods() throws IllegalArgumentException {
        long startTime = System.currentTimeMillis();

        IFrequency it = new Frequency();
        IDuplicates du = new Duplicates();
        ILength leng = new Length();
        StringBuilder text = it.textValidator(getStringFromFile());

        if (getEnumTaskType().equals(EnumTaskType.FREQUENCY)) {

            it.findResultWords(text);
        }
        if (getEnumTaskType().equals(EnumTaskType.LENGTH)) {

            leng.findResultWords(text);

        }
        if (getEnumTaskType().equals(EnumTaskType.DUPLICATES)) {

            du.findResultWords(text);

        }
        long spentTime = System.currentTimeMillis() - startTime;
        System.out.println("elapsed time:"+spentTime+" millis");
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
