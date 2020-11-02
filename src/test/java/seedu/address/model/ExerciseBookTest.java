package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.LogsCenter;

public class ExerciseBookTest {
    private static final Logger logger = LogsCenter.getLogger(ExerciseModelManager.class);

    private final ExerciseBook exerciseBook = new ExerciseBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), exerciseBook.getExerciseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseBook.resetData(null));
    }

    /*ExerciseBook is using stubs Exercise and its componenet. So unable to assert equals
    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        getTypicalExerciseBook();
        ExerciseBook newData = getTypicalExerciseBook();
        exerciseBook.resetData(newData);
        assertEquals(newData, exerciseBook);
    }
     */

    /*
    @Test
    public void resetData_withDuplicateExercises_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
            .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookTest.AddressBookStub newData = new AddressBookTest.AddressBookStub(newPersons);
        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }
    */

    @Test
    public void hasPerson_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> exerciseBook.hasExercise(null));
    }

    /* Omitted due to the use of Stubs in Exercise's property
    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(exerciseBook.hasExercise(SIT_UP));
    }

    @Test
    public void hasExercise_exerciseInExerciseBook_returnsTrue() {
        exerciseBook.addExercise(SIT_UP);
        assertTrue(exerciseBook.hasExercise(SIT_UP));
    }
    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        exerciseBook.addExercise(SIT_UP);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }
     */

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> exerciseBook.getExerciseList().remove(0));
    }

    /*
    //A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }
     */
}
