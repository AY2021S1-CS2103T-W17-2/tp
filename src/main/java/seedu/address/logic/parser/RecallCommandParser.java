package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.RecallCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class RecallCommandParser implements ExerciseParser<RecallCommand> {
    @Override
    public RecallCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new RecallCommand(args.substring(1));
    }
}
