package seedu.address.model.exercise;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.exercise.exceptions.DuplicateExerciseException;
import seedu.address.model.exercise.exceptions.ExerciseNotFoundException;


public class UniqueExerciseList implements Iterable<Exercise> {

    private final ObservableList<Exercise> internalList = FXCollections.observableArrayList();
    private final ObservableList<Exercise> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Exercise toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameExercise);
    }

    /**
     * Adds a exercise to the list.
     * The exercise must not already exist in the list.
     */
    public void add(Exercise toAdd){
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateExerciseException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedExercise}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedExercise} must not be the same as another existing person in the list.
     */
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireAllNonNull(target, editedExercise);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ExerciseNotFoundException();
        }

        if (!target.isSameExercise(editedExercise) && contains(editedExercise)) {
            throw new DuplicateExerciseException();
        }

        internalList.set(index, editedExercise);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Exercise toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            //Should throw a new error
            throw new ExerciseNotFoundException();
        }
    }

    public void setExercises(UniqueExerciseList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setExercises(List<Exercise> persons) {
        requireAllNonNull(persons);
        if (!personsAreUnique(persons)) {
            throw new DuplicateExerciseException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Exercise> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Exercise> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueExerciseList // instanceof handles nulls
                && internalList.equals(((UniqueExerciseList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Exercise> exercises) {
        for (int i = 0; i < exercises.size() - 1; i++) {
            for (int j = i + 1; j < exercises.size(); j++) {
                if (exercises.get(i).isSameExercise(exercises.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
