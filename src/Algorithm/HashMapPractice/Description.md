Description of HashMap in Java

https://www.geeksforgeeks.org/java/java-util-hashmap-in-java-with-examples/
https://blog.csdn.net/weixin_73077810/article/details/130697101

- A HashMap is a part of Java’s Collection Framework and implements the **Map interface**.
- It stores elements in key-value pairs, where, Keys are unique. and Values can be duplicated.
  - Key : unique (If there are duplicate value, the last assignment will replace the last value.)
  - Value : can be duplicated
- Query, Insertion, and removal with an average of O(1) time.
  - Time complexity O(1)
  - Space complexity O(N) : The amount of memory used is directly proportional to the number of elements (N) you store.
- HashMap is not thread-safe, to make it synchronized use Collections.synchronizedMap().
- Insertion order is not preserved.To preserve the insertion order,LinkedHashMap can be used.