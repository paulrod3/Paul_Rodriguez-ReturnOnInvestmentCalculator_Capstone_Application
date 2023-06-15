package ROICalculatorCapstone.repositories;

import ROICalculatorCapstone.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, String> {
    List<Property> findByAddress(String address);

    void deleteByAddress(String address);
}
