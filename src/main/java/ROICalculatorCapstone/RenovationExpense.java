package ROICalculatorCapstone;

public class RenovationExpense {
    private String description;
    private double amount;

    public RenovationExpense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return this.description;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
