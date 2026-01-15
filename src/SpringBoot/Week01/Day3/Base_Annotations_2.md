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

- 告訴 Spring 要去哪裡找零件（@Controller、@Service、@Repository等）
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

-----------------------
@PathVariable

- 擷取API路經中的資料，做為傳入後端的參數
- 用於從 URI 路徑中擷取資料，它會將 URL 範本路徑變數與方法變數綁定

-----------------------
@RequestBody

- "Request"
- 前端的資料會以 JSON 格式傳到後端
- 此註解用於將Http的請求從輸入的JSON格式轉為直接轉為從請求主體的網域物件

````
@RestController
public class MyController{
  
  @GetMapping("/author")
  public void printAuthor(@RequestBody Author author){
    //insert code here
  }
}
````
-----------------------
@ResponseBody

- "Response"
- 此註解用於將網域物件轉換為 HTTP 請求，形式為 JSON 或其他文字。此處，方法的回傳類型與 HTTP 回應主體綁定
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