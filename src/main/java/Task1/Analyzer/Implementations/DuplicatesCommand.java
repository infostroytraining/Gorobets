package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.Command;
import Task1.Analyzer.Inrefaces.IDuplicates;
import Task1.Analyzer.Inrefaces.ITextAnalyzer;
import com.beust.jcommander.Parameters;

/**
 * Created by invincible_g_d on 12/10/15.
 */
@Parameters(commandNames = {"duplicates"})
public class DuplicatesCommand implements Command {

    IDuplicates iDup;
    ITextAnalyzer iText;

    public DuplicatesCommand(IDuplicates iDup) {
        this.iDup = iDup;
    }

    @Override
    public void executeCommand() {

        iDup = new Duplicates();
        iText = new TextAnalyzer();

        iDup.findResultWords(iText.textValidator());

    }
}
