package pieces;

import java.util.*;

public class Knight extends gamePiece {

    public Knight(String name, int posX, int posY, boolean team) {
        super(name, posX, posY, team);
    }

    @Override
    public boolean getTeam() {
        return super.getTeam();
    }

    @Override
    public int getNumberOfMoves() {
        return super.getNumberOfMoves();
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
    public String getName() {
        return super.getName();
    }

    @Override
    public void incrementNumberOfMoves() {
        super.incrementNumberOfMoves();
    }

    @Override
    public void setPosX(int posX) {
        super.setPosX(posX);
    }

    @Override
    public void setPosY(int posY) {
        super.setPosY(posY);
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * return all possible moves of a knight not considering being out of the board or other players
     * @param grid current game state
     * @return list of possible coordinates
     */
    public ArrayList<Coordinates> moves(gamePiece[][] grid) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<>();

        //All possible moves for a knight
        for (int i = -1; i < 2; i+=2) {
            possibleMoves.add(new Coordinates(getPosX() + (i * 2),getPosY() + 1)); //upper wide
            possibleMoves.add(new Coordinates(getPosX() - (i * 2),getPosY() + -1)); //lower wide
            possibleMoves.add(new Coordinates(getPosX() - 1,getPosY() + (i*2))); //left tall
            possibleMoves.add(new Coordinates(getPosX() + 1,getPosY() - (i*2))); //right tall
        }

        //removes everything out of bounds
        int i = 0;
        while (i < possibleMoves.size()){
            if (possibleMoves.get(i).x > 7 || possibleMoves.get(i).x < 0 || possibleMoves.get(i).y > 7 || possibleMoves.get(i).y < 0) { //outside board
                possibleMoves.remove(i);

                // another piece is there
            } else if (grid[possibleMoves.get(i).x][possibleMoves.get(i).y] != null) {

                // a teammate is there
                if (grid[possibleMoves.get(i).x][possibleMoves.get(i).y].getTeam() == getTeam()) possibleMoves.remove(i);
                else i++;

            } else {
                i++; // moves increment forward if nothing was removed
            }
        }
        return possibleMoves;
    }
}