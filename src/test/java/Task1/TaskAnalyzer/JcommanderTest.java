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
    public void setUpBeforeTestJcommander() {
        sb = new StringBuilder(textExm);
        buff = null;
        textA = new TextAnalyzer();
        new JCommander(textA, "-i", " /home/invincible_g_d/Programs/IDEA/Gorobets/Task1/Test.txt", "-t", "frequency");

    }

    @Test
    public void tst_SetTasksJcommander() {
        assertEquals(textA.getTasks(), "frequency");
    }

    @Test
    public void tst_SetPathToFileJcommander() {
        assertEquals(textA.getPathToFile(), "/home/invincible_g_d/Programs/IDEA/Gorobets/Task1/Test.txt");
    }

    @Test
    public void tst_SetHelpJcommander() {
        assertEquals(textA.isHelp(), false);
    }

    @Test
    public void tst_GetEnum() {
        assertTrue(textA.getEnumTaskType() != null);
        assertTrue(textA.getEnumTaskType() instanceof Enum);
    }

    @Test
    public void tst_CheckGetStringFromFile() throws IOException {

        assertFalse(textA.getStringFromFile().length()==0);
    }



    @After

    public void setUpAfterTestJcommander() {
        textA = null;
        sb = null;
        buff = null;

    }
}
