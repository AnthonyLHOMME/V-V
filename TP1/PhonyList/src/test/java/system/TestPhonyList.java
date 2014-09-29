package system;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestPhonyList {

	@Test
    public void testIsEmpty () {
        PhonyList<Integer> list = new PhonyList<Integer>();
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
    }

}
