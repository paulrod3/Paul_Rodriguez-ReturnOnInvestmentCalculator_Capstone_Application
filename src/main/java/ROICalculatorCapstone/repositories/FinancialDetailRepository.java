package ROICalculatorCapstone.repositories;

import ROICalculatorCapstone.models.FinancialDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDetailRepository extends JpaRepository<FinancialDetail, String> {
}
