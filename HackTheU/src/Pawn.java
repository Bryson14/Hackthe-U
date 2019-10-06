import java.util.ArrayList;

public class Pawn extends gamePiece{

    private int numberOfMoves;

    Pawn(String name, int posX, int posY, boolean team) {
        super(name, posX, posY, team);
        numberOfMoves = 0;
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

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void incrementNumberOfMoves() {
        this.numberOfMoves++;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public ArrayList<Coordinates> moves() {

        ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();

        // Pawn has a special case first move
        if (numberOfMoves == 0) {
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