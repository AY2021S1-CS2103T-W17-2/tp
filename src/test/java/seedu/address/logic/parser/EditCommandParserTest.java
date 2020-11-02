package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CALORIES_DESC_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.CALORIES_DESC_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CALORIES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MUSCLES_DESC_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.MUSCLES_DESC_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_GYM;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HOUSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MUSCLES_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MUSCLES_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GYM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HOUSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EXERCISE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateCommand;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.Name;
import seedu.address.testutil.EditExerciseDescriptorBuilder;

public class EditCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE);

    private UpdateExerciseCommandParser parser = new UpdateExerciseCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_PUSH_UP, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", UpdateCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_NAME_PUSH_UP, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_NAME_PUSH_UP, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        // invalid description
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INVALID_CALORIES_DESC, Calories.MESSAGE_CONSTRAINTS); // invalid calories
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, ExerciseTag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid name followed by valid description
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + DESCRIPTION_DESC_PUSH_UP, Name.MESSAGE_CONSTRAINTS);

        // valid name followed by invalid name
        assertParseFailure(parser, "1" + NAME_DESC_PUSH_UP + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Exercise} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_GYM + TAG_DESC_HOUSE + TAG_EMPTY, ExerciseTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_GYM + TAG_EMPTY + TAG_DESC_HOUSE, ExerciseTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_GYM + TAG_DESC_HOUSE, ExerciseTag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DESCRIPTION_DESC
                            + VALID_DATE_PUSH_UP + VALID_CALORIES_PUSH_UP,
                            Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EXERCISE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP + TAG_DESC_GYM
                + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + MUSCLES_DESC_PUSH_UP + TAG_DESC_HOUSE;

        UpdateCommand.EditExerciseDescriptor descriptor =
                new EditExerciseDescriptorBuilder().withName(VALID_NAME_PUSH_UP)
                                                    .withDescription(VALID_DESCRIPTION_PUSH_UP)
                                                    .withDate(VALID_DATE_PUSH_UP)
                                                    .withCalories(VALID_CALORIES_PUSH_UP)
                                                    .withMusclesWorked(VALID_MUSCLES_PUSH_UP)
                                                    .withTags(VALID_TAG_GYM, VALID_TAG_HOUSE)
                                                    .build();

        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP;

        UpdateCommand.EditExerciseDescriptor descriptor =
                new EditExerciseDescriptorBuilder().withName(VALID_NAME_PUSH_UP)
                        .withDescription(VALID_DESCRIPTION_PUSH_UP).build();

        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_EXERCISE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_PUSH_UP;
        UpdateCommand.EditExerciseDescriptor descriptor =
                new EditExerciseDescriptorBuilder().withName(VALID_NAME_PUSH_UP).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_PUSH_UP;
        descriptor = new EditExerciseDescriptorBuilder().withDescription(VALID_DESCRIPTION_PUSH_UP).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_PUSH_UP;
        descriptor = new EditExerciseDescriptorBuilder().withDate(VALID_DATE_PUSH_UP).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // calories
        userInput = targetIndex.getOneBased() + CALORIES_DESC_PUSH_UP;
        descriptor = new EditExerciseDescriptorBuilder().withCalories(VALID_CALORIES_PUSH_UP).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // muscles worked
        userInput = targetIndex.getOneBased() + MUSCLES_DESC_PUSH_UP;
        descriptor = new EditExerciseDescriptorBuilder().withMusclesWorked(VALID_MUSCLES_PUSH_UP).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_HOUSE;
        descriptor = new EditExerciseDescriptorBuilder().withTags(VALID_TAG_HOUSE).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP + DATE_DESC_PUSH_UP
                + CALORIES_DESC_PUSH_UP + MUSCLES_DESC_PUSH_UP + TAG_DESC_HOUSE
                + NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + MUSCLES_DESC_PUSH_UP + TAG_DESC_HOUSE
                + NAME_DESC_SIT_UP + DESCRIPTION_DESC_SIT_UP + DATE_DESC_SIT_UP + CALORIES_DESC_SIT_UP
                + MUSCLES_DESC_SIT_UP + TAG_DESC_GYM;

        UpdateCommand.EditExerciseDescriptor descriptor =
                new EditExerciseDescriptorBuilder().withName(VALID_NAME_SIT_UP)
                        .withDescription(VALID_DESCRIPTION_SIT_UP)
                        .withDate(VALID_DATE_SIT_UP)
                        .withCalories(VALID_CALORIES_SIT_UP)
                        .withMusclesWorked(VALID_MUSCLES_SIT_UP)
                        .withTags(VALID_TAG_HOUSE, VALID_TAG_GYM).build();

        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + NAME_DESC_PUSH_UP;
        UpdateCommand.EditExerciseDescriptor descriptor =
                new EditExerciseDescriptorBuilder().withName(VALID_NAME_PUSH_UP).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_PUSH_UP + INVALID_DATE_DESC
                      + CALORIES_DESC_PUSH_UP + DATE_DESC_PUSH_UP;
        descriptor =
                new EditExerciseDescriptorBuilder().withDescription(VALID_DESCRIPTION_PUSH_UP)
                        .withCalories(VALID_CALORIES_PUSH_UP).withDate(VALID_DATE_PUSH_UP)
                        .build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_EXERCISE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        UpdateCommand.EditExerciseDescriptor descriptor =
                new EditExerciseDescriptorBuilder().withTags().build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
