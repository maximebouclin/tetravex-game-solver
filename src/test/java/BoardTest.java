import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void getSize() {
        Board b = new Board(3, 6);

        assertEquals(18, b.getSize(), "Incorrect size calculated");
    }

    //Common case
    @Test
    void insertPiece() {
        Board b = new Board(3, 6);
        Piece p = new Piece(99, 1, 2, 3 , 4);
        assertTrue(b.insertPiece(p, 2, 2), "Valid piece insertion should return true");
    }

    //Common case
    @Test
    void insertPiece7() {
        Board b = new Board(2, 2);
        Piece p1 = new Piece(1, 5, 14, 8, 11);
        Piece p2 = new Piece(2, 2, 3, 5, 4);
        Piece p3 = new Piece(3, 6, 4, 7, 4);
        Piece p4 = new Piece(4, 7, 11, 12, 5);
        assertTrue(b.insertPiece(p1, 1, 1), "Valid piece insertion should return true");
        assertTrue(b.insertPiece(p2, 1, 2), "Valid piece insertion should return true");
        assertTrue(b.insertPiece(p3, 2, 2), "Valid piece insertion should return true");
        assertTrue(b.insertPiece(p4, 2, 1), "Valid piece insertion should return true");
    }

    //Common case 2
    @Test
    void insertPiece8() {
        Board b = new Board(2, 2);
        Piece p1 = new Piece(1, 6, 8, 3, 4);
        Piece p2 = new Piece(2, 6, 7, 6, 1);
        Piece p3 = new Piece(3, 2, 1, 4, 3);
        Piece p4 = new Piece(4, 4, 4, 9, 2);
        assertTrue(b.insertPiece(p1, 1, 1), "Valid piece insertion should return true");
        assertTrue(b.insertPiece(p2, 1, 2), "Valid piece insertion should return true");
        assertTrue(b.insertPiece(p3, 2, 2), "Valid piece insertion should return true");
        assertTrue(b.insertPiece(p4, 2, 1), "Valid piece insertion should return true");
    }

    //Error case
    @Test
    void insertPiece9() {
        Board b = new Board(2, 2);
        Piece p1 = new Piece(1, 6, 8, 3, 4);
        Piece p2 = new Piece(2, 6, 7, 6, 1);
        Piece p3 = new Piece(3, 2, 1, 4, 3);
        Piece p4 = new Piece(4, 6, 4, 9, 2);
        assertTrue(b.insertPiece(p1, 1, 1), "Valid piece insertion should return true");
        assertTrue(b.insertPiece(p2, 1, 2), "Valid piece insertion should return true");
        assertTrue(b.insertPiece(p3, 2, 2), "Valid piece insertion should return true");
        assertFalse(b.insertPiece(p4, 2, 1), "Invalid piece insertion should return false");
    }

    //Error case 2
    @Test
    void insertPiece10() {
        Board b = new Board(2, 2);
        Piece p1 = new Piece(1, 6, 8, 3, 4);
        Piece p2 = new Piece(2, 6, 7, 6, 1);
        Piece p3 = new Piece(3, 2, 10, 4, 3);
        Piece p4 = new Piece(4, 4, 4, 9, 2);
        assertTrue(b.insertPiece(p1, 1, 1), "Valid piece insertion should return true");
        assertTrue(b.insertPiece(p2, 1, 2), "Valid piece insertion should return true");
        assertFalse(b.insertPiece(p3, 2, 2), "Invalid piece insertion should return false");
        assertTrue(b.insertPiece(p4, 2, 1), "Valid piece insertion should return true");
    }

    //Error case 3
    @Test
    void insertPiece11() {
        Board b = new Board(2, 2);
        Piece p1 = new Piece(1, 6, 8, 3, 4);
        Piece p2 = new Piece(2, 6, 7, 6, 1);
        Piece p3 = new Piece(3, 2, 10, 4, 3);
        Piece p4 = new Piece(4, 4, 4, 9, 2);
        assertTrue(b.insertPiece(p1, 1, 1), "Valid piece insertion should return true");
        assertTrue(b.insertPiece(p2, 1, 2), "Valid piece insertion should return true");
        assertFalse(b.insertPiece(p3, 1, 1), "Invalid piece insertion should return false");
        assertTrue(b.insertPiece(p4, 2, 1), "Valid piece insertion should return true");
    }

    //Edge case
    @Test
    void insertPiece2() {
        Board b = new Board(3, 6);
        Piece p = new Piece(99, 1, 2, 3 , 4);
        assertTrue(b.insertPiece(p, 1, 1), "Valid piece insertion should return true");
    }

    //Edge case
    @Test
    void insertPiece3() {
        Board b = new Board(3, 6);
        Piece p = new Piece(99, 1, 2, 3 , 4);
        assertTrue(b.insertPiece(p, 3, 6), "Valid piece insertion should return true");
    }

    //Error case
    @Test
    void insertPiece4() {
        Board b = new Board(3, 6);
        Piece p = new Piece(99, 1, 2, 3 , 4);
        assertFalse(b.insertPiece(p, 4, 6), "Invalid piece insertion should return false");
    }

    //Error case
    @Test
    void insertPiece5() {
        Board b = new Board(3, 6);
        Piece p = new Piece(99, 1, 2, 3 , 4);
        assertFalse(b.insertPiece(p, 0, 0), "Invalid piece insertion should return false");
    }

    //Error case
    @Test
    void insertPiece6() {
        Board b = new Board(3, 6);
        Piece p = new Piece(99, 1, 2, 3 , 4);
        assertFalse(b.insertPiece(p, -10, -80), "Invalid piece insertion should return false");
    }


    //Common case
    @Test
    void getPiece() {
        Board b = new Board(3, 6);
        Piece p = new Piece(99, 1, 2, 3 , 4);
        b.insertPiece(p, 2, 2);
        assertEquals(99, b.getPiece(2, 2).getId(), "Incorrect piece found");
    }

    //Edge case
    @Test
    void getPiece2() {
        Board b = new Board(3, 6);
        Piece p = new Piece(99, 1, 2, 3 , 4);
        b.insertPiece(p, 1, 1);
        assertEquals(99, b.getPiece(1, 1).getId(), "Incorrect piece found");
    }

    //Edge case
    @Test
    void getPiece3() {
        Board b = new Board(3, 6);
        Piece p = new Piece(99, 1, 2, 3 , 4);
        b.insertPiece(p, 3, 6);
        assertEquals(99, b.getPiece(3, 6).getId(), "Incorrect piece found");
    }

    //Error case
    @Test
    void getPiece4() {
        Board b = new Board(3, 6);
        assertNull(b.getPiece(4, 6), "Should have returned null for invalid index");
    }

    //Error case
    @Test
    void getPiece5() {
        Board b = new Board(3, 6);
        assertNull(b.getPiece(0, 0), "Should have returned null for invalid index");
    }


    //Empty board
    @Test
    void print(){
        Board b = new Board(3, 3);
        assertEquals("xxx\txxx\txxx\nxxx\txxx\txxx\nxxx\txxx\txxx\n", b.print());
    }

    //Full board
    @Test
    void print2(){
        Board b = new Board(2, 2);
        Piece p1 = new Piece(1, 5, 14, 8, 11);
        Piece p2 = new Piece(2, 2, 3, 5, 4);
        Piece p3 = new Piece(3, 6, 4, 7, 4);
        Piece p4 = new Piece(4, 7, 11, 12, 5);
        b.insertPiece(p1, 1, 1);
        b.insertPiece(p2, 1, 2);
        b.insertPiece(p3, 2, 2);
        b.insertPiece(p4, 2, 1);
        b.print();
        assertEquals("2\t3\n1\t4\n", b.print());

    }

    //Partially full board
    @Test
    void print3() {
        Board b = new Board(2, 2);
        Piece p1 = new Piece(1, 5, 14, 8, 11);
        Piece p2 = new Piece(3, 6, 4, 7, 4);
        Piece p3 = new Piece(4, 7, 11, 12, 5);
        b.insertPiece(p1, 1, 1);
        b.insertPiece(p2, 2, 2);
        b.insertPiece(p3, 2, 1);
        b.print();
        assertEquals("xxx\t3\n1\t4\n", b.print());
    }
}
