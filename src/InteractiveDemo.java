import java.util.Scanner;

import components.finance.HouseholdExpenseTracker;
import components.finance.HouseholdExpenseTracker1;

/**
 * A demp to use `HouseholdExpenseTracker` component.
 */
public final class InteractiveDemo {

    /**
     * Private constructor.
     */
    private InteractiveDemo() {
    }

    /**
     * Magic Numbers.
     */
    private static final int THREE = 3, FOUR = 4, FIVE = 5;

    /**
     * Demo for interacting.
     *
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        HouseholdExpenseTracker tracker = new HouseholdExpenseTracker1();

        int choice = 0;

        while (choice != FIVE) {
            System.out.println();
            System.out.println("Expense Tracker Menu");
            System.out.println("1. Add an expense");
            System.out.println("2. Remove an expense");
            System.out.println("3. Print all expenses");
            System.out.println("4. Show summaries");
            System.out.println("5. Quit");
            System.out.print("Enter choice: ");
            choice = in.nextInt();

            if (choice == 1) {
                System.out.print(
                        "Enter year and month in yyyyMM format (example: 202604): ");
                int yyyyMM = in.nextInt();

                System.out.print("Enter category (example: food): ");
                String category = in.next();

                System.out.print("Enter amount in 0.0 format (example: 12.5): ");
                double amount = in.nextDouble();

                System.out.print("Enter note (example: lunch): ");
                String note = in.next();

                tracker.add(yyyyMM, category, amount, note);
                System.out.println("Expense added.");
            } else if (choice == 2) {
                int index = in.nextInt();
                if (index >= 0 && index < tracker.size()) {
                    System.out.println("Removed: " + tracker.remove(index));
                }
            } else if (choice == THREE) {
                System.out.println(tracker);
            } else if (choice == FOUR) {
                System.out.println("Total spent: $" + tracker.totalSpent());
            }
        }

        in.close();
    }
}
