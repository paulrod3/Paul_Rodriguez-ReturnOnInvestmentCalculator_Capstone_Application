package services;

import models.Property;
import models.RenovationExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PropertyRepository;
import repositories.RenovationExpenseRepository;

import java.util.List;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final RenovationExpenseRepository renovationExpenseRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository, RenovationExpenseRepository renovationExpenseRepository) {
        this.propertyRepository = propertyRepository;
        this.renovationExpenseRepository = renovationExpenseRepository;
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyByAddress(String address) {
        return propertyRepository.findById(address).orElse(null);
    }

    public List<Property> getPropertiesByAddress(String address) {
        return propertyRepository.findAllByAddress(address);
    }

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Property updateProperty(Property property) {
        return propertyRepository.save(property);
    }

    public void deleteProperty(String address) {
        propertyRepository.deleteById(address);
    }

    public void addRenovationExpense(String address, RenovationExpense renovationExpense) {
        Property property = propertyRepository.findById(address).orElse(null);
        if (property != null) {
            renovationExpense.setProperty(property);
            renovationExpenseRepository.save(renovationExpense);
            property.addRenovationExpense(renovationExpense);
            propertyRepository.save(property);
        }
    }

    public void removeRenovationExpense(String address, Long expenseId) {
        Property property = propertyRepository.findById(address).orElse(null);
        if (property != null) {
            RenovationExpense renovationExpense = renovationExpenseRepository.findById(expenseId).orElse(null);
            if (renovationExpense != null && renovationExpense.getProperty().equals(property)) {
                property.removeRenovationExpense(renovationExpense);
                renovationExpenseRepository.delete(renovationExpense);
                propertyRepository.save(property);
            }
        }
    }

}


