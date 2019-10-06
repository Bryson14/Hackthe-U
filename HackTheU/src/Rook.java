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
    public boolean getTeam() {
        return super.getTeam();
    }

    @Override
    public void setPosX(int posX) {
        super.setPosX(posX);
    }

    @Override
    public int getNumberOfMoves() {
        return super.getNumberOfMoves();
    }

    @Override
    public void incrementNumberOfMoves() {
        super.incrementNumberOfMoves();
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
    public ArrayList<Coordinates> moves(gamePiece[][] grid) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<>();

        for (int i = 1; i < 7; i++) {

            //all horizontal and vertical
            possibleMoves.add(new Coordinates(getPosX(),getPosY() + i)); //up
            possibleMoves.add(new Coordinates(getPosX(),getPosY() - i)); // down
            possibleMoves.add(new Coordinates(getPosX() + i,getPosY())); // right
            possibleMoves.add(new Coordinates(getPosX() - i,getPosY())); // left
        }

        return super.whatsInTheWay(grid, possibleMoves);
    }
}
