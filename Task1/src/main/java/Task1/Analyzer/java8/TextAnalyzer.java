package Task1.Analyzer.java8;

import Task1.Analyzer.Inrefaces.ITextAnalyzer;
import com.beust.jcommander.Parameter;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */

public class TextAnalyzer implements ITextAnalyzer {


//    @Parameter(names = {"-t", "--task"}, description = "Task to execute", arity = 1, required = true, converter = TaskTypeConverter.class)
//    EnumTaskType tasks;

    @Parameter(names = {"-i", "--input"}, description = "Path to the input file", required = true)
    String pathToFile;

    @Parameter(names = {"-h", "--help"}, description = "A detailed information of how to use this app", help = true)
    boolean help;

//    public EnumTaskType getTasks() {
//        return tasks;
//    }

    public String getPathToFile() {
        return pathToFile;
    }

    public boolean isHelp() {
        return help;
    }



    public StringBuilder textValidator() {


        StringBuilder text = getStringFromFile();

        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == '.' || text.charAt(i) == ',' || text.charAt(i) == '!'
                    || text.charAt(i) == '?' || text.charAt(i) == '—'
                    || text.charAt(i) == '(' || text.charAt(i) == ')' || text.charAt(i) == '"'
                    || text.charAt(i) == ';' || text.charAt(i) == ':') {
                text.deleteCharAt(i);
            }
            if (text.charAt(i) == '\"' || text.charAt(i) == '\"') {
                text.deleteCharAt(i);
            }
        }

        if (text.charAt(text.length() - 1) == '.') {
            text.deleteCharAt(text.length() - 1);
        }

        return text;
    }

    private StringBuilder getStringFromFile() {

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
