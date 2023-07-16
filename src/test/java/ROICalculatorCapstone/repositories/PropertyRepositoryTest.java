package ROICalculatorCapstone.repositories;

import ROICalculatorCapstone.models.Property;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropertyRepositoryTest {
    @Autowired
    private PropertyRepository propertyRepository;

    @Test
    @Transactional
    void findByAddress() {
        // Create test data
        Property property = new Property();
        property.setAddress("123 Main St");
        propertyRepository.save(property);

        // Perform the query
        List<Property> foundProperties = propertyRepository.findByAddress("123 Main St");

        // Assert the results
        assertEquals(1, foundProperties.size());
        assertEquals("123 Main St", foundProperties.get(0).getAddress());
    }

    @Test
    @Transactional
    void deleteByAddress() {
        // Create test data
        Property property = new Property();
        property.setAddress("456 Elm St");
        propertyRepository.save(property);

        // Perform the deletion
        propertyRepository.deleteByAddress("456 Elm St");

        // Verify the deletion
        assertFalse(propertyRepository.existsByAddress("456 Elm St"));
    }
}