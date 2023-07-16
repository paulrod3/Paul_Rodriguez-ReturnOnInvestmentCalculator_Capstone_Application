package ROICalculatorCapstone.services;

import ROICalculatorCapstone.models.RenovationExpense;
import ROICalculatorCapstone.repositories.RenovationExpenseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RenovationExpenseServiceTest {

    @Autowired
    private RenovationExpenseService renovationExpenseService;

    @MockBean
    private RenovationExpenseRepository renovationExpenseRepository;

    @Test
    void getRenovationExpenseById() {
        // Create a mock RenovationExpense object
        RenovationExpense renovationExpense = new RenovationExpense();
        renovationExpense.setId(1L);
        renovationExpense.setDescription("Painting");
        renovationExpense.setAmount(1000.0);
        renovationExpense.setDateOfPurchase("2023-05-01");

        // Mock the behavior of the RenovationExpenseRepository
        Mockito.when(renovationExpenseRepository.findById(1L)).thenReturn(Optional.of(renovationExpense));

        // Call the service method
        RenovationExpense result = renovationExpenseService.getRenovationExpenseById(1L);

        // Assert the result
        assertEquals(renovationExpense, result);
    }

    @Test
    void saveRenovationExpense() {
        // Create a mock RenovationExpense object
        RenovationExpense renovationExpense = new RenovationExpense();
        renovationExpense.setDescription("Painting");
        renovationExpense.setAmount(1000.0);
        renovationExpense.setDateOfPurchase("2023-05-01");

        // Mock the behavior of the RenovationExpenseRepository
        Mockito.when(renovationExpenseRepository.save(renovationExpense)).thenReturn(renovationExpense);

        // Call the service method
        RenovationExpense result = renovationExpenseService.saveRenovationExpense(renovationExpense);

        // Assert the result
        assertEquals(renovationExpense, result);
    }

}