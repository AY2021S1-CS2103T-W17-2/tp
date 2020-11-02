package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.Muscle;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static seedu.address.model.exercise.Name parseExerciseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!seedu.address.model.exercise.Name.isValidName(trimmedName)) {
            throw new ParseException(seedu.address.model.exercise.Name.MESSAGE_CONSTRAINTS);
        }
        return new seedu.address.model.exercise.Name(trimmedName);
    }

    /**
     * Parses a {@code String description} into a {@code description}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Description} is invalid.
     */
    public static Description parseDescription(String description) throws ParseException {
        requireNonNull(description);
        String trimmedDescription = description.trim();
        if (!Description.isValidDescription(trimmedDescription)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        return new Description(trimmedDescription);
    }

    /**
     * Parses a {@code String date} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Date} is invalid.
     */
    public static Date parseDate(String date) throws ParseException {
        if (date == null) {
            return new Date(null);
        }

        String trimmedDate = date.trim();
        if (!Date.isValidDate(trimmedDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedDate);
    }

    /**
     * Parses a {@code String calories} into a {@code Calories}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Calories} is invalid.
     */
    public static Calories parseCalories(String calories) throws ParseException {
        if (calories == null) {
            return new Calories(null);
        }

        String trimmedCalories = calories.trim();
        if (!Calories.isValidCalories(trimmedCalories)) {
            throw new ParseException(Calories.MESSAGE_CONSTRAINTS);
        }
        return new Calories(trimmedCalories);
    }

    /**
     * Parses a {@code String musclesWorked} into {@code MusclesWorked}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code musclesWorked} is invalid.
     */
    public static List<Muscle> parseMusclesWorked(String musclesWorked) throws ParseException {
        if (musclesWorked == null) {
            return null;
        }

        String trimmedMusclesWorked = musclesWorked.trim();
        if (!Muscle.isValidMusclesWorked(trimmedMusclesWorked)) {
            throw new ParseException(Muscle.MESSAGE_CONSTRAINTS);
        }
        return Muscle.stringToMuscleList(trimmedMusclesWorked);
    }

    /**
     * Parses a {@code String tag} into a {@code ExerciseTag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static ExerciseTag parseExerciseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!ExerciseTag.isValidTagName(trimmedTag)) {
            throw new ParseException(ExerciseTag.MESSAGE_CONSTRAINTS);
        }
        return new ExerciseTag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<ExerciseTag>}.
     */
    public static Set<ExerciseTag> parseExerciseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<ExerciseTag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseExerciseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parse a {@code String pathLocation} into a {@code Path}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code pathLocation} is invalid.
     */
    public static Path parsePath(String pathLocation) throws ParseException {
        requireNonNull(pathLocation);
        String trimPathDirectory = pathLocation.trim();
        if (hasFileWithSameName(trimPathDirectory)) {
            throw new ParseException("A file with same name exists. Please key in a different name");
        }
        /*
        if (!hasRightToCreate(trimPathDirectory)) {
            throw new ParseException("You don't have right to create at the specified location");
        }
         */

        return Paths.get(trimPathDirectory);
    }

    private static boolean hasFileWithSameName(String filePath) {
        File targetLocation = new File(filePath);
        return targetLocation.exists();
    }

    private static boolean hasRightToCreate(String filePath) {
        return false;
        //return new File(filePath).canWrite();
    }
}
