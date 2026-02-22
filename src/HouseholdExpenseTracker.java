import java.util.ArrayList;
import java.util.List;
public class HouseholdExpenseTracker {

    /**
     * record the data
     */
    private static class Expense {
        String date;
        String category;
        double amount;
        String note;
        Expense(String date, String category, double amount, String note){
            this.date = date;
            this.category = category;
            this.amount = amount;
            this.note = note;
        }
        
        @Override
        public String toString() {
            return date + "|" + category + "| $" + amount + "|" + note;
        }
    }

    private final List<Expense> expenses;

    /**
     * constructor
     */
    public HouseholdExpenseTracker() {
        this.expenses = new ArrayList<>();
    }

    /**
     * add a new expense.
     * @param date
     * @param category
     * @param amount
     * @param note
     */
    public void add(String date, String category, double amount, String note) {
        String normalizedCategory = category.toLowerCase();
        this.expenses.add(new Expense(date, normalizedCategory, amount, note));
    }

    /**
     * remove an expense by index.
     * @param index
     * @return
     */
    public boolean remove(int index) {
        boolean result = false;
        if (index >= 0 && index < this.expenses.size()) {
            this.expenses.remove(index);
            result = true;
        }
        return result;
    }

    /**
     * total spent for all expenses.
     * @return
     */
    public double totalSpent() {
        double sum = 0.0;
        for (Expense e : this.expenses) {
            sum = sum + e.amount;
        }
        return sum;
    }

    /**
     * Total spent for one category
     * @param category
     * @return
     */
    public double totalForCategory(String category) {
        double sum = 0.0;
        String targetCategory = category.toLowerCase();

        for (Expense e : this.expenses) {
            if (e.category.equals(targetCategory)) {
                sum = sum + e.amount;
            }
        }
        return sum;

    }

    /**
     * return a list of expenses in the specifed month
     * @param yyyyMm
     * @return
     */
    public List<String> expensesInMonth(String yyyyMm) {
        List<String> result = new ArrayList<>();
        for (Expense e : this.expenses) {
            if(e.date.substring(0,6).equals(yyyyMm)){
                result.add(e.toString());
            }
        }
        return result;
    }

    /**
     * print all the expenses
     */
    public void printAll() {
        System.out.println("-----Expenses (" + this.expenses.size() + ")-----");
        for(int i = 0; i < this.expenses.size(); i++){
            System.out.println("[" + i + "]" + this.expenses.get(i));
        }
        System.out.println("---------------------");
    }

    //main method (client view)
    public static void main(String[] args) {
        HouseholdExpenseTracker tracker = new HouseholdExpenseTracker();

        tracker.add("20260208", "Food", 12.50, "Chipotle");
        tracker.add("20260210", "Transport", 2.50, "Bus");
        tracker.add("20260218", "Food", 5.00, "Breakfast");
        tracker.add("20260228", "Electricity", 50.75, "Electricity Fee for 202601");

        tracker.printAll();

        System.out.println("Total spent: $" + tracker.totalSpent());
        System.out.println("Total spent on Food: $" + tracker.totalForCategory("Food"));
        System.out.println("Remove index 1");
        tracker.remove(1);
        tracker.printAll();
        System.out.println("Total spent after remove");
        System.out.println("Total spent(after remove): $" + tracker.totalSpent());
        System.out.println("Expenses in 202602");
        for (String s : tracker.expensesInMonth("202602")){
            System.out.println(s);
        }

    }


}

    
