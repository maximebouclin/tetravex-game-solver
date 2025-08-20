import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    void getters(){
        Piece p = new Piece(1, 2, 3, 4, 5);

        assertEquals(1, p.getId());
        assertEquals(2, p.getTop());
        assertEquals(3, p.getLeft());
        assertEquals(4, p.getBottom());
        assertEquals(5, p.getRight());
    }

    @Test
    void setters(){
        Piece p = new Piece(11, 23, 34, 45, 56);
        p.setId(1);
        p.setTop(2);
        p.setLeft(3);
        p.setBottom(4);
        p.setRight(5);

        assertEquals(1, p.getId());
        assertEquals(2, p.getTop());
        assertEquals(3, p.getLeft());
        assertEquals(4, p.getBottom());
        assertEquals(5, p.getRight());
    }
}