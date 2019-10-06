import java.util.ArrayList;

public class Bishop extends gamePiece{

    Bishop(String name, int posX, int posY, boolean team) {
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
    public void incrementNumberOfMoves() {
        super.incrementNumberOfMoves();
    }

    @Override
    public int getNumberOfMoves() {
        return super.getNumberOfMoves();
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

    @Override
    public ArrayList<Coordinates> moves(gamePiece[][] grid) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<Coordinates>();

        while (grid == null) {
            for (int i = 0; i < 8; i ++)
                possibleMoves.add(new Coordinates(getPosX() + i, getPosY() + i));
        }
        while (grid == null) {
            for (int i = 0; i < 8; i++)
                possibleMoves.add(new Coordinates(getPosX() - i, getPosY() - i));
        }
        while (grid == null) {
            for (int i = 0; i < 8; i++)
                possibleMoves.add(new Coordinates(getPosX() + i, getPosY() - i));
        }
        while (grid == null) {
            for (int i = 0; i < 8; i++)
                possibleMoves.add(new Coordinates(getPosX() - i, getPosY() + i));
        }
        return super.whatsInTheWay(grid, possibleMoves);


    }
}
