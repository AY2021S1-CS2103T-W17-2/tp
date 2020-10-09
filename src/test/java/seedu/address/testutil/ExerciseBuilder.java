package seedu.address.testutil;

import seedu.address.model.exercise.*;

/**
 * A utility class to help with building Person objects.
 */
public class ExerciseBuilder {

    public static final String DEFAULT_NAME = "Push up";
    public static final String DEFAULT_DESCRIPTION = "Testing 1 2 3";
    public static final String DEFAULT_DATE = "09-10-2020";
    public static final String DEFAULT_CALORIES = "2254";

    private Name name;
    private Description description;
    private Date date;
    private Calories calories;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public ExerciseBuilder() {
        name = new Name(DEFAULT_NAME);
        description = new Description(DEFAULT_DESCRIPTION);
        date = new Date(DEFAULT_DATE);
        calories = new Calories(DEFAULT_CALORIES);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code ExerciseToCopy}.
     */
    public ExerciseBuilder(Exercise exerciseToCopy) {
        name = exerciseToCopy.getName();
        description = exerciseToCopy.getDescription();
        date = exerciseToCopy.getDate();
        calories = exerciseToCopy.getCalories();
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
     * Sets the {@code Phone} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withDate(String date) {
        this.date = new Date(date);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withCalories(String calories) {
        this.calories = new Calories(calories);
        return this;
    }

    public Exercise build() {
        return new Exercise(name, description, date, calories);
    }

}
