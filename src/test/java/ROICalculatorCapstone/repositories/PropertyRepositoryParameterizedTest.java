package ROICalculatorCapstone.repositories;

import ROICalculatorCapstone.models.Property;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropertyRepositoryParameterizedTest {

    @Autowired
    private PropertyRepository propertyRepository;

    @ParameterizedTest
    @ValueSource(strings = {"123 Main St", "456 Elm St", "789 Oak St"})
    @Transactional
    void findByAddress(String address) {
        // Create test data
        Property property = new Property();
        property.setAddress(address);
        propertyRepository.save(property);

        // Perform the query
        List<Property> foundProperties = propertyRepository.findByAddress(address);

        // Assert the results
        assertEquals(1, foundProperties.size());
        assertEquals(address, foundProperties.get(0).getAddress());
    }
}
