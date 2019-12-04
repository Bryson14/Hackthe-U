import pieces.*;

import java.util.ArrayList;

public class ChessBoard {

    private boolean currentTeam;
    private static gamePiece[][] grid = new gamePiece[8][8];
    public ArrayList<gamePiece> teamTrueGraveyard = new ArrayList<>();
    public ArrayList<gamePiece> teamFalseGraveyard = new ArrayList<>();

    ChessBoard() {
        setNewBoard();
        currentTeam = true; // white team
    }

    void reset() {
        grid = new gamePiece[8][8];
        setNewBoard();
        currentTeam = true;
        teamFalseGraveyard.clear();
        teamTrueGraveyard.clear();
    }

    /**
     * creates a new board with false(black) team on top and true(white) team on bottom
     */
    void setNewBoard() {
        // rooks
        grid[0][0] = new Rook("BlackRook", 0,0,false);
        grid[7][0] = new Rook("BlackRook", 7,0,false);
        grid[0][7] = new Rook("WhiteRook", 0,7,true);
        grid[7][7] = new Rook("WhiteRook", 7,7,true);
        //queens
        grid[3][0] = new Queen("BlackQueen", 3, 0, false);
        grid[3][7] = new Queen("WhiteQueen", 3, 7, true);
        //kings
        grid[4][0] = new King("BlackKing", 4, 0, false);
        grid[4][7] = new King("WhiteKing", 4, 7, true);
        //bishops
        grid[2][0] = new Bishop("BlackBishop", 2,0,false);
        grid[5][0] = new Bishop("BlackBishop",5,0,false);
        grid[2][7] = new Bishop("WhiteBishop",2,7,true);
        grid[5][7] = new Bishop("WhiteBishop", 5,7,true);
        //knights
        grid[1][0] = new Knight("BlackKnight", 1,0,false);
        grid[6][0] = new Knight("BlackKnight",6,0,false);
        grid[1][7] = new Knight("WhiteKnight",1,7,true);
        grid[6][7] = new Knight("WhiteKnight", 6,7,true);
        //pawns
        for (int i = 0; i < 8; i++) {
            grid[i][1] = new Pawn("BlackPawn", i, 1, false);
            grid[i][6] = new Pawn("WhitePawn", i,6,true);
        }
    }

    /**
     * prints to console a representation of the board
     */
    void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[j][i] == null) System.out.print("null         ");
                else System.out.print(grid[j][i] + "  ");
            }
            System.out.println("");
        }
    }

    public static boolean isEnemy(Coordinates newSpot){
        return grid[newSpot.x][newSpot.y] == null;
    }

    /**
     * GUI tells chessBoard that it moves an object from oldspot to newspot
     * @param oldSpot string representing the old spot of piece
     * @param newSpot string representing the new spot of piece
     */
    public void movePiece(Coordinates oldSpot, Coordinates newSpot) {
        // if there is an enemy piece in old spot, add that to the graveyard

        if (grid[newSpot.x][newSpot.y] != null) {

            if (grid[newSpot.x][newSpot.y].getName().contains("pieces.King")) {
                System.out.println("You Win");
                System.exit(1);
            }

            //adding killed player to the graveyard
            if (grid[newSpot.x][newSpot.y].getTeam()) teamTrueGraveyard.add(grid[newSpot.x][newSpot.y]);
            else teamFalseGraveyard.add(grid[newSpot.x][newSpot.y]);
        }

        grid[newSpot.x][newSpot.y] = grid[oldSpot.x][oldSpot.y];
        grid[oldSpot.x][oldSpot.y] = null;
        grid[newSpot.x][newSpot.y].incrementNumberOfMoves();
        grid[newSpot.x][newSpot.y].setPosX(newSpot.x);
        grid[newSpot.x][newSpot.y].setPosY(newSpot.y);
        currentTeam ^= true;

    }

    /**
     * return true if the spot is occupied and its that teams turn
     * @param pos a list of length 2. position[0] is row, position[1] is column
     * @return boolean if that spot can be moved on that turn
     */
    public boolean isOccupiedWithCorrectTeam(Coordinates pos) {
        if (grid[pos.x][pos.y] != null) {
            if (grid[pos.x][pos.y].getTeam() == currentTeam){
            return true;
            }
        }
        return false;
    }

    /**
     *
     * @param pos position on the grid that user wants to move
     * @return pieces.Coordinates of all possible moves for the piece
     */
    public ArrayList<Coordinates> getAvailableMoves(Coordinates pos) {
        if (grid[pos.x][pos.y] == null) return new ArrayList<>();
        return grid[pos.x][pos.y].moves(getGrid());
    }

    public boolean getCurrentTeam() {
        return currentTeam;
    }

    /**
     * a fixed 2d list of the current game board state
     * @return current game state
     */
    public gamePiece[][] getGrid() {
        return grid;
    }

//    public static void main(String[] args) {
//        ChessBoard cb = new ChessBoard();
//        cb.printBoard();
//        System.out.println(cb.getAvailableMoves(new pieces.Coordinates(4,1)));
//    }
}

