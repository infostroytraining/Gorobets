package Task1.Length;

import Task1.Analyzer.Implementations.Frequency;
import Task1.Analyzer.Implementations.Length;
import Task1.Analyzer.Implementations.TextAnalyzer;
import com.beust.jcommander.JCommander;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by invincible_g_d on 12/9/15.
 */
public class CountWordsOfText {
    TextAnalyzer textA;
    Map<String, Integer> map;
    Length leng;
    StringBuilder sb;
    String textExm = "Katherine took took Katherine took Katherine took Katherine took the";

    @Before
    public void setUpBeforeTest() {
        map = new HashMap<>();
        map.put("the", 3);
        map.put("took", 4);
        map.put("Katherine", 9);

        sb = new StringBuilder(textExm);
        leng = new Length();
        textA = new TextAnalyzer();
        new JCommander(textA, "-i", " /home/invincible_g_d/Programs/IDEA/Gorobets/Task1/Test.txt", "-t", "frequency");

    }

    @Test
    public void tstEqualMaps() {

        assertEquals(leng.countWordsOfText(sb), map);

    }

    @Test
    public void tstEqualMapElemets() {

        assertEquals(leng.countWordsOfText(sb).get(0), map.get(0));
        assertEquals(leng.countWordsOfText(sb).get(1), map.get(1));
        assertEquals(leng.countWordsOfText(sb).get(2), map.get(2));

    }

    @Test
    public void tstNotNullText() {

        assertNotNull(leng.countWordsOfText(sb));

    }

    @After

    public void setUpAfterTest() {
        textA = null;
        sb = null;
        map = null;
        leng= null;
        textExm = null;
    }
}
