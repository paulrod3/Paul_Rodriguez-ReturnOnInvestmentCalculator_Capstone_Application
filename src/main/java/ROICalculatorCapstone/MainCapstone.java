package ROICalculatorCapstone;

import ROICalculatorCapstone.models.FinancialDetail;
import ROICalculatorCapstone.models.Property;
import ROICalculatorCapstone.models.RenovationExpense;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class MainCapstone {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        // Create the Hibernate SessionFactory
        sessionFactory = new Configuration().configure().buildSessionFactory();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("ROI Calculator/Expense Tracker");
            System.out.println("1 - Display property details");
            System.out.println("2 - Add a new property");
            System.out.println("3 - Add/update Financial details");
            System.out.println("4 - Add expense");
            System.out.println("0 - Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    displayPropertyDetails(scanner);
                    break;
                case 2:
                    addNewProperty(scanner);
                    break;
                case 3:
                    addOrUpdateFinancialDetails(scanner);
                    break;
                case 4:
                    addExpense(scanner);
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        } while (choice != 0);

        scanner.close();

        // Close the Hibernate SessionFactory
        sessionFactory.close();
    }

    private static void displayPropertyDetails(Scanner scanner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            List<Property> properties = session.createQuery("FROM Property",
                    Property.class).getResultList();

            if (properties.isEmpty()) {
                System.out.println("No properties found. Please add a new property.");
                return;
            }

            System.out.println("Select a property to view details:");
            for (int i = 0; i < properties.size(); i++) {
                Property property = properties.get(i);
                System.out.println((i + 1) + ". " + property.getAddress());
            }

            System.out.print("Enter the property number: ");
            int propertyNumber = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            if (propertyNumber < 1 || propertyNumber > properties.size()) {
                System.out.println("Invalid property number.");
                return;
            }

            Property property = properties.get(propertyNumber - 1);

            System.out.println("Property Details:");
            System.out.println("Address: " + property.getAddress());
            System.out.println("Property Type: " + property.getPropertyType());
            System.out.println("Sqft: " + property.getSqft());
            System.out.println("Number of Bedrooms: " + property.getNumberOfBedrooms());
            System.out.println("Number of Bathrooms: " + property.getNumberOfBathrooms());

            FinancialDetail financialDetail = property.getFinancialDetail();
            if (financialDetail != null) {
                System.out.println("Financial Details:");
                System.out.println("Purchase Price: " + financialDetail.getPurchasePrice());
                System.out.println("Expected Rehab Costs: " +
                        financialDetail.getExpectedRehabCosts());
                System.out.println("Interest Rate: " + financialDetail.getInterestRate());
                System.out.println("Anticipated Length of Project: " +
                        financialDetail.getAnticipatedLengthOfProject());
                System.out.println("Loan Amount: " + financialDetail.getLoanAmount());
                System.out.println("Monthly Property Taxes: " +
                        financialDetail.getMonthlyPropertyTaxes());
                System.out.println("Monthly Insurance: " +
                        financialDetail.getMonthlyInsurance());
                System.out.println("Monthly Utility Bills: " +
                        financialDetail.getMonthlyUtilityBills());
                System.out.println("Other Monthly Expenses: " +
                        financialDetail.getOtherMonthlyExpenses());
                System.out.println("Costs of Sale: " + financialDetail.getCostsOfSale());
                System.out.println("After Repair Value: " +
                        financialDetail.getAfterRepairValue());
                double totalCost = financialDetail.getExpectedRehabCosts()
                        + (financialDetail.getMonthlyPropertyTaxes() +
                        financialDetail.getMonthlyInsurance() +
                        financialDetail.getMonthlyUtilityBills() +
                        financialDetail.getOtherMonthlyExpenses()) * financialDetail.getAnticipatedLengthOfProject();

                double currentCosts = calculateCurrentCosts(property.getExpenses());

                double anticipatedProfit = financialDetail.getAfterRepairValue() - totalCost;

                System.out.println("Total Cost: " + totalCost);
                System.out.println("Current Costs: " + currentCosts);
                System.out.println("Anticipated Profit: " + anticipatedProfit);
            } else {
                System.out.println("No financial details found for the property.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void addNewProperty(Scanner scanner) {
        System.out.println("Adding a new property");
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter property type: ");
        String propertyType = scanner.nextLine();
        System.out.print("Enter sqft: ");
        int sqft = scanner.nextInt();
        System.out.print("Enter number of bedrooms: ");
        int numberOfBedrooms = scanner.nextInt();
        System.out.print("Enter number of bathrooms: ");
        double numberOfBathrooms = scanner.nextDouble();
        scanner.nextLine(); // consume the newline character

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Property property = new Property(address, propertyType, sqft,
                    numberOfBedrooms, numberOfBathrooms);
            session.save(property);
            transaction.commit();
            System.out.println("New property added successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void addOrUpdateFinancialDetails(Scanner scanner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            List<Property> properties = session.createQuery("FROM Property",
                    Property.class).getResultList();

            if (properties.isEmpty()) {
                System.out.println("No properties found. Please add a new property.");
                return;
            }

            System.out.println("Select a property to add/update financial details:");
            for (int i = 0; i < properties.size(); i++) {
                Property property = properties.get(i);
                System.out.println((i + 1) + ". " + property.getAddress());
            }

            System.out.print("Enter the property number: ");
            int propertyNumber = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            if (propertyNumber < 1 || propertyNumber > properties.size()) {
                System.out.println("Invalid property number.");
                return;
            }

            Property property = properties.get(propertyNumber - 1);
            FinancialDetail financialDetail = property.getFinancialDetail();

            if (financialDetail == null) {
                financialDetail = new FinancialDetail();
                property.setFinancialDetail(financialDetail);
            }

            System.out.println("Adding/Updating Financial Details");
            System.out.print("Enter purchase price: ");
            double purchasePrice = scanner.nextDouble();
            System.out.print("Enter expected rehab costs: ");
            double expectedRehabCosts = scanner.nextDouble();
            System.out.print("Enter interest rate: ");
            double interestRate = scanner.nextDouble();
            System.out.print("Enter anticipated length of project: ");
            int anticipatedLengthOfProject = scanner.nextInt();
            System.out.print("Enter loan amount: ");
            float loanAmount = scanner.nextFloat();
            System.out.print("Enter monthly property taxes: ");
            double monthlyPropertyTaxes = scanner.nextDouble();
            System.out.print("Enter monthly insurance: ");
            double monthlyInsurance = scanner.nextDouble();
            System.out.print("Enter monthly utility bills: ");
            double monthlyUtilityBills = scanner.nextDouble();
            System.out.print("Enter other monthly expenses: ");
            double otherMonthlyExpenses = scanner.nextDouble();
            System.out.print("Enter costs of sale: ");
            double costsOfSale = scanner.nextDouble();
            System.out.print("Enter after repair value: ");
            double afterRepairValue = scanner.nextDouble();
            scanner.nextLine(); // consume the newline character

            financialDetail.setPurchasePrice(purchasePrice);
            financialDetail.setExpectedRehabCosts(expectedRehabCosts);
            financialDetail.setInterestRate(interestRate);
            financialDetail.setAnticipatedLengthOfProject(anticipatedLengthOfProject);
            financialDetail.setLoanAmount(loanAmount);
            financialDetail.setMonthlyPropertyTaxes(monthlyPropertyTaxes);
            financialDetail.setMonthlyInsurance(monthlyInsurance);
            financialDetail.setMonthlyUtilityBills(monthlyUtilityBills);
            financialDetail.setOtherMonthlyExpenses(otherMonthlyExpenses);
            financialDetail.setCostsOfSale(costsOfSale);
            financialDetail.setAfterRepairValue(afterRepairValue);

            session.saveOrUpdate(property);
            transaction.commit();

            System.out.println("Financial details added/updated successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void addExpense(Scanner scanner) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            List<Property> properties = session.createQuery("FROM Property",
                    Property.class).getResultList();

            if (properties.isEmpty()) {
                System.out.println("No properties found. Please add a new property.");
                return;
            }

            System.out.println("Select a property to add an expense:");
            for (int i = 0; i < properties.size(); i++) {
                Property property = properties.get(i);
                System.out.println((i + 1) + ". " + property.getAddress());
            }

            System.out.print("Enter the property number: ");
            int propertyNumber = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            if (propertyNumber < 1 || propertyNumber > properties.size()) {
                System.out.println("Invalid property number.");
                return;
            }

            Property property = properties.get(propertyNumber - 1);

            System.out.println("Adding Expense");

            System.out.print("Enter type of expense: ");
            String typeOfExpense = scanner.nextLine();
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // consume the newline character
            System.out.print("Enter date of purchase: ");
            String dateOfPurchase = scanner.nextLine();

            RenovationExpense expense = new RenovationExpense(typeOfExpense, amount, dateOfPurchase);
            property.getExpenses().add(expense);

            session.saveOrUpdate(property);
            transaction.commit();

            System.out.println("Expense added successfully.");
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    private static double calculateCurrentCosts(List<RenovationExpense> expenses) {
        double currentCosts = 0.0;

        if (expenses != null && !expenses.isEmpty()) {
            for (RenovationExpense expense : expenses) {
                currentCosts += expense.getAmount();
            }
        }

        return currentCosts;
    }
}