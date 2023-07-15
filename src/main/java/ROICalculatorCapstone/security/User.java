package ROICalculatorCapstone.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;

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
