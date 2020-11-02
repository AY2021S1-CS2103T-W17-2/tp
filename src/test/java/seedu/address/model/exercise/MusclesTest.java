package seedu.address.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MusclesTest {
    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, (
            ) -> seedu.address.model.exercise.Muscle.isValidMusclesWorked(null));

        // invalid name
        assertFalse(seedu.address.model.exercise.Muscle.isValidMusclesWorked("")); // empty string
        assertFalse(seedu.address.model.exercise.Muscle.isValidMusclesWorked(" ")); // spaces only
        // only non-alphanumeric characters
        assertFalse(seedu.address.model.exercise.Muscle.isValidMusclesWorked("^"));
        // contains non-alphanumeric characters
        assertFalse(seedu.address.model.exercise.Muscle.isValidMusclesWorked("run*"));
        // using other delimiters
        assertFalse(seedu.address.model.exercise.Muscle.isValidMusclesWorked("chest-leg"));
        // whitespace with delimiter
        assertFalse(seedu.address.model.exercise.Muscle.isValidMusclesWorked("chest, leg"));
        // using other names
        assertFalse(seedu.address.model.exercise.Muscle.isValidMusclesWorked("chests,leg"));

        // valid name
        // just one muscle
        assertTrue(seedu.address.model.exercise.Muscle.isValidMusclesWorked("chest"));
        // more than one muscle group
        assertTrue(seedu.address.model.exercise.Muscle.isValidMusclesWorked("chest,leg"));
        // using trailing delimiter
        assertTrue(seedu.address.model.exercise.Muscle.isValidMusclesWorked("chest,leg,"));
    }
}
