package Task1.Frequency;

import Task1.Analyzer.Implementations.Frequency;
import Task1.Analyzer.Implementations.TextAnalyzer;
import com.beust.jcommander.JCommander;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;


/**
 * Created by invincible_g_d on 12/9/15.
 */
public class CountWordsOfText {
    TextAnalyzer textA;
    Map<String, Integer> map;
    Frequency fre;
    StringBuilder sb;
    String textExm = "Katherine took took Katherine took Katherine took Katherine took";

    @Before
    public void setUpBeforeTest() {
        map = new HashMap<>();
        map.put("Katherine", 4);
        map.put("took", 5);
        sb = new StringBuilder(textExm);
        sb.append("\n");
        fre = new Frequency();
        textA = new TextAnalyzer();
        new JCommander(textA, "-i", " /home/invincible_g_d/Programs/IDEA/Gorobets/Task1/Test.txt", "-t", "frequency");

    }

    @Test
    public void tstEqualMaps() {

        assertEquals(fre.countWordsOfText(sb), map);

    }

    @Test
    public void tstEqualMapElemets() {

        assertEquals(fre.countWordsOfText(sb).get(0), map.get(0));
        assertEquals(fre.countWordsOfText(sb).get(1), map.get(1));

    }

    @Test
    public void tstNotNullText() {

        assertNotNull(fre.countWordsOfText(sb));

    }

    @After

    public void setUpAfterTest() {
        textA = null;
        sb = null;
        map = null;
        fre = null;
        textExm = null;
    }

}
