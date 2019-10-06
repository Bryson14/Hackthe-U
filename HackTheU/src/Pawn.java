import java.util.ArrayList;

public class Pawn extends gamePiece{

    Pawn(String name, int posX, int posY, boolean team) {
        super(name, posX, posY, team);
    }

    @Override
    public int getPosY() {
        return super.getPosY();
    }

    @Override
    public int getPosX() {
        return super.getPosX();
    }

    @Override
    public boolean getTeam() {
        return super.getTeam();
    }

    @Override
    public void setPosY(int posY) {
        super.setPosY(posY);
    }

    @Override
    public void setPosX(int posX) {
        super.setPosX(posX);
    }

    @Override
    public void incrementNumberOfMoves() {
        super.incrementNumberOfMoves();
    }

    @Override
    public int getNumberOfMoves() {
        return super.getNumberOfMoves();
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * return all possible moves of a pawn not considering being out of the board or other players
     * @param grid current game state
     * @return list of possible coordinates
     */
    @Override
    public ArrayList<Coordinates> moves(gamePiece[][] grid) {

        ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();


        int ySwitch;
        if (getTeam()) ySwitch = -1; // white team going up
        else ySwitch = 1; // black team going down

        try {
            // Pawn has a special case first move
            if (getNumberOfMoves() == 0 && grid[getPosX()][getPosY() + ySwitch] == null && grid[getPosX()][getPosY() + ySwitch * 2] == null) {
                possibleMoves.add(new Coordinates(getPosX(), getPosY() + 2 * ySwitch));
            }
        } catch (ArrayIndexOutOfBoundsException e) {}

        // Else they move just one or attack
        try {
            if (grid[getPosX()][getPosY() + ySwitch] != null) ; //pawn can't move if something is in front of it
            else possibleMoves.add(new Coordinates(getPosX(), getPosY() + ySwitch));
        } catch (ArrayIndexOutOfBoundsException e) {}

        try {
            if (grid[getPosX() + 1][getPosY() + ySwitch] != null) { //something is there
                if (grid[getPosX() + 1][getPosY() + ySwitch].getTeam() != getTeam()) { //its an enemy
                    possibleMoves.add(new Coordinates(getPosX() + 1, getPosY() + ySwitch));
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {}

        try {
            if (grid[getPosX() - 1][getPosY() + ySwitch] != null) { //something is there
                if (grid[getPosX() - 1][getPosY() + ySwitch].getTeam() != getTeam()) { //its an enemy
                    possibleMoves.add(new Coordinates(getPosX() - 1, getPosY() + ySwitch));
                }
            }
        } catch (ArrayIndexOutOfBoundsException e ) {}
        //removes everything out of bounds
        int i = 0;
        while (i < possibleMoves.size()){
            if (possibleMoves.get(i).x > 7 || possibleMoves.get(i).x < 0 || possibleMoves.get(i).y > 7 || possibleMoves.get(i).y < 0) { //outside board
                possibleMoves.remove(i);

            } else {
                i++; // moves increment forward if nothing was removed
            }
        }
        return possibleMoves;
    }


}