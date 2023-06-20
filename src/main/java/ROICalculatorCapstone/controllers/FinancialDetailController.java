package ROICalculatorCapstone.controllers;

import ROICalculatorCapstone.models.FinancialDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ROICalculatorCapstone.services.FinancialDetailService;

@Controller
@RequestMapping("/financialdetails")
public class FinancialDetailController {

    private final FinancialDetailService financialDetailService;

    public FinancialDetailController(FinancialDetailService financialDetailService) {
        this.financialDetailService = financialDetailService;
    }

    @GetMapping("/{address}")
    public String getFinancialDetail(@PathVariable String address, Model model) {
        FinancialDetail financialDetail = financialDetailService.getFinancialDetailByAddress(address);
        if (financialDetail != null) {
            model.addAttribute("financialDetail", financialDetail);
            return "financial_detail"; // Return the financial-details.html template
        } else {
            model.addAttribute("financialDetail", new FinancialDetail());
            return "financial_detail_form"; // Return the financial_detail_form.html template
        }
    }

    @PostMapping
    public String createFinancialDetail(@ModelAttribute("financialDetail") FinancialDetail financialDetail) {
        financialDetailService.saveFinancialDetail(financialDetail);
        return "redirect:/financialdetails/" + financialDetail.getAddress(); // Redirect to the updated financial detail page
    }

    @PutMapping("/{address}")
    public String updateFinancialDetail(@PathVariable String address, @ModelAttribute("financialDetail") FinancialDetail updatedFinancialDetail) {
        FinancialDetail financialDetail = financialDetailService.getFinancialDetailByAddress(address);
        if (financialDetail != null) {
            financialDetail.setPurchasePrice(updatedFinancialDetail.getPurchasePrice());
            financialDetail.setExpectedRehabCosts(updatedFinancialDetail.getExpectedRehabCosts());
            financialDetail.setInterestRate(updatedFinancialDetail.getInterestRate());
            financialDetail.setAnticipatedLengthOfProject(updatedFinancialDetail.getAnticipatedLengthOfProject());
            financialDetail.setLoanAmount(updatedFinancialDetail.getLoanAmount());
            financialDetail.setMonthlyPropertyTaxes(updatedFinancialDetail.getMonthlyPropertyTaxes());
            financialDetail.setMonthlyInsurance(updatedFinancialDetail.getMonthlyInsurance());
            financialDetail.setMonthlyUtilityBills(updatedFinancialDetail.getMonthlyUtilityBills());
            financialDetail.setOtherMonthlyExpenses(updatedFinancialDetail.getOtherMonthlyExpenses());
            financialDetail.setCostsOfSale(updatedFinancialDetail.getCostsOfSale());
            financialDetail.setAfterRepairValue(updatedFinancialDetail.getAfterRepairValue());

            financialDetailService.saveFinancialDetail(financialDetail);
        }
        return "redirect:/financialdetails/" + address; // Redirect to the updated financial detail page
    }



    @DeleteMapping("/{address}")
    public String deleteFinancialDetail(@PathVariable String address) {
        boolean deleted = financialDetailService.deleteFinancialDetail(address);
        if (deleted) {
            return "redirect:/properties"; // Redirect to the property list page
        } else {
            // Handle financial detail not found case
            return "redirect:/properties";
        }
    }
}