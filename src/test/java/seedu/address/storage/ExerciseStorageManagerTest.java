package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.UserPrefs;

public class ExerciseStorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManagerForExercise storageManager;

    @BeforeEach
    public void setUp() {
        JsonExerciseBookStorage exerciseBookStorage = new JsonExerciseBookStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManagerForExercise(exerciseBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    /* Unable to run test due to use of Exercise Stubs
    @Test
    public void exerciseBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
    /*
    ExerciseBook original = getTypicalExerciseBook();
        storageManager.saveExerciseBook(original);
    ReadOnlyExerciseBook retrieved = storageManager.readExerciseBook().get();

    assertEquals(original, new ExerciseBook(retrieved));
    }
    */


    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getExerciseBookFilePath());
    }
}
