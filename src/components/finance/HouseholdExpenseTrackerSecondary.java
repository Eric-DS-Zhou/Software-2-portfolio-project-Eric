package components.finance;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Layered implementations of secondary methods for
 * {@code HouseholdExpenseTracker}.
 */
public abstract class HouseholdExpenseTrackerSecondary
        implements HouseholdExpenseTracker {
    /**
     * avoid checkstyle for magic number.
     */
    private static final int TWELVE = 12, HUNDRED = 100, THREEONE = 31,
            MIN_YEARMONTH_PERVIOUS = 100101, THOUSANDONE = 1001,
            THOUSAND = 1000, HUNDREDTHOUSAND = 100000;

    /**
     * Report whether {@code yyyyMM} is a valid yearMonth.
     *
     * @param yyyyMM
     *            the year and month
     * @return true iff {@code yyyyMM} is valid
     */
    private static boolean isValidYearMonth(int yyyyMM) {
        boolean result = false;
        int month = yyyyMM % HUNDRED;

        if (yyyyMM >= HUNDREDTHOUSAND && month >= 1 && month <= TWELVE) {
            result = true;
        }

        return result;
    }

    /**
     * Add amount to the category in map.
     *
     * @param map
     *            the aim map
     * @param category
     *            the specified category
     * @param amount
     *            the new amount to add
     */
    private static void addToCategory(Map<String, Double> map, String category,
            double amount) {
        assert category.length() > 0 : "Violation of: category is not empty";

        if (map.containsKey(category)) {
            double old = map.get(category);
            map.put(category, old + amount);
        } else {
            map.put(category, amount);
        }

    }

    /**
     * Return the previous month of {@code yyyyMM}.
     *
     * @param yyyyMM
     *            the specified month
     * @return the previous month of the specified month
     * @requires yyyyMM >= 100101
     * @requires (yyyyMM % 100) >= 1 and (yyyyMM % 100) <= 12
     *
     */
    private static int previousMonth(int yyyyMM) {
        assert yyyyMM >= MIN_YEARMONTH_PERVIOUS : "Violation of: yyyyMM >= 100101";
        assert isValidYearMonth(yyyyMM) : "Violation of: yyyyMM is valid";
        int year = yyyyMM / HUNDRED;
        int month = yyyyMM % HUNDRED;
        int result = 0;

        if (month == 1) {
            result = (year - 1) * HUNDRED + TWELVE;
        } else {
            result = year * HUNDRED + (month - 1);
        }

        return result;
    }

    @Override
    public final double totalSpent() {
        double total = 0;

        for (int i = 0; i < this.size(); i++) {
            HouseholdExpense e = this.entry(i);
            total = total + e.amount();
        }

        return total;
    }

    @Override
    public final double monthlyTotal(int yyyyMM) {
        assert isValidYearMonth(yyyyMM) : "Violation of: yyyyMM is valid";

        double total = 0;

        for (int i = 0; i < this.size(); i++) {
            HouseholdExpense e = this.entry(i);
            if (e.date() == yyyyMM) {
                total = total + e.amount();
            }
        }

        return total;
    }

    @Override
    public final double yearlyTotal(int yyyy) {
        assert yyyy >= THOUSAND : "Violation of: yyyy >= 1000";

        double total = 0;

        for (int i = 0; i < this.size(); i++) {
            HouseholdExpense e = this.entry(i);
            if (e.date() / HUNDRED == yyyy) {
                total = total + e.amount();
            }
        }

        return total;
    }

    @Override
    public final double totalForCategory(String category) {
        assert category.length() > 0 : "Violation of: category is not empty";

        double total = 0;

        for (int i = 0; i < this.size(); i++) {
            HouseholdExpense e = this.entry(i);
            if (e.category().equals(category)) {
                total = total + e.amount();
            }
        }

        return total;
    }

    @Override
    public final List<HouseholdExpense> expensesInMonth(int yyyyMM) {
        assert isValidYearMonth(yyyyMM) : "Violation of: yyyyMM is valid";

        List<HouseholdExpense> result = new ArrayList<HouseholdExpense>();

        for (int i = 0; i < this.size(); i++) {
            HouseholdExpense e = this.entry(i);
            if (e.date() == yyyyMM) {
                result.add(e);
            }
        }

        return result;
    }

    @Override
    public final List<HouseholdExpense> expensesInYear(int yyyy) {
        assert yyyy >= THOUSAND : "Violation of: yyyy >= 1000";

        List<HouseholdExpense> result = new ArrayList<HouseholdExpense>();

        for (int i = 0; i < this.size(); i++) {
            HouseholdExpense e = this.entry(i);
            if (e.date() / HUNDRED == yyyy) {
                result.add(e);
            }
        }

        return result;
    }

    @Override
    public final Map<String, Double> monthlyCategoryBreakdown(int yyyyMM) {
        assert isValidYearMonth(yyyyMM) : "Violation of: yyyyMM is valid";

        Map<String, Double> result = new LinkedHashMap<String, Double>();

        for (int i = 0; i < this.size(); i++) {
            HouseholdExpense e = this.entry(i);
            if (e.date() == yyyyMM) {
                addToCategory(result, e.category(), e.amount());
            }
        }

        return result;
    }

    @Override
    public final Map<String, Double> yearlyCategoryBreakdown(int yyyy) {
        assert yyyy >= THOUSAND : "Violation of: yyyy >= 1000";

        Map<String, Double> result = new LinkedHashMap<String, Double>();

        for (int i = 0; i < this.size(); i++) {
            HouseholdExpense e = this.entry(i);
            if (e.date() / HUNDRED == yyyy) {
                addToCategory(result, e.category(), e.amount());
            }
        }

        return result;
    }

    @Override
    public final double monthToMonthChange(int yyyyMM) {
        assert yyyyMM >= MIN_YEARMONTH_PERVIOUS : "Violation of: yyyyMM >= 100101";
        assert isValidYearMonth(yyyyMM) : "Violation of: yyyyMM is valid";

        double result = 0;
        int previous = previousMonth(yyyyMM);

        result = this.monthlyTotal(yyyyMM) - this.monthlyTotal(previous);

        return result;
    }

    @Override
    public final double yearToYearChange(int yyyy) {
        assert yyyy >= THOUSANDONE : "Violation of: yyyy >= 1001";

        double result = 0;

        result = this.yearlyTotal(yyyy) - this.yearlyTotal(yyyy - 1);

        return result;
    }

    @Override
    public final void removeMonth(int yyyyMM) {
        assert isValidYearMonth(yyyyMM) : "Violation of: yyyyMM is valid";

        for (int i = this.size() - 1; i >= 0; i--) {
            HouseholdExpense e = this.entry(i);
            if (e.date() == yyyyMM) {
                this.remove(i);
            }
        }
    }

    @Override
    public final void removeCategory(String category) {
        assert category.length() > 0 : "Violation of: category is not empty";

        for (int i = this.size() - 1; i >= 0; i--) {
            HouseholdExpense e = this.entry(i);
            if (e.category().equals(category)) {
                this.remove(i);
            }
        }
    }

    @Override
    public final String toString() {
        StringBuilder result = new StringBuilder();

        result.append("<");

        for (int i = 0; i < this.size(); i++) {
            if (i > 0) {
                result.append(",\n");
            }
            result.append(this.entry(i));
        }

        result.append(">");

        return result.toString();
    }

    @Override
    public final boolean equals(Object obj) {
        boolean result = false;

        if (obj instanceof HouseholdExpenseTracker) {
            HouseholdExpenseTracker other = (HouseholdExpenseTracker) obj;
            boolean same = false;

            if (this.size() == other.size()) {
                same = true;
            }

            if (same) {
                for (int i = 0; i < this.size(); i++) {
                    HouseholdExpense thisEntry = this.entry(i);
                    HouseholdExpense otherEntry = other.entry(i);
                    if (!thisEntry.equals(otherEntry)) {
                        same = false;
                    }
                }
            }

            result = same;
        }

        return result;
    }

    @Override
    public final int hashCode() {
        int result = 1;

        for (int i = 0; i < this.size(); i++) {
            HouseholdExpense e = this.entry(i);
            result = THREEONE * result + e.hashCode();
        }

        return result;
    }
}
