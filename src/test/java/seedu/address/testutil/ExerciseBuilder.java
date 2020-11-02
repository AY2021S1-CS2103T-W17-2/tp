package seedu.address.testutil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.Muscle;
import seedu.address.model.exercise.Name;
import seedu.address.model.util.SampleDataUtil;


/**
 * A utility class to help with building Person objects.
 */
public class ExerciseBuilder {

    public static final String DEFAULT_NAME = "Push up";
    public static final String DEFAULT_DESCRIPTION = "Testing 2254";
    public static final String DEFAULT_DATE = "09-10-2020";
    public static final String DEFAULT_CALORIES = "224";
    public static final String DEFAULT_MUSCLES = "chest";

    private Name name;
    private Description description;
    private Date date;
    private Calories calories;
    private List<Muscle> musclesWorked;
    private Set<ExerciseTag> tags;

    /**
     * Creates a {@code ExerciseBuilder} with the default details.
     */
    public ExerciseBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        date = new Date(DEFAULT_DATE);
        calories = new Calories(DEFAULT_CALORIES);
        musclesWorked = Muscle.stringToMuscleList(DEFAULT_MUSCLES);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ExerciseBuilder with the data of {@code ExerciseToCopy}.
     */
    public ExerciseBuilder(Exercise exerciseToCopy) {
        name = exerciseToCopy.getName();
        description = exerciseToCopy.getDescription();
        date = exerciseToCopy.getDate();
        calories = exerciseToCopy.getCalories();
        musclesWorked = exerciseToCopy.getMusclesWorked().isPresent() ? exerciseToCopy.getMusclesWorked().get() : null;
        tags = new HashSet<>(exerciseToCopy.getExerciseTags());
    }

    /**
     * Sets the {@code Name} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Calories} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withCalories(String calories) {
        this.calories = new Calories(calories);
        return this;
    }

    /**
     * Sets the {@code Muscles} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withMusclesWorked(String musclesWorked) {
        this.musclesWorked = Muscle.stringToMuscleList(musclesWorked);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<ExerciseTag>} and set it to the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getExerciseTagSet(tags);
        return this;
    }


    public Exercise build() {
        return new Exercise(name, description, date, calories, musclesWorked, tags);
    }

}
