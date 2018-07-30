import java.util.Arrays;

class NBody{

    private static final String PLANET_FILE = "./data/planets.txt";
    private static final String BACKGROUND_IMAGE = "./images/starfield.jpg";

    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        int length = n;
        Planet[] plantArray = new Planet[length];
        in.readDouble();
        while (n > 0) {
            plantArray[length - n] = new Planet(
                in.readDouble(), 
                in.readDouble(), 
                in.readDouble(), 
                in.readDouble(),
                in.readDouble(),
                in.readString());
            n--;
        }
        return plantArray;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println("Please supply at least 3 arguments.");
            return;
        }
        
        try {
            double T = Double.parseDouble(args[0]);
            double dt = Double.parseDouble(args[1]);
            String filename = args[2];

            double radius = NBody.readRadius(filename);
            Planet[] plantArray = NBody.readPlanets(filename);

            StdDraw.setXscale(-radius, radius);
            StdDraw.setYscale(-radius, radius);

            StdDraw.enableDoubleBuffering();

            double time = 0.0;
            while (time <= T){
                // Create an xForces array and yForces array.
                // double[] xForces, yForces;
                // Calculate the net x and y forces for each planet, storing these in the xForces and yForces arrays respectively.
                // Call update on each of the planets. This will update each planet’s position, velocity, and acceleration.
                Arrays
                    .stream(plantArray)
                    .forEach(e -> 
                        e.update(
                            dt, 
                            e.calcNetForceExertedByX(plantArray), 
                            e.calcNetForceExertedByY(plantArray)
                        ));
                // Draw the background image.
                StdDraw.picture(0, 0, BACKGROUND_IMAGE);
                // Draw all of the planets.
                Arrays
                    .stream(plantArray)
                    .forEach(e -> e.draw());
                // Show the offscreen buffer (see the show method of StdDraw).
                StdDraw.show();
                // Pause the animation for 10 milliseconds (see the pause method of StdDraw). You may need to tweak this on your computer.
                StdDraw.pause(10);
                // Increase your time variable by dt.
                time += dt;
            }

            StdOut.printf("%d\n", plantArray.length);
            StdOut.printf("%.2e\n", radius);
            Arrays
                .stream(plantArray)
                .forEach(e -> 
                    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        e.xxPos, e.yyPos, e.xxVel,
                        e.yyVel, e.mass, e.imgFileName));

        } catch (Exception e) {
            StdOut.println("Oops... Something wrong!");
        }
    }
}