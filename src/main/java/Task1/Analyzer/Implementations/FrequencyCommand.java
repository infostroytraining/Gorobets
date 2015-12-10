package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.Command;
import Task1.Analyzer.Inrefaces.IFrequency;
import Task1.Analyzer.Inrefaces.ITextAnalyzer;
import com.beust.jcommander.Parameters;

/**
 * Created by invincible_g_d on 12/10/15.
 */
@Parameters(commandNames = {"frequency"})
public class FrequencyCommand implements Command{

    IFrequency iFre;
    ITextAnalyzer iText;

    public FrequencyCommand(IFrequency iFre) {
        this.iFre = iFre;
    }

    @Override
    public void executeCommand() {
       iText = new TextAnalyzer();
        iFre = new Frequency();
        iFre.findResultWords(iText.textValidator());

    }
}
