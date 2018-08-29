package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.In;

import java.util.Random;
import java.util.stream.IntStream;

public class Room {
    private static final int WALL_WIDTH = 1;
    private static final int MIN_SIDE_LENGTH = WALL_WIDTH * 2 + 1;

    // 房间的左下角坐标
    private Position root;

    // 房间的边长 包括墙壁
    private int width;
    private int height;

    Room(Position root, int width, int height) {
        if (width < MIN_SIDE_LENGTH || height < MIN_SIDE_LENGTH) {
            throw new IllegalArgumentException(
                    String.format(
                            "Width and height of the world must larger than %s.",
                            MIN_SIDE_LENGTH));
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
            int randomX = RandomUtils.uniform(random, worldWidth);
            int randomY = RandomUtils.uniform(random, worldHeight);
            // ugly, don't ask me why
            int randomWidth = RandomUtils.uniform(random, MIN_SIDE_LENGTH + 1, maxRoomWidth + 1);
            int randomHeight = RandomUtils.uniform(random, MIN_SIDE_LENGTH  + 1, MaxRoomHeight + 1);
            randRoom = new Room(new Position(randomX, randomY), randomWidth, randomHeight);
        } while (!isInTheWorld(worldWidth, worldHeight, randRoom));
        return randRoom;
    }

    /**
     * 判断指定房间是否在给定的坐标系内
     *
     * @param worldWidth 坐标系宽度
     * @param worldHeight 坐标系长度
     * @param room 给定的房间
     * @return 判断结果
     */
    private static boolean isInTheWorld(int worldWidth, int worldHeight, Room room) {
        if (room == null) {
            throw new IllegalArgumentException("The room is not nullable");
        }

        if (worldWidth < MIN_SIDE_LENGTH || worldHeight < MIN_SIDE_LENGTH) {
            throw new IllegalArgumentException(String.format("Width and height of the world must larger than %s.", MIN_SIDE_LENGTH));
        }

        return (room.getRightTopPosition().getxIndex() < (worldWidth - 1))
                && (room.getRightTopPosition().getyIndex() < (worldHeight - 1));
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

    public void drawRoom(TETile[][] world, TETile wall, TETile floor) {
        IntStream
                .range(root.getxIndex(), root.getxIndex() + width)
                .forEach(x -> IntStream
                            .range(root.getyIndex(), root.getyIndex() + height)
                            .forEach(y -> {
                                if (isInnerBlock(x, y))
                                {
                                    world[x][y] = floor;
                                } else {
                                    world[x][y] = wall;
                                }
                            })
                );
    }

    private boolean isInnerBlock(int x, int y) {
        return x >= root.getxIndex() + WALL_WIDTH
                && x < root.getxIndex() + width - WALL_WIDTH
                && y >= root.getyIndex() + WALL_WIDTH
                && y < root.getyIndex() + height - WALL_WIDTH;
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

    public Position getRandomInnerPosition(Random random){
        int randomX = RandomUtils.uniform(
                random,
                root.getxIndex() + WALL_WIDTH,
                root.getxIndex() + width - WALL_WIDTH);
        int randomY = RandomUtils.uniform(
                random,
                root.getyIndex() + WALL_WIDTH,
                root.getyIndex() + height - WALL_WIDTH);
        return  new Position(randomX, randomY);
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

    @Override
    public String toString() {
        return "Room{" +
                "root=" + root +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
