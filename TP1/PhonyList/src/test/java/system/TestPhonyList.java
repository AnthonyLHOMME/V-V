package system;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPhonyList {

    /**
     * Tests the "size" method with an empty list and a list with one element.
     *
     * @see PhonyList#size()
     * @type Functional
     * @input list=[], o=1
     * @oracle Must returns 0 then 1.
     * @passed Yes
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
     * @see PhonyList#isEmpty()
     * @type Functional
     * @input list = []
     * @oracle Must returns true then false
     * @passed Yes
     */
    @Test
    public void testIsEmpty () {
        PhonyList<Integer> list = new PhonyList<>();
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

    /**
     * Tests the "contains" method with an integer value.
     *
     * @see PhonyList#contains(Object)
     * @type Functional
     * @input list=[], o=1
     * @oracle Must returns false then true.
     * @passed No
     * @correction <pre>
     * l.184
     * - return indexOf(o) > 0;
     * + return indexOf(o) >= 0;
     * </pre>
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
     * @see PhonyList#contains(Object)
     * @type Functional
     * @input list=[], o="1"
     * @oracle Must returns false then true.
     * @passed Yes
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
     * @see PhonyList#get(int)
     * @type Functional
     * @input list = [], i = 0
     * @oracle Must throws IndexOutOfBoundsException
     * @passed Yes
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGet_emptyList () {
        PhonyList<Integer> list = new PhonyList<Integer>();
        list.get(0);
    }

    /**
     * Tests the "get" method with a filled list and an index which is not out of bounds
     *
     * @see PhonyList#get(int)
     * @type Functional
     * @input list = ["elem 1", "elem 2", "elem 3"], i1 = 0, i2 = 1, i3 = 2
     * @oracle Must returns "elem 1" then "elem 2" then "elem 3"
     * @passed Yes
     */
    @Test
    public void testGet_filledList () {
        PhonyList<String> list = new PhonyList<String>();
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
     * @see PhonyList#get(int)
     * @type Functional
     * @input list = ["elem 1", "elem 2", "elem 3"], i = 3
     * @oracle Must throws IndexOutOfBoundsException
     * @passed Yes
     */
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGet_filledList_outOfB () {
        PhonyList<String> list = new PhonyList<String>();
        list.add("elem 1");
        list.add("elem 2");
        list.add("elem 3");
        list.get(3);
    }

}
