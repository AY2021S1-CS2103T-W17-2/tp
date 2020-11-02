package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedExercise.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExercise.PUSH_UP;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Muscle;
import seedu.address.model.exercise.Name;

/*
Test for invalid input are commented out due to the use of stubs
 */
public class JsonAdaptedExerciseTest {
    private static final String INVALID_NAME = "P@ss Fail";
    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_DATE = " ";
    private static final String INVALID_CALORIES = "@@@@@";
    private static final String INVALID_MUSCLES = "abs,,";
    private static final String INVALID_TAG = "#friend";


    private static final String VALID_NAME = PUSH_UP.getName().toString();
    private static final String VALID_DESCRIPTION = PUSH_UP.getDescription().toString();
    private static final String VALID_DATE = PUSH_UP.getDate().toString();
    // Always valid
    private static final String VALID_CALORIES = PUSH_UP.getCalories().toString();
    private static final String VALID_MUSCLES = PUSH_UP.getMusclesWorkedDescription();
    private static final List<JsonAdaptedExerciseTag> VALID_TAGS = PUSH_UP.getExerciseTags().stream()
            .map(JsonAdaptedExerciseTag::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validExerciseDetails_returnsExercise() throws Exception {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(PUSH_UP);
        assertEquals(PUSH_UP, exercise.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(INVALID_NAME, VALID_DESCRIPTION,
                                                VALID_DATE, VALID_CALORIES, VALID_MUSCLES, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(null, VALID_DESCRIPTION,
                                                VALID_DATE, VALID_CALORIES, VALID_MUSCLES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(VALID_NAME, INVALID_DESCRIPTION, VALID_DATE,
                        VALID_CALORIES, VALID_MUSCLES, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(VALID_NAME, null,
                                                VALID_DATE, VALID_CALORIES, VALID_MUSCLES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(VALID_NAME, VALID_DESCRIPTION,
                        INVALID_DATE, VALID_CALORIES, VALID_MUSCLES, VALID_TAGS);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(VALID_NAME, VALID_DESCRIPTION,
                                        null, VALID_CALORIES, VALID_MUSCLES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidCalories_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(VALID_NAME, VALID_DESCRIPTION,
                                        VALID_DATE, INVALID_CALORIES, VALID_MUSCLES, VALID_TAGS);
        String expectedMessage = Calories.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidMuscles_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(VALID_NAME, VALID_DESCRIPTION,
                                        VALID_DATE, VALID_CALORIES, INVALID_MUSCLES, VALID_TAGS);
        String expectedMessage = Muscle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedExerciseTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedExerciseTag(INVALID_TAG));
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(VALID_NAME, VALID_DESCRIPTION,
                        VALID_DATE, VALID_CALORIES, INVALID_MUSCLES, invalidTags);
        assertThrows(IllegalValueException.class, exercise::toModelType);
    }
}
