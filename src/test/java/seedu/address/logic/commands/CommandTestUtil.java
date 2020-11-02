package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ExerciseBook;
import seedu.address.model.ExerciseModel;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.NameContainsKeywordsPredicateForExercise;
import seedu.address.testutil.EditExerciseDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_NAME_PUSH_UP = "Push Up";
    public static final String VALID_DESCRIPTION_PUSH_UP = "Push Up Description";
    public static final String VALID_DATE_PUSH_UP = "10-10-2020";
    public static final String VALID_CALORIES_PUSH_UP = "100";
    public static final String VALID_MUSCLES_PUSH_UP = "chest";
    public static final String VALID_TAG_GYM = "gym";
    public static final String VALID_TAG_HOUSE = "house";

    public static final String VALID_NAME_SIT_UP = "Sit Up";
    public static final String VALID_DESCRIPTION_SIT_UP = "Sit Up Description";
    public static final String VALID_DATE_SIT_UP = "10-10-2020";
    public static final String VALID_CALORIES_SIT_UP = "100";
    public static final String VALID_MUSCLES_SIT_UP = "ab";

    public static final String NAME_DESC_PUSH_UP = " " + PREFIX_NAME + VALID_NAME_PUSH_UP;
    public static final String DESCRIPTION_DESC_PUSH_UP = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_PUSH_UP;
    public static final String DATE_DESC_PUSH_UP = " " + PREFIX_DATE + VALID_DATE_PUSH_UP;
    public static final String CALORIES_DESC_PUSH_UP = " " + PREFIX_CALORIES + VALID_CALORIES_PUSH_UP;
    public static final String MUSCLES_DESC_PUSH_UP = " " + PREFIX_MUSCLES + VALID_MUSCLES_PUSH_UP;
    public static final String TAG_DESC_GYM = " " + PREFIX_TAG + VALID_TAG_GYM;
    public static final String TAG_DESC_HOUSE = " " + PREFIX_TAG + VALID_TAG_HOUSE;


    public static final String NAME_DESC_SIT_UP = " " + PREFIX_NAME + VALID_NAME_SIT_UP;
    public static final String DESCRIPTION_DESC_SIT_UP = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_SIT_UP;
    public static final String DATE_DESC_SIT_UP = " " + PREFIX_DATE + VALID_DATE_SIT_UP;
    public static final String CALORIES_DESC_SIT_UP = " " + PREFIX_CALORIES + VALID_CALORIES_SIT_UP;
    public static final String MUSCLES_DESC_SIT_UP = " " + PREFIX_MUSCLES + VALID_MUSCLES_SIT_UP;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "run&"; // '&' not allowed in names
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION; // description should not be empty
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "2020-10-10"; // date of incorrect format
    public static final String INVALID_CALORIES_DESC = " " + PREFIX_CALORIES + "abc"; // calories should be numbers
    public static final String INVALID_MUSCLES_DESC = " " + PREFIX_MUSCLES + "abs,chest"; // abs should be ab
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "gym*"; // '*' not allowed in tags


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final UpdateCommand.EditExerciseDescriptor DESC_PUSH_UP;
    public static final UpdateCommand.EditExerciseDescriptor DESC_SIT_UP;

    static {
        // Calo
        DESC_PUSH_UP = new EditExerciseDescriptorBuilder().withName(VALID_NAME_PUSH_UP)
                .withDescription(VALID_DESCRIPTION_PUSH_UP).withDate(VALID_DATE_PUSH_UP)
                .withCalories(VALID_CALORIES_PUSH_UP).withTags(VALID_TAG_GYM).build();
        DESC_SIT_UP = new EditExerciseDescriptorBuilder().withName(VALID_NAME_SIT_UP)
                .withDescription(VALID_DESCRIPTION_SIT_UP).withDate(VALID_DATE_SIT_UP)
                .withCalories(VALID_CALORIES_SIT_UP).withTags(VALID_TAG_GYM, VALID_TAG_HOUSE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(CommandForExercise command, ExerciseModel actualModel,
                                            CommandResult expectedCommandResult, ExerciseModel expectedModel) {

        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException | IOException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(CommandForExercise command, ExerciseModel actualModel,
                                            String expectedMessage, ExerciseModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(CommandForExercise command,
                                            ExerciseModel actualModel, String expectedMessage) {

        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ExerciseBook expectedExerciseBook = new ExerciseBook(actualModel.getExerciseBook());
        List<Exercise> expectedFilteredList = new ArrayList<>(actualModel.getFilteredExerciseList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedExerciseBook, actualModel.getExerciseBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredExerciseList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showExerciseAtIndex(ExerciseModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredExerciseList().size());
        Exercise exercise = model.getFilteredExerciseList().get(targetIndex.getZeroBased());
        final String[] splitName = exercise.getName().fullName.split("\\s+");
        model.updateFilteredExerciseList(new NameContainsKeywordsPredicateForExercise(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredExerciseList().size());
    }
}
