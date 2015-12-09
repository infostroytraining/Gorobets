package Task1.Frequency;

import Task1.Analyzer.Implementations.Frequency;
import Task1.Analyzer.Implementations.TextAnalyzer;
import com.beust.jcommander.JCommander;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by invincible_g_d on 12/9/15.
 */
public class TextValidatorTest {
    TextAnalyzer textA;
    Frequency fre;
    StringBuilder sb;
    String textExm = "Katherine Mansfield! took a (great) interest\" in Russian: literature, particularly, in the works of !Chekhov.";
    String expectedText = "Katherine Mansfield took a great interest in Russian literature particularly in the works of Chekhov\n";

    @Before
    public void setUpBeforeTest() {
        sb = new StringBuilder(textExm);
        sb.append("\n");
        fre = new Frequency();
        textA = new TextAnalyzer();
        new JCommander(textA, "-i", " /home/invincible_g_d/Programs/IDEA/Gorobets/Task1/Test.txt", "-t", "frequency");

    }

    @Test
    public void tstEqualText() {

        assertEquals(fre.textValidator(sb).toString(),expectedText);

    }

    @Test
    public void tstNotNullText() {

        assertNotNull(fre.textValidator(sb).toString());

    }

    @After

    public void setUpAfterTest() {
        textA = null;
        sb = null;
        fre = null;
        textExm = null;


    }

}
