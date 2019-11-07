package pieces;

import java.util.ArrayList;


public class Rook extends gamePiece{

    public Rook(String name, int posX, int posY, boolean team) {
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
        int j = 1;


        while (( upOpen || downOpen || rightOpen ||leftOpen) && j < 8) {
            if ((isEnemy(grid, getPosX() + j, getPosY()) || isOpenSpace(grid, getPosX() + j, getPosY())) && rightOpen) {
                possibleMoves.add(new Coordinates(getPosX() + j, getPosY()));
                if (isEnemy(grid, getPosX() + j, getPosY())) rightOpen = false;
            } else rightOpen = false;
            if ((isEnemy(grid, getPosX() - j, getPosY()) || isOpenSpace(grid, getPosX() - j, getPosY())) && leftOpen) {
                possibleMoves.add(new Coordinates(getPosX() - j, getPosY()));
                if (isEnemy(grid, getPosX() - j, getPosY())) leftOpen = false;
            } else leftOpen = false;
            if ((isEnemy(grid, getPosX(), getPosY() + j) || isOpenSpace(grid, getPosX(), getPosY() + j)) && upOpen) {
                possibleMoves.add(new Coordinates(getPosX(), getPosY() + j));
                if (isEnemy(grid, getPosX(), getPosY() + j)) upOpen = false;
            } else upOpen = false;
            if ((isEnemy(grid, getPosX(), getPosY() - j) || isOpenSpace(grid, getPosX(), getPosY() - j)) && downOpen) {
                possibleMoves.add(new Coordinates(getPosX(), getPosY() - j));
                if (isEnemy(grid, getPosX(), getPosY() - j)) downOpen = false;
            } else downOpen = false;

            j++;
        }

        return possibleMoves;
    }
}
