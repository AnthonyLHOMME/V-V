package vv.tp3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Simon on 30/09/14.
 */
public class TestMap<K, V> {

    protected Map<String, Integer> map;

    @Before
    public void setUp() {
        map =  getNewMap();
    }

    @After
    public void tearDown() {
        map = null;
    }

    @Test
    public void testIsEmpty() {
        assertEquals(map.isEmpty(), true);

        map.put("q",1);
        assertEquals(map.isEmpty(), false);

    }

    @Test
    public void testSize() {
        assertEquals(map.size(),0);

        map.put("1",1);
        assertEquals(map.size(),1);

        map.put("2",2);
        assertEquals(map.size(),2);

        map.put("2",2);
        assertEquals(map.size(),2);

        map.remove("1");
        assertEquals(map.size(),1);
    }

    @Test
    public void testClear() {
        map.put("1",1);
        map.put("2",2);

        assertEquals(map.isEmpty(),false);

        map.clear();
        assertEquals(map.isEmpty(),true);
        assertFalse(map.containsKey("1"));
        assertFalse(map.containsKey("2"));
    }

    @Test
    public void testRemove() {
        map.put("1", 1);
        map.put("2", 12);
        map.put("3", 2);

        assertEquals(3, map.size());
        map.remove("3");
        assertFalse(map.containsKey("3"));
        assertEquals(2, map.size());
        assertEquals(1, map.get("1"),0);
        assertEquals(12, map.get("2"),0);

        assertEquals(1, map.remove("1"),0);
        assertFalse(map.containsKey("1"));
        assertEquals(1, map.size());
        assertEquals(12, map.get("2"),0);
    }


    @Test
    public void testGet() {
        map.put("1", 1);
        map.put("2", 12);
        map.put("3", 2);

        assertEquals(map.get("1"),1,0);
        map.put("1", 11);
        assertEquals(map.get("1"),11,0);

        assertNull(map.get("4"));

        map.put("4", 4);
        assertEquals(map.get("4"),4, 0);
    }

    @Test
    public void testPut() {
        map.put("1", 1);
        map.put("2", 12);

        assertEquals(1, map.get("1"), 0);
        assertEquals(12, map.get("2"), 0);

        map.put("1", 14);
        map.put("2", 122);
        map.put("Fred", 11111);
        assertEquals(14, map.get("1"), 0);
        assertEquals(122, map.get("2"), 0);
    }

    @Test
    public void testPutAll() {
        map.put("1", 1);
        map.put("2", 12);
        map.put("3", 2);

        Map<String, Integer> map2 =  getNewMap();
        map2.putAll(map);


        assertFalse(map2.containsKey("4"));
        assertEquals(1, map2.get("1"), 0);
        assertEquals(2, map2.get("3"), 0);
        assertEquals(12, map2.get("2"), 0);
    }

    /**
     * checks if the SimpleMap equals method works.
     */
    @Test
    public void testEquals() {
        map.put("1", 1);
        map.put("2", 12);
        map.put("3", 2);

        Map<String, Integer> map2 = getNewMap();
        map2.put("1", 1);
        map2.put("2", 12);
        map2.put("3", 2);
        map2.put("4", 34);

        assertNotEquals(map, map2);

        map2.remove("4");
        assertEquals(map, map2);
    }

    /**
     * checks if the SimpleMap equals method works.
     */
    @Test
    public void testHasCode() {
        map.put("1", 1);
        map.put("2", 12);
        map.put("3", 2);

        Map<String, Integer> map2 =  getNewMap();
        map2.put("1", 1);
        map2.put("2", 12);
        map2.put("3", 2);
        map2.put("4", 34);

        assertNotEquals(map, map2);
        assertNotEquals(map.hashCode(), map2.hashCode());

        map2.remove("4");
        assertEquals(map, map2);
        assertEquals(map.hashCode(), map2.hashCode());
    }

    @Test
    public void testContains() {
        map.put("1", 1);
        map.put("2", 12);

        assertEquals(map.containsKey("1"), true);
        assertEquals(map.containsKey("2"), true);
        assertEquals(map.containsKey("3"), false);

        map.put("3", 2);

        assertEquals(map.containsKey("1"), true);
        assertEquals(map.containsKey("2"), true);
        assertEquals(map.containsKey("3"), true);

        map.remove("2");

        assertEquals(map.containsKey("1"), true);
        assertEquals(map.containsKey("2"), false);
        assertEquals(map.containsKey("3"), true);
    }

    @Test
    public void testContainsValues() {
        map.put("1", 1);
        map.put("2", 12);

        assertEquals(map.containsValue(1), true);
        assertEquals(map.containsValue(12), true);
        assertEquals(map.containsValue(2), false);

        map.put("3", 2);

        assertEquals(map.containsValue(1), true);
        assertEquals(map.containsValue(12), true);
        assertEquals(map.containsValue(2), true);

        map.remove("2");

        assertEquals(map.containsValue(1), true);
        assertEquals(map.containsValue(12), false);
        assertEquals(map.containsValue(2), true);
    }

    @Test
    public void testKeySet() {
        Set<String> set = new HashSet<String>();

        assertEquals(map.keySet(), set);

        map.put("1", 1);
        map.put("2", 12);
        set.add("1");
        set.add("2");
        assertEquals(map.keySet(), set);

        map.put("3", 2);
        set.add("3");
        assertEquals(map.keySet(), set);

        map.remove("2");
        assertNotEquals(map.keySet(), set);

        set.remove("2");
        assertEquals(map.keySet(), set);
    }

    @Test
    public void testValues() {
        List<Integer> set = new ArrayList<Integer>();

        assertEquals(map.values(), set);

        map.put("1", 1);
        map.put("2", 12);
        set.add(1);
        set.add(12);
        assertEquals(map.values(), set);
        map.put("3", 2);
        set.add(2);
        assertEquals(map.values(), set);
        map.remove("2");
        assertNotEquals(map.values(), set);
        set.remove(1);
        assertEquals(map.values(), set);
    }

    @Test(expected=NotImplementedException.class)
    public void testEntrySet() {
        map.entrySet();
    }


    protected Map<String, Integer> getNewMap() {
        return new SimpleMap<String, Integer>();
    }
}
