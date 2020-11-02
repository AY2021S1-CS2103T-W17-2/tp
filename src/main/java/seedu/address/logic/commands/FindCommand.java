package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEYWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.model.ExerciseModel;
import seedu.address.model.exercise.PropertiesMatchPredicateForExercise;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends CommandForExercise {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all exercises whose details are matched to the "
            + "given ones (case-sensitive) and whose names contain any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: " + "[" + PREFIX_NAME + "EXERCISE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_CALORIES + "CALORIES]"
            + "[" + PREFIX_KEYWORD + "KEYWORDS...]\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_NAME + "Push Up"
            + PREFIX_DESCRIPTION + "30 times"
            + PREFIX_DATE + "10-10-2020"
            + PREFIX_CALORIES + "100"
            + "(" + PREFIX_KEYWORD + "up)";

    private final PropertiesMatchPredicateForExercise predicate;

    public FindCommand(PropertiesMatchPredicateForExercise predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(ExerciseModel model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_EXERCISES_LISTED_OVERVIEW, model.getFilteredExerciseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
