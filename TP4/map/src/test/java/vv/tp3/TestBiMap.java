package vv.tp3;

import org.junit.Test;
import vv.tp3.BiMap;

import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Simon on 02/10/14.
 */
public class TestBiMap extends TestMap {

    @Test
    public void testPut() {

        map.put("1", 1);
        assertEquals(map.get("1"), 1);
        assertFalse(map.containsKey("3"));

        map.put("1", 2);
        map.put("3", 3);
        assertEquals(map.get("1"), 2);
        assertEquals(map.get("3"), 3);

        map.put("4", 2);
        assertEquals(map.get("4"), 2);
        assertFalse(map.containsKey("1"));
        assertEquals(((BiMap)map).getByValue(2), "4");

        map.put("3", 2);
        assertEquals(map.get("3"), 2);
        assertFalse(map.containsKey("4"));
        assertFalse(map.containsValue(3));
    }

    @Test
    public void testRemoveValue() {
        BiMap biMap = (BiMap)map;

        biMap.put("1", 1);
        biMap.put("2", 2);
        biMap.put("3", 3);

        biMap.removeValue(1);
        assertFalse(biMap.containsValue(1));
        assertFalse(biMap.containsKey("1"));
        assertEquals(biMap.getByValue(2), "2");
        assertEquals(biMap.get("2"), 2);
    }

    @Test
    public void testGetByValue() {
        BiMap biMap = (BiMap) map;
        biMap.put("1", "1");
        biMap.put("2", "12");
        biMap.put("3", "2");

        assertEquals(biMap.getByValue("1"),"1");
        biMap.put("1", "11");
        assertEquals(biMap.getByValue("11"),"1");

        assertNull(biMap.getByValue("4"));

        biMap.put("4", "4");
        assertEquals(biMap.getByValue("4"),"4");
    }


    protected Map<String, Integer> getNewMap() {
        return new BiMap<String, Integer>();
    }
}
