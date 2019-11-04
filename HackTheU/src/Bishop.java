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

    /**
     * return all possible moves of a bishop not considering being out of the board or other players
     * @param grid current game state
     * @return list of possible coordinates
     */
    @Override
    public ArrayList<Coordinates> moves(gamePiece[][] grid) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<>();

        boolean upRightOpen, upLeftOpen, downRightOpen, downLeftOpen;
        downRightOpen = true;
        downLeftOpen = true;
        upLeftOpen = true;
        upRightOpen = true;

        for (int i = 1; i < 8; i++) {

            if (getPosY() + i < 8 && getPosX() + i < 8 && downRightOpen) {
                if (grid[getPosX() + i][getPosY() + i] != null) {
                    downRightOpen = false;
                    if (isEnemy(grid, getPosX() + i, getPosY() + i)) {
                        possibleMoves.add(new Coordinates(getPosX() + i,getPosY() + i));
                    }
                } else possibleMoves.add(new Coordinates(getPosX() + i,getPosY() + i));
            }

            if (getPosX() - i >= 0 && getPosY() + i < 8 && downLeftOpen) {
                if (grid[getPosX() - i][getPosY() + i] != null) {

                    downLeftOpen = false;
                    if (isEnemy(grid, getPosX() - i, getPosY() + i)) {
                        possibleMoves.add(new Coordinates(getPosX() - i,getPosY() + i));
                    }

                } else possibleMoves.add(new Coordinates(getPosX() - i,getPosY() +i));
            }

            if (getPosX() + i < 8 && getPosY() - i >= 0 && upRightOpen) {
                if (grid[getPosX() + i][getPosY() - i] != null) {

                    upRightOpen = false;
                    if (isEnemy(grid, getPosX() + i, getPosY() - i)) {
                        possibleMoves.add(new Coordinates(getPosX() + i,getPosY() - i));
                    }

                } else possibleMoves.add(new Coordinates(getPosX() + i,getPosY() - i));
            }

            if (getPosX() - i >= 0 && getPosY() - i >= 0 && upLeftOpen) {
                if (grid[getPosX() - i][getPosY() - i] != null) {

                    upLeftOpen = false;
                    if (isEnemy(grid, getPosX() - i, getPosY() - i)) {
                        possibleMoves.add(new Coordinates(getPosX() - i,getPosY() - i));
                    }

                } else possibleMoves.add(new Coordinates(getPosX() - i,getPosY() - i));
            }
        }

        return possibleMoves;
    }
}
