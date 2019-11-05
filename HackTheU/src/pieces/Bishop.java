package pieces;

import java.util.ArrayList;

public class Bishop extends gamePiece{

    public Bishop(String name, int posX, int posY, boolean team) {
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
        int j = 1;

        while ((upLeftOpen || upRightOpen || downLeftOpen || downRightOpen ) && j < 8) {

            if ((isEnemy(grid, getPosX() + j, getPosY() + j) || isOpenSpace(grid, getPosX() + j, getPosY() + j)) && upRightOpen) {
                possibleMoves.add(new Coordinates(getPosX() + j, getPosY() + j));
                if (isEnemy(grid, getPosX() + j, getPosY() + j)) upRightOpen = false;
            } else upRightOpen = false;
            if ((isEnemy(grid, getPosX() - j, getPosY() - j) || isOpenSpace(grid, getPosX() - j, getPosY() - j)) && downLeftOpen) {
                possibleMoves.add(new Coordinates(getPosX() - j, getPosY() - j));
                if (isEnemy(grid, getPosX() - j, getPosY() - j)) downLeftOpen = false;
            } else downLeftOpen = false;
            if ((isEnemy(grid, getPosX() + j, getPosY() - j) || isOpenSpace(grid, getPosX() + j, getPosY() - j)) && downRightOpen) {
                possibleMoves.add(new Coordinates(getPosX() + j, getPosY() - j));
                if (isEnemy(grid, getPosX() + j, getPosY() - j)) downRightOpen = false;
            } else downRightOpen = false;
            if ((isEnemy(grid, getPosX() - j, getPosY() + j) || isOpenSpace(grid, getPosX() - j, getPosY() + j)) && upLeftOpen) {
                possibleMoves.add(new Coordinates(getPosX() - j, getPosY() + j));
                if (isEnemy(grid, getPosX() - j, getPosY() + j)) upLeftOpen = false;
            } else upLeftOpen = false;

            j++;
        }

        return possibleMoves;
    }
}
