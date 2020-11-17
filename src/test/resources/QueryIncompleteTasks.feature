Feature: Query incomplete tasks
  As a student,
  I query the incomplete tasks for a class I am taking,
  to help manage my time.

  Background:
    Given the Todo Manager Rest API server is running
    And the following projects are in memory
      | title    | completed | active | description          |
      | Class A  | false     | false  | Class A description  |
      | Class B1 | false     | true   | Class B1 description |
      | Class B2 | false     | true   | Class B2 description |
      | Class C  | false     | true   | Class C description  |
      | Class D  | true      | true   | Class D description  |
    And the following todos are saved under "Class A"
      | todoTitle            | todoDoneStatus | todoDescription             |
      | todo A High prio 1   | false          | todo A High prio 1 descr    |
      | todo A Medium prio 1 | false          | todo A Medimum prio 1 descr |
      | todo A Medium prio 2 | true           | todo A Medimum prio 2 descr |
      | todo A Low prio 1    | false          | todo A Low prio 1 descr     |
      | todo A Low prio 2    | true           | todo A Low prio 2 descr     |
    And the following todos are saved under "Class B1"
      | todoTitle             | todoDoneStatus | todoDescription              |
      | todo B1 High prio 1   | false          | todo B1 High prio 1 descr    |
      | todo B1 High prio 2   | true           | todo B1 High prio 2 descr    |
      | todo B1 Medium prio 1 | false          | todo B1 Medimum prio 1 descr |
      | todo B1 Medium prio 2 | true           | todo B1 Medimum prio 2 descr |
    And the following todos are saved under "Class B2"
      | todoTitle             | todoDoneStatus | todoDescription              |
      | todo B2 High prio 1   | false          | todo B2 High prio 1 descr    |
      | todo B2 High prio 2   | false          | todo B2 High prio 2 descr    |
      | todo B2 Medium prio 1 | false          | todo B2 Medimum prio 1 descr |
      | todo B2 Medium prio 2 | true           | todo B2 Medimum prio 2 descr |
    And the following todos are saved under "Class C"
      | todoTitle            | todoDoneStatus | todoDescription             |
      | todo C High   prio 1 | true           | todo C High prio 1 descr    |
      | todo C Medium prio 1 | true           | todo C Medimum prio 1 descr |
      | todo C Medium prio 2 | true           | todo C Medimum prio 2 descr |
      | todo C Low prio 1    | true           | todo C Low prio 1 descr     |
      | todo C Low prio 2    | true           | todo C Low prio 2 descr     |
    And the following todos are saved under "Class D"
      | todoTitle | todoDoneStatus | todoDescription |

  Scenario Outline: Normal flow - Query incomplete tasks from a project (class) with incomplete tasks
    Given there exists a project with title <string> in the system
    Given the project with title "<title>" has active tasks
    When the user attempts to query the incomplete high priority tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title    | n |
      | Class B1 | 1 |
      | Class B2 | 2 |

  Scenario Outline: Alternate flow - Query incomplete tasks from a project (class) with no incomplete tasks
    Given there exists a project with title <string> in the system
    Given the project with title "<title>" has no active tasks
    When the user attempts to query the incomplete high priority tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title   | n |
      | Class C | 0 |

  Scenario Outline: Alternate flow - Query incomplete tasks from an inactive project (class) with incomplete tasks
    Given there exists a project with title <string> in the system
    When the user attempts to query the incomplete high priority tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title   | n |
      | Class A | 0 |

  Scenario Outline: Alternate flow - Query incomplete tasks from a project (class) with no tasks
    Given there exists a project with title <string> in the system
    Given the project with title "<title>" has no active tasks
    When the user attempts to query the incomplete high priority tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title   | n |
      | Class D | 0 |

  Scenario Outline: Error flow - Query incomplete tasks from a nonexistant project (class)
    Given there does not exist a project with title <string> in the system
    When the user attempts to query the incomplete high priority tasks of the project with title "<title>"
    Then "<n>" todos will be returned
    Examples:
      | title   | n |
      | Class E | 0 | 
  
  
