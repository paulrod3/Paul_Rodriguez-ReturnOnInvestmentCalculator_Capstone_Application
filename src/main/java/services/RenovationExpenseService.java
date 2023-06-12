package services;

import models.Property;
import models.RenovationExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PropertyRepository;
import repositories.RenovationExpenseRepository;

@Service
public class RenovationExpenseService {
    private final RenovationExpenseRepository renovationExpenseRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public RenovationExpenseService(RenovationExpenseRepository renovationExpenseRepository, PropertyRepository propertyRepository) {
        this.renovationExpenseRepository = renovationExpenseRepository;
        this.propertyRepository = propertyRepository;
    }

    public RenovationExpense getRenovationExpenseById(Long id) {
        return renovationExpenseRepository.findById(id).orElse(null);
    }

    public RenovationExpense saveRenovationExpense(RenovationExpense renovationExpense) {
        return renovationExpenseRepository.save(renovationExpense);
    }

    public RenovationExpense updateRenovationExpense(RenovationExpense renovationExpense) {
        return renovationExpenseRepository.save(renovationExpense);
    }

    public void deleteRenovationExpense(Long id) {
        renovationExpenseRepository.deleteById(id);
    }

    public Property getPropertyByAddress(String address) {
        return propertyRepository.findById(address).orElse(null);
    }
}
