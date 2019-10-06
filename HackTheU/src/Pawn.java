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

    @Override
    public ArrayList<Coordinates> moves() {

        ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();

        // Pawn has a special case first move
        if (getNumberOfMoves() == 0) {
            possibleMoves.add(new Coordinates(getPosX(), getPosY() + 2));
        }
        // Else they move just one or attack
        possibleMoves.add(new Coordinates(getPosX(), getPosY() + 1));
        possibleMoves.add(new Coordinates(getPosX(), getPosY() + 1));
        possibleMoves.add(new Coordinates(getPosX() + 1, getPosY() + 1));
        possibleMoves.add(new Coordinates(getPosX() - 1, getPosY() + 1));
        return possibleMoves;
    }
}