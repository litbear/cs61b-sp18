package byog.lab5;

import byog.Core.Position;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class TesselationDemo {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 40;



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
        HexWorld.addTesselationPisitions(world, root, 3, 3, new Random(9527));


        ter.renderFrame(world);
    }
}
