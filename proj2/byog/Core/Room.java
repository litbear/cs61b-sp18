package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.In;

import java.util.Random;
import java.util.stream.IntStream;

public class Room {

    // 房间的左下角坐标
    private Position root;

    // 房间的边长 不包括墙壁
    private int width;
    private int height;

    Room(Position root, int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException(
                            "Width and height of the world must larger than 1.");
        }
        this.root = root;
        this.width = width;
        this.height = height;
    }


    /**
     * 在给定坐标系内，以指定的宽高生成随机位置的Room
     *
     * @param worldWidth 坐标系的宽度
     * @param worldHeight 坐标系的高度
     * @param maxRoomWidth Room的最大宽度
     * @param MaxRoomHeight Room的最大高度
     * @param random 随机数对象
     * @return 生成的Room
     */
    public static Room createRandomRoomInWorld
            (int worldWidth,
             int worldHeight,
             int maxRoomWidth,
             int MaxRoomHeight,
             Random random) {
        Room randRoom;
        do {
            // [a, b)
            int randomX = RandomUtils.uniform(random, 1, worldWidth - 1);
            int randomY = RandomUtils.uniform(random, 1, worldHeight - 1);
            int randomWidth = RandomUtils.uniform(random, 2, maxRoomWidth + 1);
            int randomHeight = RandomUtils.uniform(random, 2, MaxRoomHeight + 1);
            randRoom = new Room(new Position(randomX, randomY), randomWidth, randomHeight);
        } while (!isInTheWorld(worldWidth, worldHeight, randRoom));
        return randRoom;
    }

    public static void drawCorridor(
            TETile[][] world,
            TETile floor,
            Random random,
            Room r1,
            Room r2) {
        Position p1 = r1.getCenter();
        Position p2 = r2.getCenter();
        int randomInt = RandomUtils.uniform(random, 2);
        // ugly...
        int x, y, xMin, xMax, yMin, yMax;
        if (randomInt == 0) {
            // r1 hCorridor
            y = p1.getyIndex();
            xMin = p1.getxIndex() > p2.getxIndex()? p2.getxIndex(): p1.getxIndex();
            xMax = p1.getxIndex() < p2.getxIndex()? p2.getxIndex(): p1.getxIndex();
            IntStream.range(xMin, xMax + 1).forEach(e -> world[e][y] = floor);
            // r2 vCorridor
            x = p2.getxIndex();
            yMin = p1.getyIndex() > p2.getyIndex()? p2.getyIndex(): p1.getyIndex();
            yMax = p1.getyIndex() < p2.getyIndex()? p2.getyIndex(): p1.getyIndex();
            IntStream.range(yMin, yMax + 1).forEach(e -> world[x][e] = floor);
        } else {
            // r1 vCorridor
            x = p1.getxIndex();
            yMin = p1.getyIndex() > p2.getyIndex()? p2.getyIndex(): p1.getyIndex();
            yMax = p1.getyIndex() < p2.getyIndex()? p2.getyIndex(): p1.getyIndex();
            IntStream.range(yMin, yMax + 1).forEach(e -> world[x][e] = floor);
            // r2 hCorridor
            y = p2.getyIndex();
            xMin = p1.getxIndex() > p2.getxIndex()? p2.getxIndex(): p1.getxIndex();
            xMax = p1.getxIndex() < p2.getxIndex()? p2.getxIndex(): p1.getxIndex();
            IntStream.range(xMin, xMax + 1).forEach(e -> world[e][y] = floor);
        }

    }

    /**
     * 判断指定房间是否在给定的坐标系内
     * (必须为房间外围留出1个宽度的围墙)
     *
     * @param worldWidth 坐标系宽度
     * @param worldHeight 坐标系长度
     * @param room 给定的房间
     * @return 判断结果
     */
    static boolean isInTheWorld(int worldWidth, int worldHeight, Room room) {
        if (room == null) {
            throw new IllegalArgumentException("The room is not nullable");
        }
        Position root = room.getRoot();
        Position rightTopPosition = room.getRightTopPosition();

        return root.getxIndex() > 1
                && root.getyIndex() > 1
                && rightTopPosition.getxIndex() < worldWidth - 1
                && rightTopPosition.getyIndex() < worldHeight - 1;
    }


    /**
     * 判断给定的两个房间是否重叠
     *
     * @param r1 给定房间1
     * @param r2 给定房间2
     * @return 判断结果
     */
    public static boolean isRoomOverlap(Room r1, Room r2) {
        Position r1LeftBottom = r1.getLeftBottomPosition();
        Position riRightTop = r1.getRightTopPosition();
        Position r2LeftBottom = r2.getLeftBottomPosition();
        Position r2RightTop = r2.getRightTopPosition();
        if (r1LeftBottom.getxIndex() > r2RightTop.getxIndex()
            || r2LeftBottom.getxIndex() > riRightTop.getxIndex())
        {
            return false;
        }

        if (r1LeftBottom.getyIndex() > r2RightTop.getyIndex()
            || r2LeftBottom.getyIndex() > riRightTop.getyIndex()) {
            return false;
        }
        return true;
    }

    // -----------------------------------------------

    public void drawRoom(TETile[][] world, TETile floor) {
        IntStream
                .range(root.getxIndex(), root.getxIndex() + width)
                .forEach(x -> IntStream
                            .range(root.getyIndex(), root.getyIndex() + height)
                            .forEach(y -> world[x][y] = floor)
                );
    }

    /**
     * 获取给定房间左下角坐标
     *
     * @return 左下角坐标
     */
    private Position getLeftBottomPosition() {
        return root;
    }

    /**
     * 获取给定房间右上角坐标
     *
     * @return 右上角坐标
     */
    private Position getRightTopPosition(){
        return new Position(
                root.getxIndex() + width - 1,
                root.getyIndex() + height - 1);
    }


    public Position getRoot() {
        return getLeftBottomPosition();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Position getCenter() {
        return new Position(root.getxIndex() + width / 2, root.getyIndex() + height / 2);
    }

    @Override
    public String toString() {
        return "Room{" +
                "root=" + root +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
