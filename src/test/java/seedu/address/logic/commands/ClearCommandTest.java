package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExercise.getTypicalExerciseBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.ExerciseBook;
import seedu.address.model.ExerciseModel;
import seedu.address.model.ExerciseModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyExerciseBook_success() {
        ExerciseModel model = new ExerciseModelManager();
        ExerciseModel expectedModel = new ExerciseModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyExerciseBook_success() {
        ExerciseModel model = new ExerciseModelManager(getTypicalExerciseBook(), new UserPrefs());
        ExerciseModel expectedModel = new ExerciseModelManager(getTypicalExerciseBook(), new UserPrefs());
        expectedModel.setExerciseBook(new ExerciseBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
