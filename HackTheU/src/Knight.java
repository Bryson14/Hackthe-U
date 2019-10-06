import java.util.*;

class Knight extends gamePiece {

    private int numberOfMoves;

    Knight(String name, int posX, int posY, boolean team) {
        super(name, posX, posY, team);
        numberOfMoves = 0;
    }

    @Override
    public boolean getTeam() {
        return super.getTeam();
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
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
    public void incrementNumberOfMoves() {
        this.numberOfMoves++;
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

    public ArrayList<Coordinates> moves() {
        return null;
    }
}