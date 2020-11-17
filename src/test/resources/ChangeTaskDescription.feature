Feature: Change a task description
  As a student,
  I want to change a task description,
  to better represent the work to do.

  Background:
    Given the Todo Manager Rest API server is running
    Given the following todos exist in the system
      | title      | description          |
      | project    | Use Gherkin          |
      | midterm1   | Very hard midterm    |
      | midterm2   |                      |
      | assignment |                      |

  Scenario Outline: Add the description for an existent task (Normal Flow)
    Given there exists a todo with title "<title>" and description "<description>" in the system
    When the user attempts to change the description to "<newDescription>" for the todo with title "<title>"
    Then the todo with title "<title>" shall have description "<newDescription>"

    Examples:
      | title      | description    | newDescription    |
      | midterm2   |                | Chapters 1 to 6 |
      | assignment |                | On unit testing |

  Scenario Outline: Change the description for an existent task (Alternate Flow)
    Given there exists a todo with title "<title>" and description "<description>" in the system
    When the user attempts to change the description to "<newDescription>" for the todo with title "<title>"
    Then the todo with title "<title>" shall have description "<newDescription>"

    Examples:
      | title    | description       | newDescription     |
      | project  | Use Gherkin       | Use Cucumber  |
      | midterm1 | Very hard midterm | Study earlier |

  Scenario Outline: Change the description for a nonexistent task (Error Flow)
    Given there does not exist a todo with title "<title>" in the system
    When the user attempts to change the description to "<newDescription>" for the todo with title "<title>"
    Then the system shall report the error code "<errorCode>"

    Examples:
      | title       | newDescription | errorCode |
      | assignment2 |      fail      | 404       |
      | final       | also fails     | 404       |
