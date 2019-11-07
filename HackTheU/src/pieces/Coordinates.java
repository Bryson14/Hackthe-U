package pieces;

public class Coordinates{

    public int x;
    public int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
    return "(" + this.x + "," + this.y + ")";
}

    @Override
    public boolean equals(Object obj) {
        try {
            Coordinates o = (Coordinates)obj;
            if (this.x == o.x && this.y == o.y) return true;
            else return false;
        } catch (Exception e ) {
            System.out.println("Unable to cast object to coordinates");
            System.out.println(e);
            System.exit(-1);
        }
        return false;
    }
}
