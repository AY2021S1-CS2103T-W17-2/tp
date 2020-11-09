---
layout: page
title: Lee Wei Min's Project Portfolio Page
---

## Project: Calo

Calo is a desktop app that is designed for keeping track of calories burnt throughout the day. It is optimized for use via a Command Line Interface (CLI) while still having Graphical User Interface (GUI).

We built Calo upon the [Address Book Level 3 (AB3) project](https://github.com/nus-cs2103-AY1920S1/addressbook-level3).

Given below are my contributions to the project.

* **New Feature**: Added the ability to update a `Exercise` record.
  * What it does: Allow the user to edit the `Exercise` at the specified `INDEX`. For more details, please refer to the [User Guide](https://ay2021s1-cs2103t-w17-2.github.io/tp/UserGuide.html#33-update-exercises--update).
  * Justification: This feature improves the product significantly because a user can update the `Exercise` record (eg. when he has made a mistake in adding an `Exercise` entry). The app should provide a convenient way to do so.
  * Highlights: This enhancement is a refactor of the existing `edit` commands in AB3. It required an in-depth analysis of the initial codebase, architecture and design alternatives. The implementation was challenging as it required changes throughout the stack: front-end to display the updated `Exercise` record, and back-end to evaluate the different fields in `Exercise`).

* **New Feature**: Added a `help` command that displays a link to the user guide.
  * Justification: This feature allows the user to conveniently access all available commands with a simple command.

* **New Feature**: Added an `exit` command that allows the user to exit the app.
  * Justification: This feature allows the user to exit the app using a simple command, without having to point the mouse to the close button.

* **New Feature**: Added an `clear` command that allows the user to clear all existing `Exercise` entries.
  * Justification: This feature allows the user to reset his logs using a simple command.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=leeweiminsg)

* **Project management**:
  * Managed [releases](https://github.com/AY2021S1-CS2103T-W17-2/tp/releases) `v1.3.1`, `v1.3.2`, `v1.4 Trial` (3 releases) on GitHub
  * Cleaned up code remnants from AB3: [\#86](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/86)
  * Documentation: [\#8](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/8), [\#96](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/96/files)
  * UG: [\#168](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/168), [\#172](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/172)
  * DG: [\#26](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/26)
  * Helped teammates (bug-fix, enhancements): [\#61](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/61) , [\#70](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/70), [\#95](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/95/files), [\#98](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/98), [\#104](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/104)

* **Enhancements to existing features**:
  * Added `MuscleTag` and `ExerciseTag` fields for `Exercise`: [\#72](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/72), [\#79](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/79), [\#83](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/83)
  * Made `Date` and `Calories` optional for `add` command: [\#79](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/79)
  * Wrote additional test cases (for me and teammates) to increase code coverage: [\#38](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/38), [\#41](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/41) [\#72](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/72)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `help`, `exit` and `clear` commands: [\#72]()
    * Added documentation for `MuscleTag`: [\#173](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/173)
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Refactored architecture section: [\#184](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/184), [\#187](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/187), [\#192](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/192), [\#194](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/194)
    * Added implementation details of the `update` feature: [\#206](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/206), [\#208](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/208)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#24](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/24), [\#29](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/29), [\#31](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/31), [\#32](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/32), [\#33](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/33), [\#35](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/35), [\#55](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/55), [\#62](https://github.com/AY2021S1-CS2103T-W17-2/tp/pull/62)
  * Reported bugs and suggestions for other teams in the class - PE dry run: [\#162](https://github.com/AY2021S1-CS2103T-T17-4/tp/issues/162), [\#163](https://github.com/AY2021S1-CS2103T-T17-4/tp/issues/163), [\#164](https://github.com/AY2021S1-CS2103T-T17-4/tp/issues/164), [\#165](https://github.com/AY2021S1-CS2103T-T17-4/tp/issues/165), [\#166](https://github.com/AY2021S1-CS2103T-T17-4/tp/issues/166), [\#167](https://github.com/AY2021S1-CS2103T-T17-4/tp/issues/167), [\#168](https://github.com/AY2021S1-CS2103T-T17-4/tp/issues/168), [\#169](https://github.com/AY2021S1-CS2103T-T17-4/tp/issues/169), [\#170](https://github.com/AY2021S1-CS2103T-T17-4/tp/issues/170), [\#171](https://github.com/AY2021S1-CS2103T-T17-4/tp/issues/171)