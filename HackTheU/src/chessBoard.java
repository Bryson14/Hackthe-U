import java.util.ArrayList;

public class chessBoard {

    private boolean currentTeam;
    private gamePiece grid[][] = new gamePiece[8][8];
    public ArrayList<gamePiece> teamTrueGraveyard = new ArrayList<>();
    public ArrayList<gamePiece> teamFalseGraveyard = new ArrayList<>();

    chessBoard() {
        setNewBoard();
        currentTeam = true; // white team
    }

    void setNewBoard() {
        // rooks
        grid[0][0] = new Rook("BlackRook1", 0,0,false);
        grid[7][0] = new Rook("BlackRook2", 7,0,false);
        grid[0][7] = new Rook("WhiteRook1", 0,7,true);
        grid[7][7] = new Rook("WhileRook2", 7,7,true);
        //queens
        grid[3][0] = new Queen("BlackQueen", 3, 0, false);
        grid[3][7] = new Queen("WhiteQueen", 3, 7, true);
        //kings
        grid[4][0] = new King("BlackKing", 4, 0, false);
        grid[4][7] = new King("WhiteKing", 4, 7, true);
        //bishops
        grid[2][0] = new Bishop("BlackBishop1", 2,0,false);
        grid[5][0] = new Bishop("BlackBishop2",5,0,false);
        grid[2][7] = new Bishop("WhiteBishop1",2,7,true);
        grid[5][7] = new Bishop("WhiteBishop2", 5,7,true);
        //knights
        grid[1][0] = new Knight("BlackKnight1", 1,0,false);
        grid[6][0] = new Knight("BlackKnight2",6,0,false);
        grid[1][7] = new Knight("WhiteKnight1",1,7,true);
        grid[6][7] = new Knight("WhiteKnight2", 6,7,true);
        //pawns
        for (int i = 0; i < 8; i++) {
            grid[i][1] = new Pawn("BlackPawn"+i, i, 2, false);
            grid[i][6] = new Pawn("WhilePawn"+i, i,6,true);
        }

        printBoard();

    }

    void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(grid[j][i] + "\t");
            }
            System.out.println("");
        }
    }

    /**
     * LOTS IF logic to see if anything is blocking the piece's moves
     * @param objectsMoves
     * @return
     */
    public ArrayList<String> availableMoves(ArrayList<String> objectsMoves) {
        return null;
    }

    /**
     * GUI tells chessBoard that it moves an object from oldspot to newspot
     * @param oldSpot string representing the old spot of piece
     * @param newSpot string representing the new spot of piece
     */
    public void movePiece(int[][] oldSpot, int[][] newSpot) {
        // if there is an enemy piece in old spot, add that to the graveyard

    }

    /**
     * return true if the spot is occupied and its that teams turn
     * @param position a list of length 2. position[0] is row, position[1] is column
     * @return boolean if that spot can be moved on that turn
     */
    public boolean isOccupiedWithCorrectTeam(int[] position) {

        return true;
    }

    public ArrayList<String> getSpotsMoves(int[] position) {
        //identify the object that is there
        //return availablemoves(pieces.moves()) to the GUI
        //
        return null;
    }

    public static void main(String[] args) {
        chessBoard cb = new chessBoard();
    }
}

