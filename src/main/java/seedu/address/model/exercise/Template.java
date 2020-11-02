package seedu.address.model.exercise;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Template {
    private String name;
    private String description;
    private Integer calories;
    private List<Muscle> musclesWorked;
    private Set<ExerciseTag> tags = new HashSet<>();


    /**
     * Template constructor
     *
     * All fields must be non-null
     *
     * @param name        name of the template
     * @param description description of the template
     * @param calories    calories of the template
     */
    public Template(String name, String description, Integer calories) {
        requireAllNonNull(name, description, calories);
        this.name = name;
        this.description = description;
        this.calories = calories;
        //this.musclesWorked = musclesWorked;
        //this.tags.addAll(tags);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCalories() {
        return calories;
    }

    public List<Muscle> getMusclesWorked() {
        return musclesWorked;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ExerciseTag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    /**
     * toString method of the template class
     *
     * @return the string representation of the template
     */
    @Override
    public String toString() {
        return "Template{"
                + "name:'" + name + '\''
                + ", description:'" + description
                + '\'' + ", calories:"
                + calories + '}';
    }

    public String parseToArgument() {
        return "n/" + getName() + " d/" + getDescription() + " c/" + getCalories();
    }

    /**
     * Write the template list to the file
     * @param lists template list
     * @throws IOException
     */
    public static void writeToFile(List<Template> lists) throws IOException {
        FileWriter fw = new FileWriter("data/template.txt");
        String textToAdd = "";

        for (int i = 0; i < lists.size(); i++) {
            Template template = lists.get(i);
            textToAdd = textToAdd + template.parseToArgument() + System.lineSeparator();
        }

        fw.write(textToAdd);
        fw.close();
    }

    @Override
    public boolean equals(Object toCreate) {
        Template template = (Template) toCreate;
        return this.getName().equals(template.getName());
    }
}
