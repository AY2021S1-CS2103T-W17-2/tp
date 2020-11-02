package seedu.address.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.exercise.Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> seedu.address.model.exercise.Date.isValidDate(null));

        // invalid date
        assertFalse(seedu.address.model.exercise.Date.isValidDate("")); // empty string
        assertFalse(seedu.address.model.exercise.Date.isValidDate("09-30-2020")); // mm-dd-yyyy
        assertFalse(seedu.address.model.exercise.Date.isValidDate("2020-09-30")); // yyyy-mm-dd

        // valid date
        assertTrue(seedu.address.model.exercise.Date.isValidDate("30-09-2020")); //dd-mm-yyyy
    }

}
