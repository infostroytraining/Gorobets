package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.Command;
import Task1.Analyzer.Inrefaces.IFrequency;
import Task1.Analyzer.Inrefaces.ITextAnalyzer;
import com.beust.jcommander.Parameters;

/**
 * Created by invincible_g_d on 12/10/15.
 */
@Parameters(commandNames = {"frequency"}, commandDescription = "Find words by their length and display them")
public class FrequencyCommand implements Command{

    IFrequency iFre;
    TextAnalyzer iText;

    public FrequencyCommand(IFrequency iFre, TextAnalyzer iText) {
        this.iFre = iFre;
        this.iText = iText;
    }

    @Override
    public void executeCommand() {

        iFre.findResultWords(iText.textValidator());

    }
}
