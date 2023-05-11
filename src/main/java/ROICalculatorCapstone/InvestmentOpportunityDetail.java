package ROICalculatorCapstone;

import java.util.ArrayList;
import java.util.Scanner;

public class InvestmentOpportunityDetail {

    private String address;
    private double purchasePrice;
    private double expectedRehabCost;
    private double interestRate;
    private ArrayList renovationExpense;
    private ArrayList<RenovationExpense> renovationExpenses = new ArrayList<RenovationExpense>();

    public InvestmentOpportunityDetail(String address, Scanner scanner) {
        this.address = address;

        System.out.print("Enter initial investment amount: ");
        this.purchasePrice = scanner.nextDouble();

        System.out.print("Enter Expected Rehab Cost amount: ");
        this.expectedRehabCost = scanner.nextDouble();

        System.out.print("Enter annual interest rate (as a decimal): ");
        this.interestRate = scanner.nextDouble();

        scanner.nextLine(); // consume newline character

        // Loop to input renovation expenses
        while (true) {
            System.out.print("Enter renovation expense (or 'done' to finish): ");
            String inputExpense = scanner.nextLine();

            if (inputExpense.equals("done")) {
                break;
            }

            System.out.print("Enter renovation expense amount: ");
            double expenseAmount = scanner.nextDouble();

            scanner.nextLine(); // consume newline character

            RenovationExpense renovationExpense = new RenovationExpense(inputExpense, expenseAmount);
            this.renovationExpenses.add(renovationExpense);
        }
    }

    public void updateDetails(Scanner scanner) {
        System.out.print("Enter initial investment amount: ");
        this.purchasePrice = scanner.nextDouble();

        System.out.print("Enter target investment amount: ");
        this.expectedRehabCost = scanner.nextDouble();

        System.out.print("Enter annual interest rate (as a decimal): ");
        this.interestRate = scanner.nextDouble();

        scanner.nextLine(); // consume newline character

        // Loop to update renovation expenses
        for (RenovationExpense renovationExpense : this.renovationExpenses) {
            System.out.print("Enter new amount for " + renovationExpense.getDescription() + ": ");
            double newAmount = scanner.nextDouble();
            renovationExpense.setAmount(newAmount);

            scanner.nextLine();

        }
    }

    public void displayDetails() {
        System.out.println("Address: " + this.address);
        System.out.println("Initial Investment: $" + this.purchasePrice);
        System.out.println("Target Investment: $" + this.expectedRehabCost);
        System.out.println("Annual Interest Rate: " + (this.interestRate * 100) + "%");

        System.out.println("Renovation Expenses:");
        for (RenovationExpense renovationExpense : this.renovationExpenses) {
            System.out.println(renovationExpense.getDescription() + ": $" + renovationExpense.getAmount());
        }
    }

    public void calculateAndOutputProjectedTotalReturn() {
        double totalReturn = this.purchasePrice;
        int years = 0;

        while (totalReturn < this.expectedRehabCost) {
            totalReturn += (totalReturn * this.interestRate);
            years++;
        }

        System.out.println("Projected Total Return for " + this.address + ": $" + totalReturn + " after " + years + " years.");
    }

    public void calculateAndOutputRenovationExpenses() {
        double totalRenovationExpenses = 0;

        for (RenovationExpense renovationExpense : this.renovationExpenses) {
            totalRenovationExpenses += renovationExpense.getAmount();
        }

        System.out.println("Total Renovation Expenses for " + this.address + ": $" + totalRenovationExpenses);
    }

}