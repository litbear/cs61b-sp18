package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Draws a world consisting of hexagonal regions.
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


        Position[] startPositions = getStartPositionArray(p, side);
        int[] rowLength = getRowLengthArray(side);
        IntStream
                .range(0, startPositions.length)
                .forEach(e -> drawRow(world, startPositions[e], rowLength[e], t));
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

    /**
     * 根据边长获取每一行的长度
     *
     * @param side 边长
     * @return 每一行的长度组成的列表
     */
    static int[] getRowLengthArray(int side) {
        return IntStream
                .range(0, (side << 1))
                .map(e -> getRowLength(e, side))
                .toArray();
    }

    /**
     * 根据行号与边长计算每一行的长度
     *
     * @param i 行号
     * @param side 边长
     * @return 该行的长度
     */
    private static int getRowLength(int i, int side) {
        return getXOffset(i, side) * 2 + side;
    }


    /**
     * 根据左下角坐标和边长生成起始点的坐标
     *
     * @param p 起始点坐标
     * @param side 六边形边长
     * @return  起始点坐标数组
     */
    static Position[] getStartPositionArray(Position p, int side) {
        return IntStream
                .range(0, side << 1)
                .mapToObj(e -> getPositionByOffset(p, getXOffset(e, side), e))
                .toArray(Position[]::new);
    }

    /**
     * 根据六边形的行数计算相应行起始位置的坐标x轴偏移量
     *
     * @param i 行数
     * @param side 边长
     * @return x轴偏移量
     */
    private static int getXOffset(int i, int side){
        return i < side? i: (2 * side - 1 - i);
    }

    /**
     * 根据偏移量产生新Position
     *
     * @param p 原始坐标
     * @param xOffset y轴偏移量
     * @param yOffset x轴偏移量
     * @return 偏移后的坐标
     */
    private static Position getPositionByOffset(Position p, int xOffset, int yOffset) {
        return new Position(p.getxIndex() - xOffset, p.getyIndex() + yOffset);
    }

    /**
     * 根据六边形左下顶点坐标及边长计算其右上方相邻六边形坐标
     *
     * @param p 给定左下顶点
     * @param side 边长
     * @return 右上方相邻六边形左下顶点坐标
     */
    public static Position topRightNeighbor(Position p, int side) {
        return new Position(p.getxIndex() + 2 * side, p.getyIndex() + side);
    }

    /**
     * 根据六边形左下顶点坐标及边长计算其左上方相邻六边形坐标
     *
     * @param p 给定左下顶点
     * @param side 边长
     * @return 左上方相邻六边形左下顶点坐标
     */
    public static Position topLeftNeighbor(Position p, int side) {
        return new Position(p.getxIndex() - 2 * side, p.getyIndex() + side);
    }


    /**
     * 根据六边形左下顶点坐标及边长计算其上方相邻六边形坐标
     *
     * @param p 给定左下顶点
     * @param side 边长
     * @return 上方相邻六边形左下顶点坐标
     */
    public static Position topNeighbor(Position p, int side) {
        return new Position(p.getxIndex(), p.getyIndex() + 2 * side);
    }
}
