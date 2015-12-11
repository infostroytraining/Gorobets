package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.Command;
import Task1.Analyzer.Inrefaces.IFrequency;
import com.beust.jcommander.Parameters;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
@Parameters(commandNames = {"frequency"}, commandDescription = "Find the most two frequent words and print them out " +
        "sorted alphabetically in a reversed order")
public class FrequencyCommand implements Command{

    IFrequency iFre;
    TextAnalyzer textA;

    public FrequencyCommand(IFrequency iFre, TextAnalyzer textA) {
        this.iFre = iFre;
        this.textA = textA;
    }

    @Override
    public void executeCommand() {

        iFre.findResultWords(textA.textValidator());

    }
}
