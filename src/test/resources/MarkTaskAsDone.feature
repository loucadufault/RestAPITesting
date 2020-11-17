Feature: Mark a task as done
  As a student,
  I mark a task as done on my course to do list,
  so I can track my accomplishments.

  Background:
    Given the Todo Manager Rest API server is running
    And the following todos exist in the system:
      | title       | doneStatus |
      | project     | false      |
      | assignmentA | false      |
      | assignmentB | true       |
      | midterm     | true       |

  Scenario Outline: Normal flow - Mark uncompleted task as done
    Given there exists a todo with title "<title>" and done status "<doneStatus>" in the system
    When user attempts to set done status to "<doneStatus>" for the todo with title "<title>"
    Then the todo with title "<title>" shall have done status "<doneStatus>"
    Examples:

      | title       | doneStatus |
      | assignmentA | false      |
      | project     | false      |

  Scenario Outline: Alternate flow - Mark completed task as done
    Given there exists a todo with title "<title>" and done status "<doneStatus>" in the system
    When the user attempts to set done status to "<doneStatus>" for the todo with title "<title>"
    Then todo with title "<title>" shall have done status "<doneStatus>"
    Examples:

      | title       | doneStatus |
      | assignmentB | true       |
      | midterm     | true       |

  Scenario Outline: Error flow - Mark nonexistent task as done
    Given there does not exist a todo with title "<title>" in the system
    When the user attempts to set done status to "<doneStatus>" for the todo with title "<title>"
    Then the system shall report the error code "<errorCode>"
    Examples:

      | title       | errorCode |
      | assignmentC | 404       |
      | final       | 404       |
