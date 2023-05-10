package ROICalculatorCapstone;

import java.util.HashMap;
import java.util.Scanner;

public class MainCapstone {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create HashMap to store investment details
        HashMap<String, InvestmentOpportunity> investmentOpportunities = new HashMap<String, InvestmentOpportunity>();

        // Loop to input investment details
        while (true) {
            System.out.print("Enter address (or 'exit' to quit): ");
            String inputAddress = scanner.nextLine();

            if (inputAddress.equals("exit")) {
                break;
            }

            InvestmentOpportunity investmentOpportunity = investmentOpportunities.get(inputAddress);

            if (investmentOpportunity != null) {
                // Investment opportunity already exists, update details
                System.out.println("Investment opportunity already exists, updating details...");
                investmentOpportunity.updateDetails(scanner);

                System.out.println("Investment opportunity details updated successfully!");
            } else {
                // Investment opportunity does not exist, add details
                System.out.println("Investment opportunity does not exist, adding details...");
                InvestmentOpportunity newInvestmentOpportunity = new InvestmentOpportunity(inputAddress, scanner);
                investmentOpportunities.put(inputAddress, newInvestmentOpportunity);

                System.out.println("Investment opportunity details added successfully!");
            }
        }

        // Loop to output projected total return and renovation expenses for each investment opportunity
        for (InvestmentOpportunity investmentOpportunity : investmentOpportunities.values()) {
            investmentOpportunity.calculateAndOutputProjectedTotalReturn();
            investmentOpportunity.calculateAndOutputRenovationExpenses();
        }
    }


}
