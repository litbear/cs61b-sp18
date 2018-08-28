package byog.lab5;
import byog.Core.Position;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Draws a world consisting of hexagonal regions.
 *
 * @Todo  Stream zip/combination
 * https://www.baeldung.com/java-collections-zip
 *
 */
public class HexWorld {

    /**
     * 在坐标系上的指定位置绘制指定边长的六边形并填充
     *
     * @param world 待填充的坐标系
     * @param p 六边形左下角的坐标
     * @param side 六边形变长
     * @param t 待绘图的方块
     */
    public static void addHexagon(TETile[][] world, Position p, int side, TETile t) {
        if (side < 2) {
            throw new IllegalArgumentException("the side length must be larger than 1");
        }
        if (world.length < 1 || world[0].length < 1) {
            throw new IllegalArgumentException("you ruined the world!");
        }

        int[] xOffsets = IntStream
                .range(0, side << 1)
                .map(e -> e < side? e: (2 * side - 1 - e))
                .map(e -> -e)
                .toArray();
        int[] yOffsets = IntStream
                .range(0, side << 1)
                .toArray();
        int[] rowLengths = IntStream
                .range(0, (side << 1))
                .map(e -> side + 2 * (xOffsets[e] < 0? -xOffsets[e]: xOffsets[e]))
                .toArray();
        Position[] rowHeads = IntStream
                .range(0, side << 1)
                .mapToObj(e -> getNewPositionByOffset(p, xOffsets[e], yOffsets[e]))
                .toArray(Position[]::new);
        IntStream
                .range(0, side << 1)
                .forEach(e -> drawRow(world, rowHeads[e], rowLengths[e], t));
    }

    /**
     * 根据给定的坐标和偏移量得出新的坐标
     *
     * @param p  基坐标
     * @param xOffset x轴偏移量
     * @param yOffset y轴偏移量
     * @return 新的坐标点
     */
    private static Position getNewPositionByOffset(Position p, int xOffset, int yOffset){
        return new Position(p.getxIndex() + xOffset, p.getyIndex() + yOffset);
    }

    /**
     * 根据起始点坐标和长度在指定坐标系上以指定方块画线
     *
     * @param world 待填充的坐标系
     * @param startPosition 起始点坐标
     * @param length 该行的长度
     * @param t 待绘图的方块
     */
    private static void drawRow(TETile[][] world, Position startPosition, int length, TETile t) {
        IntStream
                .range(0, length)
                .mapToObj(e -> new Position(startPosition.getxIndex() + e, startPosition.getyIndex()))
                .filter(e -> isInWorld(world, e))
                .forEach(e -> world[e.getxIndex()][e.getyIndex()] = t);
    }

    private static boolean isInWorld(TETile[][] world, Position position) {
        int length = world.length;
        int height = world[0].length;
        return position.getxIndex() >= 0
                && position.getxIndex() < length
                && position.getyIndex() >= 0
                && position.getyIndex() < height;
    }


    //----------------------------------------------------


    /**
     * 根据六边形左下顶点坐标及边长计算其右上方相邻及延长线上的六边形坐标
     *
     * @param p 给定左下顶点
     * @param side 边长
     * @param n    移动的次数
     * @return 右上方相邻六边形左下顶点坐标
     */
    private static Position topRightNeighbor(Position p, int side, int n) {
        return new Position(p.getxIndex() + (2 * side - 1) * n, p.getyIndex() + n * side);
    }

    /**
     * 根据六边形左下顶点坐标及边长计算其左上方相邻及延长线上的六边形坐标
     *
     * @param p 给定左下顶点
     * @param side 边长
     * @param n    移动的次数
     * @return 左上方相邻六边形左下顶点坐标
     */
    private static Position topLeftNeighbor(Position p, int side, int n) {
        return new Position(p.getxIndex() - (2 * side - 1) * n, p.getyIndex() + n * side);
    }


    /**
     * 根据六边形左下顶点坐标及边长计算其上方相邻及延长线上的六边形坐标
     *
     * @param p 给定左下顶点
     * @param side 边长
     * @param n    移动的次数
     * @return 上方相邻六边形左下顶点坐标
     */
    private static Position topNeighbor(Position p, int side, int n) {
        return new Position(p.getxIndex(), p.getyIndex() + 2 * n * side);
    }

    /**
     * 生成密铺六边形的左下角顶点数组
     *
     * @param root 密铺起点
     * @param side 小六边形边长
     * @param n 大六边形边长
     */
    public static void addTesselationPisitions(TETile[][] world, Position root, int side, int n, Random random) {
        if (side < 2) {
            throw new IllegalArgumentException("the side length must be larger than 1");
        }
        if (n < 2) {
            throw new IllegalArgumentException("hexagon must repeat more than once!");
        }
        // 3 4 5 4 3
        int[] columnLengths = IntStream
                .range(1 - n, n)
                .map(e -> (e < 0) ? (((2 * n) - 1) + e) : ((2 * n) - 1 - e))
                .toArray();

        Position[] positions = IntStream
                .range(1 - n, n)
                .mapToObj(e -> e < 0 ? topLeftNeighbor(root, side, -e) : topRightNeighbor(root, side, e))
                .toArray(Position[]::new);

        IntStream
                .range(0, positions.length)
                .mapToObj(e -> getPositionsToTop(positions[e], columnLengths[e], side))
                .forEach(e -> e.forEach(p -> addHexagon(world, p, side, randomTile(random))));
    }

    /**
     * 获取随机方块
     *
     * @param r 随机数对象
     * @return 随机方块
     */
    private static TETile randomTile(Random r) {
        int tileNum = r.nextInt(10);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 4: return Tileset.FLOOR;
            case 5: return Tileset.WATER;
            case 6: return Tileset.MOUNTAIN;
            case 7: return Tileset.TREE;
            case 8: return Tileset.PLAYER;
            case 9: return Tileset.UNLOCKED_DOOR;
            default: return Tileset.GRASS;
        }
    }

    /**
     * 根据给定的起点坐标和列的长度列出该列所有六边形的左下顶点
     *
     * @param bottom 起点坐标
     * @param columnLength 列的长度
     * @param side  小六边形边长
     * @return 该列所有六边形的左下顶点
     */
    private static Stream<Position> getPositionsToTop(Position bottom, int columnLength, int side) {
        return IntStream
                .range(0, columnLength)
                .mapToObj(e -> topNeighbor(bottom, side, e));
    }
}
