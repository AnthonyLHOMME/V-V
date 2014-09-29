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

}
