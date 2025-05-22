import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class ExpenseTracker {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;
        List<Expense> expenses = new ArrayList<>();
        String dateString;
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        do {
            System.out.println("----------MENU----------");
            System.out.println("1. Enter Expenses");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Total Spent");
            System.out.println("4. View Totals by Category");
            System.out.println("5. Save Expenses to File");
            System.out.println("6. Exit Program");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                System.out.println("Please enter the expense amount.");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("Please enter the category of the expense.");
                String category = scanner.nextLine();

                System.out.println("Please enter the date in the following format: YYYY-MM-DD");
                dateString = scanner.nextLine();
                try{
                    date = LocalDate.parse(dateString, formatter);
                    Expense newExpense = new Expense(amount, category, date);
                    expenses.add(newExpense);

                    System.out.println("Expense added successfully!\n");
                } catch (DateTimeException e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD");
                }
                break;

                case 2:
                for (Expense e : expenses){
                    System.out.println(e);
                }
                break;

                case 3:
                double total = 0;
                for (Expense e : expenses){
                    total += e.getAmount();
                }
                System.out.printf("Total Spent: $%.2f\n\n", total);
                break;

                case 4:
                if (expenses.isEmpty()){
                    System.out.println("No Expenses recorded.\n");
                    break;
                }
                Map<String, Double> categoryTotals = new HashMap<>();
                for (Expense e : expenses){
                    String cat = e.getCategory();
                    double amt = e.getAmount();
                    categoryTotals.put(cat, categoryTotals.getOrDefault(cat,0.0)+ amt);
                }

                System.out.println("Total by category:");
                for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
                    System.out.printf("%s: $%.2f\n", entry.getKey(), entry.getValue());
                }
                System.out.println();
                break;

                case 5:
                File file = new File("expenses.csv");
                try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                    for (Expense e : expenses) {
                        writer.printf("%.2f,%s,%s\n", e.getAmount(), e.getCategory(), e.getDate().toString());
                    }
                    System.out.println("Expenses saved to expenses.csv\n");
                } catch (IOException e) {
                    System.out.println("Error writing to file: " + e.getMessage());
                }
                break;

                case 6:
                System.out.println("Goodbye.");
                break;

            }

        } while (choice != 6);

        scanner.close();

    }
}
