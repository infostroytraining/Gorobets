package Task1.Analyzer.java8;

import com.beust.jcommander.Parameter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 */

public class TextAnalyzer {

    @Parameter(names = {"-i", "--input"}, description = "Path to the input file", required = true)
    private String pathToFile;


    @Parameter(names = {"-p", "--parallel"}, description = "Parallel executing of tasks", required = false)
    private boolean parallel;

    @Parameter(names = {"-h", "--help"}, description = "A detailed information of how to use this app", help = true)
    private boolean help;


    public String getPathToFile() {
        return pathToFile;
    }

    public boolean isHelp() {
        return help;
    }

    public boolean isParallel() {
        return parallel;
    }


    public List<String> textValidator() {


        StringBuilder text = getStringFromFile();

        String[] textStr = text.toString().split(" ");

       List<String> wordList =  Arrays.stream(textStr).filter(word -> !Objects.equals(word, "")
               && !Objects.equals(word, ".") && !Objects.equals(word, ",")
               && !Objects.equals(word, ":") && !Objects.equals(word, ";")
               && !Objects.equals(word, "?") && !Objects.equals(word, "\"")
               && !Objects.equals(word, "!") && !Objects.equals(word, " ")
               && !Objects.equals(word, "(") && !Objects.equals(word, ")")
               && !Objects.equals(word, "-")).map(String::trim)
               .map(word -> word.replace(".", "")).map(word -> word.replace(",", ""))
               .map(word -> word.replace("!", "")).map(word -> word.replace("\\?", ""))
               .map(word -> word.replace("\"", "")).map(word -> word.replace(" ", ""))
               .map(word -> word.replace("-", "")).map(word -> word.replace(":", ""))
               .map(word -> word.replace("\\)", "")).map(word -> word.replace("\\(", ""))
               .map(word -> word.replace(";", "")).collect(Collectors.toList());



        return wordList;
    }

    private StringBuilder getStringFromFile() {

        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader buff = new BufferedReader(new FileReader(pathToFile))) {
            stringBuilder = buff.lines().filter(string -> string != null && !string.isEmpty())
                    .map(StringBuilder::new).reduce(StringBuilder::append).get();

        } catch (IOException e) {
            //LOGGER
            e.getMessage();
        }

        return stringBuilder;
    }

}
