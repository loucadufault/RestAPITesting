Feature: Query incomplete high priority tasks
  As a student,
  I query all incomplete HIGH priority tasks from all my classes,
  to identity my short-termgoals

  Background:
    Given the Todo Manager Rest API server is running
    And the following priorities are in the system
      | title  | description           |
      | LOW    | LOW Priority Tasks    |
      | MEDIUM | MEDIUM Priority Tasks |
      | HIGH   | HIGH Priority Tasks   |

  Scenario Outline: Normal flow - Query incomplete high priority tasks from a project (class) with incomplete high priority tasks
    Given there exists the following projects in the system
      | title    | completed | active | description          |
      | Class B1 | false     | true   | Class B1 description |
      | Class B2 | false     | true   | Class B2 description |
    Given there exists the following todos in the system that are saved under "Class B1"
      | todoTitle             | todoDoneStatus | todoDescription              | todoPriority |
      | todo B1 HIGH prio 1   | false          | todo B1 HIGH prio 1 descr    | HIGH         |
      | todo B1 HIGH prio 2   | true           | todo B1 HIGH prio 2 descr    | HIGH         |
      | todo B1 MEDIUM prio 1 | false          | todo B1 Medimum prio 1 descr | MEDIUM       |
      | todo B1 MEDIUM prio 2 | true           | todo B1 Medimum prio 2 descr | MEDIUM       |
    Given there exists the following todos in the system that are saved under "Class B2"
      | todoTitle             | todoDoneStatus | todoDescription              | todoPriority |
      | todo B2 HIGH prio 1   | false          | todo B2 HIGH prio 1 descr    | HIGH         |
      | todo B2 HIGH prio 2   | false          | todo B2 HIGH prio 2 descr    | HIGH         |
      | todo B2 MEDIUM prio 1 | false          | todo B2 Medimum prio 1 descr | MEDIUM       |
      | todo B2 MEDIUM prio 2 | true           | todo B2 Medimum prio 2 descr | MEDIUM       |
    When the user attempts to query the incomplete high priority tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title    | n |
      | Class B1 | 1 |
      | Class B2 | 2 |

  Scenario Outline: Alternate flow - Query incomplete high priority tasks from a project (class) with no incomplete high priority tasks
    Given there exists the following projects in the system
      | title    | completed | active | description          |
      | Class C  | false     | true   | Class C description  |
    Given there exists the following todos in the system that are saved under "Class C"
      | todoTitle            | todoDoneStatus | todoDescription             | todoPriority |
      | todo C HIGH   prio 1 | true           | todo C HIGH prio 1 descr    | HIGH         |
      | todo C MEDIUM prio 1 | false          | todo C Medimum prio 1 descr | MEDIUM       |
      | todo C MEDIUM prio 2 | true           | todo C Medimum prio 2 descr | MEDIUM       |
      | todo C LOW prio 1    | false          | todo C LOW prio 1 descr     | LOW          |
      | todo C LOW prio 2    | true           | todo C LOW prio 2 descr     | LOW          |
    When the user attempts to query the incomplete high priority tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title   | n |
      | Class C | 0 |

  Scenario Outline: Alternate flow - Query incomplete high priority tasks from an inactive project (class) with incomplete high priority tasks
    Given there exists the following projects in the system
      | title    | completed | active | description          |
      | Class A  | false     | false  | Class A description  |
    Given there exists the following todos in the system that are saved under "Class A"
      | todoTitle            | todoDoneStatus | todoDescription             | todoPriority |
      | todo A HIGH prio 1   | false          | todo A HIGH prio 1 descr    | HIGH         |
      | todo A MEDIUM prio 1 | false          | todo A Medimum prio 1 descr | MEDIUM       |
      | todo A MEDIUM prio 2 | true           | todo A Medimum prio 2 descr | MEDIUM       |
      | todo A LOW prio 1    | false          | todo A LOW prio 1 descr     | LOW          |
      | todo A LOW prio 2    | true           | todo A LOW prio 2 descr     | LOW          |
    When the user attempts to query the incomplete high priority tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title   | n |
      | Class A | 0 |

  Scenario Outline: Alternate Flow - Query incomplete high priority tasks from a project (class) with no tasks
    Given there exists the following projects in the system
      | title    | completed | active | description          |
      | Class D  | false     | true   | Class D description  |
    Given the project with title "<title>" has no active tasks
    When the user attempts to query the incomplete high priority tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title   | n |
      | Class D | 0 |

  Scenario Outline: Error Flow - Query incomplete high priority tasks from a nonexistent project (class)
    Given there does not exist the project "<title>" in the system
    When the user attempts to query the incomplete high priority tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title   | n |
      | Class E | 0 | 

