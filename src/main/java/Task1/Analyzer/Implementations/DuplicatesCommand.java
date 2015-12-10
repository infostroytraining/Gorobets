package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.Command;
import Task1.Analyzer.Inrefaces.IDuplicates;
import Task1.Analyzer.Inrefaces.ITextAnalyzer;
import com.beust.jcommander.Parameters;

/**
 * Created by invincible_g_d on 12/10/15.
 */
@Parameters(commandNames = {"duplicates"}, commandDescription = "Find words by their length and display them")
public class DuplicatesCommand implements Command {

    IDuplicates iDup;
    TextAnalyzer iText;

    public DuplicatesCommand(IDuplicates iDup, TextAnalyzer iText) {
        this.iDup = iDup;
        this.iText = iText;
    }

    @Override
    public void executeCommand() {


        iDup.findResultWords(iText.textValidator());

    }
}
