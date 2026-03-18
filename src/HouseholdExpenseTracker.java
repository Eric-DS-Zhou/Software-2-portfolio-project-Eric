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
     * @ensures totalSpent = sum of expense.amount()
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
     * @ensures the map contains each categories for the specified month
     *          in the tracker
     */
    Map<String, Double> monthlyCategoryBreakdown(int yyyyMM);

    /**
     * Return the total expense for each category for the specified year.
     *
     * @param yyyy
     *          the specified year (e.g. 2026)
     * @return the map that contains total amount for each category in that
     *         year
     * @requires yyyy >=1
     * @ensures the map contains each categories for the specified year in the
     *          tracker
     */
    Map<String, Double> yearlyCategoryBreakdown(int yyyy);

    /**
     * Return the change in total spending from the previous month to the given
     * month.
     *
     * @param yyyyMM
     *            the specified month in format yyyyMM (e.g. 202603)
     * @return monthlyTotal(yyyyMM) - monthlyTotal(yyyyMM - 1)
     * @requires yyyyMM >= 100000
     * @requires (yyyyMM % 100) >= 1 and (yyyyMM % 100) <= 12
     * @ensures monthToMonthChanges = monthlyTotal(yyyyMM) - monthlyTotal(
     *          yyyyMM - 1)
     */
    double monthToMonthChange(int yyyyMM);

    /**
     * Return the change in total spending from the previous year to the given
     * year.
     * @param yyyy
     *          the specified year (e.g. 2026)
     * @return yearlyTotal(yyyy) - yearlyTotal(yyyy - 1)
     * @requires yyyy >= 2
     * @ensures yearToYearChange = yearlyTotal(yyyy) - yearlyTotal (yyyy - 1)
     */
    double yearToYearChange(int yyyy);

    /**
     * Removes all expense records whose date equlas{@code yyyyMM}.
     *
     * @param yyyyMM
     *            the specified month in format yyyyMM (e.g. 202603)
     * @requires yyyyMM >= 100000
     * @requires (yyyyMM % 100) >= 1 and (yyyyMM % 100) <= 12
     * @ensures this = #this with all records e.date() != {@code yyyyMM}
     * @updates this
     */
    void removeMonth(int yyyyMM);

    /**
     * Removes all expenses records in the specified category.
     * @param category
     *              the name of specified category
     * @requires category is not empty
     * @updates this
     * @ensures this = #this with all records e.category() != {@code category}
     */
    void removeCategory(String category);

}
