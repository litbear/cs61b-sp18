package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.HexWorld;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

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

        TETile[][] finalWorldFrame = getTeTilesWithNothing();
        int maxRooms = 25;
        int maxRoomWidth = 6;
        int maxRoomHeight = 8;
        Random random = new Random(2134);
        ArrayList<Room> rooms = new ArrayList<>(maxRooms);
        while (rooms.size() != maxRooms) {
            Room newRoom = Room.createRandomRoomInWorld(WIDTH, HEIGHT, maxRoomWidth, maxRoomHeight, random);
            if (rooms.stream().noneMatch(e -> Room.isRoomOverlap(e, newRoom))) {
                rooms.add(newRoom);
            }
        }
        rooms.forEach(e -> e.drawRoom(finalWorldFrame, Tileset.WALL, Tileset.FLOOR));
        return finalWorldFrame;
    }

    private static TETile[][] getTeTilesWithNothing() {
        return IntStream.range(0, WIDTH)
                .mapToObj(w ->
                        IntStream
                                .range(0, HEIGHT)
                                .mapToObj(h -> Tileset.NOTHING)
                                .toArray(TETile[]::new))
                .toArray(TETile[][]::new);
    }
}
