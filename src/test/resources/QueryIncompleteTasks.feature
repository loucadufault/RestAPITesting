Feature: Query incomplete tasks
  As a student,
  I query the incomplete tasks for a class I am taking,
  to help manage my time.

  Background:
    Given the Todo Manager Rest API server is running

  Scenario Outline: Normal flow - Query incomplete tasks from a project (class) with incomplete tasks
    Given there exists the following projects in the system
      | title    | completed | active | description          |
      | Class B1 | false     | true   | Class B1 description |
      | Class B2 | false     | true   | Class B2 description |
    Given there exists the following todos in the system that are saved under "Class B1"
      | todoTitle             | todoDoneStatus | todoDescription              |
      | todo B1 HIGH prio 1   | false          | todo B1 HIGH prio 1 descr    |
      | todo B1 HIGH prio 2   | true           | todo B1 HIGH prio 2 descr    |
      | todo B1 MEDIUM prio 1 | false          | todo B1 Medimum prio 1 descr |
      | todo B1 MEDIUM prio 2 | true           | todo B1 Medimum prio 2 descr |
    Given there exists the following todos in the system that are saved under "Class B2"
      | todoTitle             | todoDoneStatus | todoDescription              |
      | todo B2 HIGH prio 1   | false          | todo B2 HIGH prio 1 descr    |
      | todo B2 HIGH prio 2   | false          | todo B2 HIGH prio 2 descr    |
      | todo B2 MEDIUM prio 1 | false          | todo B2 Medimum prio 1 descr |
      | todo B2 MEDIUM prio 2 | true           | todo B2 Medimum prio 2 descr |
    When the user attempts to query the incomplete tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title    | n |
      | Class B1 | 2 |
      | Class B2 | 3 |

  Scenario Outline: Alternate flow - Query incomplete tasks from a project (class) with no incomplete tasks
    Given there exists the following projects in the system
      | title    | completed | active | description          |
      | Class C  | false     | true   | Class C description  |
    Given there exists the following todos in the system that are saved under "Class C"
      | todoTitle            | todoDoneStatus | todoDescription             |
      | todo C HIGH   prio 1 | true           | todo C HIGH prio 1 descr    |
      | todo C MEDIUM prio 1 | true           | todo C Medimum prio 1 descr |
      | todo C MEDIUM prio 2 | true           | todo C Medimum prio 2 descr |
      | todo C LOW prio 1    | true           | todo C LOW prio 1 descr     |
      | todo C LOW prio 2    | true           | todo C LOW prio 2 descr     |
    Given the project with title "<title>" has no active tasks
    When the user attempts to query the incomplete tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title   | n |
      | Class C | 0 |

  Scenario Outline: Alternate flow - Query incomplete tasks from an inactive project (class) with incomplete tasks
    Given there exists the following projects in the system
      | title    | completed | active | description          |
      | Class A  | false     | false  | Class A description  |
    Given there exists the following todos in the system that are saved under "Class A"
      | todoTitle            | todoDoneStatus | todoDescription             |
      | todo A HIGH prio 1   | false          | todo A HIGH prio 1 descr    |
      | todo A MEDIUM prio 1 | false          | todo A Medimum prio 1 descr |
      | todo A MEDIUM prio 2 | true           | todo A Medimum prio 2 descr |
      | todo A LOW prio 1    | false          | todo A LOW prio 1 descr     |
      | todo A LOW prio 2    | true           | todo A LOW prio 2 descr     |
    When the user attempts to query the incomplete tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title   | n |
      | Class A | 0 |

  Scenario Outline: Alternate flow - Query incomplete tasks from a project (class) with no tasks
    Given there exists the following projects in the system
      | title    | completed | active | description          |
      | Class D  | true      | true   | Class D description  |
    Given the project with title "<title>" has no active tasks
    When the user attempts to query the incomplete tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title   | n |
      | Class D | 0 |

  Scenario Outline: Error flow - Query incomplete tasks from a nonexistant project (class)
    Given there does not exist the project "<title>" in the system
    When the user attempts to query the incomplete tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title   | n |
      | Class E | 0 | 
  
  
