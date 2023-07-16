package ROICalculatorCapstone.repositories;

import ROICalculatorCapstone.models.RenovationExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RenovationExpenseRepository extends JpaRepository<RenovationExpense, Long> {

    //Retrieves a list of renovation expenses associated with a property based on the provided
    // property address.
    List<RenovationExpense> findByPropertyAddress(String address);
}
