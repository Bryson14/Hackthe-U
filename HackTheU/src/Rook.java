<<<<<<< HEAD
import java.util.ArrayList;

public class Rook extends gamePiece{


    Rook(String name, int posX, int posY, boolean team) {
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
    public void setPosX(int posX) {
        super.setPosX(posX);
    }

    @Override
    public void setPosY(int posY) {
        super.setPosY(posY);
    }

    @Override
    public ArrayList<String> moves() {
        return null;
    }
}
=======
import java.util.*;

class Rook extends gamePiece {

    Rook(String name, int posX, int posY, boolean team) {
        super(name, posX, posY, team);
        name = "Rook";

    }

    public void canMove() {

    }

    public void move(){

    }

    public ArrayList<String> moves() {
        return null;
    }
}
>>>>>>> 1fbf3195d5e742428c60d79a297fbcdd54161de5
