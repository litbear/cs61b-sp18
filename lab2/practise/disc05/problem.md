### 输如文章，输出每个单词的频率

```java

List wordList = ReadFileToString('./filename');
Map wordCount = new Map<String, Integer>();

for(String word: wordList) {
    if(wordCount.containsKey(word)) {
        int currentCount = wordCount.get(word);
        wordCount.put(word, currentCount);
    }
    wordCount.put(word, 0);
}

```

### 输入无序数组，输出从小到大的数组

答案给出的是priority queue, 类似睡排序... 将优先级设置成与其值相同。然后再反向输出...可是怎么插进去也是个问题啊

实现浏览器的前进和后退按钮
两个 stack

### 实现一个双向映射的map，且提供小于指定key的所有子集

不做了，答案比较蛋疼

### 仅用 Stack 的接口实现一个 Queue

- 答案1 吃了吐，用一个临时的stack去不断接内部stack吐出来的元素，取到最后一个然后吐出来灌到内部stack内
- 答案2 用递归的方式遍历内部stack，取到最后一个然后pop出来