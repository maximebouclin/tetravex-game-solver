import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;

public class PlayTetravex {
    private static final String printCommand = "print";
    private static final String solveCommand = "solve";
    private static final String helpCommand = "help";
    private static final String quitCommand = "quit";

    public static void main( String[] args ) throws IOException {
        Scanner inputStream = new Scanner( System.in );

        // Get the game to load
        System.out.println( "Enter the file name that contains the puzzle to load" );
        String filename = inputStream.nextLine().trim();
        BufferedReader gameStream;
        try {
            gameStream = new BufferedReader(new FileReader(filename));
        } catch (IOException e) {
            System.out.println( "Unable to open file" );
            return;
        }

        // Get the board going
        Tetravex game = new Tetravex();
        if ( game.loadPuzzle( gameStream ) ) {
            // Ask the user what to do

            String line = inputStream.nextLine().trim();
            while (line.compareToIgnoreCase( quitCommand ) != 0) {
                // Simple parsing of commands for now
                if (line.compareToIgnoreCase( printCommand ) == 0 ) {
                    System.out.println( game.print() );
                } else if (line.compareToIgnoreCase( helpCommand ) == 0) {
                    System.out.println("Commands:\n  "+printCommand+" -- show the board\n"+
                            "  p x y -- place piece 'p' in position (x,y)\n" +
                            "  rm x y -- remove the piece in position (x,y)\n" +
                            "  "+solveCommand+" -- give up and have the algorithm find a solution for you\n" +
                            "  "+quitCommand+" -- exit\n"+
                            "  "+helpCommand+" -- print this information" );
                } else if (line.compareToIgnoreCase( solveCommand ) == 0){
                    if(game.solve()) {
                        System.out.println("\nSolution:");
                        System.out.println(game.solution);
                    }else{
                        System.out.println("Puzzle has no solution");
                    }
                }
                else {
                    String[] parts = line.split(" " );
                    if (parts.length == 3) {
                        if(parts[0].equals("rm")){
                            System.out.println("Removing piece " + "at position (" + parts[1] + ", " + parts[2] + ")");
                            if (game.removePiece(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]))) {
                                System.out.println("   --- removement of piece complete");
                            } else {
                                System.out.println("   --- removement of piece rejected");
                            }
                        }
                        else {
                            System.out.println("Placing piece " + parts[0] + " at position (" + parts[1] + ", " + parts[2] + ")");
                            if (game.placePiece(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]))) {
                                System.out.println("   --- placement of piece accepted");
                                if(game.isComplete()){
                                System.out.println("\nCongratulations! You have completed the puzzle.");
                                return;
                                }
                            } else {
                                System.out.println("   --- placement of piece rejected");
                            }
                        }
                    } else {
                        System.out.println( "Wrong number of parameters to place a piece" );
                    }

                }
                line = inputStream.nextLine().trim();
            }
        } else {
            System.out.println( "Unable to read in the game file" );
        }
    }
}
