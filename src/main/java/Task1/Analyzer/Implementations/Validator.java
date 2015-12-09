package Task1.Analyzer.Implementations;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by invincible_g_d on 12/8/15.
 */
public abstract class Validator {
//
//    TextAnalyzer ta = new TextAnalyzer();
//    String pathToFile = ta.getPathToFile();
//
//    private StringBuilder text = getStringFromFile();
//
//    public Validator() {
//    }
//
//
//
//
//    StringBuilder textValidator() {
//        for (int i = 0; i < text.length() - 1; i++) {
//            if (text.charAt(i) == '.' || text.charAt(i) == ',' || text.charAt(i) == '!'
//                    || text.charAt(i) == '?' || text.charAt(i) == '—'
//                    || text.charAt(i) == '(' || text.charAt(i) == ')' || text.charAt(i) == '"'
//                    || text.charAt(i) == ';' || text.charAt(i) == ':') {
//                text.deleteCharAt(i);
//            }
//            if (text.charAt(i) == '\"' || text.charAt(i) == '\"') {
//                text.deleteCharAt(i);
//            }
//        }
//
//        if (text.charAt(text.length() - 1) == '.') {
//            text.deleteCharAt(text.length() - 1);
//        }
//
//        return text;
//    }
//
//    public StringBuilder getStringFromFile() {
//
//        StringBuilder sb = new StringBuilder();
//        BufferedReader buff = null;
//
//        try {
//            buff = new BufferedReader(new FileReader(pathToFile));
//
//            for (; ; ) {
//                String fileContent = buff.readLine();
//
//                if (fileContent == null) {
//                    break;
//                }
//                sb.append(fileContent).append("\n");
//            }
//
//        } catch (IOException e) {
//            e.getMessage();
//        } finally {
//            closeStream(buff);
//        }
//
//        return sb;
//    }
//
//    private void closeStream(Closeable stream) {
//        if (stream != null) {
//            try {
//                stream.close();
//            } catch (IOException e) {
//                e.getMessage();
//            }
//
//        }
//    }
//
//    abstract Map<String, Integer> countWordsOfText(StringBuilder text);
//
//    abstract void findResultWords(StringBuilder text);
}

