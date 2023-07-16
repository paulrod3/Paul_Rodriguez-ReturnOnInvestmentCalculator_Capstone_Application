package ROICalculatorCapstone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

//The UserServiceImpl class is an implementation of the UserService interface. It provides
// the functionality to create a user, validate user credentials, retrieve user information,
// update user details, and delete a user. It uses the UserRepository for data access and
// PasswordEncoder for password encoding.
@Service
public class UserServiceImpl implements UserService {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Boolean validateUser(String username, String password) {

        User existingUser = userRepository.findByUsername(username);

        if (existingUser == null) {
            throw new ResourceNotFoundException("User does not exist with Username: " + username);
        }

        boolean isValid = (username == existingUser.getUsername() && password == existingUser.getPassword());

        return isValid;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser == null) {
            throw new ResourceNotFoundException("User does not exist with Username: " + user.getUsername());
        }

        existingUser.setPassword(passwordEncoder().encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        User existingUser = userRepository.findByUsername(username);

        if (existingUser == null) {
            throw new ResourceNotFoundException("User does not exist with Username: " + username);
        }

        userRepository.delete(existingUser);
    }

}
