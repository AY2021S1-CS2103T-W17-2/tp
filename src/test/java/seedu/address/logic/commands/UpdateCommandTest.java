package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GYM;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showExerciseAtIndex;
import static seedu.address.testutil.TypicalExercise.getTypicalExerciseBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ExerciseBook;
import seedu.address.model.ExerciseModel;
import seedu.address.model.ExerciseModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exercise.Exercise;
import seedu.address.testutil.EditExerciseDescriptorBuilder;
import seedu.address.testutil.ExerciseBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for UpdateExerciseCommand.
 */
class UpdateCommandTest {

    private ExerciseModel model = new ExerciseModelManager(getTypicalExerciseBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Exercise editedExercise = new ExerciseBuilder().build();
        UpdateCommand.EditExerciseDescriptor descriptor =
                new EditExerciseDescriptorBuilder(editedExercise).build();
        UpdateCommand updateExerciseCommand = new UpdateCommand(INDEX_FIRST_EXERCISE, descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        ExerciseModel expectedModel = new ExerciseModelManager(new ExerciseBook(model.getExerciseBook()),
                new UserPrefs());
        expectedModel.setExercise(model.getFilteredExerciseList().get(0), editedExercise);

        assertCommandSuccess(updateExerciseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastExercise = Index.fromOneBased(model.getFilteredExerciseList().size());
        Exercise lastExercise = model.getFilteredExerciseList().get(indexLastExercise.getZeroBased());

        ExerciseBuilder exerciseInList = new ExerciseBuilder(lastExercise);
        Exercise editedExercise = exerciseInList.withName(VALID_NAME_PUSH_UP).withDescription(VALID_DESCRIPTION_PUSH_UP)
                .withDate(VALID_DATE_PUSH_UP).withCalories(VALID_CALORIES_PUSH_UP).withTags(VALID_TAG_GYM).build();

        UpdateCommand.EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder()
                .withName(VALID_NAME_PUSH_UP)
                .withDescription(VALID_DESCRIPTION_PUSH_UP)
                .withDate(VALID_DATE_PUSH_UP).withCalories(VALID_CALORIES_PUSH_UP).withTags(VALID_TAG_GYM).build();
        UpdateCommand updateExerciseCommand = new UpdateCommand(indexLastExercise, descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        ExerciseModel expectedModel = new ExerciseModelManager(new ExerciseBook(model.getExerciseBook()),
                new UserPrefs());

        expectedModel.setExercise(lastExercise, editedExercise);

        assertCommandSuccess(updateExerciseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        UpdateCommand updateExerciseCommand = new UpdateCommand(INDEX_FIRST_EXERCISE,
                new UpdateCommand.EditExerciseDescriptor());
        Exercise editedExercise = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());

        String expectedMessage = String.format(UpdateCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        ExerciseModel expectedModel = new ExerciseModelManager(new ExerciseBook(model.getExerciseBook()),
                new UserPrefs());

        assertCommandSuccess(updateExerciseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);

        Exercise exerciseInFilteredList = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());
        Exercise editedExercise = new ExerciseBuilder(exerciseInFilteredList).withName(VALID_NAME_PUSH_UP).build();
        UpdateCommand updateExerciseCommand = new UpdateCommand(INDEX_FIRST_EXERCISE,
                new EditExerciseDescriptorBuilder().withName(VALID_NAME_PUSH_UP).build());

        String expectedMessage = String.format(UpdateCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        ExerciseModel expectedModel = new ExerciseModelManager(new ExerciseBook(model.getExerciseBook()),
                new UserPrefs());
        expectedModel.setExercise(model.getFilteredExerciseList().get(0), editedExercise);

        assertCommandSuccess(updateExerciseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateExerciseUnfilteredList_failure() {
        Exercise firstExercise = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());
        UpdateCommand.EditExerciseDescriptor descriptor =
                                                      new EditExerciseDescriptorBuilder(firstExercise).build();
        UpdateCommand updateExerciseCommand =
              new UpdateCommand(INDEX_SECOND_EXERCISE, descriptor);

        assertCommandFailure(updateExerciseCommand, model, UpdateCommand.MESSAGE_DUPLICATE_EXERCISE);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);

        // edit exercise in filtered list into a duplicate in exercise book
        Exercise exerciseInList = model.getExerciseBook()
                                        .getExerciseList().get(INDEX_SECOND_EXERCISE.getZeroBased());
        UpdateCommand updateExerciseCommand = new UpdateCommand(INDEX_FIRST_EXERCISE,
                                                          new EditExerciseDescriptorBuilder(exerciseInList)
                                                          .build());

        assertCommandFailure(updateExerciseCommand, model, UpdateCommand.MESSAGE_DUPLICATE_EXERCISE);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of exercise book
     */
    @Test
    public void execute_invalidExerciseIndexFilteredList_failure() {
        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);
        Index outOfBoundIndex = INDEX_SECOND_EXERCISE;

        // ensures that outOfBoundIndex is still in bounds of exercise book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExerciseBook().getExerciseList().size());

        UpdateCommand updateExerciseCommand = new UpdateCommand(outOfBoundIndex,
                new EditExerciseDescriptorBuilder().withName(VALID_NAME_PUSH_UP).build());

        assertCommandFailure(updateExerciseCommand, model, Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final UpdateCommand standardCommand = new UpdateCommand(INDEX_FIRST_EXERCISE, DESC_PUSH_UP);

        // same values -> returns true
        UpdateCommand.EditExerciseDescriptor copyDescriptor =
                new UpdateCommand.EditExerciseDescriptor(DESC_PUSH_UP);
        UpdateCommand commandWithSameValues = new UpdateCommand(INDEX_FIRST_EXERCISE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        // assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UpdateCommand(INDEX_SECOND_EXERCISE, DESC_PUSH_UP)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UpdateCommand(INDEX_FIRST_EXERCISE, DESC_SIT_UP)));
    }
}
