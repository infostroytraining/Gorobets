package Task1.Analyzer.java8.frequency;


import Task1.Analyzer.java8.Command;
import Task1.Analyzer.java8.TextAnalyzer;
import com.beust.jcommander.Parameters;

/**
 *
 */
@Parameters(commandNames = {"frequency"}, commandDescription = "Find the most two frequent words and print them out " +
        "sorted alphabetically in a reversed order")
public class FrequencyCommand implements Command {

    IFrequency iFre;
    TextAnalyzer textA;

    public FrequencyCommand(IFrequency iFre, TextAnalyzer textA) {
        this.iFre = iFre;
        this.textA = textA;
    }

    /**
     *
     */
    @Override
    public void executeCommand() {

        iFre.findResultWords(textA.textValidator(), textA.isParallel());

    }
}
