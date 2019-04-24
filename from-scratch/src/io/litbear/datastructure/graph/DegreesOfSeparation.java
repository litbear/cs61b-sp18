package io.litbear.datastructure.graph;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * N度人脉
 */
public class DegreesOfSeparation {
    public static void main(String[] args) {
        String filename = args[0];
        String delimiter = args[1];
        String source = args[2];

        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph G = sg.graph();
        int sourceIndex = sg.indexOf(source);
        BreadthFirstPaths bfp = new BreadthFirstPaths(G, sourceIndex);

        while (!StdIn.isEmpty()) {
            String target = StdIn.readLine();
            if (sg.contains(target)) {
                int targetIndex = sg.indexOf(target);
                if (bfp.hasPathTo(targetIndex)) {
                    for (int v : bfp.pathTo(targetIndex)) {
                        StdOut.println("   " + sg.nameOf(v));
                    }
                } else {
                    StdOut.println("Not connected");
                }
            } else {
                StdOut.println("   Not in database.");
            }
        }
    }
}
