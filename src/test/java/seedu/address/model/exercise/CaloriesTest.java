package seedu.address.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CaloriesTest {
    @Test
    public void constructor_invalidCalories_throwsIllegalArgumentException() {
        String invalidCalories = "";
        assertThrows(IllegalArgumentException.class, () -> new Calories(invalidCalories));
    }

    @Test
    public void isValidCalories() {
        // null calories
        assertThrows(NullPointerException.class, () -> Calories.isValidCalories(null));

        // invalid calories
        assertFalse(Calories.isValidCalories("")); // empty string
        assertFalse(Calories.isValidCalories(" ")); // spaces only
        assertFalse(Calories.isValidCalories("phone")); // non-numeric
        assertFalse(Calories.isValidCalories("9011p041")); // alphabets within digits
        assertFalse(Calories.isValidCalories("9312 1534")); // spaces within digits

        // valid calories
        assertTrue(Calories.isValidCalories("9")); // exactly 1 number
        assertTrue(Calories.isValidCalories("93121534"));
        assertTrue(Calories.isValidCalories("124293842033123")); // long number
    }

}
