package Task1.Analyzer.java8.length;


import Task1.Analyzer.java8.Command;
import Task1.Analyzer.java8.TextAnalyzer;
import com.beust.jcommander.Parameters;

/**
 *
 */
@Parameters(commandNames = {"length"}, commandDescription = "Find first three longest words and print this words along " +
        "with the their length sorted them in a descend order by the total number of letters each word contains")
public class LengthCommand implements Command {

    ILength iLen;
    TextAnalyzer textA;


    public LengthCommand(ILength iLen, TextAnalyzer textA) {
        this.textA = textA;
        this.iLen = iLen;
    }

    /**
     * 
     */
    @Override
    public void executeCommand() {

        iLen.findResultWords(textA.textValidator(),textA.isParallel());

    }
}
