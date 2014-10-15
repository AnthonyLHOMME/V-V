package vv.tp3;

import org.junit.Test;
import vv.tp3.factory.IntegerToStringFactory;
import vv.tp3.factory.StringToIntegerFactory;

import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * Created by Simon on 14/10/14.
 */
public class TestLazyBiMap extends TestBiMap {


    @Test
    public void testGet() {
        map.put("1", 1);
        map.put("2", 12);
        map.put("3", 2);

        assertEquals(map.get("1"),1);
        map.put("1", 11);
        assertEquals(map.get("1"),11);

        assertEquals(map.get("4"), 4);
    }


    @Test
    public void testGetByValue() {
        LazyBiMap biMap = (LazyBiMap) map;
        biMap.put("1", 1);
        biMap.put("2", 12);
        biMap.put("3", 2);

        assertEquals(biMap.getByValue(1),"1");
        biMap.put("1", 11);
        assertEquals(biMap.getByValue(11),"1");

        assertEquals(biMap.getByValue(4), "4");

        biMap.put("5", 5);
        assertEquals(biMap.getByValue(5),"5");
    }

    protected Map<String, Integer> getNewMap() {
        return new LazyBiMap<String, Integer>(new IntegerToStringFactory(), new StringToIntegerFactory());
    }

}
