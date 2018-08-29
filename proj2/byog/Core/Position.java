package byog.Core;

import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Position {
    private int xIndex;
    private int yIndex;

    public Position(int xIndex, int yIndex) {
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }

    /**
     * 在指定坐标范围内，获取指定坐标点的所有相邻元素
     *
     * @param p 指定坐标点
     * @param width 指定宽度范围
     * @param height 指定高度范围
     * @return 相邻坐标数组
     */
    public static Stream<Position> getAllNeighbors(Position p, int width, int height) {
        return IntStream
                .range(-1, 2)
                .mapToObj(
                        x -> IntStream
                                .range(-1, 2)
                                .mapToObj(
                                        y -> new Position(p.getxIndex() + x, p.getyIndex() + y)
                                )
                ).reduce(Stream::concat)
                .orElseGet(Stream::empty)
                .filter(o -> !p.equals(o))
                .filter(e -> e.getxIndex() >= 0 && e.getyIndex() >= 0 && e.getxIndex() < width && e.getyIndex() < height);

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return xIndex == position.xIndex &&
                yIndex == position.yIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xIndex, yIndex);
    }
}
