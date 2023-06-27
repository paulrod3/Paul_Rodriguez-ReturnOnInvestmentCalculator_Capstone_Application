package ROICalculatorCapstone.controllers;

import ROICalculatorCapstone.models.Property;
import ROICalculatorCapstone.models.RenovationExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ROICalculatorCapstone.services.PropertyService;
import ROICalculatorCapstone.services.RenovationExpenseService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/renovationexpenses")
public class RenovationExpenseController {
    private final RenovationExpenseService renovationExpenseService;
    private final PropertyService propertyService;

    public RenovationExpenseController(RenovationExpenseService renovationExpenseService, PropertyService propertyService) {
        this.renovationExpenseService = renovationExpenseService;
        this.propertyService = propertyService;
    }

    @GetMapping("/{id}")
    public String getRenovationExpense(@PathVariable Long id, Model model) {
        RenovationExpense renovationExpense = renovationExpenseService.getRenovationExpenseById(id);
        if (renovationExpense != null) {
            model.addAttribute("renovationExpense", renovationExpense);
            return "renovation_expense_details"; // Return the HTML template to display the renovation expense details
        } else {
            return "redirect:/renovationexpenses"; // Redirect to the list of renovation expenses if not found
        }
    }

    @GetMapping("/property/{address}")
    public String getRenovationExpensesByPropertyAddress(@PathVariable String address, Model model) {
        List<RenovationExpense> renovationExpenses = renovationExpenseService.getRenovationExpensesByPropertyAddress(address);
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            model.addAttribute("renovationExpenses", renovationExpenses);
            model.addAttribute("property", property);
            return "renovation_expense"; // Return the HTML template to display the renovation expenses for a property
        } else {
            return "redirect:/renovationexpenses"; // Redirect to the list of renovation expenses if property not found
        }
    }

    @PostMapping("/property/{address}")
    public String createRenovationExpense(@PathVariable String address, @ModelAttribute("renovationExpense") RenovationExpense renovationExpense) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            renovationExpense.setProperty(property);
            renovationExpenseService.saveRenovationExpense(renovationExpense);
        }
        return "redirect:/renovationexpenses/property/{address}"; // Redirect to the renovation expenses for the property
    }

    @DeleteMapping("/{id}")
    public String deleteRenovationExpense(@PathVariable Long id) {
        RenovationExpense renovationExpense = renovationExpenseService.getRenovationExpenseById(id);
        if (renovationExpense != null) {
            String address = renovationExpense.getProperty().getAddress();
            boolean deleted = renovationExpenseService.deleteRenovationExpense(id);
            if (deleted) {
                return "redirect:/renovationexpenses/property/" + address; // Redirect to the renovation expenses for the property after deletion
            }
        }
        return "redirect:/renovationexpenses"; // Redirect to the list of renovation expenses if not found
    }
}