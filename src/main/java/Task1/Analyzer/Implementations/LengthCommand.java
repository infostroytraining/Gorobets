package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.Command;
import Task1.Analyzer.Inrefaces.IDuplicates;
import Task1.Analyzer.Inrefaces.ILength;
import Task1.Analyzer.Inrefaces.ITextAnalyzer;
import com.beust.jcommander.Parameters;

/**
 * Created by invincible_g_d on 12/10/15.
 */
@Parameters(commandNames = {"length"})
public class LengthCommand implements Command {

    ILength iLen;
    ITextAnalyzer iText;

    public LengthCommand(ILength iLen) {
        this.iLen = iLen;
    }

    @Override
    public void executeCommand() {
        iLen = new Length();
        iText = new TextAnalyzer();

        iLen.findResultWords(iText.textValidator());

    }
}
