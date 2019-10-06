import java.util.*;

class Knight extends gamePiece {

    Knight(String name, int posX, int posY, boolean team) {
        super(name, posX, posY, team);
    }

    @Override
    public boolean getTeam() {
        return super.getTeam();
    }

    @Override
    public int getNumberOfMoves() {
        return super.getNumberOfMoves();
    }

    @Override
    public int getPosY() {
        return super.getPosY();
    }

    @Override
    public int getPosX() {
        return super.getPosX();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void incrementNumberOfMoves() {
        super.incrementNumberOfMoves();
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
     * return all possible moves of a knight not considering being out of the board or other players
     * @param grid current game state
     * @return list of possible coordinates
     */
    public ArrayList<Coordinates> moves(gamePiece[][] grid) {
        ArrayList<Coordinates> possibleMoves = new ArrayList<>();

        for (int i = -1; i < 2; i+=2) {
            possibleMoves.add(new Coordinates(getPosX() + (i * 2),getPosY() + 1)); //upper wide
            possibleMoves.add(new Coordinates(getPosX() - (i * 2),getPosY() + -1)); //lower wide
            possibleMoves.add(new Coordinates(getPosX() - 1,getPosY() + (i*2))); //left tall
            possibleMoves.add(new Coordinates(getPosX() + 1,getPosY() - (i*2))); //right tall
        }

        return super.whatsInTheWay(grid, possibleMoves);
    }
}