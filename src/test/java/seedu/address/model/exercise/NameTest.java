package seedu.address.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.exercise.Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.exercise.Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> seedu.address.model.exercise.Name.isValidName(null));

        // invalid name
        assertFalse(seedu.address.model.exercise.Name.isValidName("")); // empty string
        assertFalse(seedu.address.model.exercise.Name.isValidName(" ")); // spaces only
        assertFalse(seedu.address.model.exercise.Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(seedu.address.model.exercise.Name.isValidName("run*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(seedu.address.model.exercise.Name.isValidName("pull up")); // alphabets only
        assertTrue(seedu.address.model.exercise.Name.isValidName("12345")); // numbers only
        assertTrue(seedu.address.model.exercise.Name.isValidName("2nd pull up")); // alphanumeric characters
        assertTrue(seedu.address.model.exercise.Name.isValidName("Run")); // with capital letters
        assertTrue(Name.isValidName("pull up 2 times")); // long names
    }

}
