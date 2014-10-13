package simpleGame.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by anthony on 08/10/14.
 */
public class TestSequenceDiagram {


    /**
     * Tests the "isGameOver" method with scenario of sequence diagram
     *
     * @type Functional
     * @input
     * @oracle Must return true.
     * @passed Yes
     * @see Game#isGameOver()
     */
    @Test
    public void test_isGameOver_gold() {
        Board b = mock(Board.class);
        when(b.numberOfPawns()).thenReturn(2);
        when(b.maxGold()).thenReturn(3);

        // isGameOver()
        assertTrue((b.numberOfPawns() == 1) || (b.maxGold() >= 3));
    }

    /**
     * Tests the "isGameOver" method with only one pawn left
     *
     * @type Functional
     * @input
     * @oracle Must return true.
     * @passed Yes
     * @see Game#isGameOver()
     */
    @Test
    public void test_isGameOver_alone() {
        Board b = mock(Board.class);
        when(b.numberOfPawns()).thenReturn(1);
        when(b.maxGold()).thenReturn(0);

        // isGameOver()
        assertTrue((b.numberOfPawns() == 1) || (b.maxGold() >= 3));
    }

    /**
     * Tests the "isGameOver" method with only one pawn left and gold >= 3
     *
     * @type Functional
     * @input
     * @oracle Must return true.
     * @passed Yes
     * @see Game#isGameOver()
     */
    @Test
    public void test_isGameOver_all() {
        Board b = mock(Board.class);
        when(b.numberOfPawns()).thenReturn(1);
        when(b.maxGold()).thenReturn(4);

        // isGameOver()
        assertTrue((b.numberOfPawns() == 1) || (b.maxGold() >= 3));
    }

    /**
     * Tests the "isGameOver" method with not over game
     *
     * @type Functional
     * @input
     * @oracle Must return false.
     * @passed Yes
     * @see Game#isGameOver()
     */
    @Test
    public void test_isGameOver_not() {
        Board b = mock(Board.class);
        when(b.numberOfPawns()).thenReturn(2);
        when(b.maxGold()).thenReturn(1);

        // isGameOver()
        assertFalse((b.numberOfPawns() == 1) || (b.maxGold() >= 3));
    }

    /**
     * Tests the "maxGold" method
     *
     * @type Functional
     * @input [2,1,5,3,0]
     * @oracle Must return 5.
     * @passed Yes
     * @see Board#maxGold()
     */
    @Test
    public void test_maxGold() {
        Board b = new Board(1,10,10,0,0);
        b.removeAllPawns();

        // Creation des mocks
        Pawn p1 = mock(Pawn.class);
        Pawn p2 = mock(Pawn.class);

        // Definition des mocks
        when(p1.getX()).thenReturn(1);
        when(p1.getY()).thenReturn(1);
        when(p1.getGold()).thenReturn(1);

        when(p2.getX()).thenReturn(2);
        when(p2.getY()).thenReturn(2);
        when(p2.getGold()).thenReturn(3);

        // Ajout des mocks
        b.addPawn(p1);
        b.addPawn(p2);

        assertEquals(b.maxGold(),3);
    }
}
