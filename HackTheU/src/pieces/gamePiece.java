package pieces;

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

    boolean isEnemy(gamePiece[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) { //out of Bounds
            return false;
        } else {
            return (grid[x][y] != null && grid[x][y].getTeam() != getTeam());
            }
    }

    boolean isOpenSpace(gamePiece[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) { //out of Bounds
            return false;
        } else {
            return (grid[x][y] == null);
        }
    }
}