package ROICalculatorCapstone.controllers;

import ROICalculatorCapstone.models.FinancialDetail;
import ROICalculatorCapstone.models.Property;
import ROICalculatorCapstone.services.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ROICalculatorCapstone.services.FinancialDetailService;

@Controller
@RequestMapping("/financialdetails")
public class FinancialDetailController {


    private final FinancialDetailService financialDetailService;
    private final PropertyService propertyService;

    public FinancialDetailController(FinancialDetailService financialDetailService, PropertyService propertyService) {
        this.financialDetailService = financialDetailService;
        this.propertyService = propertyService;
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


    @PostMapping("/create")
    public String createFinancialDetail(@ModelAttribute("financialDetail") FinancialDetail financialDetail,
                                        Model model) {
        Property property = propertyService.getPropertyByAddress(financialDetail.getAddress());

        if (property != null) {
            financialDetail.setProperty(property);
            financialDetailService.saveFinancialDetail(financialDetail);
            return "financial_detail";
        } else {
            model.addAttribute("address", financialDetail.getAddress());
            throw new IllegalArgumentException("Invalid property address: " + financialDetail.getAddress());
        }
    }

    @GetMapping("/{address}/update")
    public String showUpdateForm(@PathVariable String address, Model model) {
        FinancialDetail financialDetail = financialDetailService.getFinancialDetailByAddress(address);
        if (financialDetail != null) {
            model.addAttribute("financialDetail", financialDetail);
            return "financial_detail_update"; // Return the financial_detail_update.html template
        }
        return "redirect:/financialdetails"; // Redirect to the financial details list if not found
    }

    @PostMapping("/{address}")
    public String updateFinancialDetail(@PathVariable String address, @ModelAttribute("financialDetail")
    FinancialDetail updatedFinancialDetail) {
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
            return "redirect:/financialdetails/{address}"; // Redirect back to the financial detail page
        }
    }

}