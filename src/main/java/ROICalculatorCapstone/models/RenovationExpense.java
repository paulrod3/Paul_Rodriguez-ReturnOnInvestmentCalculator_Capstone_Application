package ROICalculatorCapstone.models;

import javax.persistence.*;

@Entity
public class RenovationExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String typeOfExpense;
    private double amount;
    private String dateOfPurchase;

    public RenovationExpense() {
        // Default constructor for Hibernate
    }

    public RenovationExpense(String typeOfExpense, double amount, String dateOfPurchase) {
        this.typeOfExpense = typeOfExpense;
        this.amount = amount;
        this.dateOfPurchase = dateOfPurchase;
    }

    @ManyToOne
    @JoinColumn(name = "property_address")
    private Property property;

    // Getters and setters
    public Property getProperty() {
        return property;
    }
    public void setProperty(Property property) {
        this.property = property;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTypeOfExpense() {
        return typeOfExpense;
    }

    public void setTypeOfExpense(String typeOfExpense) {
        this.typeOfExpense = typeOfExpense;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
