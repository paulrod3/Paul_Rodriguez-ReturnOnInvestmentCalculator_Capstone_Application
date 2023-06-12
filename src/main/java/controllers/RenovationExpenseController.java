package controllers;

import models.Property;
import models.RenovationExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.PropertyService;
import services.RenovationExpenseService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/renovationexpenses")
public class RenovationExpenseController {
    private final RenovationExpenseService renovationExpenseService;
    private final PropertyService propertyService;

    @Autowired
    public RenovationExpenseController(RenovationExpenseService renovationExpenseService,
                                       PropertyService propertyService) {
        this.renovationExpenseService = renovationExpenseService;
        this.propertyService = propertyService;
    }

    @PostMapping("/{address}")
    public RenovationExpense addRenovationExpense(@PathVariable String address,
                                                  @RequestBody RenovationExpense renovationExpense) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            renovationExpense.setProperty(property);
            property.addRenovationExpense(renovationExpense);
            propertyService.saveProperty(property);
            return renovationExpenseService.saveRenovationExpense(renovationExpense);
        }
        return null; // Handle error if property not found
    }

    @GetMapping("/{address}")
    public List<RenovationExpense> getRenovationExpensesByAddress(@PathVariable String address) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            return property.getExpenses();
        }
        return Collections.emptyList(); // Handle error if property not found
    }

    @GetMapping("/{address}/{id}")
    public Optional<RenovationExpense> getRenovationExpenseById(@PathVariable String address,
                                                                @PathVariable Long id) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            RenovationExpense renovationExpense = renovationExpenseService.getRenovationExpenseById(id);
            return Optional.ofNullable(renovationExpense);
        }
        return Optional.empty(); // Handle error if property not found
    }

    @PutMapping("/{address}/{id}")
    public RenovationExpense updateRenovationExpense(@PathVariable String address,
                                                     @PathVariable Long id, @RequestBody RenovationExpense
                                                                 updatedRenovationExpense) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            Optional<RenovationExpense> existingRenovationExpense =
                    Optional.ofNullable(renovationExpenseService.getRenovationExpenseById(id));
            if (existingRenovationExpense.isPresent()) {
                RenovationExpense renovationExpense = existingRenovationExpense.get();
                // Update the necessary fields of the renovationExpense with the updatedRenovationExpense values
                renovationExpense.setTypeOfExpense(updatedRenovationExpense.getTypeOfExpense());
                renovationExpense.setAmount(updatedRenovationExpense.getAmount());
                renovationExpense.setDateOfPurchase(updatedRenovationExpense.getDateOfPurchase());

                return renovationExpenseService.saveRenovationExpense(renovationExpense);
            }
        }
        return null; // Handle error if property or renovationExpense not found
    }

    @DeleteMapping("/{address}/{id}")
    public void deleteRenovationExpense(@PathVariable String address, @PathVariable Long id) {
        Property property = propertyService.getPropertyByAddress(address);
        if (property != null) {
            renovationExpenseService.deleteRenovationExpense(id);
        }
    }
}