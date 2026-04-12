package components.finance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Junit tests for secondary methods of {@code HouseholdExpenseTracker}.
 */
public class HouseholdExpenseTrackerTest {

    /**
     * Create a new empty tracker.
     *
     * @return the new empty tracker
     */
    private HouseholdExpenseTracker constructorTest() {
        return new HouseholdExpenseTracker1();
    }

    /**
     * Create a tracker with some given records.
     *
     * @return tracker containing those sample records
     */
    private HouseholdExpenseTracker createSampleTracker() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.add(202601, "food", 10.0, "breakfast");
        t.add(202601, "food", 50.0, "dinner");
        t.add(202601, "transport", 5.0, "bus");
        t.add(202602, "food", 20.0, "dinner");
        t.add(202602, "rent", 1000, "monthlyRent");
        t.add(202701, "food", 15.0, "lunch");

        return t;
    }

    @Test
    public void testTotalSpentEmpty() {
        HouseholdExpenseTracker t = this.constructorTest();

        assertEquals(0.0, t.totalSpent(), 0.0001);
        assertEquals(0, t.size());
    }

    @Test
    public void testTotalSpentNonEmpty() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        assertEquals(1100.0, t.totalSpent(), 0.0001);
        assertEquals(6, t.size());
    }

    @Test
    public void testMonthlyTotalZero() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        assertEquals(0.0, t.monthlyTotal(202603), 0.0001);
        assertEquals(6, t.size());
    }

    @Test
    public void testMonthlyTotalNonZero() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        assertEquals(65.0, t.monthlyTotal(202601), 0.0001);
        assertEquals(6, t.size());
    }

    @Test
    public void testYearlyTotalZero() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        assertEquals(0.0, t.yearlyTotal(2025), 0.0001);
        assertEquals(6, t.size());
    }

    @Test
    public void testYearlyTotalNonZero() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        assertEquals(1085.0, t.yearlyTotal(2026), 0.0001);
        assertEquals(6, t.size());
    }

    @Test
    public void testTotalForCategorylZero() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        assertEquals(0.0, t.totalForCategory("utilties"), 0.0001);
        assertEquals(6, t.size());
    }

    @Test
    public void testTotalForCategorylNonZero() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        assertEquals(95.0, t.totalForCategory("food"), 0.0001);
        assertEquals(6, t.size());
    }

    @Test
    public void testExpensesInMonthEmpty() {
        HouseholdExpenseTracker t = this.createSampleTracker();
        List<HouseholdExpense> result = t.expensesInMonth(202603);

        assertEquals(0, result.size());
        assertEquals(6, t.size());
    }

    @Test
    public void testExpensesInMonthJanuary() {
        HouseholdExpenseTracker t = this.createSampleTracker();
        List<HouseholdExpense> result = t.expensesInMonth(202601);

        assertEquals(3, result.size());
        assertEquals(new HouseholdExpense(202601, "food", 10.0, "breakfast"),
                result.get(0));
        assertEquals(new HouseholdExpense(202601, "food", 50.0, "dinner"),
                result.get(1));
        assertEquals(new HouseholdExpense(202601, "transport", 5.0, "bus"),
                result.get(2));
        assertEquals(6, t.size());
    }

    @Test
    public void testExpensesInYearEmpty() {
        HouseholdExpenseTracker t = this.createSampleTracker();
        List<HouseholdExpense> result = t.expensesInYear(2025);

        assertEquals(0, result.size());
        assertEquals(6, t.size());
    }

    @Test
    public void testExpensesInYear2026() {
        HouseholdExpenseTracker t = this.createSampleTracker();
        List<HouseholdExpense> result = t.expensesInYear(2026);

        assertEquals(new HouseholdExpense(202601, "food", 10.0, "breakfast"),
                result.get(0));
        assertEquals(new HouseholdExpense(202601, "food", 50.0, "dinner"),
                result.get(1));
        assertEquals(new HouseholdExpense(202601, "transport", 5.0, "bus"),
                result.get(2));
        assertEquals(new HouseholdExpense(202602, "food", 20.0, "dinner"),
                result.get(3));
        assertEquals(new HouseholdExpense(202602, "rent", 1000, "monthlyRent"),
                result.get(4));
        assertEquals(5, result.size());
        assertEquals(6, t.size());
    }

    @Test
    public void testMonthlyCategoryBreakdownEmpty() {
        HouseholdExpenseTracker t = this.createSampleTracker();
        Map<String, Double> result = t.monthlyCategoryBreakdown(202603);

        assertEquals(0, result.size());
        assertEquals(6, t.size());
    }

    @Test
    public void testMonthlyCategoryBreakdownJanuary() {
        HouseholdExpenseTracker t = this.createSampleTracker();
        Map<String, Double> result = t.monthlyCategoryBreakdown(202601);
        Map<String, Double> expected = new LinkedHashMap<String, Double>();

        expected.put("food", 60.0);
        expected.put("transport", 5.0);

        assertEquals(expected, result);
        assertEquals(6, t.size());
    }

    @Test
    public void testYearlyCategoryBreakdownEmpty() {
        HouseholdExpenseTracker t = this.createSampleTracker();
        Map<String, Double> result = t.yearlyCategoryBreakdown(2025);

        assertEquals(0, result.size());
        assertEquals(6, t.size());
    }

    @Test
    public void testYearlyCategoryBreakdown2026() {
        HouseholdExpenseTracker t = this.createSampleTracker();
        Map<String, Double> result = t.yearlyCategoryBreakdown(2026);
        Map<String, Double> expected = new LinkedHashMap<String, Double>();

        expected.put("food", 80.0);
        expected.put("transport", 5.0);
        expected.put("rent", 1000.0);

        assertEquals(expected, result);
        assertEquals(6, t.size());
    }

    @Test
    public void testMonthToMonthChangeJanuary() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        assertEquals(65.0, t.monthToMonthChange(202601), 0.0001);
        assertEquals(6, t.size());
    }

    @Test
    public void testMonthToMonthChangeFebruary() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        assertEquals(955.0, t.monthToMonthChange(202602), 0.0001);
        assertEquals(6, t.size());
    }

    @Test
    public void testYearToYearChange2027() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        assertEquals(-1070.0, t.yearToYearChange(2027), 0.0001);
        assertEquals(6, t.size());
    }

    @Test
    public void testRemoveMonthEmpty() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        t.removeMonth(202603);

        assertEquals(6, t.size());
        assertEquals(1100.0, t.totalSpent(), 0.0001);
    }

    @Test
    public void testRemoveMonthWithMatchJanuary() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        t.removeMonth(202601);

        assertEquals(3, t.size());
        assertEquals(1035.0, t.totalSpent(), 0.0001);
        assertEquals(0.0, t.monthlyTotal(202601), 0.0001);
    }

    @Test
    public void testRemoveCategoryEmpty() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        t.removeCategory("utilities");

        assertEquals(6, t.size());
        assertEquals(1100.0, t.totalSpent(), 0.0001);
    }

    @Test
    public void testRemoveCategoryFood() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        t.removeCategory("food");

        assertEquals(2, t.size());
        assertEquals(1005.0, t.totalSpent(), 0.0001);
        assertEquals(0.0, t.totalForCategory("food"), 0.0001);
    }

    @Test
    public void testRemoveCategoryRent() {
        HouseholdExpenseTracker t = this.createSampleTracker();

        t.removeCategory("rent");

        assertEquals(5, t.size());
        assertEquals(100.0, t.totalSpent(), 0.0001);
        assertEquals(0.0, t.totalForCategory("rent"), 0.0001);
    }

    @Test
    public void testToStringEmpty() {
        HouseholdExpenseTracker t = this.constructorTest();

        assertEquals("<>", t.toString());
    }

    @Test
    public void testToStringNonEmpty() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.add(202601, "food", 10.0, "breakfast");
        t.add(202602, "rent", 1000.0, "monthlyRent");

        assertEquals(
                "<HouseholdExpense[date=202601, category=food, amount=10.0, "
                        + "note=breakfast], HouseholdExpense[date=202602, category=rent, "
                        + "amount=1000.0, note=monthlyRent]>",
                t.toString());
    }

    @Test
    public void testEqualsTrue() {
        HouseholdExpenseTracker t1 = this.createSampleTracker();
        HouseholdExpenseTracker t2 = this.createSampleTracker();

        boolean result = t1.equals(t2);
        assertEquals(true, result);
    }

    @Test
    public void testEqualsFalse() {
        HouseholdExpenseTracker t1 = this.createSampleTracker();
        HouseholdExpenseTracker t2 = this.createSampleTracker();

        t2.remove(0);

        boolean result = t1.equals(t2);
        assertEquals(false, result);
    }

    @Test
    public void testHashCodeTrue() {
        HouseholdExpenseTracker t1 = this.createSampleTracker();
        HouseholdExpenseTracker t2 = this.createSampleTracker();

        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    public void testHashCodeFalse() {
        HouseholdExpenseTracker t1 = this.createSampleTracker();
        HouseholdExpenseTracker t2 = this.createSampleTracker();
        t2.remove(0);

        assertNotEquals(t1.hashCode(), t2.hashCode());
    }
}
