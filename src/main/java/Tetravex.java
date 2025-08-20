import java.io.*;
import java.util.ArrayList;

public class Tetravex {
    Board board;
    ArrayList<Piece> allPieces = new ArrayList<>();
    ArrayList<Piece> unplacedPieces = new ArrayList<>();
    String solution;
    public boolean loadPuzzle(BufferedReader stream) throws IOException {
        try {
            //Get puzzle dimensions
            String[] puzzleDimensions = stream.readLine().split("\\s+");
            int width = Integer.parseInt(puzzleDimensions[0]);
            int height = Integer.parseInt(puzzleDimensions[1]);
            if (width < 1 || height < 1){
                return false;
            }
            board = new Board(width, height);

            //Get puzzle pieces
            String line;
            while ((line = stream.readLine()) != null) {
                //Create a new piece object with each parameter being corresponding token in the line
                String[] tokens = line.split("\\s+");
                //Check that id is not already used
                for(Piece p : allPieces){
                    if(p.getId() == Integer.parseInt(tokens[0])){
                        allPieces.clear();
                        board = null;
                        return false;
                    }
                }
                allPieces.add(new Piece(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
                                        Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]),
                                        Integer.parseInt(tokens[4])));
            }
            unplacedPieces.addAll(allPieces);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public boolean placePiece(int pieceId, int xPosition, int yPosition){
        for (Piece p : unplacedPieces){
            if(p.getId() == pieceId){
                if(board.insertPiece(p, xPosition, yPosition)) {
                    unplacedPieces.remove(p);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removePiece(int xPosition, int yPosition){
        Piece pieceToRemove = board.removePiece(xPosition, yPosition);
        if(pieceToRemove != null){
            unplacedPieces.add(pieceToRemove);
        }
        return false;
    }

    public boolean solve(){
        //Remove all the pieces from the board in case a player had already placed some
        if (allPieces.size() < board.getSize()) { return false; }
        for (Piece p : allPieces) {
            if (!unplacedPieces.contains(p)) {
                unplacedPieces.add(p);
            }
        }
        board.clearBoard();

        ArrayList<Piece> unplacedPiecesSolving = new ArrayList<>(allPieces);
        for(int i = 0; i < allPieces.size(); i++){
            if(placeSolving(allPieces.get(i), 1, 1, unplacedPiecesSolving)){
                return true;
            };
        }
        return false;
    }

    public boolean placeSolving(Piece pieceToInsert, int xPos, int yPos, ArrayList<Piece> unplacedPiecesSolving) {
        //Try to insert piece. If possible, continue recursion
        if (board.insertPiece(pieceToInsert, xPos, yPos)) {
            unplacedPiecesSolving.remove(pieceToInsert);

            if(unplacedPiecesSolving.isEmpty()){
                //return true if no more pieces can be placed
                solution = board.print();
                return true;
            }

            //If open space above find match
            if (yPos != board.height && board.getPiece(xPos, yPos + 1) == null) {
                //For all available pieces
                int arrayListSize = unplacedPiecesSolving.size();
                for (int i = 0; i < arrayListSize; i++) {
                    //If piece matches
                    if (pieceToInsert.getTop() == unplacedPiecesSolving.get(i).getBottom()) {
                        ArrayList<Piece> passedUnplaced = new ArrayList<>(unplacedPiecesSolving);
                        if (placeSolving(unplacedPiecesSolving.get(i), xPos, yPos + 1, passedUnplaced)) {
                            return true;
                        }
                    }
                }
                unplacedPiecesSolving.add(pieceToInsert);
                board.removePiece(xPos, yPos);
                return false;
            }

            //If open space bellow find match
            if (yPos != 1 && board.getPiece(xPos, yPos - 1) == null) {
                //For all available pieces
                int arrayListSize = unplacedPiecesSolving.size();
                for (int i = 0; i < arrayListSize; i++) {
                    //If piece matches
                    if (pieceToInsert.getBottom() == unplacedPiecesSolving.get(i).getTop()) {
                        ArrayList<Piece> passedUnplaced = new ArrayList<>(unplacedPiecesSolving);
                        if (placeSolving(unplacedPiecesSolving.get(i), xPos, yPos - 1, passedUnplaced)) {
                            return true;
                        }
                    }
                }
                unplacedPiecesSolving.add(pieceToInsert);
                board.removePiece(xPos, yPos);
                return false;
            }

            //If open space to the right find match
            if (xPos != board.width && board.getPiece(xPos + 1, yPos) == null) {
                //For all available pieces
                int arrayListSize = unplacedPiecesSolving.size();
                for (int i = 0; i < arrayListSize; i++) {
                    //If piece matches
                    if (pieceToInsert.getRight() == unplacedPiecesSolving.get(i).getLeft()) {
                        ArrayList<Piece> passedUnplaced = new ArrayList<>(unplacedPiecesSolving);
                        if (placeSolving(unplacedPiecesSolving.get(i), xPos + 1, yPos, passedUnplaced)) {
                            return true;
                        }
                    }
                }
                unplacedPiecesSolving.add(pieceToInsert);
                board.removePiece(xPos, yPos);
                return false;
            } return false;
        } else{
            return false; }
    }

    public boolean isComplete(){
        return allPieces.size() - unplacedPieces.size() == board.getSize();
    }

    public String print(){
        String output = board.print();
        for (int i = 0; i < 20; i ++){
            output += "-";
        }
        if (unplacedPieces.size() > 0) { output += "\n"; }
        for (int i = 0; i < unplacedPieces.size(); i++){
            output += unplacedPieces.get(i).getId();
            output += "\t";
            output += unplacedPieces.get(i).getTop();
            output += "\t";
            output += unplacedPieces.get(i).getLeft();
            output += "\t";
            output += unplacedPieces.get(i).getBottom();
            output += "\t";
            output += unplacedPieces.get(i).getRight();
            if(i != unplacedPieces.size()-1) {
                output += "\n";
            }
        }
        return output;
    }
}
