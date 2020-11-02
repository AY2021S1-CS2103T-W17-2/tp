package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EXERCISES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExercise.PULL_UP;
import static seedu.address.testutil.TypicalExercise.PUSH_UP;
import static seedu.address.testutil.TypicalExercise.SIT_UP;
import static seedu.address.testutil.TypicalExercise.SQUAT;
import static seedu.address.testutil.TypicalExercise.getTypicalExerciseBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.ExerciseModel;
import seedu.address.model.ExerciseModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Name;
import seedu.address.model.exercise.PropertiesMatchPredicateForExercise;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private ExerciseModel model = new ExerciseModelManager(getTypicalExerciseBook(), new UserPrefs());
    private ExerciseModel expectedModel = new ExerciseModelManager(getTypicalExerciseBook(), new UserPrefs());

    @Test
    public void equals() {
        Name name = new Name("Push Up");
        Description description = new Description("test");
        Date date = new Date("10-10-2020");
        Calories calories = new Calories("224");
        PropertiesMatchPredicateForExercise firstPredicate =
                new PropertiesMatchPredicateForExercise(name, description, date,
                        calories, Collections.singletonList("first"));
        PropertiesMatchPredicateForExercise secondPredicate =
                new PropertiesMatchPredicateForExercise(name, description, date,
                        calories, Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noExerciseFound() {
        String expectedMessage = String.format(MESSAGE_EXERCISES_LISTED_OVERVIEW, 0);
        String input = " ";
        List<String> keywords = Arrays.asList(input.split("\\s+"));
        PropertiesMatchPredicateForExercise predicate =
                new PropertiesMatchPredicateForExercise(null, null, null, null, keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredExerciseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredExerciseList());
    }

    @Test
    public void execute_multipleKeywords_multipleExercisesFound() {
        String expectedMessage = String.format(MESSAGE_EXERCISES_LISTED_OVERVIEW, 4);
        String input = "up sQuat";
        List<String> keywords = Arrays.asList(input.split("\\s+"));
        PropertiesMatchPredicateForExercise predicate =
                new PropertiesMatchPredicateForExercise(null, null, null, null, keywords);
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredExerciseList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PULL_UP, PUSH_UP, SIT_UP, SQUAT), model.getFilteredExerciseList());
    }
}
