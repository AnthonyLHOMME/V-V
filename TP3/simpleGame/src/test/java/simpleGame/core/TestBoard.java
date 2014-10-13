package simpleGame.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Anthony LHOMME & Robin Hacault
 */
@RunWith(MockitoJUnitRunner.class)
public class TestBoard {

    Board board;

    /**
     * Init a board 10x10 with no pawns and cell bonus at (0,0)
     */
    @Before
    public void initBoard() {
        board = new Board(1, 10, 10, 0, 0);
        board.removeAllPawns();
    }

    @After
    public void nullBoard() {
        board = null;
    }

    /**
     * Tests the "addPawn" method with two pawns at different place
     *
     * @type Functional
     * @input
     * @oracle
     * @passed Yes
     * @see Board#addPawn(Pawn)
     */
    @Test
    public void test_addPawn_diffPlace() {
        Pawn p1 = mock(Pawn.class);
        Pawn p2 = mock(Pawn.class);
        when(p1.getX()).thenReturn(0);
        when(p1.getY()).thenReturn(0);
        when(p2.getX()).thenReturn(1);
        when(p2.getY()).thenReturn(1);

        assertEquals(board.numberOfPawns(), 0);
        board.addPawn(p1);
        board.addPawn(p2);
        assertEquals(board.numberOfPawns(), 2);
    }

    /**
     * Tests the "addPawn" method with two pawns at same "x" but different "y"
     *
     * @type Functional
     * @input
     * @oracle
     * @passed Yes
     * @see Board#addPawn(Pawn)
     */
    @Test
    public void test_addPawn_sameX() {
        Pawn p1 = mock(Pawn.class);
        Pawn p2 = mock(Pawn.class);
        when(p1.getX()).thenReturn(0);
        when(p1.getY()).thenReturn(0);
        when(p2.getX()).thenReturn(0);
        when(p2.getY()).thenReturn(1);

        assertEquals(board.numberOfPawns(), 0);
        board.addPawn(p1);
        board.addPawn(p2);
        assertEquals(board.numberOfPawns(), 2);
    }

    /**
     * Tests the "addPawn" method with two pawns at same place
     *
     * @type Functional
     * @input
     * @oracle
     * @passed Yes
     * @see Board#addPawn(Pawn)
     */
    @Test
    public void test_addPawn_samePlace() {
        Pawn p1 = mock(Pawn.class);
        Pawn p2 = mock(Pawn.class);
        when(p1.getX()).thenReturn(0);
        when(p1.getY()).thenReturn(0);
        when(p2.getX()).thenReturn(0);
        when(p2.getY()).thenReturn(0);

        assertEquals(board.numberOfPawns(), 0);
        board.addPawn(p1);
        board.addPawn(p2);
        assertEquals(board.numberOfPawns(), 1);
    }


    /**
     * Tests the "getSquareContent" method with a pawn at coord
     *
     * @type Functional
     * @input p.x = 2, p.y = 5
     * @oracle Must return the pawn.
     * @passed Yes
     * @see Board#getSquareContent(int, int)
     */
    @Test
    public void test_getSquareContent_find() {
        Pawn p = mock(Pawn.class);
        when(p.getX()).thenReturn(2);
        when(p.getY()).thenReturn(5);

        board.addPawn(p);
        assertEquals(board.getSquareContent(2, 5), p);
    }

    /**
     * Tests the "getSquareContent" method with no pawn at coord
     *
     * @type Functional
     * @input p.x = 2, p.y = 5
     * @oracle Must return null.
     * @passed Yes
     * @see Board#getSquareContent(int, int)
     */
    @Test
    public void test_getSquareContent_notFind() {
        Pawn p = mock(Pawn.class);
        when(p.getX()).thenReturn(2);
        when(p.getY()).thenReturn(5);

        board.addPawn(p);
        assertEquals(board.getSquareContent(8, 5), null);
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
        // Creation des mocks
        Pawn p1 = mock(Pawn.class);
        Pawn p2 = mock(Pawn.class);
        Pawn p3 = mock(Pawn.class);
        Pawn p4 = mock(Pawn.class);
        Pawn p5 = mock(Pawn.class);

        // Definition des mocks
        when(p1.getX()).thenReturn(1);
        when(p1.getY()).thenReturn(1);
        when(p1.getGold()).thenReturn(2);

        when(p2.getX()).thenReturn(2);
        when(p2.getY()).thenReturn(2);
        when(p2.getGold()).thenReturn(1);

        when(p3.getX()).thenReturn(3);
        when(p3.getY()).thenReturn(3);
        when(p3.getGold()).thenReturn(5);

        when(p4.getX()).thenReturn(4);
        when(p4.getY()).thenReturn(4);
        when(p4.getGold()).thenReturn(3);

        when(p5.getX()).thenReturn(5);
        when(p5.getY()).thenReturn(5);
        when(p5.getGold()).thenReturn(0);

        // Ajout des mocks
        board.addPawn(p1);
        board.addPawn(p2);
        board.addPawn(p3);
        board.addPawn(p4);
        board.addPawn(p5);

        assertEquals(board.maxGold(),5);
    }

    /**
     * Tests the "squareContentSprite" method
     *
     * @type Functional
     * @input
     * @oracle
     * @passed Yes
     * @see Board#squareContentSprite(int, int) 
     */
    @Test
    public void test_squareContentSprite() {
        for (int i = 0; i < board.getXSize(); i++) {
            for (int j = 0; j < board.getYSize(); j++) {
                if (i == 0 && j == 0) {
                    // Bonus cell
                    assertEquals(board.squareContentSprite(0,0),'#');
                } else {
                    // Empty cell
                    assertEquals(board.squareContentSprite(0,1),'.');
                }
            }
        }

        Pawn p1 = mock(Pawn.class);
        when(p1.getX()).thenReturn(2);
        when(p1.getY()).thenReturn(5);
        when(p1.getLetter()).thenReturn('a');

        Pawn p2 = mock(Pawn.class);
        when(p2.getX()).thenReturn(0);
        when(p2.getY()).thenReturn(4);
        when(p2.getLetter()).thenReturn('b');

        Pawn p3 = mock(Pawn.class);
        when(p3.getX()).thenReturn(1);
        when(p3.getY()).thenReturn(5);
        when(p3.getLetter()).thenReturn('d');

        board.addPawn(p1);
        board.getNextPawn(); // Define p1 as current pawn
        board.addPawn(p2);
        board.addPawn(p3);

        // Current pawn cell
        assertEquals(board.squareContentSprite(2,5),'c');

        // Not current pawn cell
        assertEquals(board.squareContentSprite(0,4),'b');
        assertEquals(board.squareContentSprite(1,5),'d');
    }
}