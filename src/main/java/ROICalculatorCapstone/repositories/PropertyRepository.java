package ROICalculatorCapstone.repositories;

import ROICalculatorCapstone.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, String> {

    //Retrieves a list of properties based on the provided address.
    List<Property> findByAddress(String address);

    //Deletes a property based on the provided address.
    void deleteByAddress(String address);

    // Checks if a property exists in the database based on the provided address.
    boolean existsByAddress(String s);

}
