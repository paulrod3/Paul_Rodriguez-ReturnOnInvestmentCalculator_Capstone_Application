package ROICalculatorCapstone.services;

import ROICalculatorCapstone.models.Property;
import ROICalculatorCapstone.repositories.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropertyServiceTest {

    @Autowired
    private PropertyService propertyService;

    @MockBean
    private PropertyRepository propertyRepository;

    @Test
    void getAllProperties() {
        // Create a list of mock Property objects
        List<Property> properties = new ArrayList<>();
        properties.add(new Property("123 Main St"));
        properties.add(new Property("456 Elm St"));

        // Mock the behavior of the PropertyRepository
        Mockito.when(propertyRepository.findAll()).thenReturn(properties);

        // Call the service method
        List<Property> result = propertyService.getAllProperties();

        // Assert the result
        assertEquals(properties, result);
    }

    @Test
    void getPropertyByAddress() {
        // Create a mock Property object
        Property property = new Property("123 Main St");

        // Mock the behavior of the PropertyRepository
        Mockito.when(propertyRepository.findById("123 Main St")).thenReturn(Optional.of(property));

        // Call the service method
        Property result = propertyService.getPropertyByAddress("123 Main St");

        // Assert the result
        assertEquals(property, result);
    }

}