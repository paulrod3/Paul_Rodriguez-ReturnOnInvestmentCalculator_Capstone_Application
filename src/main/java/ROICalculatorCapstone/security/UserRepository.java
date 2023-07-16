package ROICalculatorCapstone.security;

import org.springframework.data.jpa.repository.JpaRepository;

//The UserRepository interface extends the JpaRepository interface,
// providing CRUD operations for the User entity. It also defines a custom method
// findByUsername to retrieve a user by their username.
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);

}
