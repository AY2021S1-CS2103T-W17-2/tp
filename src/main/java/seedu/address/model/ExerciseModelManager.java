package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.goal.Goal;
import seedu.address.model.exercise.Template;
import seedu.address.model.exercise.TemplateList;

/**
 * Represents the in-memory model of the exercise book data.
 */
public class ExerciseModelManager implements ExerciseModel {
    private static final Logger logger = LogsCenter.getLogger(ExerciseModelManager.class);

    private final ExerciseBook exerciseBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Exercise> filteredExercises;
    private final FilteredList<Template> filteredTemplates;
    private final GoalBook goalBook;

    /**
     * Initializes a ExerciseModelManager with the given exerciseBook and userPrefs.
     */
    public ExerciseModelManager(ReadOnlyExerciseBook exerciseBook,ReadOnlyGoalBook goalBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(exerciseBook, userPrefs);
        logger.fine("Initializing with exercise book: " + exerciseBook + " and user prefs " + userPrefs);

        this.exerciseBook = new ExerciseBook(exerciseBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredExercises = new FilteredList<>(this.exerciseBook.getExerciseList());
        this.goalBook = new GoalBook(goalBook);
        this.filteredTemplates = new FilteredList<>(this.exerciseBook.getTemplateList());
    }

    public ExerciseModelManager() {
        this(new ExerciseBook(), new GoalBook(),new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getExerciseBookFilePath() {
        return userPrefs.getExerciseBookFilePath();
    }

    @Override
    public Path getGoalBookFilePath() {
        return userPrefs.getGoalBookFilePath();
    }


    @Override
    public void setExerciseBookFilePath(Path exerciseBookFilePath) {
        requireNonNull(exerciseBookFilePath);
        userPrefs.setExerciseBookFilePath(exerciseBookFilePath);
    }

    @Override
    public void setGoalBookFilePath(Path goalBookFilePath) {
        requireNonNull(goalBookFilePath);
        userPrefs.setGoalBookFilePath(goalBookFilePath);
    }

    //=========== ExerciseBook ================================================================================

    @Override
    public void setExerciseBook(ReadOnlyExerciseBook exerciseBook) {
        this.exerciseBook.resetData(exerciseBook);
    }

    @Override
    public ReadOnlyExerciseBook getExerciseBook() {
        return exerciseBook;
    }

    @Override
    public boolean hasExercise(Exercise exercise) {
        requireNonNull(exercise);
        return exerciseBook.hasExercise(exercise);
    }

    @Override
    public void deleteExercise(Exercise target) {
        this.exerciseBook.removeExercise(target);
    }
    

    @Override
    public void addExercise(Exercise exercise) {
        exerciseBook.addExercise(exercise);
        updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISE);
    }

    @Override
    public void addTemplate(Template template) {
        TemplateList.addTemplate(template);
        //updateFilteredExerciseList(PREDICATE_SHOW_ALL_EXERCISE);
    }

    @Override
    public void setExercise(Exercise target, Exercise editedExercise) {
        requireAllNonNull(target, editedExercise);

        exerciseBook.setExercise(target, editedExercise);
    }

    @Override
    public void archive(Path path) {
        System.out.println("Archived");
    }

    //=========== GoalBook ================================================================================
    @Override
    public void setGoalBook(ReadOnlyGoalBook goalBook) {
        this.goalBook.resetData(goalBook);
    }

    @Override
    public void addGoal(Goal goal) {
        goalBook.addGoal(goal);
    }

    @Override
    public boolean hasGoal (Goal goal) {
        requireNonNull(goal);
        return goalBook.hasGoal(goal);
    }

    @Override
    public ReadOnlyGoalBook getGoalBook() {
        return goalBook;
    }

    @Override
    public void deleteGoal(Goal target) {
        this.goalBook.removeGoal(target);
    }

    @Override
    public void setGoal(Goal target, Goal editedGoal) {
        requireAllNonNull(target, editedGoal);

        goalBook.setGoal(target, editedGoal);
    }
    
    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedExerciseBook}
     */
    @Override
    public ObservableList<Exercise> getFilteredExerciseList() {

        return filteredExercises;
    }
    

    @Override
    public ObservableList<Template> getFilteredTemplateList() {

        return TemplateList.getObservableList();
    }

    public HashMap<String, Integer> getCaloriesByDay() {
        return exerciseBook.getCaloriesByDay();
    }

    @Override
    public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
        requireNonNull(predicate);
        filteredExercises.setPredicate(predicate);
    }
    
    public void updateGoalMap() {
        
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ExerciseModelManager)) {
            return false;
        }

        // state check
        ExerciseModelManager other = (ExerciseModelManager) obj;
        return exerciseBook.equals(other.exerciseBook)
                && userPrefs.equals(other.userPrefs)
                && filteredExercises.equals(other.filteredExercises);
    }

}
