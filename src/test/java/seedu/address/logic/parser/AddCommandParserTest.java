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
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MUSCLES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MUSCLES_DESC_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.MUSCLES_DESC_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_SIT_UP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalExercise.TYPICAL_EXERCISE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Muscle;
import seedu.address.model.exercise.Name;
import seedu.address.testutil.ExerciseBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Exercise expectedExercise = new ExerciseBuilder(TYPICAL_EXERCISE).build();

        // normal input
        assertParseSuccess(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + MUSCLES_DESC_PUSH_UP,
                new AddCommand(expectedExercise));

        // multiple names - last name accepted
        assertParseSuccess(parser,
                NAME_DESC_SIT_UP + NAME_DESC_PUSH_UP
                        + DESCRIPTION_DESC_PUSH_UP + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + MUSCLES_DESC_PUSH_UP,
                new AddCommand(expectedExercise));

        //multiple descriptions - last description accepted
        assertParseSuccess(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_SIT_UP
                        + DESCRIPTION_DESC_PUSH_UP + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + MUSCLES_DESC_PUSH_UP,
                new AddCommand(expectedExercise));

        //multiple date
        assertParseSuccess(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_SIT_UP + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + MUSCLES_DESC_PUSH_UP,
                new AddCommand(expectedExercise));

        //multiple calories
        assertParseSuccess(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_PUSH_UP + CALORIES_DESC_SIT_UP + CALORIES_DESC_PUSH_UP + MUSCLES_DESC_PUSH_UP,
                new AddCommand(expectedExercise));

        //multiple groups of muscles
        assertParseSuccess(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_PUSH_UP + CALORIES_DESC_SIT_UP + MUSCLES_DESC_SIT_UP + MUSCLES_DESC_PUSH_UP,
                new AddCommand(expectedExercise));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        //missing name
        assertParseFailure(parser, DESCRIPTION_DESC_PUSH_UP + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP,
                expectedMessage);

        //missing description
        assertParseFailure(parser, NAME_DESC_PUSH_UP + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid name
        assertParseFailure(parser,
                INVALID_NAME_DESC + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP,
                Name.MESSAGE_CONSTRAINTS);

        //invalid description
        assertParseFailure(parser,
                NAME_DESC_PUSH_UP + INVALID_DESCRIPTION_DESC
                        + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP,
                Description.MESSAGE_CONSTRAINTS);

        //invalid date
        assertParseFailure(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + INVALID_DATE_DESC + CALORIES_DESC_PUSH_UP,
                Date.MESSAGE_CONSTRAINTS);

        //invalid calories
        assertParseFailure(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_PUSH_UP + INVALID_CALORIES_DESC,
                Calories.MESSAGE_CONSTRAINTS);

        //invalid muscles worked
        assertParseFailure(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + INVALID_MUSCLES_DESC,
                Muscle.MESSAGE_CONSTRAINTS);
    }

}
