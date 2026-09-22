package components.finance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit tests for kernel and standard methods of
 * {@code HouseholdExpenseTracker1}.
 */
public class HouseholdExpenseTracker1Test {

    /**
     * Returns a new empty tracker.
     *
     * @return a new empty tracker
     */
    private HouseholdExpenseTracker constructorTest() {
        return new HouseholdExpenseTracker1();
    }

    @Test
    public void testConstructor() {
        HouseholdExpenseTracker t = this.constructorTest();

        assertEquals(0, t.size());
        assertEquals("<>", t.toString());
    }

    @Test
    public void testAddToEmpty() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.add(202601, "food", 10.0, "breakfast");

        assertEquals(1, t.size());
        assertEquals(new HouseholdExpense(202601, "food", 10.0, "breakfast"),
                t.entry(0));
    }

    @Test
    public void testAddToNonEmpty() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.add(202601, "food", 10.0, "breakfast");
        t.add(202602, "rent", 1000.0, "monthlyRent");

        assertEquals(2, t.size());
        assertEquals(new HouseholdExpense(202601, "food", 10.0, "breakfast"),
                t.entry(0));
        assertEquals(
                new HouseholdExpense(202602, "rent", 1000.0, "monthlyRent"),
                t.entry(1));
    }

    @Test
    public void testEmptyAfterRemove() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.add(202601, "food", 10.0, "breakfast");
        HouseholdExpense removed = t.remove(0);

        assertEquals(new HouseholdExpense(202601, "food", 10.0, "breakfast"),
                removed);
        assertEquals(0, t.size());
        assertEquals("<>", t.toString());
    }

    @Test
    public void testRemoveFirst() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.add(202601, "food", 10.0, "breakfast");
        t.add(202602, "rent", 1000.0, "monthlyRent");
        t.add(202701, "food", 15.0, "lunch");

        HouseholdExpense removed = t.remove(0);

        assertEquals(new HouseholdExpense(202601, "food", 10.0, "breakfast"),
                removed);
        assertEquals(2, t.size());
        assertEquals(
                new HouseholdExpense(202602, "rent", 1000.0, "monthlyRent"),
                t.entry(0));
        assertEquals(new HouseholdExpense(202701, "food", 15.0, "lunch"),
                t.entry(1));
    }

    @Test
    public void testRemoveNonFirst() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.add(202601, "food", 10.0, "breakfast");
        t.add(202602, "rent", 1000.0, "monthlyRent");
        t.add(202701, "food", 15.0, "lunch");

        HouseholdExpense removed = t.remove(1);

        assertEquals(
                new HouseholdExpense(202602, "rent", 1000.0, "monthlyRent"),
                removed);
        assertEquals(2, t.size());
        assertEquals(new HouseholdExpense(202601, "food", 10.0, "breakfast"),
                t.entry(0));
        assertEquals(new HouseholdExpense(202701, "food", 15.0, "lunch"),
                t.entry(1));
    }

    @Test
    public void testSizeEmpty() {
        HouseholdExpenseTracker t = this.constructorTest();

        assertEquals(0, t.size());
    }

    @Test
    public void testSizeNonEmpty() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.add(202601, "food", 10.0, "breakfast");
        t.add(202602, "rent", 1000.0, "monthlyRent");
        t.add(202701, "food", 15.0, "lunch");

        assertEquals(3, t.size());
    }

    @Test
    public void testEntryFirst() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.add(202601, "food", 10.0, "breakfast");
        t.add(202602, "rent", 1000.0, "monthlyRent");

        assertEquals(new HouseholdExpense(202601, "food", 10.0, "breakfast"),
                t.entry(0));
        assertEquals(2, t.size());
    }

    @Test
    public void testEntryNonFirst() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.add(202601, "food", 10.0, "breakfast");
        t.add(202602, "rent", 1000.0, "monthlyRent");

        assertEquals(
                new HouseholdExpense(202602, "rent", 1000.0, "monthlyRent"),
                t.entry(1));
        assertEquals(2, t.size());
    }

    @Test
    public void testClearEmpty() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.clear();

        assertEquals(0, t.size());
        assertEquals("<>", t.toString());
    }

    @Test
    public void testClearNonEmpty() {
        HouseholdExpenseTracker t = this.constructorTest();

        t.add(202601, "food", 10.0, "breakfast");
        t.add(202602, "rent", 1000.0, "monthlyRent");

        t.clear();

        assertEquals(0, t.size());
        assertEquals("<>", t.toString());
    }

    @Test
    public void testNewInstance() {
        HouseholdExpenseTracker t = this.constructorTest();
        t.add(202601, "food", 10.0, "breakfast");

        HouseholdExpenseTracker newTracker = t.newInstance();

        assertTrue(newTracker instanceof HouseholdExpenseTracker1);
        assertEquals(0, newTracker.size());
        assertEquals("<>", newTracker.toString());

        assertEquals(1, t.size());
        assertEquals(new HouseholdExpense(202601, "food", 10.0, "breakfast"),
                t.entry(0));
    }

    @Test
    public void testTransferFromEmptyToEmpty() {
        HouseholdExpenseTracker t1 = this.constructorTest();
        HouseholdExpenseTracker t2 = this.constructorTest();

        t1.transferFrom(t2);

        assertEquals(0, t1.size());
        assertEquals(0, t2.size());
        assertEquals("<>", t1.toString());
        assertEquals("<>", t2.toString());
    }

    @Test
    public void testTransferFromNonEmptyToEmpty() {
        HouseholdExpenseTracker t1 = this.constructorTest();
        HouseholdExpenseTracker t2 = this.constructorTest();

        t2.add(202601, "food", 10.0, "breakfast");
        t2.add(202602, "rent", 1000.0, "monthlyRent");

        t1.transferFrom(t2);

        assertEquals(2, t1.size());
        assertEquals(new HouseholdExpense(202601, "food", 10.0, "breakfast"),
                t1.entry(0));
        assertEquals(
                new HouseholdExpense(202602, "rent", 1000.0, "monthlyRent"),
                t1.entry(1));

        assertEquals(0, t2.size());
        assertEquals("<>", t2.toString());
    }

    @Test
    public void testTransferFromEmptyToNonEmpty() {
        HouseholdExpenseTracker t1 = this.constructorTest();
        HouseholdExpenseTracker t2 = this.constructorTest();

        t1.add(202701, "food", 15.0, "lunch");

        t1.transferFrom(t2);

        assertEquals(0, t1.size());
        assertEquals(0, t2.size());
        assertEquals("<>", t1.toString());
        assertEquals("<>", t2.toString());
    }

    @Test
    public void testTransferFromNonEmptyToNonEmpty() {
        HouseholdExpenseTracker t1 = this.constructorTest();
        HouseholdExpenseTracker t2 = this.constructorTest();

        t1.add(202701, "food", 15.0, "lunch");

        t2.add(202601, "food", 10.0, "breakfast");
        t2.add(202602, "rent", 1000.0, "monthlyRent");

        t1.transferFrom(t2);

        assertEquals(2, t1.size());
        assertEquals(new HouseholdExpense(202601, "food", 10.0, "breakfast"),
                t1.entry(0));
        assertEquals(
                new HouseholdExpense(202602, "rent", 1000.0, "monthlyRent"),
                t1.entry(1));

        assertEquals(0, t2.size());
        assertEquals("<>", t2.toString());
    }
}
