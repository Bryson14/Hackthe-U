import java.util.*;

class King extends gamePiece {

    King(String name, int posX, int posY, boolean team) {
        super(name, posX, posY, team);
    }

    @Override
    public boolean getTeam() {
        return super.getTeam();
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
    public void incrementNumberOfMoves() {
        super.incrementNumberOfMoves();
    }

    @Override
    public int getNumberOfMoves() {
        return super.getNumberOfMoves();
    }

    @Override
    public void setPosY(int posY) {
        super.setPosY(posY);
    }

    @Override
    public void setPosX(int posX) {
        super.setPosX(posX);
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * return all possible moves of a king not considering being out of the board or other players
     * @param grid current game state
     * @return list of possible coordinates
     */
    public ArrayList<Coordinates> moves(gamePiece[][] grid) {ArrayList<Coordinates> possibleMoves = new ArrayList<>();

        if(isEnemy(grid, getPosX() + 1, getPosY()) || isOpenSpace(grid, getPosX() + 1, getPosY())) {
            possibleMoves.add(new Coordinates(getPosX() + 1, getPosY()));
        }
        if(isEnemy(grid, getPosX() - 1, getPosY()) || isOpenSpace(grid, getPosX() - 1, getPosY())) {
            possibleMoves.add(new Coordinates(getPosX() - 1, getPosY()));
        }
        if(isEnemy(grid, getPosX(), getPosY() + 1) || isOpenSpace(grid, getPosX(), getPosY() + 1)) {
            possibleMoves.add(new Coordinates(getPosX(), getPosY() + 1));
        }
        if(isEnemy(grid, getPosX(), getPosY() - 1) || isOpenSpace(grid, getPosX(), getPosY() - 1)) {
            possibleMoves.add(new Coordinates(getPosX(), getPosY() - 1));
        }
        if(isEnemy(grid, getPosX() + 1, getPosY() + 1) || isOpenSpace(grid, getPosX() + 1, getPosY() + 1)) {
            possibleMoves.add(new Coordinates(getPosX() + 1, getPosY() + 1));
        }
        if(isEnemy(grid, getPosX() - 1, getPosY() - 1) || isOpenSpace(grid, getPosX() - 1, getPosY() - 1)) {
            possibleMoves.add(new Coordinates(getPosX() - 1, getPosY() - 1));
        }
        if(isEnemy(grid, getPosX() + 1, getPosY() - 1) || isOpenSpace(grid, getPosX() + 1, getPosY() - 1)) {
            possibleMoves.add(new Coordinates(getPosX() + 1, getPosY() - 1));
        }
        if(isEnemy(grid, getPosX() - 1, getPosY() + 1) || isOpenSpace(grid, getPosX() - 1, getPosY() + 1)){
            possibleMoves.add(new Coordinates(getPosX() - 1, getPosY() + 1));
        }

        return possibleMoves;
    }
}