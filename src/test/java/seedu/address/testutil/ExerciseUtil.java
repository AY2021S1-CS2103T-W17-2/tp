package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.UpdateCommand;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.Muscle;

/**
 * A utility class for Person.
 */
public class ExerciseUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Exercise exercise) {
        return AddCommand.COMMAND_WORD + " " + getExerciseDetails(exercise);
    }

    /**
     * Returns the part of command string for the given {@code exercise}'s details.
     */
    public static String getExerciseDetails(Exercise exercise) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + exercise.getName().toString() + " ");
        sb.append(PREFIX_DESCRIPTION + exercise.getDescription().toString() + " ");
        sb.append(PREFIX_DATE + exercise.getDate().toString() + " ");
        sb.append(PREFIX_CALORIES + exercise.getCalories().toString() + " ");
        sb.append(PREFIX_MUSCLES + exercise.getMusclesWorkedDescription() + " ");
        exercise.getExerciseTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditExerciseDescriptorDetails(UpdateCommand.EditExerciseDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.toString()).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESCRIPTION)
                .append(description.toString()).append(" "));
        descriptor.getDate().ifPresent(date -> sb.append(PREFIX_DATE).append(date.toString()).append(" "));
        descriptor.getCalories().ifPresent(calories -> sb.append(PREFIX_CALORIES)
                .append(calories.toString()).append(" "));
        descriptor.getMusclesWorked().ifPresent(muscles -> sb.append(PREFIX_MUSCLES)
                .append(Muscle.muscleListToString(muscles)).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<ExerciseTag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
