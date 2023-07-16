package ROICalculatorCapstone.security;

import java.util.List;

//The UserService interface defines methods for user-related operations such as
// creating a user, validating user credentials, retrieving a user by username,
// retrieving all users, updating a user, and deleting a user.
public interface UserService {

    // Post
    public User createUser(User user);

    // Post
    public Boolean validateUser(String username, String password);

    // Get One
    public User getUser(String username);

    // Get All
    public List<User> getUsers();

    // Put
    public User updateUser(User user);

    // Delete
    public void deleteUser(String username);

}
