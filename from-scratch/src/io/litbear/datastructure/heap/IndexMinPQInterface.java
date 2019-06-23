package io.litbear.datastructure.heap;

import java.util.NoSuchElementException;

public interface IndexMinPQInterface<Key extends Comparable<Key>> {

    /**
     * 判断当前队列是否为空
     * @return 判断结果
     */
    boolean isEmpty();

    /**
     * 判断是否包含指定关联索引
     * @param i 关联索引
     * @return 判断结果
     */
    boolean contains(int i);

    /**
     * 返回当前关联对象数量
     * @return 当前关联对象数量
     */
    int size();


    /**
     * 根据给定关联索引插入对象
     * @param i 关联索引
     * @param key 对象
     */
    void insert(int i, Key key);

    /**
     * 返回最小对象的关联索引
     * @return 最小对象的关联索引
     */
    int minIndex();


    /**
     * 返回最小的对象
     * @return 最小对象
     */
    Key minKey();


    /**
     * 删除最小的对象并返回
     * @return 被删除对象
     */
    Key delMin();


    /**
     * 根据指定关联索引取出相应对象
     * @param i 关联索引
     * @return
     */
    Key keyOf(int i);

    /**
     * 更改指定关联索引的对象为新的对象
     * @param i 关联索引
     * @param key 新的对象
     */
    void changeKey(int i, Key key);

    // 与update没有明显差异，因此不做实现
//    void decreaseKey(int i, Key key);
//    void increaseKey(int i, Key key);

    /**
     * 根据指定关联索引移除相应对象
     * @param i 关联索引
     */
    void delete(int i);

}
