package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exercise.ExerciseTag;

/**
 * Jackson-friendly version of {@link ExerciseTag}.
 */
class JsonAdaptedExerciseTag {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedExerciseTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedExerciseTag(ExerciseTag source) {
        tagName = source.tagName;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code ExerciseTag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public ExerciseTag toModelType() throws IllegalValueException {
        if (!ExerciseTag.isValidTagName(tagName)) {
            throw new IllegalValueException(ExerciseTag.MESSAGE_CONSTRAINTS);
        }
        return new ExerciseTag(tagName);
    }

}
