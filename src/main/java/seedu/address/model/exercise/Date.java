package seedu.address.model.exercise;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be in the format of DD-MM-YYYY";
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public final String value;
    // name collision with Date class
    private java.util.Date actualDate;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid input.
     */
    public Date(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        if (date == null) {
            actualDate = new java.util.Date();
            value = dateFormat.format(actualDate);
        } else {
            checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);

            try {
                actualDate = dateFormat.parse(date);
            } catch (java.text.ParseException e) {
                // actually ParseException will never be thrown because we already check if the input is valid.
                actualDate = null;
            }

            value = date;
        }
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        try {
            timeFormatter.parse(test);

        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public boolean isBefore(Date other) {
        return actualDate.before(other.actualDate);
    }

    public java.util.Date getActualDate() {
        return actualDate;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.exercise.Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
