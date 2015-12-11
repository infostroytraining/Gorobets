package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.Command;
import Task1.Analyzer.Inrefaces.IDuplicates;
import com.beust.jcommander.Parameters;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
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

    @Override
    public void executeCommand() {


        iDup.findResultWords(textA.textValidator());

    }
}
