import java.util.ArrayList;

public abstract class  gamePiece {
    private int posX;
    private int posY;
    private String name;
    private boolean team;
    private int numberOfMoves;

    public gamePiece(String name, int posX, int posY, boolean team) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.team = team;
        this.numberOfMoves = 0;
    }

    public abstract ArrayList<Coordinates> moves(gamePiece[][] grid);

    public String getName() {
        return name;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    public boolean getTeam() {
        return team;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void incrementNumberOfMoves() {
        this.numberOfMoves++;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    ArrayList<Coordinates> whatsInTheWay(gamePiece[][] grid, ArrayList<Coordinates> moves) {

        // removes moves outside of board
        int i = 0;
        while (i < moves.size()){
            if (moves.get(i).x > 7 || moves.get(i).x < 0 || moves.get(i).y > 7 || moves.get(i).y < 0) { //outside board
                moves.remove(i);
            } else i++; // moves increment forward if nothing was removed
        }

         //check to see if something is in the way
         int j = 0;
        while (j < moves.size()) {
            if (grid[moves.get(j).x][moves.get(j).y] != null) {
                if (getTeam() == team)
                moves.remove(j);
                j++;
            }

        }
        return moves;
    }

    boolean isEnemy(gamePiece[][] grid, gamePiece piece) {
        if (piece.getTeam() == getTeam()) return false;
        else return true;
    }
}