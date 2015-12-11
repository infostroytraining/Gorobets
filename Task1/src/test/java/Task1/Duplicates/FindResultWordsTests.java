package Task1.Duplicates;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by invincible_g_d on 12/9/15.
 */
public class FindResultWordsTests {
    Map<String, Integer> map;
    List<String> resultWords;
    List<String> expResultWords;
    List<Integer> list;
    List<Integer> expextedList;

    @Before
    public void setUpBeforeTest() {
        expextedList = new ArrayList<>();
        expextedList.add(3);
        expextedList.add(4);
        expextedList.add(5);
        expResultWords = new ArrayList<>();
        expResultWords.add("Katherine");
        expResultWords.add("took");

        resultWords = new ArrayList<>();
        map = new HashMap<>();
        map.put("Katherine", 4);
        map.put("took", 5);
        map.put("is", 3);

        Collection<Integer> tr = map.values();
        list = new ArrayList<>(tr);
        Collections.sort(list);
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (int i = list.size() - 1; i > list.size() - 3; i--) {
            Integer value = list.get(i);
            for (Map.Entry<String, Integer> pair : entrySet) {
                if (pair != null) {
                    if (value.equals(pair.getValue())) {
                        resultWords.add(pair.getKey());
                    }
                }
            }
        }
        Collections.reverse(resultWords);

    }

    @Test
    public void tstEqualCollections() {

        assertEquals(list, expextedList);
        assertEquals(resultWords, expResultWords);

    }

    @Test
    public void tstEqualMapElemets() {

        assertEquals(list.get(0), expextedList.get(0));
        assertEquals(list.get(1), expextedList.get(1));
        assertEquals(list.get(2), expextedList.get(2));
        assertEquals(resultWords.get(0), expResultWords.get(0));
        assertEquals(resultWords.get(1), expResultWords.get(1));


    }

    @Test
    public void tstNotNullText() {

        assertNotNull(list);
        assertNotNull(resultWords);

    }

    @After

    public void setUpAfterTest() {
        map = null;
        resultWords = null;
        expResultWords = null;
        list = null;
        expextedList = null;
    }
}
