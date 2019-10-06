import java.util.ArrayList;

public class Rook extends gamePiece{

    private int numberOfMoves;
    Rook(String name, int posX, int posY, boolean team) {
        super(name, posX, posY, team);
        numberOfMoves = 0;
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
    public void setPosY(int posY) {
        super.setPosY(posY);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public ArrayList<Coordinates> moves() {
        return null;
    }
}
