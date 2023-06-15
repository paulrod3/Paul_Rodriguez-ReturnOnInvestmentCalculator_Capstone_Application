package ROICalculatorCapstone.models.controllers;

import ROICalculatorCapstone.models.FinancialDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<FinancialDetail> getFinancialDetail(@PathVariable String address) {
        FinancialDetail financialDetail = financialDetailService.getFinancialDetailByAddress(address);
        if (financialDetail != null) {
            return ResponseEntity.ok(financialDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FinancialDetail> createFinancialDetail(@RequestBody FinancialDetail financialDetail) {
        FinancialDetail createdFinancialDetail = financialDetailService.saveFinancialDetail(financialDetail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFinancialDetail);
    }

    @PutMapping("/{address}")
    public ResponseEntity<FinancialDetail> updateFinancialDetail(
            @PathVariable String address, @RequestBody FinancialDetail financialDetail) {
        FinancialDetail updatedFinancialDetail = financialDetailService.updateFinancialDetail(financialDetail);
        if (updatedFinancialDetail != null) {
            return ResponseEntity.ok(updatedFinancialDetail);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{address}")
    public ResponseEntity<Void> deleteFinancialDetail(@PathVariable String address) {
        boolean deleted = financialDetailService.deleteFinancialDetail(address);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}