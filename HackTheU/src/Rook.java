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

    /**
     * return all possible moves of a rook not considering being out of the board or other players
     * @param grid current game state
     * @return list of possible coordinates
     */
    @Override
    public ArrayList<Coordinates> moves(gamePiece[][] grid) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<>();

        boolean rightOpen, leftOpen, upOpen, downOpen;
        upOpen = true;
        downOpen = true;
        leftOpen = true;
        rightOpen = true;

        for (int i = 1; i < 8; i++) {

            if (getPosY() + i < 8 && downOpen) {
                if (grid[getPosX()][getPosY() + i] != null) {
                    downOpen = false;
                    if (isEnemy(grid, getPosX(), getPosY() + i)) {
                        possibleMoves.add(new Coordinates(getPosX(),getPosY() + i));
                    }
                } else possibleMoves.add(new Coordinates(getPosX(),getPosY() + i));
            }

            if (getPosY() - i >= 0 && upOpen) {
                if (grid[getPosX()][getPosY() - i] != null) {

                    upOpen = false;
                    if (isEnemy(grid, getPosX(),getPosY() - i)) {
                        possibleMoves.add(new Coordinates(getPosX(),getPosY() - i));
                    }

                } else possibleMoves.add(new Coordinates(getPosX(),getPosY() - i));
            }

            if (getPosX() + i < 8 && rightOpen) {
                if (grid[getPosX() + i][getPosY()] != null) {

                    rightOpen = false;
                    if (isEnemy(grid, getPosX() + i, getPosY())) {
                        possibleMoves.add(new Coordinates(getPosX() + i,getPosY()));
                    }

                } else possibleMoves.add(new Coordinates(getPosX() + i,getPosY()));
            }

            if (getPosX() - i >= 0 && leftOpen) {
                if (grid[getPosX() - i][getPosY()] != null) {

                    leftOpen = false;
                    if (isEnemy(grid, getPosX() - i, getPosY())) {
                        possibleMoves.add(new Coordinates(getPosX() - i,getPosY()));
                    }

                } else possibleMoves.add(new Coordinates(getPosX() - i,getPosY()));
            }
        }

        return possibleMoves;
    }
}
