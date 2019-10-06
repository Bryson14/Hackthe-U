import javafx.scene.layout.CornerRadii;

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

    /**
     * creates a new board with false(black) team on top and true(white) team on bottom
     */
    void setNewBoard() {
        // rooks
        grid[0][0] = new Rook("BlackRook1 ", 0,0,false);
        grid[7][0] = new Rook("BlackRook2 ", 7,0,false);
        grid[0][7] = new Rook("WhiteRook1 ", 0,7,true);
        grid[7][7] = new Rook("WhiteRook2 ", 7,7,true);
        //queens
        grid[3][0] = new Queen("BlackQueen   ", 3, 0, false);
        grid[3][7] = new Queen("WhiteQueen   ", 3, 7, true);
        //kings
        grid[4][0] = new King("BlackKing    ", 4, 0, false);
        grid[4][7] = new King("WhiteKing    ", 4, 7, true);
        //bishops
        grid[2][0] = new Bishop("BlackBishop1 ", 2,0,false);
        grid[5][0] = new Bishop("BlackBishop2 ",5,0,false);
        grid[2][7] = new Bishop("WhiteBishop1 ",2,7,true);
        grid[5][7] = new Bishop("WhiteBishop2 ", 5,7,true);
        //knights
        grid[1][0] = new Knight("BlackKnight1", 1,0,false);
        grid[6][0] = new Knight("BlackKnight2",6,0,false);
        grid[1][7] = new Knight("WhiteKnight1",1,7,true);
        grid[6][7] = new Knight("WhiteKnight2", 6,7,true);
        //pawns
        for (int i = 0; i < 8; i++) {
            grid[i][1] = new Pawn("BlackPawn"+i +" ", i, 1, false);
            grid[i][6] = new Pawn("WhitePawn"+i+ " ", i,6,true);
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
        ArrayList<Integer> test = new ArrayList<>();
        test.add(5, 6);
    }



    /**
     * GUI tells chessBoard that it moves an object from oldspot to newspot
     * @param oldSpot string representing the old spot of piece
     * @param newSpot string representing the new spot of piece
     */
    public void movePiece(Coordinates oldSpot, Coordinates newSpot) {
        // if there is an enemy piece in old spot, add that to the graveyard

        if (grid[newSpot.x][newSpot.y] != null) {

            if (grid[newSpot.x][newSpot.y].getName().contains("King")) {
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
     * @return Coordinates of all possible moves for the piece
     */
    public ArrayList<Coordinates> getAvailableMoves(Coordinates pos) {
        if (grid[pos.x][pos.y] == null) return new ArrayList<>();
        return grid[pos.x][pos.y].moves(getGrid());
    }

    /**
     * a fixed 2d list of the current game board state
     * @return current game state
     */
    public gamePiece[][] getGrid() {
        return grid;
    }

    public static void main(String[] args) {
        chessBoard cb = new chessBoard();
        cb.printBoard();
//        cb.movePiece(new Coordinates(0,0), new Coordinates(7,4));
//        System.out.println(cb.teamTrueGraveyard);
//        cb.printBoard();
//        System.out.println(cb.grid[1][0].toString());
//        cb.movePiece(new Coordinates(4,0), new Coordinates(4,3));
//        cb.printBoard();
//        System.out.println("moving" + cb.grid[3][7].toString());
//        System.out.println(cb.getAvailableMoves(new Coordinates(4,1)));
    }
}

