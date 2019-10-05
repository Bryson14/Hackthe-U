public abstract class  gamePiece {
    private int posX;
    private int posY;
    private String name;
    private boolean team;

    public gamePiece(String name, int posX, int posY, boolean team) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.team = team;
    }
}
