# data-converter

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c2776a4e8c5b44dbadc83f5ac7a6e9b6)](https://app.codacy.com/gh/NextPlugins/data-converter?utm_source=github.com&utm_medium=referral&utm_content=NextPlugins/data-converter&utm_campaign=Badge_Grade)

Essentialy, it was created to help convert data of a plugin from an other. But, we made it a
adapter which can be used as a converter too.

## How to use
Let's use it as a adapter.

That's my class:
```java
class User {
  
  private final int id;
  private final String name;
  
  User(int id, String name) {
    this.id = id;
    this.name = name;
  }

  // Getters...

}
```

Let's adapt the data from database to a object:

```java
Converter.<User>builder()
       .connection(myConnectionHere)
       .create()
       .request("SELECT `id`, `name` FROM `users` WHERE `id` = '1'")
       .convert((resultSet) -> new User(resultSet.get("id"), resultSet.get("name"))
       .responseEach((user) -> System.out.printf("My ID | My Name: %s | %s", user.getId(), user.getName()));
```
