package repositories;

import models.FinancialDetail;
import models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, String> {
    List<Property> findAllByAddress(String address);

}
