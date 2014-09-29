package system;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPhonyList {

	@Test
    public void testSize() {
        PhonyList<Integer> list = new PhonyList<>();
        assertEquals(list.size(),0);
        list.add(1);
        assertEquals(list.size(), 1);
    }

}
