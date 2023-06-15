package ROICalculatorCapstone.services;

import ROICalculatorCapstone.models.Property;
import ROICalculatorCapstone.models.RenovationExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ROICalculatorCapstone.repositories.PropertyRepository;
import ROICalculatorCapstone.repositories.RenovationExpenseRepository;

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
        return propertyRepository.findByAddress(address);
    }

    public Property saveProperty(Property property) {

        return propertyRepository.save(property);
    }

    public Property updateProperty(String address, Property updatedProperty) {
        Property property = (Property) propertyRepository.findByAddress(address);
        if (property != null) {
            // Update the necessary fields of the property with the updatedProperty values
            property.setPropertyType(updatedProperty.getPropertyType());
            property.setSqft(updatedProperty.getSqft());
            property.setNumberOfBedrooms(updatedProperty.getNumberOfBedrooms());
            property.setNumberOfBathrooms(updatedProperty.getNumberOfBathrooms());

            return propertyRepository.save(property);
        }
        return null; // Handle error if property not found
    }

    public void deleteProperty(String address) {
        propertyRepository.deleteByAddress(address);
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


