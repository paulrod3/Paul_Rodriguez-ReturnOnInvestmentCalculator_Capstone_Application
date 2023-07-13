package ROICalculatorCapstone.security;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();
    User findByEmail(String email);
    User save(UserRegistrationData registration);
    User updateUser(User user);

    User getUserById(long id);
    void deleteUserById(long id);

}
