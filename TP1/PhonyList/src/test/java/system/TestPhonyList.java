package system;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

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

    /**
     * Tests the "remove" method
     *
     * @type Functional
     * @input list = [2,5,4,-1,8,5,7], o = [4,-1,2,7,5]
     * @oracle The obtained list must be [8,5].
     * @passed Yes
     * @see PhonyList#remove(Object)
     */
    @Test
    public void testRemove() {
        int nbElem = 7;
        PhonyList<Integer> list = new PhonyList<>();
        list.add(2);
        list.add(5);
        list.add(4);
        list.add(-1);
        list.add(8);
        list.add(5);
        list.add(7);

        assertEquals(list.size(), nbElem);

        // Suppression d'un element
        assertTrue(list.contains(4));
        list.remove(new Integer(4));
        assertFalse(list.contains(4));

        assertEquals(list.size(),--nbElem);

        // Suppression d'un element negatif
        assertTrue(list.contains(-1));
        list.remove(new Integer(-1));
        assertFalse(list.contains(-1));

        assertEquals(list.size(),--nbElem);

        // Suppression du premier element
        assertTrue(list.contains(2));
        list.remove(new Integer(2));
        assertFalse(list.contains(2));

        assertEquals(list.size(), --nbElem);

        // Suppression du dernier element
        assertTrue(list.contains(7));
        list.remove(new Integer(7));
        assertFalse(list.contains(7));

        assertEquals(list.size(),--nbElem);

        // Suppression d'un element en doublon
        assertTrue(list.contains(5));
        list.remove(new Integer(5));
        assertTrue(list.contains(5));

        assertEquals(list.size(),--nbElem);
    }

    /**
     * Tests the "remove" method with a null value
     *
     * @type Functional
     * @input list = [null,1], o = null
     * @oracle The obtained list must be [1].
     * @passed No
     * @correction <pre>
     * l.259
     * - if (elementData[index] != null) {
     * + if (elementData[index] == null) {
     * </pre>
     * @see PhonyList#remove(Object)
     */
    @Test
    public void testRemove_null() {
        PhonyList<Integer> list = new PhonyList<>();
        list.add(1);
        list.add(null);

        assertEquals(list.size(), 2);

        assertTrue(list.contains(null));
        list.remove(null);
        assertFalse(list.contains(null));

        assertEquals(list.size(),1);
    }

    /**
     * Tests the "remove" method with an element which does not exist
     *
     * @type Functional
     * @input list = [], o = 1
     * @oracle It must return false.
     * @passed Yes
     * @see PhonyList#remove(Object)
     */
    @Test
    public void testRemove_notExist() {
        PhonyList<Integer> list = new PhonyList<>();
        list.add(4);
        list.add(-2);

        assertEquals(list.size(),2);
        assertFalse(list.remove(new Integer(1)));
        assertEquals(list.size(),2);
        assertFalse(list.remove(null));
        assertEquals(list.size(),2);
    }

    /**
     * Tests the "addAll" method with an empty list
     *
     * @type Functional
     * @input list = [], o = [4,-5,8,2]
     * @oracle The obtained list must be [4,-5,8,2].
     * @passed Yes
     * @see PhonyList#addAll(int, java.util.Collection)
     */
    @Test
    public void testAddAll_emptyList() {
        PhonyList<Integer> list = new PhonyList<>();
        Collection<Integer> listAdd = new ArrayList<>();

        listAdd.add(4);
        listAdd.add(-5);
        listAdd.add(8);
        listAdd.add(2);
        list.addAll(0,listAdd);

        assertEquals(list.indexOf(4),0);
        assertEquals(list.indexOf(-5),1);
        assertEquals(list.indexOf(8),2);
        assertEquals(list.indexOf(2),3);
        assertEquals(list.size(),4);
    }

    /**
     * Tests the "addAll" method with a filled list
     *
     * @type Functional
     * @input list = [7,-1,3], o = [4,-5,8,2]
     * @oracle The obtained list must be [7,4,-5,8,2,-1,3].
     * @passed Yes
     * @see PhonyList#addAll(int, java.util.Collection)
     */
    @Test
    public void testAddAll_filledList() {
        PhonyList<Integer> list = new PhonyList<>();
        list.add(7);
        list.add(-1);
        list.add(3);

        Collection<Integer> listAdd = new ArrayList<>();
        listAdd.add(4);
        listAdd.add(-5);
        listAdd.add(8);
        listAdd.add(2);

        list.addAll(1,listAdd);

        assertEquals(list.indexOf(7),0);
        assertEquals(list.indexOf(4),1);
        assertEquals(list.indexOf(-5),2);
        assertEquals(list.indexOf(8),3);
        assertEquals(list.indexOf(2),4);
        assertEquals(list.indexOf(-1),5);
        assertEquals(list.indexOf(3),6);
        assertEquals(list.size(),7);
    }

    /**
     * Tests the "addAll" method with a filled list and a collection at the end of the list
     *
     * @type Functional
     * @input list = [7,-1,3], o = [4,-5,8,2]
     * @oracle The obtained list must be [7,-1,3,4,-5,8,2].
     * @passed Yes
     * @see PhonyList#addAll(int, java.util.Collection)
     */
    @Test
    public void testAddAll_filledList_atEnd() {
        PhonyList<Integer> list = new PhonyList<>();
        list.add(7);
        list.add(-1);
        list.add(3);

        Collection<Integer> listAdd = new ArrayList<>();
        listAdd.add(4);
        listAdd.add(-5);
        listAdd.add(8);
        listAdd.add(2);

        list.addAll(3,listAdd);

        assertEquals(list.indexOf(7),0);
        assertEquals(list.indexOf(-1),1);
        assertEquals(list.indexOf(3),2);
        assertEquals(list.indexOf(4),3);
        assertEquals(list.indexOf(-5),4);
        assertEquals(list.indexOf(8),5);
        assertEquals(list.indexOf(2),6);
        assertEquals(list.size(),7);
    }

    /**
     * Tests the "addAll" method with a filled list and empty list
     *
     * @type Functional
     * @input list = [7,-1,3], o = []
     * @oracle The obtained list must be [].
     * @passed Yes
     * @see PhonyList#addAll(int, java.util.Collection)
     */
    @Test
    public void testAddAll_notChange() {
        PhonyList<Integer> list = new PhonyList<>();
        list.add(7);
        list.add(-1);
        list.add(3);

        Collection<Integer> listAdd = new ArrayList<>();

        assertFalse(list.addAll(0,listAdd));

        assertEquals(list.indexOf(7),0);
        assertEquals(list.indexOf(-1),1);
        assertEquals(list.indexOf(3),2);
        assertEquals(list.size(),3);
    }

    /**
     * Tests the "addAll" method with an index out of bounds
     *
     * @type Functional
     * @input list = [7,-1,3], o = 4
     * @oracle Must throws IndexOutOfBoundsException
     * @passed Yes
     * @see PhonyList#addAll(int, java.util.Collection)
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAll_outOfBounds() {
        PhonyList<Integer> list = new PhonyList<>();
        list.add(7);
        list.add(-1);
        list.add(3);

        Collection<Integer> listAdd = new ArrayList<>();
        listAdd.add(4);

        list.addAll(4,listAdd);
    }

    /**
     * Tests the "addAll" method with a null collection
     *
     * @type Functional
     * @input list = [7,-1,3], o = null
     * @oracle Must throws NullPointerException
     * @passed Yes
     * @see PhonyList#addAll(int, java.util.Collection)
     */
    @Test(expected = NullPointerException.class)
    public void testAddAll_nullPointer() {
        PhonyList<Integer> list = new PhonyList<>();
        list.add(7);
        list.add(-1);
        list.add(3);

        list.addAll(0,null);
    }

    /**
     * Tests the "removeAll" method with an empty list
     *
     * @type Functional
     * @input list = [4,-5, 8, 2], o = []
     * @oracle The obtained list must be [4,-5,8,2].
     * @passed Yes
     * @see PhonyList#removeAll(java.util.Collection)
     */
    @Test
    public void testRemoveAll_emptyList() {
        PhonyList<Integer> list = new PhonyList<>();
        Collection<Integer> listRemove = new ArrayList<>();

        list.add(4);
        list.add(-5);
        list.add(8);
        list.add(2);
        list.removeAll(listRemove);

        assertEquals(list.indexOf(4),0);
        assertEquals(list.indexOf(-5),1);
        assertEquals(list.indexOf(8),2);
        assertEquals(list.indexOf(2),3);
        assertEquals(list.size(),4);
    }

    /**
     * Tests the "removeAll" method with a filled list
     *
     * @type Functional
     * @input list = [7,4,-5,8,2,-1,3], o = [4,-5,8,2]
     * @oracle The obtained list must be [7,-1,3].
     * @passed Yes
     * @see PhonyList#removeAll(java.util.Collection)
     */
    @Test
    public void testRemoveAll_filledList() {
        PhonyList<Integer> list = new PhonyList<>();
        list.add(7);
        list.add(4);
        list.add(-5);
        list.add(8);
        list.add(2);
        list.add(-1);
        list.add(3);

        Collection<Integer> listRemove = new ArrayList<>();
        listRemove.add(4);
        listRemove.add(-5);
        listRemove.add(8);
        listRemove.add(2);

        list.removeAll(listRemove);

        assertEquals(list.indexOf(7),0);
        assertEquals(list.indexOf(-1),1);
        assertEquals(list.indexOf(3),2);
        assertEquals(list.size(),3);
    }

    /**
     * Tests the "removeAll" method with a filled list and a collection at the end of the list
     *
     * @type Functional
     * @input list = [7,-1,3,4,-5,8,2], o = [4,-5,8,2]
     * @oracle The obtained list must be [7,-1,3].
     * @passed No
     * @correction <pre>
     * l.413
     * - for (; r < size - 1; r++) {
     * + for (; r < size; r++) {
     * </pre>
     * @see PhonyList#removeAll(java.util.Collection)
     */
    @Test
    public void testRemoveAll_filledList_atEnd() {
        PhonyList<Integer> list = new PhonyList<>();
        list.add(7);
        list.add(-1);
        list.add(3);
        list.add(4);
        list.add(-5);
        list.add(8);
        list.add(2);

        Collection<Integer> listRemove = new ArrayList<>();
        listRemove.add(4);
        listRemove.add(-5);
        listRemove.add(8);
        listRemove.add(2);

        list.removeAll(listRemove);

        assertEquals(list.indexOf(7),0);
        assertEquals(list.indexOf(-1),1);
        assertEquals(list.indexOf(3),2);
        assertEquals(list.size(),3);
    }

    /**
     * Tests the "removeAll" method with a filled list and a collection which contains
     * elements which are not in the list
     *
     * @type Functional
     * @input list = [1, 2, 3, 4], o = [2, 7, 3, 8]
     * @oracle The obtained list must be [1, 4].
     * @passed Yes
     * @see PhonyList#removeAll(java.util.Collection)
     */
    @Test
    public void testRemoveAll_filledListWithOtherElems() {
        PhonyList<Integer> list = new PhonyList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Collection<Integer> listRemove = new ArrayList<>();
        listRemove.add(2);
        listRemove.add(7);
        listRemove.add(3);
        listRemove.add(8);

        list.removeAll(listRemove);

        assertEquals(list.indexOf(1),0);
        assertEquals(list.indexOf(4),1);
        assertEquals(list.size(),2);
    }

}
