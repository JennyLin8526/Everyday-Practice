
@PathVariable

- 擷取API路經中的資料，做為傳入後端的參數
- 用於從 URI 路徑中擷取資料，它會將 URL 範本路徑變數與方法變數綁定
  - ex. http://localhost:8080/users/123 -> 123 為 PathVariable 會被截取出，userId = 123

```
@GetMapping("/users/{userId}")
public User get(@PathVariable("userId") Long userId) {}
```
-----------------------
@RequestParam

- 使用 @RequestParam 從請求裡面提取查詢參數、表單參數、檔案
- ex. http://localhost:8080/springmvc/hello/101?param1=10&param2=20

```
@GetMapping("/users/{userId}")
public User get(
@PathVariable(value = "userId") Long userId,
@RequestParam(value = "param1", required = false, defaultValue = "0") Integer param1,
@RequestParam(value = "param2", required = false, defaultValue = "0") Integer param2
) {}
```

-----------------------
@RequestBody

- "Request"
- 前端的資料會以 JSON 格式傳到後端
- @RequestBody 最常用在 POST/PUT/PATCH
- Spring 會用 HttpMessageConverter（常見是 Jackson）把 JSON 反序列化成 Java 物件

````
@RestController
public class MyController{
  
  @PostMapping("/author")
  public void printAuthor(@RequestBody Author author) { ... }
}
````
-----------------------
@ResponseBody

- "Response"
- 此註解用於將網域物件轉換為 HTTP 回應，形式為 JSON 或其他文字。此處，方法的回傳類型與 HTTP 回應主體綁定
````
@Controller
public class MyController{
  
  public @ResponseBody Author getAuthor(){
    Author author = new Author();
    author.setName("GFG");
    author.setAge(20);
    return author;
  }
}
````

-----------------------
@RequestHeader

- 接收 Http 表投中的參數
  - ex. User-Agent、Accept-Language、Token、Cookie....
- Reference:
  - https://docs.spring.io/spring-framework/reference/web/webflux/controller/ann-methods/requestheader.html
```
@RestController
@GetMapping("/header")
public String getHeader(
    @RequestHeader("User-Agent") String userAgent,
    @RequestHeader("Accept-Language") String acceptLanguage){
          return "User-Agent: " + userAgent + ", Accept-Language: " + acceptLanguage;
}
```
-----------------------
@CookieValue

- 從 Cookie 中提取值並將其綁定到方法參數
- Reference:
  - https://docs.spring.io/spring-framework/reference/web/webflux/controller/ann-methods/cookievalue.htm

```
JSESSIONID=415A4AC178C59DACE0B2C9CA727CDD84
```
```
@GetMapping("/demo")
public void handle(@CookieValue("JSESSIONID") String cookie) { 
}
```