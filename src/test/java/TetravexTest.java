import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class TetravexTest {

    Tetravex setup(String fileName){
        try {
            BufferedReader gameStream = new BufferedReader(new FileReader(fileName));
            Tetravex T = new Tetravex();
            T.loadPuzzle(gameStream);
            return T;
        } catch (IOException e) {
            System.out.println( "Unable to open file" );
            return null;
        }
    }

    //Board with no pieces
    @Test
    void puzzleLoadTest(){
        Tetravex T = setup("test1.txt");

        assertEquals(3, T.board.width, "Incorrect puzzle width");
        assertEquals(6, T.board.height, "Incorrect puzzle height");
    }

    //Board with one piece
    @Test
    void puzzleLoadTest2(){
        Tetravex T = setup("test2.txt");

        assertEquals(3, T.board.width, "Incorrect puzzle width");
        assertEquals(4, T.board.height, "Incorrect puzzle height");
        assertEquals(1, T.allPieces.size(), "Piece not loaded into the arraylist");

        //Check that piece attributes were loaded correctly
        assertEquals(99, T.allPieces.get(0).getId(), "Piece attribute incorrectly loaded");
        assertEquals(1, T.allPieces.get(0).getTop(), "Piece attribute incorrectly loaded");
        assertEquals(2, T.allPieces.get(0).getLeft(), "Piece attribute incorrectly loaded");
        assertEquals(3, T.allPieces.get(0).getBottom(), "Piece attribute incorrectly loaded");
        assertEquals(4, T.allPieces.get(0).getRight(), "Piece attribute incorrectly loaded");
    }

    //Board with 2 pieces
    @Test
    void puzzleLoadTest3(){
        Tetravex T = setup("test3.txt");

        assertEquals(3, T.board.width, "Incorrect puzzle width");
        assertEquals(4, T.board.height, "Incorrect puzzle height");
        assertEquals(2, T.allPieces.size(), "Piece not loaded into the arraylist");

        //Check that piece attributes were loaded correctly
        assertEquals(10, T.allPieces.get(0).getId(), "Piece attribute incorrectly loaded");
        assertEquals(1, T.allPieces.get(0).getTop(), "Piece attribute incorrectly loaded");
        assertEquals(2, T.allPieces.get(0).getLeft(), "Piece attribute incorrectly loaded");
        assertEquals(3, T.allPieces.get(0).getBottom(), "Piece attribute incorrectly loaded");
        assertEquals(4, T.allPieces.get(0).getRight(), "Piece attribute incorrectly loaded");
        assertEquals(11, T.allPieces.get(1).getId(), "Piece attribute incorrectly loaded");
        assertEquals(10, T.allPieces.get(1).getTop(), "Piece attribute incorrectly loaded");
        assertEquals(20, T.allPieces.get(1).getLeft(), "Piece attribute incorrectly loaded");
        assertEquals(30, T.allPieces.get(1).getBottom(), "Piece attribute incorrectly loaded");
        assertEquals(40, T.allPieces.get(1).getRight(), "Piece attribute incorrectly loaded");
    }

    //ErrorCase
    @Test
    void puzzleLoadTest4(){
        try{
            BufferedReader gameStream = new BufferedReader(new FileReader("test5.txt"));
            Tetravex T = new Tetravex();
            if(T.loadPuzzle(gameStream)){
                fail();
            }
        } catch (Exception e){
            fail();
        }

    }

    //Partially full board
    @Test
    void print() {
        Tetravex T = setup("test4.txt");
        T.placePiece(1, 1, 1);
        T.placePiece(3, 2, 2);
        T.placePiece(4, 2, 1);
        assertEquals("xxx\t3\n1\t4\n--------------------\n2\t2\t3\t5\t4", T.print());
    }

    //Full board
    @Test
    void print2() {
        Tetravex T = setup("test4.txt");
        T.placePiece(1, 1, 1);
        T.placePiece(2, 1, 2);
        T.placePiece(3, 2, 2);
        T.placePiece(4, 2, 1);
        assertEquals("2\t3\n1\t4\n--------------------", T.print());
    }

    //Remove one element
    @Test
    void remove() {
        Tetravex T = setup("test4.txt");
        T.placePiece(1, 1, 1);
        T.placePiece(2, 1, 2);
        T.removePiece(1, 2);
        T.placePiece(3, 2, 2);
        T.placePiece(4, 2, 1);
        assertEquals("xxx\t3\n1\t4\n--------------------\n2\t2\t3\t5\t4", T.print());
    }

    //Remove full board
    @Test
    void remove2() {
        Tetravex T = setup("test4.txt");
        T.placePiece(1, 1, 1);
        T.placePiece(2, 1, 2);
        T.placePiece(3, 2, 2);
        T.placePiece(4, 2, 1);
        T.removePiece(1, 1);
        T.removePiece(1, 2);
        T.removePiece(2, 2);
        T.removePiece(2, 1);
        assertEquals("xxx\txxx\nxxx\txxx\n--------------------\n1\t5\t14\t8\t11\n2\t2\t3\t5\t4\n3\t6\t4\t7\t4\n4\t7\t11\t12\t5", T.print());
    }

    //Error case
    @Test
    void remove3() {
        Tetravex T = setup("test4.txt");
        T.placePiece(1, 1, 1);
        T.placePiece(3, 2, 2);
        T.placePiece(4, 2, 1);
        T.removePiece(4, 1);
        T.removePiece(1, 2);
        assertEquals("xxx\t3\n1\t4\n--------------------\n2\t2\t3\t5\t4", T.print());
    }

    @Test
    void solve2by2(){
        Tetravex T = setup("test4.txt");
        T.solve();
        System.out.print(T.solution);
    }

    @Test
    void solve3by3(){
        Tetravex T = setup("test6.txt");
        T.solve();
        System.out.print(T.solution);
    }

    @Test
    void solve3by3again(){
        Tetravex T = setup("test8.txt");
        T.solve();
        System.out.print(T.solution);
    }

    @Test
    void solve3by3againagain(){
        Tetravex T = setup("test10.txt");
        T.solve();
        System.out.print(T.solution);
    }

    @Test
    void solve4by4(){
        Tetravex T = setup("test11.txt");
        T.solve();
        System.out.print(T.solution);
    }

    @Test
    void solve4by4again(){
        Tetravex T = setup("test14.txt");
        T.solve();
        System.out.print(T.solution);
    }

    @Test
    void solve5by5(){
        Tetravex T = setup("test9.txt");
        T.solve();
        System.out.print(T.solution);
    }

    @Test
    void solve6by6(){
        Tetravex T = setup("test12.txt");
        T.solve();
        System.out.print(T.solution);
    }

    @Test
    void solve7by7(){
        Tetravex T = setup("test13.txt");
        T.solve();
        System.out.print(T.solution);
    }
}


