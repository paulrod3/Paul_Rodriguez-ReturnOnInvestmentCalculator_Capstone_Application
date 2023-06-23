package ROICalculatorCapstone.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class FinancialDetail {
    @Id
    private String address;
    private double purchasePrice;
    private double expectedRehabCosts;
    private double interestRate;
    private int anticipatedLengthOfProject;
    private float loanAmount;
    private double monthlyPropertyTaxes;
    private double monthlyInsurance;
    private double monthlyUtilityBills;
    private double otherMonthlyExpenses;
    private double costsOfSale;
    private double afterRepairValue;

    @OneToOne

    @JoinColumn(name = "property_address")
    private Property property;
    public FinancialDetail() {
        // Default constructor for Hibernate
    }

    public FinancialDetail(String address, double purchasePrice, double expectedRehabCosts,
                           double interestRate, int anticipatedLengthOfProject, float loanAmount,
                           double monthlyPropertyTaxes, double monthlyInsurance, double monthlyUtilityBills,
                           double otherMonthlyExpenses, double costsOfSale, double afterRepairValue,
                           Property property) {
        this.address = address;
        this.purchasePrice = purchasePrice;
        this.expectedRehabCosts = expectedRehabCosts;
        this.interestRate = interestRate;
        this.anticipatedLengthOfProject = anticipatedLengthOfProject;
        this.loanAmount = loanAmount;
        this.monthlyPropertyTaxes = monthlyPropertyTaxes;
        this.monthlyInsurance = monthlyInsurance;
        this.monthlyUtilityBills = monthlyUtilityBills;
        this.otherMonthlyExpenses = otherMonthlyExpenses;
        this.costsOfSale = costsOfSale;
        this.afterRepairValue = afterRepairValue;
        this.property = property;
    }

    // Getters and setters


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getExpectedRehabCosts() {
        return expectedRehabCosts;
    }

    public void setExpectedRehabCosts(double expectedRehabCosts) {
        this.expectedRehabCosts = expectedRehabCosts;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getAnticipatedLengthOfProject() {
        return anticipatedLengthOfProject;
    }

    public void setAnticipatedLengthOfProject(int anticipatedLengthOfProject) {
        this.anticipatedLengthOfProject = anticipatedLengthOfProject;
    }

    public float getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(float loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getMonthlyPropertyTaxes() {
        return monthlyPropertyTaxes;
    }

    public void setMonthlyPropertyTaxes(double monthlyPropertyTaxes) {
        this.monthlyPropertyTaxes = monthlyPropertyTaxes;
    }

    public double getMonthlyInsurance() {
        return monthlyInsurance;
    }

    public void setMonthlyInsurance(double monthlyInsurance) {
        this.monthlyInsurance = monthlyInsurance;
    }

    public double getMonthlyUtilityBills() {
        return monthlyUtilityBills;
    }

    public void setMonthlyUtilityBills(double monthlyUtilityBills) {
        this.monthlyUtilityBills = monthlyUtilityBills;
    }

    public double getOtherMonthlyExpenses() {
        return otherMonthlyExpenses;
    }

    public void setOtherMonthlyExpenses(double otherMonthlyExpenses) {
        this.otherMonthlyExpenses = otherMonthlyExpenses;
    }

    public double getCostsOfSale() {
        return costsOfSale;
    }

    public void setCostsOfSale(double costsOfSale) {
        this.costsOfSale = costsOfSale;
    }

    public double getAfterRepairValue() {
        return afterRepairValue;
    }

    public void setAfterRepairValue(double afterRepairValue) {
        this.afterRepairValue = afterRepairValue;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}