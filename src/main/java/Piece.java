
public class Piece {
        private int id;
        private int top;
        private int left;
        private int bottom;
        private int right; // Numbers on edges

        Piece(int id, int top, int left, int bottom, int right) {
        this.id = id;
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        }

        //Getters
        public int getId(){ return id; }
        public int getTop() { return top; }
        public int getLeft() { return left; }
        public int getBottom() { return bottom; }
        public int getRight() { return right; }


        //Setters
        public void setId(int id) { this.id = id; }
        public void setTop(int top) { this.top = top; }
        public void setLeft(int left) { this.left = left; }
        public void setBottom(int bottom) { this.bottom = bottom; }
        public void setRight(int right) { this.right = right; }

}