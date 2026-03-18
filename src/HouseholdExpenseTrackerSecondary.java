import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Layered implementations of secondary methods for {@code HouseholdExpenseTracker}.
 */
public abstract class HouseholdExpenseTrackerSecondary
                                            implements HouseholdExpenseTracker {
    /**
     * avoid checkstyle for magic number.
     */
    private static final int TWELVE = 12, HUNDRED = 100, THOUSAND = 1000,
                                HUNDREDTHOUSAND = 100000;

    /**
     * Report whether {@code yyyyMM} is a valid yearMonth.
     *
     * @param yyyyMM
     *            the year and month
     * @return turn iff {@code yyyyMM} is valid
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
     * @param map
     *          the aim map
     * @param category
     *          the specified category
     * @param amount
     *          the new amount to add
     */
    private static void addToCategory(Map<String, Double> map,
                                                String category, double amount) {
        assert category.length() > 0 : "Violation of: category is not empty";

        if (map.containsKey(category)) {
            double old = map.get(category);
            map.put(category, old + amount);
        } else {
            map.put(category, amount);
        }

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
}
