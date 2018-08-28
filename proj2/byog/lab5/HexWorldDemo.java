package byog.lab5;

import byog.Core.Position;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


public class HexWorldDemo {
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

        Position position = new Position(23, 20);
        int length = 6;
        HexWorld.addHexagon(world, position, length, Tileset.SAND);

        ter.renderFrame(world);
    }
}
