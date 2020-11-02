package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ExerciseModel;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Weight;

/**
 * Adds an exercise to Calo.
 */
public class AddCommand extends CommandForExercise {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exercise to Calo. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + "[" + PREFIX_DATE + "DATE]"
            + "[" + PREFIX_CALORIES + "CALORIES] "
            + "[" + PREFIX_MUSCLES + "MUSCLES_WORKED] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "running "
            + PREFIX_DESCRIPTION + "10 mins "
            + PREFIX_DATE + "31-12-2020 "
            + PREFIX_CALORIES + "100 "
            + PREFIX_MUSCLES + "chest,arm "
            + PREFIX_TAG + "home "
            + PREFIX_TAG + "gym";

    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s\n"+
            "Congratulations! You have burnt %2$.6s kg";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in the address book";

    private final Exercise toAdd;
    private final Weight burntWeight;

    /**
     * Creates an AddCommand to add the specified {@code Exercise}
     */
    public AddCommand(Exercise exercise) {
        requireNonNull(exercise);
        toAdd = exercise;
        this.burntWeight = new Weight(toAdd.getCalories());
    }

    @Override
    public CommandResult execute(ExerciseModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasExercise(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }

        model.addExercise(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd, burntWeight.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd)); // state check
    }
}
