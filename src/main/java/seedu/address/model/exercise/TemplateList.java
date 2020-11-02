package seedu.address.model.exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.parser.AddTemplateCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

public class TemplateList {
    private static ArrayList<Template> list = new ArrayList<Template>();
    private static ObservableList<Template> observableList = FXCollections.observableArrayList();

    public static List<Template> getList() {
        return list;
    }

    public static ObservableList<Template> getObservableList() {
        return observableList;
    }

    public static Template getTemp(String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                return list.get(i);
            }
        }
        return null;
    }

    /**
     * Adds a template to the list.
     */
    public static void addTemplate(Template template) {
        list.add(template);
        observableList.add(template);
    }

    /**
     * Load the template list from the file
     */
    public static void load() {
        String filePath = "data/template.txt";
        File f = new File(filePath);
        if (f.exists()) {
            try {
                Scanner s = new Scanner(f);
                readTask(f);
            } catch (FileNotFoundException e) {
                new File("data").mkdir();
            }
        } else {
            new File("data").mkdir();
        }
    }

    /**
     * Read from the template file
     * @param f file to be read from
     */
    public static void readTask(File f) {
        list = new ArrayList<>();
        AddTemplateCommandParser parser = new AddTemplateCommandParser();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String string = s.nextLine();
                list.add(parser.parseTemp(" " + string));
                observableList.add(parser.parseTemp(" " + string));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check whether the given template is equal to any of the template in the list
     * @param template template to check
     * @return true if the template is equal to some template in the list, false otherwise
     */
    public static boolean checkEqual(Template template) {
        for (int i = 0; i < list.size(); i++) {
            if (template.equals(list.get(i))) {
                return true;
            }
        }

        return false;
    }
}
