@RequestParam

- 使用 @RequestParam 從請求裡面提取查詢參數、表單參數、檔案

-----------------------
@RequestMapping

- @RequestMapping 是用來處理 HTTP 請求的注解，可以用於類別或方法上，指定請求的 URL 路徑和 HTTP 方法
- @RequestMapping 後面需要加上 HTTP 方法的註解，它可以用來處理多種 HTTP 方法，例如 GET、POST、PUT、DELETE 等
  - @RequestMapping (method = RequestMethod.GET)

```
@Controller
@RequestMapping("/login")/* 具體指出request的類別*/
public class LoginController {

    @GetMapping("/user")
    public String getUser() {
        return "Get User";
    }
}

-----------or方法上使用------------

@Controller
@RequestMapping("/login")/* 具體指出request的類別*/
public class LoginController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser() {
        return "Get User";
    }
}
```

-----------------------
@GetMapping

- @GetMapping是寫在方法上面的，不可以寫在類別上面


-----------------------
@PostMapping


-----------------------
@PutMapping


-----------------------
@DeleteMapping


-----------------------
@PatchMapping


-----------------------