import java.util.ArrayList;

public class chessBoard {

    private boolean currentTeam;
    private gamePiece grid[][] = new gamePiece[8][8];
    public ArrayList<gamePiece> teamTrueGraveyard = new ArrayList<>();
    public ArrayList<gamePiece> teamFalseGraveyard = new ArrayList<>();

    void setNewBoard() {

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
    public void movePiece(String oldSpot, String newSpot) {
        // if there is an enemy piece in old spot, add that to the graveyard
    }

    /**
     * return true if the spot is occupied and its that teams turn
     * @param position position on grid
     * @return boolean if that spot can be moved on that turn
     */
    public boolean isOccupiedWithCorrectTeam(String position) {
        return true;
    }

    public ArrayList<String> getSpotsMoves(String position) {
        //identify the object that is there
        //return availablemoves(pieces.moves()) to the GUI
        //
        return null;
    }
}
