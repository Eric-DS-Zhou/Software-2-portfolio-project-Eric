/**
 * The format of expense.
 *
 * @param date
 *            the date in format yyyyMM (e.g 200603)
 * @param category
 *            the category name (e.g. food)
 * @param amount
 *            the amount of expense ($, e.g. 12.26)
 * @param note
 *            additioinal note (e.g. Starbucks)
 * @requires yyyyMM >= 100000
 * @requires (yyyyMM % 100) >= 1 and (yyyyMM % 100) <= 12
 * @requires category is not empty
 * @requires amount >= 0
 * @updates this
 */
public record HouseholdExpense(int date, String category, double amount,
        String note) {

}
