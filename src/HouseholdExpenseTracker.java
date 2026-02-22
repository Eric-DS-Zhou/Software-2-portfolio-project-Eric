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
        this.expenses.add(new Expense(date, category, amount, note));
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
        for (Expense e : this.expenses) {
            if (e.category.equals(category)) {
                sum = sum + e.amount;
            }
        }
        return sum;

    }





}

    
