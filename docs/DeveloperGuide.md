---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ExerciseListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `ExerciseBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding an exercise).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Exercise>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Exercise` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Exercise` needing their own `Tag` object.<br>

![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the exercise book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Updating an exercise

Author: Lee Wei Min

This section details how an `Exercise` is modified using the `update` command.

<div markdown="span" class="alert alert-info">:information_source: **Note:** The update command 
updates an existing exercise, where all fields are optional but at least one field to update must
be specified. For more details, please refer to the [update section](https://ay2021s1-cs2103t-w17-2.github.io/tp/UserGuide.html#33-update-exercises--update) of the user guide
</div>

#### Implementation

We will use the following example command: `update 1 d/30 c/260 m/chest t/home`.

The below sequence diagram details the execution flow:

Here are the steps:
- Step 1: `LogicManager` calls its  `execute` method, supplying the argument "update 1 d/30 c/260 m/chest t/home", which was entered by the user. 
- Step 2: `LogicManager` calls the `exerciseBookParser`'s `parseCommand` method, supplying the user input.
- Step 3: In `parseCommand`, the user input is parsed and its command word (`update`) is matched to the `UpdateCommandParser`. `UpdateCommandParser`'s `parse` method is called, passing in the parsed arguments.
- Step 4: In `UpdateCommandParser`'s `parse` method, a `EditExerciseDescriptor` object
is created. Each field of the parsed arguments are added to the `EditExerciseDescriptor` object. `UpdateCommandParser` then creates an `UpdateCommand` object containing the index of the `exercise` to edit and the `EditExerciseDescriptor` object. In the sequence diagram, the argument `index` refers
to the `Index` object representing the index of the first exercise, while `editExerciseDescriptor`
refers to the `EditExerciseDescriptor` object that contains the data (from the parsed
arguments) to update.
- Step 5: `LogicManager` obtains the `UpdateCommand` object, which is referenced by the `command` variable. It then executes the `execute` method of  the `UpdateCommand` object.
- Step 6: In the `execute` method, the `UpdateCommand` object calls `getFilteredExerciseList` to 
to obtain `lastShownExerciseList`. The `Exercise` to edit is retrieved from the `lastShownExerciseList` using the `index`, and assigned to `exerciseToEdit`. Another `Exercise` object, named `editedExercise` is created to hold the data to be updated. The `UpdateCommand` object then calls the `setExercise` method of `Model`, with `exerciseToEdit` and `editedExercise`.
- Step 7: A new `CommandResult` is created containing the message to be displayed to the user,
which is "Edited Exercise: Name: running Description: 30 Date: 10-12-2020 Calories: 260 Muscles worked:[chest] Tags: [home]". This `CommandResult` is returned to `LogicManager`.

<div markdown="span" class="alert alert-info">:information_source: **Note:** The activation bar
of commandResult should be joined to the side of the box representing the commandResult instance.
Due to a limitation of PlantUML, it is not possible to do so here.
</div>

--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage their workouts
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage workouts faster than a typical mouse/GUI driven desktop/mobile app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | user                                       | add an exercise                | keep track of calories burnt through the day                           |
| `* * *`  | user                                       | have a system that tolerate invalid/incomplete command      |                                                                        |
| `* * *`  | data conscious user                        | list down all the exercises for the day       | monitor the calories burned accurately                                   |
| `* * *`  | user                                       | delete an exercise in case I key in wrongly          |  |
| `* *`    | user                                       | update an exercise             |                 |
| `* *`    | user                                       | save my data in a file         | import the saved data into the new computer                                                 |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Calo` and the **Actor** is the `user`, unless specified otherwise)

  #### **Use case: add an exercise**

  *MSS*

1.  User requests to add an exercise

2.  Calo adds the exercise
  Use case ends.

#### **Use case: Find exercises with a keyword**

*MSS*

1.  User requests to find exercises with a keyword
2.  Calo shows a list of exercises which contain the keyword
    Use case ends.

*Extensions*
<p>
    The list is empty <br>
      1a1. Calo shows a message indicating that no such exercise exists.<br>
    Use case ends.
</p>

#### **Use case: Update an exercise**

*MSS*

1.  User requests to update a specific exercise in the list
2.  Calo updates the exercise
    Use case ends.<br>
*Extensions*
<p>
    1a. The index is invalid<br>
      1a1. Calo shows a message indicating that no such exercise exists.<br>
    Use case ends.
</p>

#### **Use case: Delete an exercise**

*MSS*

1.  User requests to delete a specific exercise in the list.
2.  Calo deletes the exercise

    Use case ends.

*Extensions*
<p>
    1a. The index is invalid<br>
      1a1. Calo shows a message indicating that no such exercise exists.<br>
    Use case ends.
</p>

  #### **Use case: Archive data**
  *MSS*
  1.  User requests to archive data to a different file location
  2.  Calo archives data to the specified location
    Use case ends.

*Extensions*
<p>
    1a. User does not have permission to create file at specified location <br>
      1a1. Calo shows a message indicating that file cannot be created at specified file.<br>
    Use case ends.
</p>

  #### **Use case: List exercises**

  *MSS*<br>
  1.  User requests to list exercises
  2.  Calo shows a list of exercises
      Use case ends.

### Non-Functional Requirements
1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 exercise items without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Exercise**: an exercise record entered by the user, consisting of exercise name, description, and date (optionally calories)
--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown
1. Initial launch
   1. Download the jar file and copy into an empty folder
   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.
1. Saving window preferences
   1. Resize the window to an optimum size. Move the window to a different location. Close the window.
   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.
1. _{ more test cases …​ }_

### Deleting an exercise
1. Deleting an exercise while all exercises are being shown
   1. Prerequisites: List all exercises using the `list` command. Multiple exercises in the list.
   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.
   1. Test case: `delete 0`<br>
      Expected: No exercise is deleted. Error details shown in the status message. Status bar remains the same.
   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.
1. _{ more test cases …​ }_


### Saving data

1. Dealing with missing/corrupted data files
   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_
1. _{ more test cases …​ }_
