# data-converter
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
       .request("SELECT `id`, `name` FROM `users` WHERE `id` = '1'")
       .convert((resultSet) -> new User(resultSet.get("id"), resultSet.get("name"))
       .responseEach((user) -> System.out.printf("My ID | My Name: %s | %s", user.getId(), user.getName()));
```
