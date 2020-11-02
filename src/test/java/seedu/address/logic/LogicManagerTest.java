package seedu.address.logic;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
//import static seedu.address.logic.commands.CommandTestUtil.CALORIES_DESC_PUSH_UP;
//import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_PUSH_UP;
//import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_PUSH_UP;
//import static seedu.address.logic.commands.CommandTestUtil.MUSCLES_DESC_PUSH_UP;
//import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_PUSH_UP;
import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalExercise.PUSH_UP;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

//import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ExerciseModel;
import seedu.address.model.ExerciseModelManager;
import seedu.address.model.ReadOnlyExerciseBook;
import seedu.address.model.UserPrefs;
//import seedu.address.model.exercise.Exercise;
import seedu.address.storage.JsonExerciseBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManagerForExercise;
//import seedu.address.testutil.ExerciseBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private ExerciseModel model = new ExerciseModelManager();
    private LogicForExercise logic;

    @BeforeEach
    public void setUp() {
        JsonExerciseBookStorage exerciseBookStorage =
                new JsonExerciseBookStorage(temporaryFolder.resolve("exerciseBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManagerForExercise storage = new StorageManagerForExercise(exerciseBookStorage, userPrefsStorage);
        logic = new LogicManagerForExercise(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    //    @Test
    //    public void execute_storageThrowsIoException_throwsCommandException() {
    //        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
    //        JsonExerciseBookStorage exerciseBookStorage =
    //                new JsonExerciseBookIoExceptionThrowingStub(
    //                temporaryFolder.resolve("ioExceptionExerciseBook.json"));
    //        JsonUserPrefsStorage userPrefsStorage =
    //                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
    //        StorageManagerForExercise storage = new StorageManagerForExercise(exerciseBookStorage, userPrefsStorage);
    //        logic = new LogicManagerForExercise(model, storage);
    //
    //        // Execute add command
    //        String addCommand = AddCommand.COMMAND_WORD
    //                  + NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP + DATE_DESC_PUSH_UP
    //                  + CALORIES_DESC_PUSH_UP + MUSCLES_DESC_PUSH_UP;
    //        Exercise expectedExercise = new ExerciseBuilder(PUSH_UP).withTags().build();
    //        ExerciseModelManager expectedModel = new ExerciseModelManager();
    //        expectedModel.addExercise(expectedExercise);
    //        String expectedMessage = LogicManagerForExercise.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
    //        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    //    }

    @Test
    public void getFilteredExerciseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredExerciseList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, ExerciseModel)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            ExerciseModel expectedModel) throws CommandException, ParseException, IOException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, ExerciseModel)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, ExerciseModel)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, ExerciseModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        ExerciseModel expectedModel = new ExerciseModelManager(model.getExerciseBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, ExerciseModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, ExerciseModel expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonExerciseBookIoExceptionThrowingStub extends JsonExerciseBookStorage {
        private JsonExerciseBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveExerciseBook(ReadOnlyExerciseBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}

