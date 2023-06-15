package ROICalculatorCapstone.services;

import ROICalculatorCapstone.models.FinancialDetail;
import ROICalculatorCapstone.models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ROICalculatorCapstone.repositories.FinancialDetailRepository;
import ROICalculatorCapstone.repositories.PropertyRepository;

@Service
public class FinancialDetailService {
    private final FinancialDetailRepository financialDetailRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public FinancialDetailService(FinancialDetailRepository financialDetailRepository,
                                  PropertyRepository propertyRepository) {
        this.financialDetailRepository = financialDetailRepository;
        this.propertyRepository = propertyRepository;
    }

    public FinancialDetail getFinancialDetailByAddress(String address) {
        return financialDetailRepository.findById(address).orElse(null);
    }

    public FinancialDetail saveFinancialDetail(FinancialDetail financialDetail) {
        return financialDetailRepository.save(financialDetail);
    }

    public FinancialDetail updateFinancialDetail(FinancialDetail financialDetail) {
        return financialDetailRepository.save(financialDetail);
    }

    public boolean deleteFinancialDetail(String address) {
        try {
            financialDetailRepository.deleteById(address);
            return true; // Return true if deletion was successful
        } catch (Exception e) {
            return false; // Return false if deletion failed
        }

    }

    public Property getPropertyByAddress(String address) {
        return propertyRepository.findById(address)
                .orElse(null);
    }

}
