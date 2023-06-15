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

    public RenovationExpenseController(RenovationExpenseService renovationExpenseService) {
        this.renovationExpenseService = renovationExpenseService;
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

    @PostMapping
    public String createRenovationExpense(@ModelAttribute("renovationExpense") RenovationExpense renovationExpense) {
        RenovationExpense createdRenovationExpense = renovationExpenseService.saveRenovationExpense(renovationExpense);
        return "redirect:/renovationexpenses/" + createdRenovationExpense.getId(); // Redirect to the newly created renovation expense details
    }

    @PutMapping("/{id}")
    public String updateRenovationExpense(@PathVariable("id") Long id, @ModelAttribute("renovationExpense") RenovationExpense renovationExpense) {
        renovationExpense.setId(id);
        RenovationExpense updatedRenovationExpense = renovationExpenseService.updateRenovationExpense(renovationExpense);
        if (updatedRenovationExpense != null) {
            return "redirect:/renovationexpenses/" + updatedRenovationExpense.getId(); // Redirect to the updated renovation expense details
        } else {
            return "redirect:/renovationexpenses"; // Redirect to the list of renovation expenses if not found
        }
    }

    @DeleteMapping("/{id}")
    public String deleteRenovationExpense(@PathVariable Long id) {
        boolean deleted = renovationExpenseService.deleteRenovationExpense(id);
        if (deleted) {
            return "redirect:/renovationexpenses"; // Redirect to the list of renovation expenses after deletion
        } else {
            return "redirect:/renovationexpenses"; // Redirect to the list of renovation expenses if not found
        }
    }

    @GetMapping("/property/{address}")
    public String getRenovationExpensesByPropertyAddress(@PathVariable String address, Model model) {
        List<RenovationExpense> renovationExpenses = renovationExpenseService.getRenovationExpensesByPropertyAddress(address);
        model.addAttribute("renovationExpenses", renovationExpenses);
        return "property_renovation_expenses"; // Return the HTML template to display the renovation expenses for a property
    }
}
