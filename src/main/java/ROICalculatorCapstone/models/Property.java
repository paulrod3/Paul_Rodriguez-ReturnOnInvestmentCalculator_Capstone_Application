package ROICalculatorCapstone.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Property {
    @Id
    private String address;
    private String propertyType;
    private int sqft;
    private int numberOfBedrooms;
    private double numberOfBathrooms;

    @OneToOne(cascade = CascadeType.ALL)
    private FinancialDetail financialDetail;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RenovationExpense> expenses;

    public Property() {
        // Default constructor for Hibernate
        expenses = new ArrayList<>();
    }

    public Property(String address, String propertyType, int sqft, int numberOfBedrooms, double numberOfBathrooms) {
        this.address = address;
        this.propertyType = propertyType;
        this.sqft = sqft;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        expenses = new ArrayList<>();
    }

    // Getters and setters

    public void addRenovationExpense(RenovationExpense renovationExpense) {
        expenses.add(renovationExpense);
        renovationExpense.setProperty(this);
    }

    public void removeRenovationExpense(RenovationExpense renovationExpense) {
        expenses.remove(renovationExpense);
        renovationExpense.setProperty(null);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public int getSqft() {
        return sqft;
    }

    public void setSqft(int sqft) {
        this.sqft = sqft;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public double getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(double numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public FinancialDetail getFinancialDetail() {
        return financialDetail;
    }

    public void setFinancialDetail(FinancialDetail financialDetail) {
        this.financialDetail = financialDetail;
    }

    public List<RenovationExpense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<RenovationExpense> expenses) {
        this.expenses = expenses;
    }
}

