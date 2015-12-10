package Task1.TaskAnalyzer;

import Task1.Analyzer.Implementations.TextAnalyzer;
import com.beust.jcommander.JCommander;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by invincible_g_d on 12/9/15.
 */
public class JcommanderTest {
    TextAnalyzer textA;
    StringBuilder sb;
    BufferedReader buff;
    String textExm = "Katherine Mansfield took a great interest in Russian literature, particularly in the works of Chekhov.\n";

    @Before
    public void setUpBeforeTest() {

        textA = new TextAnalyzer();
        new JCommander(textA, "-i", " /home/invincible_g_d/Programs/IDEA/Gorobets/Task1/Test.txt", "-t", "frequency");

    }

    @Test
    public void tstSetTasksJcommander() {
        assertNotNull(textA.getStringFromFile());

    }

    @Test
    public void tstSetPathToFileJcommander() {
        assertNotNull(textA.getPathToFile());
        assertEquals(textA.getPathToFile(), "/home/invincible_g_d/Programs/IDEA/Gorobets/Task1/Test.txt");
    }

    @Test
    public void tstSetHelpJcommander() {
        assertNotNull(textA.isHelp());
        assertEquals(textA.isHelp(), false);
    }

      @After

    public void setUpAfterTest() {
        textA = null;

    }
}
