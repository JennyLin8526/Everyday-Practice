@Component

**@Component 是通用的 Spring Bean 註解
**類別級的註解，表示這個類別為組件，使用@Component將Bean標記為Spring的託管組件
**@Controller / @Service / @Repository 本質上都是「@Component 的衍生註解」

-----------------------
@Controller / @RestController

**控制層，它用於將類標記為 Web 請求處理程序，前端傳進後端的資料在這裡接收
<br>
**Spring 調度程序掃描此類帶註釋的類以查找映射方法並檢測 @RequestMapping 註釋

```
@RequestMapping
- GetMapping - 查詢 / 取得資料 
- PostMapping - 新增資料 / 建立資源
- DeleteMapping - 刪除資料
- PutMapping - 整筆更新（全量更新）
- PatchMapping - 局部更新（部分欄位更新）
*備註 - @GetMapping = @RequestMapping(method = RequestMethod.GET)
```
**@RestController = @Controller + @ResponseBody = 回傳值會直接變成 Response Body (由 HttpMessageConverter處理)
```
@Controller
public class ApiController {

    @GetMapping("/api/user")
    @ResponseBody
    public User getUser() {
        return new User("Jenny");
    }
}
```
```
@RestController
public class UserController {

    @GetMapping("/api/user")
    public User getUser() {
        return new User("Jenny"); // 自動轉 JSON
    }
}
```
-----------------------
@Service

**業務邏輯實作層，@Controller從前端拿到的參數會在這一層進行邏輯的處理
<br>
**資料將會從@Repository取得

-----------------------
@Repository

**資料存取層（DAO layer），負責與資料來源互動（DB/Redis等），提供 CRUD 操作
<br>
**常見實作方式：
- Spring Data JPA（底層可能是 Hibernate）
- MyBatis / MyBatis-Plus（手寫 SQL）
- JDBC（JdbcTemplate）

-----------------------
**備註**

**@Transactional 通常標在 Service 層，作為 transaction boundary（交易邊界）**

**Spring MVC 核心**
<br>
Spring MVC 的核心為 DispatcherServlet，負責將 HTTP request 分派到對應 Controller method。

**Bean 怎麼被掃描到**
<br>
Spring Boot 預設會從 @SpringBootApplication 所在 package 往下掃
<br>
掃描包含：@Component + 衍生註解
<br>
可用 @ComponentScan 變更掃描範圍

**為什麼不要全用 @Component?**
<br>
@Controller：MVC 特化（支援 handler mapping 等
<br>
@Service：標記業務層，語意清楚（可讀性）
<br>
@Repository：會觸發 Spring 的例外轉換機制（很重要）
- Spring 會把底層 persistence exception（JPA/Hibernate/JDBC）轉成 Spring 的 DataAccessException
<br>
<br>


-----------------------
**Reference**
<br>
https://www.geeksforgeeks.org/java/difference-between-component-repository-service-and-controller-annotations-in-spring/
