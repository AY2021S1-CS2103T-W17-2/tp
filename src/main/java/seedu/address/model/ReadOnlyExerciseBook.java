package seedu.address.model;

import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Template;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyExerciseBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Exercise> getExerciseList();

    ObservableList<Template> getTemplateList();

    HashMap<String, Integer> getCaloriesByDay();
}
