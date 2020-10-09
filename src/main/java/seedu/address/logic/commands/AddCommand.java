package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ExerciseModel;
import seedu.address.model.exercise.Exercise;

/**
 * Adds an exercise to Calo.
 */
public class AddCommand extends CommandForExercise {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exercise to Calo. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION"
            + PREFIX_DATE + "DATE "
            + "[" + PREFIX_CALORIES + "CALORIES]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "running "
            + PREFIX_DESCRIPTION + "10 mins"
            + PREFIX_DATE + "31-12-2020 "
            + PREFIX_CALORIES + "100 kcal ";

    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s";

    private final Exercise toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Exercise}
     */
    public AddCommand(Exercise exercise) {
        requireNonNull(exercise);
        toAdd = exercise;
    }

    @Override
    public CommandResult execute(ExerciseModel model) throws CommandException {
        requireNonNull(model);

        model.addExercise(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
