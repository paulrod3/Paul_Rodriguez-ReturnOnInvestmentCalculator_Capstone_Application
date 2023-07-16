package ROICalculatorCapstone.services;

import ROICalculatorCapstone.models.FinancialDetail;
import ROICalculatorCapstone.repositories.FinancialDetailRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FinancialDetailServiceTest {

    @Autowired
    private FinancialDetailService financialDetailService;

    @MockBean
    private FinancialDetailRepository financialDetailRepository;

    @Test
    void getFinancialDetailByAddress() {
        // Create a mock FinancialDetail object
        FinancialDetail financialDetail = new FinancialDetail();
        financialDetail.setAddress("123 Main St");
        financialDetail.setIncome(1000);
        financialDetail.setExpenses(500);

        // Mock the behavior of the FinancialDetailRepository
        Mockito.when(financialDetailRepository.findById("123 Main St")).thenReturn(Optional.of(financialDetail));

        // Call the service method
        FinancialDetail result = financialDetailService.getFinancialDetailByAddress("123 Main St");

        // Assert the result
        assertEquals(financialDetail, result);
    }

    @Test
    void saveFinancialDetail() {
        // Create a mock FinancialDetail object
        FinancialDetail financialDetail = new FinancialDetail();
        financialDetail.setAddress("123 Main St");
        financialDetail.setIncome(1000);
        financialDetail.setExpenses(500);

        // Call the service method
        financialDetailService.saveFinancialDetail(financialDetail);

        // Verify that the repository's save method was called with the correct object
        Mockito.verify(financialDetailRepository).save(financialDetail);
    }

    @Test
    void updateFinancialDetail() {
        // Create a mock FinancialDetail object
        FinancialDetail financialDetail = new FinancialDetail();
        financialDetail.setAddress("123 Main St");
        financialDetail.setIncome(1000);
        financialDetail.setExpenses(500);

        // Call the service method
        financialDetailService.updateFinancialDetail(financialDetail);

        // Verify that the repository's save method was called with the correct object
        Mockito.verify(financialDetailRepository).save(financialDetail);
    }

    @Test
    void deleteFinancialDetail() {
        // Call the service method
        boolean result = financialDetailService.deleteFinancialDetail("123 Main St");

        // Verify that the repository's deleteById method was called with the correct address
        Mockito.verify(financialDetailRepository).deleteById("123 Main St");

        // Assert the result
        assertTrue(result);
    }
}