public class Board {
    private Piece[][] grid;
    private final int size;
    int width;
    int height;
    public Board (int colCount, int rowCount){
        this.width = colCount;
        this.height = rowCount;
        grid = new Piece[rowCount][colCount];
        size = rowCount * colCount;
    }
    public int getSize() {
        return size;
    }

    public boolean insertPiece(Piece pieceToInsert, int columnIndex, int rowIndex){
        //Calculate indexes is terms of the 2d Array
        int firstArrayIndex = height-rowIndex;
        int secondArrayIndex = columnIndex-1;

        //Return false if rowIndex is not in grid
        if(firstArrayIndex < 0 || firstArrayIndex > height-1){
            return false;
        }
        //Return false if columnIndex is not in grid
        if(secondArrayIndex < 0 || secondArrayIndex > width-1){
            return false;
        }
        //Return false if there is already a piece in the specified location
        if (grid[firstArrayIndex][secondArrayIndex] != null){
            return false;
        }

        //Check that top neighbor is valid
        if (rowIndex != height
            && grid[firstArrayIndex-1][secondArrayIndex] != null
            && pieceToInsert.getTop() != grid[firstArrayIndex-1][secondArrayIndex].getBottom())
            {
            return false;
        }

        //Check that left neighbor is valid
        if (columnIndex != 1
            && grid[firstArrayIndex][secondArrayIndex-1] != null
            && pieceToInsert.getLeft() != grid[firstArrayIndex][secondArrayIndex-1].getRight())
            {
            return false;
        }

        //Check that bottom neighbor is valid
        if (rowIndex != 1
            && grid[firstArrayIndex+1][secondArrayIndex] != null
            && pieceToInsert.getBottom() != grid[firstArrayIndex+1][secondArrayIndex].getTop())
            {
            return false;
        }

        //Check that right neighbor is valid
        if (columnIndex != width
            && grid[firstArrayIndex][secondArrayIndex+1] != null
            && pieceToInsert.getRight() != grid[firstArrayIndex][secondArrayIndex+1].getLeft())
            {
            return false;
        }

        //Insert piece in grid if position is valid
        grid[firstArrayIndex][secondArrayIndex] = pieceToInsert;
        return true;
    }

    public Piece getPiece(int columnIndex, int rowIndex){
        //Calculate indexes is terms of the 2d Array
        int firstArrayIndex = height-rowIndex;
        int secondArrayIndex = columnIndex-1;

        //Return false if rowIndex is not in grid
        if(firstArrayIndex < 0 || firstArrayIndex > height-1){
            return null;
        }
        //Return false if columnIndex is not in grid
        if(secondArrayIndex < 0 || secondArrayIndex > width-1){
            return null;
        }
        //Return false if there is no piece in the specified location
        if (grid[firstArrayIndex][secondArrayIndex] == null){
            return null;
        }

        //Return piece in grid if position is valid
        return grid[firstArrayIndex][secondArrayIndex];
    }

    public Piece removePiece(int columnIndex, int rowIndex) {
        //Calculate indexes is terms of the 2d Array
        int firstArrayIndex = height-rowIndex;
        int secondArrayIndex = columnIndex-1;
        Piece pieceToRemove = getPiece(columnIndex, rowIndex);
        if (pieceToRemove != null){
            grid[firstArrayIndex][secondArrayIndex] = null;
            return pieceToRemove;
        }
        return null;
    }

    public void clearBoard() {
        for (int i = 0; i < height; i ++){
            for (int j = 0; j < width; j++){
                grid[i][j] = null;
            }
        }
    }

    public String print() {
        String output = "";
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (grid[i][j] == null){
                    output += "xxx";
                }
                else{
                    output += grid[i][j].getId();
                }

                if(j != width-1){
                    output += "\t";
                }
            }
            output += "\n";
        }
        return output;
    }
}
