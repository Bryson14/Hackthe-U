import java.util.ArrayList;

public class Bishop extends gamePiece{

    Bishop(String name, int posX, int posY, boolean team) {
        super(name, posX, posY, team);
    }

    @Override
    public int getPosX() {
        return super.getPosX();
    }

    @Override
    public int getPosY() {
        return super.getPosY();
    }

    @Override
    public boolean getTeam() {
        return super.getTeam();
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
     * return all possible moves of a bishop not considering being out of the board or other players
     * @param grid current game state
     * @return list of possible coordinates
     */
    @Override
    public ArrayList<Coordinates> moves(gamePiece[][] grid) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();

        for (int i = 1; i < 7; i++) {
            possibleMoves.add(new Coordinates(getPosX() - i,getPosY() + i)); // upper left diag
            possibleMoves.add(new Coordinates(getPosX() + i,getPosY() - i)); // lower right diag
            possibleMoves.add(new Coordinates(getPosX() + i,getPosY() + i)); // upper right diag
            possibleMoves.add(new Coordinates(getPosX() - i,getPosY() - i)); // lower left diag
        }
        return super.whatsInTheWay(grid, possibleMoves);


    }
}
