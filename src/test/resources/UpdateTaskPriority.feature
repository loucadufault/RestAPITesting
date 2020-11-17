Feature: Update Task Priority
  As a student,
  I want to adjust the priority of a task,
  to better manage my time.

  Background:
    Given the Todo Manager Rest API server is running
    And the following todos exist in the system:
      | title             | doneStatus | description |
      | Requirements      | false      | Define reqs |
      | Development       | false      | Create app  |
      | Quality Assurance | false      | Test app    |

  Scenario Outline: Normal flow - Update priority of existing todo with assigned priority
    Given there exists a todo with title "<title>" in the system
    Given the todo "<title>" is assigned priority "<priority>"
    When the user attempts to assign priority "<newPriority>" to todo "<title>"
    Then the priority of todo "<title>" is updated from "<priority>" to "<newPriority>"

    Examples:
      | title             | priority  | newPriority |
      | Requirements      | LOW       | MEDIUM      |
      | Quality Assurance | MEDIUM    | HIGH        |

  Scenario Outline: Alternate flow - Update Priority of Existing Todo without Assigned Priority
    Given there exists a todo with title "<title>" in the system
    Given the todo with title "<title>" is not assigned to a priority
    When the user attempts to assign priority "<newPriority>" to todo "<title>"
    Then the todo "<title>" will have priority "<newPriority>"

    Examples:
      | title             | newPriority |
      | Requirements      | LOW         |
      | Quality Assurance | MEDIUM      |

  Scenario Outline: Error flow - Update priority of nonexistent todo
    Given there does not exist a todo with title "<title>" in the system
    When the user attempts to assign priority "<newPriority>" to todo "<title>"
    Then the system shall report the error code "<errorCode>"

    Examples:
      | title       | newPriority | errorCode |
      | Deployment  | HIGH        | 404       |
      | Brain-Storm | MEDIUM      | 404       |