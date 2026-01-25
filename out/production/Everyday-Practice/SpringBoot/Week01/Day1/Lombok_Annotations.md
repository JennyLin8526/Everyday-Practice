@ToString

**印出所有非Static的欄位

-----------------------

@AllArgsConstructor

**@AllArgsConstructor一  定要記得搭配使用 @NoArgsConstructor才不會發生不預期的錯誤

@NoArgsConstructor

**生成一個無參數的建構函數

@RequiredArgsConstructor

**如果變數有被final修飾，生成一個包含所有被final修飾的建構函數

-----------------------
@Data

**內含5種annotations
- @Getter/@Setter
- @ToString
- @EqualsAndHashCode
- @RequiredArgsConstructor

-----------------------
@Value

**內含4種annotations
- @Getter(沒有Setter) 
- @ToString
- @EqualsAndHashCode
- @RequiredArgsConstructor

-----------------------
@Builder

**用了Builder就不用再用一堆Setter了

-但是一定要實作Setter方法

ex.
```
User user = User.builder().age(18).name("Lin").build();
```
```
@builder
public class User{
    private Integer age;
    private String name;

    public void setAge(Integer age){
        this.age = age;
    }
    
    public void setName(String name){
        this.name = name;
    }
}
```
-----------------------
@EqualsAndHashCode

**Lombok會自動幫你生成
- public boolean equals(Object o)
- public int hashCode()


````
@EqualsAndHashCode
public class User {
  private Long id;
  private String name;
}
````
````
· Entity 常見寫法是只用 id，@EqualsAndHashCode 預設會把所有欄位都拿去算

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEntity {
  @EqualsAndHashCode.Include
  private Long id;
}
````

-----------------------
**Note**

- @Data適合用在POJO或是DTO上，@Value適合放在值不希望被改變的類別(因為沒有Setter)
- @Data和@Builer會一起使用
- 