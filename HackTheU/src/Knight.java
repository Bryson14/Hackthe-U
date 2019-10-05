import java.util.*;

class Knight extends gamePiece {

    Knight(String name, int posX, int posY, boolean team) {
        super(name, posX, posY, team);
        name = "Knight";

    }

    @Override
    public boolean getTeam() {
        return super.getTeam();
    }

    @Override
    public String toString() {
        return getName();
    }

    public ArrayList<String> moves() {
        return null;
    }
}