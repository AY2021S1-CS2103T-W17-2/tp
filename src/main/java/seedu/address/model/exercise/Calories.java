package seedu.address.model.exercise;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class Calories {

    public static final String MESSAGE_CONSTRAINTS =
            "Calories should be at least 1 digit long, or must be an integer";

    /*
     * The first character must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d+";

    public final String value;

    /**
     * Constructs a {@code Calories}.
     *
     * @param calories A valid input.
     */
    public Calories(String calories) {
        if (calories == null) {
            value = "0";
        } else {
            checkArgument(isValidCalories(calories), MESSAGE_CONSTRAINTS);
            value = calories;
        }
    }

    /**
     * Returns true if a given string is a valid input.
     */
    public static boolean isValidCalories(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.exercise.Calories // instanceof handles nulls
                && value.equals(((Calories) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Subtracts a {@code Calories}.
     *
     * @param calories A valid input.
     */
    public Calories subtract(Calories calories) {
        Integer currentCalories = Integer.parseInt(value);
        Integer removedCalories = Integer.parseInt(calories.value);
        
        return new Calories(String.valueOf(currentCalories - removedCalories));
    }

}
