package seedu.address.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExercise.PUSH_UP;
import static seedu.address.testutil.TypicalExercise.SIT_UP;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import seedu.address.model.exercise.exceptions.DuplicateExerciseException;
import seedu.address.model.exercise.exceptions.ExerciseNotFoundException;

class UniqueExerciseListTest {

    private final UniqueExerciseList uniqueExerciseList = new UniqueExerciseList();

    @Test
    public void contains_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.contains(null));
    }

    @Test
    public void contains_exerciseNotInList_returnsFalse() {
        assertFalse(uniqueExerciseList.contains(PUSH_UP));
    }

    @Test
    public void contains_exerciseInList_returnsTrue() {
        uniqueExerciseList.add(PUSH_UP);
        assertTrue(uniqueExerciseList.contains(PUSH_UP));
    }

    @Test
    public void add_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.add(null));
    }

    @Test
    public void add_duplicateExercise_throwsDuplicateExerciseException() {
        uniqueExerciseList.add(PUSH_UP);
        assertThrows(DuplicateExerciseException.class, () -> uniqueExerciseList.add(PUSH_UP));
    }

    @Test
    public void setExercise_nullTargetExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.updateExercise(null, PUSH_UP));
    }

    @Test
    public void setExercise_nullEditedExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.updateExercise(PUSH_UP, null));
    }

    @Test
    public void setExercise_targetExerciseNotInList_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> uniqueExerciseList.updateExercise(PUSH_UP, PUSH_UP));
    }

    /*
    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueExerciseList.add(PUSH_UP);
        uniqueExerciseList.setPerson(PUSH_UP, PUSH_UP);
        uniqueExerciseList expectedUniqueExerciseList = new uniqueExerciseList();
        expectedUniqueExerciseList.add(PUSH_UP);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }


    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueExerciseList.add(PUSH_UP);
        Person editedPUSH_UP = new PersonBuilder(PUSH_UP).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueExerciseList.setPerson(PUSH_UP, editedPUSH_UP);
        uniqueExerciseList expectedUniqueExerciseList = new uniqueExerciseList();
        expectedUniqueExerciseList.add(editedPUSH_UP);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }
    */

    @Test
    public void setExercise_editedExerciseHasDifferentIdentity_success() {
        uniqueExerciseList.add(PUSH_UP);
        uniqueExerciseList.updateExercise(PUSH_UP, SIT_UP);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(SIT_UP);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercise_editedExerciseHasNonUniqueIdentity_throwsDuplicateExerciseException() {
        uniqueExerciseList.add(PUSH_UP);
        uniqueExerciseList.add(SIT_UP);
        assertThrows(DuplicateExerciseException.class, () -> uniqueExerciseList.updateExercise(PUSH_UP, SIT_UP));
    }

    @Test
    public void remove_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.remove(null));
    }

    @Test
    public void remove_exerciseDoesNotExist_throwsExerciseNotFoundException() {
        assertThrows(ExerciseNotFoundException.class, () -> uniqueExerciseList.remove(PUSH_UP));
    }

    @Test
    public void remove_existingExercise_removesExercise() {
        uniqueExerciseList.add(PUSH_UP);
        uniqueExerciseList.remove(PUSH_UP);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercises_nullUniqueExerciseList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercises((UniqueExerciseList) null));
    }

    @Test
    public void setExercises_uniqueExerciseList_replacesOwnListWithProvideduniqueExerciseList() {
        uniqueExerciseList.add(PUSH_UP);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(SIT_UP);
        uniqueExerciseList.setExercises(expectedUniqueExerciseList);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercises_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExerciseList.setExercises((List<Exercise>) null));
    }

    @Test
    public void setExercises_list_replacesOwnListWithProvidedList() {
        uniqueExerciseList.add(PUSH_UP);
        List<Exercise> exerciseList = Collections.singletonList(SIT_UP);
        uniqueExerciseList.setExercises(exerciseList);
        UniqueExerciseList expectedUniqueExerciseList = new UniqueExerciseList();
        expectedUniqueExerciseList.add(SIT_UP);
        assertEquals(expectedUniqueExerciseList, uniqueExerciseList);
    }

    @Test
    public void setExercises_listWithDuplicateExercises_throwsDuplicateExerciseException() {
        List<Exercise> duplicateExerciseList = Arrays.asList(PUSH_UP, PUSH_UP);
        assertThrows(DuplicateExerciseException.class, () -> uniqueExerciseList.setExercises(duplicateExerciseList));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        Executable supplier = () -> uniqueExerciseList.asUnmodifiableObservableList().remove(0);
        assertThrows(UnsupportedOperationException.class, supplier);
    }

    @Test
    public void checkCaloriesForDay_emptyList_returnFalse() {
        UniqueExerciseList uniqueExerciseList = new UniqueExerciseList();
        assertFalse(uniqueExerciseList.getCaloriesByDay().containsKey(PUSH_UP.getDate().value));
    }

    @Test
    public void addExercise_checkCaloriesByDay() {
        UniqueExerciseList uniqueExerciseList = new UniqueExerciseList();
        assertFalse(uniqueExerciseList.getCaloriesByDay().containsKey(PUSH_UP.getDate().value));
        uniqueExerciseList.add(PUSH_UP);
        assertTrue(uniqueExerciseList.getCaloriesByDay().containsKey(PUSH_UP.getDate().value));
        assertEquals(uniqueExerciseList.getCaloriesByDay().get(PUSH_UP.getDate().value),
                Integer.parseInt(PUSH_UP.getCalories().toString()));
    }

    @Test
    public void setExercise_checkCaloriesByDay() {
        UniqueExerciseList uniqueExerciseList = new UniqueExerciseList();
        uniqueExerciseList.add(PUSH_UP);
        uniqueExerciseList.updateExercise(PUSH_UP, SIT_UP);
        assertTrue(uniqueExerciseList.getCaloriesByDay().containsKey(PUSH_UP.getDate().value));
        assertEquals(uniqueExerciseList.getCaloriesByDay().get(PUSH_UP.getDate().value), 0);
        assertTrue(uniqueExerciseList.getCaloriesByDay().containsKey(SIT_UP.getDate().value));
        assertEquals(uniqueExerciseList.getCaloriesByDay().get(SIT_UP.getDate().value),
                Integer.parseInt(SIT_UP.getCalories().toString()));
    }
}
