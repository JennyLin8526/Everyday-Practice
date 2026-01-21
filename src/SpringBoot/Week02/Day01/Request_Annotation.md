@RequestMapping

- @RequestMapping 是用來處理 HTTP 請求的注解，可以用於類別或方法上，指定請求的 URL 路徑和 HTTP 方法
- @RequestMapping 可以指定 path 與 method；不指定 method 會匹配多種 HTTP 方法
  - class 上通常用 @RequestMapping("/basePath") 做共用前綴比較清楚
  - 它可以用來處理多種 HTTP 方法，例如 GET、POST、PUT、DELETE 等
  - ex. @RequestMapping (method = RequestMethod.GET)

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

- 取得資料
- 通常用在方法上；類別上多用 @RequestMapping 設定 base path

```
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/getUserInfo")
    public String getUserInfo(Integer userId) {
        return "Get User Id: " + userId;
    }
}
```

-----------------------
@PostMapping

- 新增資料
- PostMapping是寫在方法上面的，不可以寫在類別上面

```
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping("/updateUserInfo")
    public String updateUserInfo(@RequestBody UserInfo userInfo) {
        return "Update user - User Id" + userInfo.getId();
    }
}
```

-----------------------
@PutMapping

- 整筆更新/取代資源
- 但其實在實務上不太會用到這個Annotation，PUT在前端並不好處理
  - 會有 CORS preflight 問題(很常出現)
  - 某些 gateway 或 proxy 對非 GET/POST 無法支援

```
@RestController
@RequestMapping("/login")
public class LoginController {

    @PutMapping("/updateUserInfo")
    public String updateUserInfo(@RequestBody UserInfo userInfo) {
        return "Update user - User Id" + userInfo.getId();
    }
}
```
-----------------------
@DeleteMapping

- 刪除資源

```
@RestController
@RequestMapping("/login")
public class LoginController {

    @DeleteMapping("/deleteUserId")
    public String deleteId(@PathVariable Integer deleteUserId) {
        return "Delete User Id : "+deleteUserId;
    }
}
```
-----------------------
@PatchMapping

- 部分更新資源

```
@RestController
@RequestMapping("/login")
public class LoginController {

    @PatchMapping("/patchUpdate")
    public void patchUpdate(Integer userId) {
        Patch....update....
    }
}
```
-----------------------
```
@RestController
@RequestMapping("/users") // class 做 base path
public class UserController {

    // GET：取得資源
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return "Get User Id: " + id;
    }

    // POST：新增資源（通常由後端產生 id）
    @PostMapping
    public String createUser(@RequestBody UserInfo userInfo) {
        return "Create user: " + userInfo.getName();
    }

    // PUT：整筆更新/取代（指定 id）
    @PutMapping("/{id}")
    public String replaceUser(@PathVariable Long id,
                              @RequestBody UserInfo userInfo) {
        return "Replace user " + id;
    }

    // PATCH：部分更新（指定 id）
    @PatchMapping("/{id}")
    public String patchUser(@PathVariable Long id,
                            @RequestBody Map<String, Object> patch) {
        return "Patch user " + id;
    }

    // DELETE：刪除資源（指定 id）
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "Delete user " + id;
    }
}


```