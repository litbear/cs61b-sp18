package io.litbear.datastructure.digraph;

public interface SP {
    /**
     * 从起点s到顶点v的最短路径，
     * 如顶点v不可达，则返回正无穷
     *
     * @param v 顶点
     * @return 最短路径
     */
    double distTo(int v);

    /**
     * 判断指定顶点v是否从起点s可达
     *
     * @param v 顶点
     * @return 是否可达
     */
    boolean hasPathTo(int v);

    /**
     * 获取从起点s到顶点v的路径
     * 如顶点v不可达，则返回null
     *
     * @param v 顶点
     * @return 路径
     */
    Iterable<DirectedEdge> pathTo(int v);
}
