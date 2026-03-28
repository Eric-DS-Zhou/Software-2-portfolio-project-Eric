import java.util.ArrayList;
import java.util.Iterator;

/**
 * {@Code HouseholdExpenseTracker} represented as an {@code ArrayList}
 * of {@code HouseholdExepnse} records.
 *
 * @convention $this.rep /= null and
 *              [every element in $this.rep is a valid HouseholdExpense record]
 * @correspondence this = sequence of expense records in $this.rep
 */
public final class HouseholdExpenseTracker1 extends HouseholdExpenseTrackerSecondary {

    /**
     * Private members ----------------------------------------------------------------
     */

    /**
     * Magic numbers.
     */
    private static final int TWELVE = 12, HUNDRED = 100, MIN_YEARMONTH_PERVIOUS = 100101;

    /**
     * Representation of this {@Code HouseholdExpenseTracker}.
     */
    private ArrayList<HouseholdExpense> rep;

    /**
     * Create a new representation.
     */
    private void createNewRep() {
        this.rep = new ArrayList<HouseholdExpense>();
    }

    /**
     * Constructor --------------------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public HouseholdExpenseTracker1() {
        this.createNewRep();
    }

    /**
     * Standard methods ---------------------------------------------------------------.
     */

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public HouseholdExpenseTracker newInstance() {
        HouseholdExpenseTracker result = new HouseholdExpenseTracker1();

        return result;
    }

    @Override
    public void transferFrom(HouseholdExpenseTracker source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof HouseholdExpenseTracker1
                            : "Violation of: source is of type HouseholdExpenseTracker1";
        HouseholdExpenseTracker1 localSource = (HouseholdExpenseTracker1) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    @Override
    public void add(int yyyyMM, String category, double amount, String note) {
        assert yyyyMM >= MIN_YEARMONTH_PERVIOUS : "Violation of: yyyyMM >= 100101";
        assert (yyyyMM % HUNDRED) >= 1 && (yyyyMM % HUNDRED) <= TWELVE
                                            : "Violation of: month is between 1 and 12";
        assert category.length() > 0 : "Violation of: category is not empty";
        assert amount >= 0 : "Vialation of: amount >= 0";

        HouseholdExpense entry = new HouseholdExpense(yyyyMM, category, amount, note);
        this.rep.add(entry);

    }

    @Override
    public HouseholdExpense remove(int index) {
        assert index >= 0 : "Violation of: index >= 0";
        assert index < this.size() : "Violation of: index < this.size()";

        HouseholdExpense result = this.rep.remove(index);

        return result;
    }

    @Override
    public int size() {
        int result = this.rep.size();

        return result;
    }

    @Override
    public HouseholdExpense entry(int index) {
        assert index >= 0 : "Violation of: index >= 0";
        assert index < this.size() : "Violation of: index < this.size()";

        HouseholdExpense result = this.rep.get(index);

        return result;
    }

    @Override
    public Iterator<HouseholdExpense> iterator() {
        Iterator<HouseholdExpense> result = this.rep.iterator();

        return result;
    }


}
