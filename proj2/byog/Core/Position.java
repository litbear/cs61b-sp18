package byog.Core;

public class Position {
    private int xIndex;
    private int yIndex;

    public Position(int xIndex, int yIndex) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    public Position() {
    }

    public int getxIndex() {
        return xIndex;
    }

    public int getyIndex() {
        return yIndex;
    }

    @Override
    public String toString() {
        return "Position{" + xIndex + ", " + yIndex + '}';
    }
}
