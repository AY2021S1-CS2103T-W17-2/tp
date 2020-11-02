package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Name;
import seedu.address.model.exercise.PropertiesMatchPredicateForExercise;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements ExerciseParser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Name name = null;
        Description description = null;
        Date date = null;
        Calories calories = null;
        String[] keywords = null;

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_CALORIES, PREFIX_KEYWORD);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            name = ParserUtil.parseExerciseName(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        }
        if (argMultimap.getValue(PREFIX_CALORIES).isPresent()) {
            calories = ParserUtil.parseCalories(argMultimap.getValue(PREFIX_CALORIES).get());
        }
        if (argMultimap.getValue(PREFIX_KEYWORD).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_KEYWORD).get().split("\\s+");
        }

        return new FindCommand(new PropertiesMatchPredicateForExercise(name, description, date, calories,
                keywords == null ? null : Arrays.asList(keywords)));
    }

}
