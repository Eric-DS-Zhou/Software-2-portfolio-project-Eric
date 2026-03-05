import java.util.List;
import java.util.Map;

/**
 * Enhanced interface for {@code HouseholdExpenseTracker}.
 */
public interface HouseholdExpenseTracker extends HouseholdExpenseTrackerKernel {

    /**
     * Returns the total amount spent across all record in the tracker.
     *
     * @return total amount spent
     * @ensure totalSpent = sum of expense.amount()
     */
    double totalSpent();

    /**
     * Return the total amount spent during the specified month.
     *
     * @param yyyyMM
     *          the specified month in format yyyyMM (e.g. 202603)
     * @return the total amount spent in {@code yyyyMM}
     * @requires yyyyMM >= 100000
     * @requires (yyyyMM % 100) >= 1 and (yyyyMM % 100) <= 12
     * @ensures monthlyTotal = sum of all expense.amount() in {@code yyyyMM}
     */
    double monthlyTotal(int yyyyMM);

    /**
     * Return the total amount spent during the specified year.
     *
     * @param yyyy
     *          the specified year (e.g. 2026)
     * @return the total spent in {@code yyyy}
     * @requires yyyy >= 1
     * @ensures yearTotal = sum of all expense.amount() in {@code yyyy}
     */
    double yearlyTotal(int yyyy);

    /**
     * Return the total amount spent for specifed categories.
     *
     * @param category
     *          the category name (e.g. food)
     * @return the amount spent for {@code category}
     * @requires category is not empty
     * @ensures totalForCategory = sum of expense.amount() in {@code category}
     */
    double totalForCategory(String category);

    /**
     * Return all expense records in the specified month.
     *
     * @param yyyyMM
     *          the specified month in format YYYYMM (e.g. 202603)
     * @return a list of expenses recorded for that month, in the same relative
     *         order
     * @requires yyyyMM >= 100000
     * @requires (yyyyMM % 100) >= 1 and (yyyyMM % 100) <= 12
     * @ensures expensesInMonth contains all records expense in that
     *          month, in the same relative order
     */
    List<HouseholdExpense> expensesInMonth(int yyyyMM);

    /**
     * Return all expense records in the specified year.
     *
     * @param yyyy
     *            the specified year (e.g. 2026)
     * @return a list of expenses recorded for that year, in the same relative
     *         order
     * @requires yyyy >= 1
     * @ensures expensesInYear contains all records expense in that
     *          year, in the same relative order
     */
    List<HouseholdExpense> expensesInYear(int yyyy);

    /**
     * Return the total expense for each category for the specified month.
     *
     * @param yyyyMM
     *            the specified month in format YYYYMM (e.g. 202603)
     * @return the map that contains total amount for each category in that
     *         month
     * @requires yyyyMM >= 100000
     * @requires (yyyyMM % 100) >= 1 and (yyyyMM % 100) <= 12
     */
    Map<String, Double> monthlyCategoryBreakdown(int yyyyMM);

    /**
     * Return the total expense for each category for the specified year.
     *
     * @param yyyy
     *          the specified year (e.g. 2026)
     * @return the map that contains total amount for each category in that
     *         year
     */
    Map<String, Double> yearlyCategoryBreakdown(int yyyy);

}
