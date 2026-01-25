@Bean

- 用來告訴 Spring 這個物件要怎麼被建立並交給容器管理
- 定義一個 Bean，通常放在 @Configuration 類別的"內部方法"，方法的返回值將被註冊為 Spring 容器中的 Bean(Bean 名稱預設是 method 名稱)
- @Bean 只是建立 Spring Bean 的一種方式，並不是等於 Spring 裡面的 Bean，要小心不要誤會了
- 使用情境
  - 第三方套件的類別你無法加 @Component
  - 需要自定義初始化邏輯

```
ex. 假如我使用一個第三方的SDK

public class ThirdPartyClient {
    public ThirdPartyClient(String apiKey) {}
}
----------@Autowired使用-------------
@Service
public class PaymentService {

    @Autowired
    private ThirdPartyClient client;
}
```
```
會發生：
NoSuchBeanDefinitionException: No qualifying bean of type 'ThirdPartyClient'
因為這個Bean還沒有被管理
```
-----------------------
@Configuration

- 宣告「這是一個 Spring 設定類」
- 代表這個 class 裡面會提供 Spring 的 Bean 設定（通常搭配 @Bean），Spring 會掃描並建立其中的 bean
- @Configuration 其實是一種特殊的 @Component
- @Configuration 會讓 Spring 對這個 class 做 CGLIB 代理，確保： 
  - 同一個 @Bean method 多次呼叫，回傳的是同一個單例（singleton）bean

```
@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public UserService userService() {
        // 會拿到 Spring 管理的那個 userRepository (singleton)
        return new UserService(userRepository());
    }
}
```

-----------------------
@PostConstruct

- 在 Spring 完成依賴注入後（Bean 初始化階段） 
- 自動呼叫一次，用於初始化
- 使用情境 :  
  - 初始化 cache 
  - 預載資料 
  - 檢查設定是否正確 
  - 建立連線池
  - 載入 metadata

```
@Component
public class CourseCache {

    @PostConstruct
    public void init() {
        System.out.println("CourseCache 初始化完成，載入資料...");
    }
}
```
-----------------------
@PreDestroy

- 容器關閉前，自動執行一次（善後）
- Spring 容器關閉時（例如 App shutdown），會自動呼叫一次，用於釋放資源
- 使用情境 :
  - 關閉 thread pool
  - 關閉 socket / connection
  - flush buffer、log 最後一筆資料

```
@Component
public class ResourceManager {

    @PreDestroy
    public void destroy() {
        System.out.println("Spring 要關閉了，釋放資源...");
    }
}
```
-----------------------
@Lazy

- 預設 Spring Boot 啟動時會把 bean 建好（Eager initialization）
- @Lazy 表示： 直到第一次被使用 / 注入 / 呼叫 才建立
- 使用情境
  - 很重的 bean（初始化慢） 
  - 啟動時不一定會用到 
  - 避免循環依賴（某些 case 可救急）

```
@Component
@Lazy
public class HeavyService {

    public HeavyService() {
        System.out.println("HeavyService 建立中（很慢）...");
    }
}

--- 注入時也Lazy ---

@Service
public class UserService {

    private final HeavyService heavyService;

    public UserService(@Lazy HeavyService heavyService) {
        this.heavyService = heavyService;
    }
}

```
-----------------------
@Scope

- 設定 Bean 的「生命週期 / 建立策略」
- Scope	說明
  - singleton (預設)	整個 Spring 容器只有一個 instance
  - prototype	每次 getBean() 都 new 一個
  - request	每個 HTTP request 一個（Web 才有）
  - session	每個 session 一個（Web 才有）

```
@Component
@Scope("prototype")
public class RequestIdGenerator {

    private final String id = UUID.randomUUID().toString();

    public String getId() {
        return id;
    }
}
```