package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.Muscle;
import seedu.address.model.exercise.Name;

public class ParserUtilTest {
    private static final String INVALID_NAME = "run&"; // '&' not allowed in names
    private static final String INVALID_DESCRIPTION = " "; // description should not be empty
    private static final String INVALID_DATE = "2020-10-10"; // date of incorrect format
    private static final String INVALID_CALORIES = "abc"; // calories should be numbers
    private static final String INVALID_MUSCLES = "abs,chest"; // abs should be ab
    private static final String INVALID_TAG = "#gym"; //no symbol allowed

    private static final String VALID_NAME = "Push Up";
    private static final String VALID_DESCRIPTION = "Push Up Description";
    private static final String VALID_DATE = "10-10-2020";
    private static final String VALID_CALORIES = "100";
    private static final String VALID_MUSCLES = "chest";
    private static final String VALID_TAG_1 = "gym";
    private static final String VALID_TAG_2 = "house";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_EXERCISE, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_EXERCISE, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExerciseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExerciseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseExerciseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseExerciseName(nameWithWhitespace));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseDate_validValueWithoutWhitespace_returnsDate() throws Exception {
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(VALID_DATE));
    }

    @Test
    public void parseDate_validValueWithWhitespace_returnsTrimmedDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE + WHITESPACE;
        Date expectedDate = new Date(VALID_DATE);
        assertEquals(expectedDate, ParserUtil.parseDate(dateWithWhitespace));
    }

    @Test
    public void parseCalories_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseCalories(INVALID_CALORIES));
    }

    @Test
    public void parseCalories_validValueWithoutWhitespace_returnsCalories() throws Exception {
        Calories expectedCalories = new Calories(VALID_CALORIES);
        assertEquals(expectedCalories, ParserUtil.parseCalories(VALID_CALORIES));
    }

    @Test
    public void parseCalories_validValueWithWhitespace_returnsTrimmedCalories() throws Exception {
        String caloriesWithWhitespace = WHITESPACE + VALID_CALORIES + WHITESPACE;
        Calories expectedCalories = new Calories(VALID_CALORIES);
        assertEquals(expectedCalories, ParserUtil.parseCalories(caloriesWithWhitespace));
    }

    @Test
    public void parseMusclesWorked_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseMusclesWorked(INVALID_MUSCLES));
    }

    @Test
    public void parseMusclesWorked_validValueWithoutWhitespace_returnsMusclesWorkedDescription() throws Exception {
        List<Muscle> expectedMusclesWorkedDescription = Muscle.stringToMuscleList(VALID_MUSCLES);
        assertEquals(expectedMusclesWorkedDescription, ParserUtil.parseMusclesWorked(VALID_MUSCLES));
    }

    @Test
    public void parseMusclesWorked_validValueWithWhitespace_returnsTrimmedMusclesWorkedDescription() throws Exception {
        String musclesWithWhitespace = WHITESPACE + VALID_MUSCLES + WHITESPACE;
        List<Muscle> expectedMusclesWorkedDescription = Muscle.stringToMuscleList(VALID_MUSCLES);
        List<Muscle> parsedMusclesWorkedDescription = ParserUtil.parseMusclesWorked(musclesWithWhitespace);
        assertEquals(expectedMusclesWorkedDescription, parsedMusclesWorkedDescription);
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExerciseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExerciseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        ExerciseTag expectedTag = new ExerciseTag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseExerciseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        ExerciseTag expectedTag = new ExerciseTag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseExerciseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseExerciseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExerciseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseExerciseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<ExerciseTag> actualTagSet = ParserUtil.parseExerciseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<ExerciseTag> expectedTagSet = new HashSet<ExerciseTag>(
                Arrays.asList(new ExerciseTag(VALID_TAG_1), new ExerciseTag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
