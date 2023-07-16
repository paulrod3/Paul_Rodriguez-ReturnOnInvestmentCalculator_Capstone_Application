package ROICalculatorCapstone.services;

import ROICalculatorCapstone.models.Property;
import ROICalculatorCapstone.models.RenovationExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ROICalculatorCapstone.repositories.PropertyRepository;
import ROICalculatorCapstone.repositories.RenovationExpenseRepository;

import javax.transaction.Transactional;
import java.util.List;

//The PropertyService class is a service component that provides methods for interacting with
// properties and their associated renovation expenses. It utilizes the PropertyRepository and
// RenovationExpenseRepository for data access. The methods include retrieving all properties,
// getting a property by address, saving a property, updating a property, deleting a property,
// adding a renovation expense to a property, and removing a renovation expense from a property.
// The service is annotated with @Transactional to ensure data consistency during transactions.
@Service
@Transactional
public class PropertyService {
    private final PropertyRepository propertyRepository;


    @Autowired
    public PropertyService(PropertyRepository propertyRepository, RenovationExpenseRepository renovationExpenseRepository) {
        this.propertyRepository = propertyRepository;
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
        propertyRepository.deleteById(address);;
    }

    public void addRenovationExpense(String address, RenovationExpense renovationExpense) {
        Property property = getPropertyByAddress(address);
        if (property != null) {
            property.addRenovationExpense(renovationExpense);
            renovationExpense.setProperty(property);
            propertyRepository.save(property);
        }
    }

    public void removeRenovationExpense(String address, Long expenseId) {
        Property property = getPropertyByAddress(address);
        if (property != null) {
            RenovationExpense renovationExpense = property.getExpenses().stream()
                    .filter(expense -> expense.getId() == expenseId)
                    .findFirst()
                    .orElse(null);

            if (renovationExpense != null) {
                property.removeRenovationExpense(renovationExpense);
                propertyRepository.save(property);
            }
        }
    }

}


