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

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    @Override
    public ArrayList<String> moves() {
        return null;
    }
}
