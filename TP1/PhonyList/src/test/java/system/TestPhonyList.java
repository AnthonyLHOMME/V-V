package system;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestPhonyList {

    /*
     * Helper method to create lists [0,1,2,...,max-1].
     */
    private PhonyList<Integer> list(int max) {
        PhonyList<Integer> list = new PhonyList<>();
        for (int i = 0; i < max; i++) {
            list.add(i);
        }
        return list;
    }


    /**
     * Tests the "size" method with an empty list and a list with one element.
     *
     * @type Functional
     * @input list=[], o=1
     * @oracle Must returns 0 then 1.
     * @passed Yes
     * @see PhonyList#size()
     */
    @Test
    public void testSize() {
        PhonyList<Integer> list = new PhonyList<>();
        assertEquals(list.size(), 0);
        list.add(1);
        assertEquals(list.size(), 1);
    }

    /**
     * Tests the "isEmpty" method with an empty list and a list with one element
     *
     * @type Functional
     * @input list = []
     * @oracle Must returns true then false
     * @passed Yes
     * @see PhonyList#isEmpty()
     */
    @Test
    public void testIsEmpty() {
        PhonyList<Integer> list = new PhonyList<>();
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    /**
     * Tests the "contains" method with an integer value.
     *
     * @type Functional
     * @input list=[], o=1
     * @oracle Must returns false then true.
     * @passed No
     * @correction <pre>
     * l.184
     * - return indexOf(o) > 0;
     * + return indexOf(o) >= 0;
     * </pre>
     * @see PhonyList#contains(Object)
     */
    @Test
    public void testContains_int() {
        PhonyList<Integer> list = new PhonyList<>();
        assertFalse(list.contains(1));
        list.add(1);
        assertTrue(list.contains(1));
    }

    /**
     * Tests the "contains" method with a string value.
     *
     * @type Functional
     * @input list=[], o="1"
     * @oracle Must returns false then true.
     * @passed Yes
     * @see PhonyList#contains(Object)
     */
    @Test
    public void testContains_string() {
        PhonyList<String> list = new PhonyList<>();
        assertFalse(list.contains("1"));
        list.add("1");
        assertTrue(list.contains("1"));
    }

    /**
     * Tests the "get" method with an empty list and an out of bounds index
     *
     * @type Functional
     * @input list = [], i = 0
     * @oracle Must throws IndexOutOfBoundsException
     * @passed Yes
     * @see PhonyList#get(int)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet_emptyList() {
        PhonyList<Integer> list = new PhonyList<>();
        list.get(0);
    }

    /**
     * Tests the "get" method with a filled list and an index which is not out of bounds
     *
     * @type Functional
     * @input list = ["elem 1", "elem 2", "elem 3"], i1 = 0, i2 = 1, i3 = 2
     * @oracle Must returns "elem 1" then "elem 2" then "elem 3"
     * @passed Yes
     * @see PhonyList#get(int)
     */
    @Test
    public void testGet_filledList() {
        PhonyList<String> list = new PhonyList<>();
        list.add("elem 1");
        list.add("elem 2");
        list.add("elem 3");
        assertEquals(list.get(0), "elem 1");
        assertEquals(list.get(1), "elem 2");
        assertEquals(list.get(2), "elem 3");
    }

    /**
     * Tests the "get" method with a filled list and an out of bounds index
     *
     * @type Functional
     * @input list = ["elem 1", "elem 2", "elem 3"], i = 3
     * @oracle Must throws IndexOutOfBoundsException
     * @passed Yes
     * @see PhonyList#get(int)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet_filledList_outOfB() {
        PhonyList<String> list = new PhonyList<>();
        list.add("elem 1");
        list.add("elem 2");
        list.add("elem 3");
        list.get(3);
    }

    /**
     * Tests the "set" method with integer values
     *
     * @type Functional
     * @input list = [0,1,2,...,nbElem-1], i = [O,1,2,...,nbElem-1]
     * @oracle The index of "nbElem-i" element must be "i".
     * @passed No
     * @correction <pre>
     * l.227
     * - elementData[++index] = element;
     * + elementData[index++] = element;
     * </pre>
     * @see PhonyList#set(int, Object)
     */
    @Test
    public void testSet() {
        int nbElem = 100;
        PhonyList<Integer> list = list(nbElem);
        for(int i = 0; i < nbElem; i++){
            list.set(i, nbElem - i);
            assertEquals(list.indexOf(nbElem-i),i); // indexOf return the index of the first occurrence
        }
    }

    /**
     * Tests the "set" method with negative index
     *
     * @type Functional
     * @input list = [0,1,2,3,4,5,6,7,8,9], i = -1
     * @oracle Must throws IndexOutOfBoundsException
     * @passed Yes
     * @see PhonyList#set(int, Object)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet_negative() {
        PhonyList<Integer> list = list(10);
        list.set(-1,0);
    }

    /**
     * Tests the "set" method with index higher than size of list
     *
     * @type Functional
     * @input list = [0,1,2,3,4,5,6,7,8,9], i = 10
     * @oracle Must throws IndexOutOfBoundsException
     * @passed Yes
     * @see PhonyList#set(int, Object)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSet_higher() {
        PhonyList<Integer> list = list(10);
        list.set(10,0);
    }

    /**
     * Tests the "add" method with integer values
     *
     * @type Functional
     * @input list = [], o = [0,1,2,3,4,5,6,7,8,9]
     * @oracle The obtained list must be [0,1,2,3,4,5,6,7,8,9].
     * @passed Yes
     * @see PhonyList#add(Object)
     */
    @Test
    public void testAdd_int() {
        PhonyList<Integer> list = new PhonyList<>();
        for(int i = 0; i < 10; i++) {
            list.add(i);
            assertEquals(list.size(),i+1);
        }
        for(int i = 0; i < 10; i++) {
            assertEquals(list.indexOf(i), i);
        }
    }

    /**
     * Tests the "add" method with string values
     *
     * @type Functional
     * @input list = [], o = [0,1,2,3,4,5,6,7,8,9]
     * @oracle The obtained list must be ["elem 0","elem 1",...,"elem 9"].
     * @passed Yes
     * @see PhonyList#add(Object)
     */
    @Test
    public void testAdd_string() {
        PhonyList<String> list = new PhonyList<>();
        for(int i = 0; i < 10; i++) {
            list.add("elem "+i);
            assertEquals(list.size(),i+1);
        }
        for(int i = 0; i < 10; i++) {
            assertEquals(list.indexOf("elem "+i), i);
        }
    }

    /**
     * Tests the "add" method with an null value
     *
     * @type Functional
     * @input list = [], o = null
     * @oracle The obtained list must be ["elem 0","elem 1",...,"elem 9"].
     * @passed Yes
     * @see PhonyList#add(Object)
     */
    @Test
    public void testAdd_null() {
        PhonyList<Object> list = new PhonyList<>();
        list.add(null);
        assertEquals(list.indexOf(null), 0);
    }
}
