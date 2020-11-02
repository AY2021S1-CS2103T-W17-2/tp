package seedu.address.logic.commands;

import seedu.address.model.ExerciseModel;

/**
 * Terminates the program.
 */
public class ExitCommand extends CommandForExercise {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Exercise Book as requested ...";

    @Override
    public CommandResult execute(ExerciseModel model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
