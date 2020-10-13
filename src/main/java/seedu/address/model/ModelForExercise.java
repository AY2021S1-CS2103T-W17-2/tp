package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.exercise.Exercise;

/**
 * The API of the Model component.
 */
public interface ModelForExercise {
    /** {@code Exercise} that always evaluate to true */
    Predicate<Exercise> PREDICATE_SHOW_ALL_EXERCISE = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getExerciseBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setExerciseBookFilePath(Path exerciseBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setExerciseBook(ReadOnlyExerciseBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyExerciseBook getExerciseBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasExercise(Exercise exercise);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteExercise(Exercise target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addExercise(Exercise exercise);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setExercise(Exercise target, Exercise editExercise);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Exercise> getFilteredExerciseList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredExerciseList(Predicate<Exercise> predicate);
}
