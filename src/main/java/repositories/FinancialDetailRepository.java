package repositories;

import models.FinancialDetail;
import models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDetailRepository extends JpaRepository<FinancialDetail, String> {
}
