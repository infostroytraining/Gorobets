package Task1.Analyzer.java8.duplicates;



import Task1.Analyzer.java8.Command;
import Task1.Analyzer.java8.TextAnalyzer;
import com.beust.jcommander.Parameters;

/**
 *
 */
@Parameters(commandNames = {"duplicates"}, commandDescription = "Find first three words which have duplicates and print" +
        " them inversely (e.g. map -> pam) in the upper case sorted by length in ascending order")
public class DuplicatesCommand implements Command {

    IDuplicates iDup;
    TextAnalyzer textA;

    public DuplicatesCommand(IDuplicates iDup, TextAnalyzer textA) {
        this.iDup = iDup;
        this.textA = textA;
    }

    /**
     *
     */
    @Override
    public void executeCommand() {

        iDup.findResultWords(textA.textValidator(),textA.isParallel());

    }
}
