equals()

**判斷兩個物件的"內容/值"是否相等

````
@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return Objects.equals(name, player.name);
}
````

-----------------------
hashCode()

**給物件一個整數代碼，用來快速分桶查找

- 同一個物件狀態（用來計算 hashCode 的欄位值）不變時，hashCode() 應該回傳同樣結果

```
@Override
public int hashCode() { 
    return Objects.hash(name);
}
```
主要用在：
- HashMap 
- HashSet

**備註**
<br>
- 如果 equals() 相等，hashCode() 必須相等
  <br>
    也就是 a.equals(b) == true → a.hashCode() == b.hashCode() 必須成立
  <br>
    (只要 equals() 被 override，就必須同時 override hashCode())
- hashCode() 相等 不保證 equals() 相等（可能 hash collision）
-----------------------