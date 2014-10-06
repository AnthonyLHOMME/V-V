package simpleGame.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestBoard {
//
//    @Test
//    public void test_getSquareContent() {
//        Board board = new Board(10, 10, 10, 0, 0);
//        int nbPawns = 0;
//        for (int i = 0; i < board.getXSize(); i++) {
//            for (int j = 0; j < board.getYSize(); j++) {
//                if (board.getSquareContent(i, j) != null) {
//                    nbPawns++;
//                }
//            }
//        }
//        assertEquals(nbPawns, 10);
//    }

    @Test
    public void test_addPawn_diffPlace() {
        Board board = new Board(1, 10, 10, 0, 0);

        Pawn p1 = mock(Pawn.class);
        Pawn p2 = mock(Pawn.class);
        when(p1.getX()).thenReturn(0);
        when(p1.getY()).thenReturn(0);
        when(p2.getX()).thenReturn(1);
        when(p2.getY()).thenReturn(1);

        board.removeAllPawns();
        assertEquals(board.numberOfPawns(), 0);
        board.addPawn(p1);
        board.addPawn(p2);
        assertEquals(board.numberOfPawns(), 2);
    }

    @Test
    public void test_addPawn_sameX() {
        Board board = new Board(1, 10, 10, 0, 0);

        Pawn p1 = mock(Pawn.class);
        Pawn p2 = mock(Pawn.class);
        when(p1.getX()).thenReturn(0);
        when(p1.getY()).thenReturn(0);
        when(p2.getX()).thenReturn(0);
        when(p2.getY()).thenReturn(1);

        board.removeAllPawns();
        assertEquals(board.numberOfPawns(), 0);
        board.addPawn(p1);
        board.addPawn(p2);
        assertEquals(board.numberOfPawns(), 2);
    }

    @Test
    public void test_addPawn_samePlace() {
        Board board = new Board(1, 10, 10, 0, 0);

        Pawn p1 = mock(Pawn.class);
        Pawn p2 = mock(Pawn.class);
        when(p1.getX()).thenReturn(0);
        when(p1.getY()).thenReturn(0);
        when(p2.getX()).thenReturn(0);
        when(p2.getY()).thenReturn(0);

        board.removeAllPawns();
        assertEquals(board.numberOfPawns(), 0);
        board.addPawn(p1);
        board.addPawn(p2);
        assertEquals(board.numberOfPawns(), 1);
    }

}