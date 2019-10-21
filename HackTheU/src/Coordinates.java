public class Coordinates implements Comparable<Coordinates>{

    int x;
    int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
    return "(" + this.x + "," + this.y + ")";
}

    @Override
    public int compareTo(Coordinates o) {
        if (this.x == o.x && this.y == o.y) return 0;
        else return -1;
    }
}
