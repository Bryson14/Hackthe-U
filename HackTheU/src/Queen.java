import java.util.*;

class Queen extends gamePiece {

    private int numberOfMoves;

    Queen(String name, int posX, int posY, boolean team) {
        super(name, posX, posY, team);
        numberOfMoves = 0;
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
    public int getPosX() {
        return super.getPosX();
    }

    @Override
    public int getPosY() {
        return super.getPosY();
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void incrementNumberOfMoves() {
        this.numberOfMoves++;
    }

    @Override
    public boolean getTeam() {
        return super.getTeam();
    }

    public ArrayList<Coordinates> moves() {
        return null;
    }

    @Override
    public String toString() {
        return getName();
    }
}