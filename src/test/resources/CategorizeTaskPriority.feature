Feature: Categorize Task Priority
  As a student,
  I categorize tasks as HIGH, MEDIUM or LOW priority,
  so i can better manage my time.

  Background:
    Given the Todo Manager Rest API server is running
    Given the following categories exist in the system:
      | title  | description|
      | LOW    | false      |
      | MEDIUM | false      |
      | HIGH   | false      |
    Given the following todos exist in the system:
      | title             | doneStatus | description |
      | Requirements      | false      | Define reqs |
      | Development       | false      | Create app  |
      | Quality Assurance | false      | Test app    |


  Scenario Outline: Normal flow - Assign priority to existing task
    Given there exists a todo with title "<title>" in the system
    When the user attempts to assign priority "<priority>" to todo "<title>"
    Then the todo "<title>" will be have priority "<priority>"
    Examples:
      | title             | priority |
      | Requirements      | LOW      |
      | Quality Assurance | MEDIUM   |

  Scenario Outline: Alternate flow - Assign priority to existing task with already assigned priority
    Given there exists a todo with title "<title>" in the system
    Given the todo "<title>" is assigned priority "<priority1>"
    When the user attempts to assign priority "<priority2>" to todo "<title>"
    Then the priority of todo "<title>" is updated from "<priority1>" to "<priority2>"
    Examples:
      | title             | priority1 | priority2 |
      | Requirements      | LOW       | MEDIUM    |
      | Quality Assurance | MEDIUM    | HIGH      |

  Scenario Outline: Error flow - Assign priority to nonexistent task
    Given there does not exist a todo with title "<title>" in the system
    When the user attempts to assign priority "<priority>" to todo "<title>"
    Then the system shall report the error code "<errorCode>"
    Examples:
      | title       | priority | errorCode |
      | Deployment  | HIGH     | 404       |
      | Brain-Storm | MEDIUM   | 404       |