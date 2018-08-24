package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.stream.IntStream;

public class TesselationDemo {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;



    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Position root = new Position(23, 0);
        int length = 3;
//        HexWorld.addHexagon(world, root, length, Tileset.SAND);

        // 3 4 5 4 3
        IntStream
                .range(0, 5)
                .map(e -> e < 2? e + 3: 4 - e + 3)
                .forEach(System.out::println);

        // -2 -1 0 1 2
        IntStream
                .range(0, 5)
                .map(e -> e - 2)
                .forEach(System.out::println);


        ter.renderFrame(world);
    }
}
