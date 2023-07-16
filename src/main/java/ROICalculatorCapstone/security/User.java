package ROICalculatorCapstone.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;

//The User class is an entity representing user information. It is mapped to the "users"
// table in the database and contains fields for username and password.
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;



}
