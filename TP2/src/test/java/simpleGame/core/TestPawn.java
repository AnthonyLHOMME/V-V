package simpleGame.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import simpleGame.exception.OutOfBoardException;

/**
 * V&V - TP2
 * @authors Hacault Robin - Lhomme Anyhony
 */
@RunWith(MockitoJUnitRunner.class)
public class TestPawn {

    public Board board;
    public Pawn p1;

    /**
     * Before each test : Init a 10 x 10 mocked board and a pawn on the (0,0) square of this board
     */
    @Before
    public void init() {
        // Mock a board : 10 x 10 with 0 bonus square, and a pawn on (0,0)
        board = mock(Board.class);
        p1 = new Pawn('A', 0, 0, board);
        when(board.getSquareContent(0, 0)).thenReturn(p1);
        when(board.getXSize()).thenReturn(10);
        when(board.getYSize()).thenReturn(10);
    }

    /**
     * Tests the move of the pawn p1 from (0,0) [normal square] to (0,1), where an other pawn p2 is located
     *
     * @type Functional
     * @oracle Must returns "A attacks!\nB loses 1 hitpoints.", and gold_current = gold_before
     * @passed No, throws OutOfBoardException
     * @correction
     * <pre>
     * l.105
     * - newy > 0
     * + newy >= 0;
     * l.106
     * - newx > 0
     * + newx >= 0
     * </pre>
     * @see Pawn#move(Direction d)
     */
    @Test
    public void test_move_UP_FromNormalSquare_OnUsedSquare() throws OutOfBoardException {
        Pawn p2 = new Pawn('B', 0, 1, board);
        when(board.getSquareContent(0, 1)).thenReturn(p2);
        when(board.isBonusSquare(0, 1)).thenReturn(false);
        int gold_before = p1.getGold();
        assertEquals(p1.move(Direction.Up), "A attacks!\nB loses 1 hitpoints.");
        assertEquals(p1.getGold(), gold_before);
    }

    /**
     * Tests the move of the pawn p1 from (0,0) [bonus square] to (0,1), where an other pawn p2 is located
     *
     * @type Functional
     * @oracle Must returns "A attacks!\nB loses 2 hitpoints.B is dead.", and gold_current = gold_before - 1
     * @passed Yes
     * @see Pawn#move(Direction d)
     */
    @Test
    public void test_move_UP_FromBonusSquare_OnUsedSquare() throws OutOfBoardException {
        Pawn p2 = new Pawn('B', 0, 1, board);
        when(board.getSquareContent(0, 1)).thenReturn(p2);
        when(board.isBonusSquare(0, 0)).thenReturn(true);
        int gold_before = p1.getGold();
        assertEquals(p1.move(Direction.Up), "A attacks!\nB loses 2 hitpoints.B is dead.");
        assertEquals(p1.getGold() - 1, gold_before);
    }

    /**
     * Tests the move of the pawn p1 from (0,0) [bonus square] to (1,0), which is free
     *
     * @type Functional
     * @oracle Must returns "", and gold_current = gold_before
     * @passed Yes
     * @see Pawn#move(Direction d)
     */
    @Test
    public void test_move_RIGHT_OnFreeSquare()  throws OutOfBoardException {
        int gold_before = p1.getGold();
        assertEquals(p1.move(Direction.Right), "");
        assertEquals(p1.getGold(), gold_before);
    }

    /**
     * Tests the move of the pawn p1 from (0,0) [normal square] to (0,-1), which is out of the board
     *
     * @type Functional
     * @oracle Must throws an OutOfBoardException
     * @passed Yes
     * @see Pawn#move(Direction d)
     */
    @Test (expected = OutOfBoardException.class)
    public void test_move_DOWN_toOutOfBoard() throws OutOfBoardException {
        p1.move(Direction.Down);
    }


}
