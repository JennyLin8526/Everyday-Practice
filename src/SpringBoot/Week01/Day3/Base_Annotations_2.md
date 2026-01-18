
**@Autowired 、@Resource、@Inject 都在做DI注入**

@Autowired
- 預設是by type用「型別」去找 Bean
- 指定某個 Bean 
  - @Qualifier("name") / @Primary
- 有不同的依賴注入方式
  - 欄位注入
  - Setter注入
  - 建構子注入

```
// 假設有多個介面的實作，要用@Qualifier去匹配Bean
public interface PaymentService { }

@Autowired
@Qualifier("ecpay")
private PaymentService paymentService; 

@Autowired
@Qualifier("linePay")
private PaymentService paymentService; 
```

@Resource
- 預設是「by name」：先用「名字」找 Bean
- 指定某個 Bean 
  - @Resource(name="...")
```
// 這通常會去找 bean name = "ecpay" 的 Bean（如果你的 Bean 名剛好就叫 ecpay）
@Resource
private PaymentService ecpay;

@Resource(name = "ecpay")
private PaymentService paymentService;
```
@Inject
- 預設是by type用「型別」去找 Bean
- 指定某個 Bean
  - @Named("...")（或自訂 qualifier）

-----------------------
**同一個介面有多個實作時，讓Spring知道要注入哪一個**

@Qualifier("ecpay")
- 指定要哪一個 Bean
- 影響範圍:只影響這一次注入點
- 寫在注入點（欄位/參數）

```
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(@Qualifier("ecpay") PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```
也可以寫這樣
```
@Autowired
@Qualifier("ecpay")
private PaymentService paymentService;
```

-----------------------
**同一個介面有多個實作時，讓Spring知道要優先注入哪一個**

@Primary
- 設定預設優先用哪一個 Bean
- 只有在沒 Qualifier 時才會生效(盡量還是用@Qualifier)
- 影響範圍:影響整個專案所有未指定的注入點
- 寫在 Bean 類別上
```
@Primary
@Service("ecpay")
public class EcpayPaymentService implements PaymentService {}
```

```
@Autowired
private PaymentService paymentService; // 會自動注入 @Primary 那個
```
-----------------------

@SpringBootApplication
- 表示標註的類別為主類別(通常放在最外層的package的主類別)
- @ComponentScan 的預設掃描範圍是 主類別所在 package + 子 package
- 封裝了 @SpringBootConfiguration、@EnableAutoConfiguration、@ComponentScan 這個三個屬性

````
@SpringBootConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
````
-----------------------
@EnableAutoConfiguration

- 根據條件，匯入Auto-configuration（配置類）
- 例如：偵測到你有 spring-webmvc 就配置 MVC、偵測到有 datasource 就配置 DataSource、TransactionManager 等
- 依賴環境自動套用Spring Boot的配置

````
@Configuration
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
````
-----------------------
@ComponentScan

- 告訴 Spring 要去哪裡找元件（@Controller、@Service、@Repository等）
- 預設掃描：主類別所在 package + 子 package
- 通常搭配@Configuration一起使用

````
@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
````
-----------------------
@RequestParam

- 用於讀取前端資料，並將網頁請求參數綁定到特定的控制器方法
- 讀取 query string 或 form data
- 常用參數： 
  - (必填)required = false    
  - (預設值) defaultValue = "xxx"
```
@GetMapping("/books")
public List<Book> list(@RequestParam(defaultValue="1") int page,
                       @RequestParam(required=false) String keyword) {}
```

-----------------------
@PathVariable

- 擷取API路經中的資料，做為傳入後端的參數
- 用於從 URI 路徑中擷取資料，它會將 URL 範本路徑變數與方法變數綁定

```
@GetMapping("/users/{id}")
public User get(@PathVariable("id") Long userId) {}
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