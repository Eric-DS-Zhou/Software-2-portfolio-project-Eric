import components.finance.HouseholdExpenseTracker;
import components.finance.HouseholdExpenseTracker1;

/**
 * A demo to use `HouseholdExpenseTracker` component.
 */
public final class ExpenseDemo {

    /**
     * Private constructor.
     */
    private ExpenseDemo() {
    }

    /**
     * Demo for tracking my expenses.
     *
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        HouseholdExpenseTracker tracker = new HouseholdExpenseTracker1();

        tracker.add(202604, "food", 12.5, "Lunch");
        tracker.add(202604, "transport", 2.5, "Bus");
        tracker.add(202604, "food", 8.0, "Dinner");
        tracker.add(202604, "shopping", 20.0, "Snacks");
        tracker.add(202604, "school", 15.0, "Webassign access");
        tracker.add(202604, "entertainment", 10.0, "Game");

        System.out.println("All expenses:");
        System.out.println(tracker);

        System.out.println("Total spent: $" + tracker.totalSpent());
        System.out.println("Food total: $" + tracker.totalForCategory("food"));
        System.out.println("April total: $" + tracker.monthlyTotal(202604));

        System.out.println("Remove one record...");
        tracker.remove(1);

        System.out.println("After removal:");
        System.out.println(tracker);
        System.out.println("Updated total spent: $" + tracker.totalSpent());
    }
}
