import java.util.*;

class Knight extends gamePiece {

    Knight(String name, int posX, int posY, boolean team) {
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

    public ArrayList<Coordinates> moves() {
        return null;
    }
}