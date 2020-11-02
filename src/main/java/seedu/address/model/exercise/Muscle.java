package seedu.address.model.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents the exercise's muscles worked.
 * Based on major muscle groups.
 */
public enum Muscle {
    SHOULDERS("shoulder"),
    CHEST("chest"),
    ARMS("arm"),
    BACK("back"),
    ABDOMINALS("ab"),
    LEG("leg");

    public static final String DELIMITER = ",";
    public static final String MESSAGE_CONSTRAINTS;
    private static final Map<String, Muscle> ENUM_MAP;

    private String name;

    Muscle(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // Build an immutable map of String name to enum pairs.
    // Any Map impl can be used.

    static {
        Map<String, Muscle> map = new HashMap<String, Muscle>();
        for (Muscle muscle : Muscle.values()) {
            map.put(muscle.getName(), muscle);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
        MESSAGE_CONSTRAINTS = String.format(
                "Muscles worked should be in the following format: m/MUSCLE_1,MUSCLE_2,...\n"
                        + "Muscle names must belong to one of the following:\n"
                        + "%s\n"
                        + "No whitespaces between commas!", muscleListToString(Arrays.asList(Muscle.values())));
    }
    public static Muscle get(String name) {
        return ENUM_MAP.get(name);
    }

    /**
     * Returns true if a given string is a valid input.
     */
    public static boolean isValidMusclesWorked(String test) {
        String[] musclesWorkedArr = test.split(DELIMITER);

        for (String muscleStr: musclesWorkedArr) {
            if (!ENUM_MAP.containsKey(muscleStr)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Utility method to convert string representation of muscles worked to list (for JsonAdaptedExercise).
     */
    public static List<Muscle> stringToMuscleList(String musclesWorked) {
        String[] musclesWorkedArr = musclesWorked.split(DELIMITER);

        List<Muscle> musclesWorkedLst = new ArrayList<>();

        for (String muscleStr: musclesWorkedArr) {
            Muscle muscle = Muscle.get(muscleStr);
            musclesWorkedLst.add(muscle);
        }

        return musclesWorkedLst;
    }

    /**
     * Utility method to convert list of muscles worked to its string representation (for JsonAdaptedExercise).
     */
    public static String muscleListToString(List<Muscle> musclesWorked) {
        return musclesWorked.stream().map(muscle -> muscle.toString())
                .collect(Collectors.joining(DELIMITER));
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
