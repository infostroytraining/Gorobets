package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.Command;
import Task1.Analyzer.Inrefaces.IDuplicates;
import Task1.Analyzer.Inrefaces.ILength;
import Task1.Analyzer.Inrefaces.ITextAnalyzer;
import com.beust.jcommander.Parameters;

/**
 * Created by invincible_g_d on 12/10/15.
 */
@Parameters(commandNames = {"length"}, commandDescription = "Find words by their length and display them")
public class LengthCommand implements Command {

    ILength iLen;
    TextAnalyzer iText;


    public LengthCommand(ILength iLen, TextAnalyzer iText ) {
        this.iText = iText;
        this.iLen = iLen;
    }

    @Override
    public void executeCommand() {

        iLen.findResultWords(iText.textValidator());

    }
}
