import java.util.*;

class Queen extends gamePiece {

    Queen(String name, int posX, int posY, boolean team) {
        super(name, posX, posY, team);
        name = "Queen";

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

    public void canMove() {

    }

    public void move(){

    }

    public ArrayList<String> moves() {
        // Queens can make any movement on the board
        return null;
    }
}