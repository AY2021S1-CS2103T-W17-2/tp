---
layout: page
title: Phyo Han Project Portfolio Page
---
### Summary of Project
Calo is a desktop app that is designed for keeping track of calories burnt throughout the day.
It is optimized for use via a Command Line Interface (CLI) while still having Graphical User Interface (GUI) so that
Skilled users can carry out various tasks such as adding new exercises and checking records for previous days
much faster than the traditional GUI apps.  

We built Calo upon the [Address Book Level 3 (AB3) project](https://github.com/se-edu/addressbook-level3).

## Summary of Contributions

### Enhancement
**Code contribution:** You can view my code contribution from this [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=nahoyhp&sort=groupTitle&sortWithin=title&since=2020-08-14&until=2020-11-09&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other).  

Below is a summary of the significant contribution that I have made to `Calo`.

* **Major Enhancement:** Added the Ui Component for CaloriesGraph and modify the relevant components to support the display.  
  * What it does: Show the user the trends of Calories burnt for the past few days through the exercises that the user record.
  * Justification: Since Calo aims to hold the User accountable for his exercises, having a graph is a direct way to showing the progress and motivates the user if he/she has not been exercising enough the past few days.   
  * Highlights: This enhancement requires working with a different Javafx component, LineChart and Serires. A deeper understanding of `.fxml` file and the application `SceneBuilder` is obtained from this implementation.

* **Major Enhancement:** Archive Command  
  * What it does: ALlow User to store the current records of `Exercise` into a file location specified by the user.
  * Justification: Since there are multiple records of exercises for the same day, the user have significant records that can slow down the performance of the application.
  * Highlights: Has experience working with `.json` file and writing to file on the system.  

* **Minor Enhancement:** Convert Code From AddressBook3 to `Calo`. 
  * What it does: Since this is a brown-field project, we need to convert the existing code to something that is relevant to what we plan to implement in the future.


* **Project management**:
  * Managed releases `v1.3` - `v1.4` (2 releases) on GitHub
  * Create milestone `v1.2 - v1.4` on GitHub.

* **Enhancements to existing features**:
  * Morphe `AddressBook3` to `Calo`, mainly the `Logic`, `Storage` and `Ui` Command. [\#24](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/24), [\#35](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/35)
  * Implement `archive` command. [\#24](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/24)  

* **Documentation**:
  * User Guide:
    * Added implementation for the features `archive` and `' [\#18](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/18)
    * Did cosmetic tweaks to existing documentation of features  and diagrams: [\#203](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/203) [\#221](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/221) [\#223](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/223) [\#225](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/225)
  * Developer Guide:
    * Added implementation details of the `archive` and `Calories Graph` feature.[\#203](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/203)
    * Did cosmetic tweaks to existing doucmentation of features and diagrams: [\#203](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/203) [\#221](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/221) [\#223](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/223) [\#225](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/225)
   

* **Community**:
  * Fixes bugs causes by Integration different parts: [\#170](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/170) [\#174](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/174) [\#179](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/179)
  * PRs reviewed (with non-trivial review comments): No evidence since most of the review is done in a Zoom Meeting where the group comes together and discuss.
  * Contributed to forum discussions [\#164](https://github.com/nus-cs2103-AY2021S1/forum/issues/164) [\#197](https://github.com/nus-cs2103-AY2021S1/forum/issues/197) [\#227](https://github.com/nus-cs2103-AY2021S1/forum/issues/227) [\#329](https://github.com/nus-cs2103-AY2021S1/forum/issues/329),
  * Reported bugs and suggestions for other teams in the class 
      * Review [fitNUS](https://github.com/AY2021S1-CS2103T-T09-2/tp/)
      * Here are the significants suggestions rasied from the review: [\#164](https://github.com/AY2021S1-CS2103T-T09-2/tp/issues/164) [\#165](https://github.com/AY2021S1-CS2103T-T09-2/tp/issues/165) [\#166](https://github.com/AY2021S1-CS2103T-T09-2/tp/issues/166) [\#168](https://github.com/AY2021S1-CS2103T-T09-2/tp/issues/168)
