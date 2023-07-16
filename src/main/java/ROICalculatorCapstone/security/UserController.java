package ROICalculatorCapstone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//The UserController class is a RESTful API controller that handles user-related
// operations such as creating, retrieving, updating, and deleting users. It
// interacts with the UserService to perform these operations and returns responses
// using the ResponseEntity class.
@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("users")
    public User createUser(@RequestBody User user) {

        return userService.createUser(user);

    }

    @GetMapping("users/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {

        User existinguser = userService.getUser(username);

        return ResponseEntity.ok(existinguser);

    }

    @GetMapping("users")
    public List<User> getAllUsers() {

        return userService.getUsers();

    }

    @PutMapping("users/{username}")
    public ResponseEntity<User> updateUser(@PathVariable("username") String username, @RequestBody User user) {

        User existingUser = userService.updateUser(user);

        return ResponseEntity.ok(existingUser);

    }

    @DeleteMapping("users/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username) {

        userService.deleteUser(username);

        return ResponseEntity.ok("User deleted");
    }

}
