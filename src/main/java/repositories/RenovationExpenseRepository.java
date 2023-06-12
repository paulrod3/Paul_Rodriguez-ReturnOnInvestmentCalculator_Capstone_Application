package repositories;

import models.RenovationExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RenovationExpenseRepository extends JpaRepository<RenovationExpense, Long> {
}
