package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = setAllTeTilesWith(Tileset.WALL);
        int maxRooms = 15;
        int maxRoomWidth = 6;
        int maxRoomHeight = 8;
        Random random = new Random(getSeedFromString(input));

        ArrayList<Room> rooms = getRandomRooms(finalWorldFrame, maxRooms, maxRoomWidth, maxRoomHeight, random);
        connectEveryRooms(finalWorldFrame, random, rooms);
        eraseNeedlessWall(finalWorldFrame, Tileset.WALL, Tileset.NOTHING);
        return finalWorldFrame;
    }

    long getSeedFromString(String input) {
        int start = input.toLowerCase().indexOf('n');
        int end = input.toLowerCase().indexOf('s');
        if (start == -1 || end == -1) {
            throw new IllegalArgumentException("please input a seed for Random.");
        }
        return Long.valueOf(input.substring(start + 1, end));
    }

    /**
     * 将坐标系中所有四周全是wall的坐标点替换为nothing
     *
     * @param world 待操作坐标系
     * @param needless 待替换的tile
     * @param newTile 新的tile
     */
    private void eraseNeedlessWall(TETile[][] world, TETile needless, TETile newTile) {
        // you can't merge two stream below together
        Position[] positions = IntStream.range(0, WIDTH).mapToObj(x -> IntStream.range(0, HEIGHT).mapToObj(y -> new Position(x, y)))
                .reduce(Stream::concat).orElseGet(Stream::empty) // all positions in world
                .filter(
                        e -> Position
                                .getAllNeighbors(e, WIDTH, HEIGHT)
                                .allMatch(p -> world[p.getxIndex()][p.getyIndex()] == needless))
                .toArray(Position[]::new);
        Stream.of(positions).forEach(p -> world[p.getxIndex()][p.getyIndex()] = newTile);

    }

    /**
     * 联通每个相连房间
     *
     * @param finalWorldFrame 待操作坐标系
     * @param random 随机对象
     * @param rooms 房间列表
     */
    private static void connectEveryRooms(TETile[][] finalWorldFrame, Random random, ArrayList<Room> rooms) {
        IntStream
                .range(0, rooms.size() - 1)
                .forEach(e -> Room.drawCorridor(
                        finalWorldFrame,
                        Tileset.FLOOR,
                        random,
                        rooms.get(e),
                        rooms.get(e + 1)));
    }

    /**
     * 在坐标系内生成指定个数，指定最大宽度，指定最大高度的随机位置房间
     *
     * @param finalWorldFrame 待操作坐标系
     * @param maxRooms 待生成房间数
     * @param maxRoomWidth 房间最大宽度
     * @param maxRoomHeight 房间最大高度
     * @param random 随机对象
     * @return 房间数组
     */
    private static ArrayList<Room> getRandomRooms(TETile[][] finalWorldFrame, int maxRooms, int maxRoomWidth, int maxRoomHeight, Random random) {
        ArrayList<Room> rooms = new ArrayList<>(maxRooms);
        while (rooms.size() != maxRooms) {
            Room newRoom = Room.createRandomRoomInWorld(WIDTH, HEIGHT, maxRoomWidth, maxRoomHeight, random);
            if (rooms.stream().noneMatch(e -> Room.isRoomOverlap(e, newRoom))) {
                rooms.add(newRoom);
            }
        }
        rooms.forEach(e -> e.drawRoom(finalWorldFrame, Tileset.FLOOR));
        return rooms;
    }

    /**
     * 将待操作坐标系填充为指定的tile
     *
     * @param tile 待填充tile
     * @return 填充后的坐标系
     */
    private static TETile[][] setAllTeTilesWith(TETile tile) {
        return IntStream.range(0, WIDTH)
                .mapToObj(w ->
                        IntStream
                                .range(0, HEIGHT)
                                .mapToObj(h -> tile)
                                .toArray(TETile[]::new))
                .toArray(TETile[][]::new);
    }
}
