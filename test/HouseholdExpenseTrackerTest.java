import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Junit tests for secondary methods of {@code HouseholdExpenseTracker}.
*/
public class HouseholdExpenseTrackerTest {

    /**
     * Create a new empty tracker.
     * @return the new empty tracker
     */
    private HouseholdExpenseTracker constructorTest() {
        return new HouseholdExpenseTracker1();
    }

    /**
     * Create a tracker with some given records.
     * @return tracker containing those sample records
     */
    private HouseholdExpenseTracker createSampleTracker() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.add(202601, "food", 10.0, "breakfast");
        t.add(202601, "food", 50.0, "dinner");
        t.add(202601, "transport", 5.0, "bus");
        t.add(202601, "food", 20.0, "dinner");
        t.add(202601, "rent", 1000, "monthlyRent");
        t.add(202601, "food", 15.0, "lunch");

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
}
