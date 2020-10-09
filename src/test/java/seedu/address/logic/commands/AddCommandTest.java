package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.*;
import seedu.address.model.exercise.Exercise;
import seedu.address.testutil.ExerciseBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Exercise validExercise = new ExerciseBuilder().build();

        CommandResult commandResult = new AddCommand(validExercise).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validExercise), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validExercise), modelStub.exercisesAdded);
    }

    @Test
    public void equals() {
        Exercise running = new ExerciseBuilder().withName("running").build();
        Exercise jumping = new ExerciseBuilder().withName("jumping").build();
        AddCommand addRunningCommand = new AddCommand(running);
        AddCommand addJumpingCommand = new AddCommand(jumping);

        // same object -> returns true
        assertTrue(addRunningCommand.equals(addRunningCommand));

        // different types -> returns false
        assertFalse(addRunningCommand.equals(1));

        // null -> returns false
        assertFalse(addRunningCommand.equals(null));

        // different person -> returns false
        assertFalse(addRunningCommand.equals(addJumpingCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements ExerciseModel {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getExerciseBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExerciseBookFilePath(Path exerciseBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExercise(Exercise exercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExerciseBook(ReadOnlyExerciseBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyExerciseBook getExerciseBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExercise(Exercise exercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExercise(Exercise target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setExercise(Exercise target, Exercise editExercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Exercise> getFilteredExerciseList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Exercise> exercisesAdded = new ArrayList<>();

        @Override
        public void addExercise(Exercise exercise) {
            requireNonNull(exercise);
            exercisesAdded.add(exercise);
        }

        @Override
        public ReadOnlyExerciseBook getExerciseBook() {
            return new ExerciseBook();
        }
    }

}
