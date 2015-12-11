package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.Command;
import Task1.Analyzer.Inrefaces.ILength;
import com.beust.jcommander.Parameters;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
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

    @Override
    public void executeCommand() {

        iLen.findResultWords(textA.textValidator());

    }
}
