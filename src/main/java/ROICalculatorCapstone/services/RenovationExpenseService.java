package ROICalculatorCapstone.services;

import ROICalculatorCapstone.models.Property;
import ROICalculatorCapstone.models.RenovationExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ROICalculatorCapstone.repositories.PropertyRepository;
import ROICalculatorCapstone.repositories.RenovationExpenseRepository;

import java.util.Collections;
import java.util.List;

@Service
public class RenovationExpenseService {
    private final RenovationExpenseRepository renovationExpenseRepository;
    private final PropertyService propertyService;

    @Autowired
    public RenovationExpenseService(RenovationExpenseRepository renovationExpenseRepository, PropertyService propertyService) {
        this.renovationExpenseRepository = renovationExpenseRepository;
        this.propertyService = propertyService;
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

    public boolean deleteRenovationExpense(Long id) {
        try {
            renovationExpenseRepository.deleteById(id);
            return true; // Return true if deletion was successful
        } catch (Exception e) {
            return false; // Return false if deletion failed
        }
    }

    public List<RenovationExpense> getRenovationExpensesByPropertyAddress(String address) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            return property.getExpenses();
        }
        return Collections.emptyList();
    }

}
