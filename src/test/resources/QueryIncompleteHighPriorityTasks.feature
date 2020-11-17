Feature: Query incomplete high priority tasks
  As a student,
  I query all incomplete HIGH priority tasks from all my classes,
  to identity my short-termgoals

  Background:
    Given the Todo Manager Rest API server is running
    And the following priorities are in memory
      | title  | description           |
      | Low    | Low Priority Tasks    |
      | Medium | Medium Priority Tasks |
      | High   | High Priority Tasks   |
    And the following projects are in memory
      | title    | completed | active | description          |
      | Class A  | false     | false  | Class A description  |
      | Class B1 | false     | true   | Class B1 description |
      | Class B2 | false     | true   | Class B2 description |
      | Class C  | false     | true   | Class C description  |
      | Class D  | true      | true   | Class D description  |
    And the following todos are saved under 'Class A'
      | todoTitle            | todoDoneStatus | todoDescription             | todoPriority |
      | todo A High prio 1   | false          | todo A High prio 1 descr    | High         |
      | todo A Medium prio 1 | false          | todo A Medimum prio 1 descr | Medium       |
      | todo A Medium prio 2 | true           | todo A Medimum prio 2 descr | Medium       |
      | todo A Low prio 1    | false          | todo A Low prio 1 descr     | Low          |
      | todo A Low prio 2    | true           | todo A Low prio 2 descr     | Low          |
    And the following todos are saved under 'Class B1'
      | todoTitle             | todoDoneStatus | todoDescription              | todoPriority |
      | todo B1 High prio 1   | false          | todo B1 High prio 1 descr    | High         |
      | todo B1 High prio 2   | true           | todo B1 High prio 2 descr    | High         |
      | todo B1 Medium prio 1 | false          | todo B1 Medimum prio 1 descr | Medium       |
      | todo B1 Medium prio 2 | true           | todo B1 Medimum prio 2 descr | Medium       |
    And the following todos are saved under 'Class B2'
      | todoTitle             | todoDoneStatus | todoDescription              | todoPriority |
      | todo B2 High prio 1   | false          | todo B2 High prio 1 descr    | High         |
      | todo B2 High prio 2   | false          | todo B2 High prio 2 descr    | High         |
      | todo B2 Medium prio 1 | false          | todo B2 Medimum prio 1 descr | Medium       |
      | todo B2 Medium prio 2 | true           | todo B2 Medimum prio 2 descr | Medium       |
    And the following todos are saved under 'Class C'
      | todoTitle            | todoDoneStatus | todoDescription             | todoPriority |
      | todo C High   prio 1 | true           | todo C High prio 1 descr    | High         |
      | todo C Medium prio 1 | false          | todo C Medimum prio 1 descr | Medium       |
      | todo C Medium prio 2 | true           | todo C Medimum prio 2 descr | Medium       |
      | todo C Low prio 1    | false          | todo C Low prio 1 descr     | Low          |
      | todo C Low prio 2    | true           | todo C Low prio 2 descr     | Low          |
    And the following todos are saved under 'Class D'
      | todoTitle | todoDoneStatus | todoDescription | todoPriority |

  Scenario Outline: Normal flow - Query incomplete high priority tasks from a project (class) with incomplete high priority tasks
    Given there exists a project with title <title> in the system
    Given the project with title <title> has active tasks
    When the user attempts to query the incomplete high priority tasks of the project with title <title>
    Then <n> todos will be returned
    Examples:
      | title    | n |
      | Class B1 | 1 |
      | Class B2 | 2 |

  Scenario Outline: Alternate flow - Query incomplete high priority tasks from a project (class) with no incomplete high priority tasks
    Given there exists a project with title <title> in the system
    Given the project with title <title> has active tasks
    When the user attempts to query the incomplete high priority tasks of the project with title <title>
    Then <n> todos will be returned
    Examples:
      | title   | n |
      | Class C | 0 |

  Scenario Outline: Alternate flow - Query incomplete high priority tasks from an inactive project (class) with incomplete high priority tasks
    Given there exists a project with title <title> in the system
    Given the project with title <title> is inactive
    When the user attempts to query the incomplete high priority tasks of the project with title <title>
    Then <n> todos will be returned
    Examples:
      | title   | n |
      | Class A | 0 |

  Scenario Outline: Alternate Flow - Query incomplete high priority tasks from a project (class) with no tasks
    Given there exists a project with title <title> in the system
    Given the project with title <title> has no tasks
    When the user attempts to query the incomplete high priority tasks of the project with title <title>
    Then <n> todos will be returned
    Examples:
      | title   | n |
      | Class D | 0 |

  Scenario Outline: Error Flow - Query incomplete high priority tasks from a nonexistent project (class)
    Given there exists a project with title <title> in the system
    When the user attempts to query the incomplete high priority tasks of the project with title <title>
    Then <n> todos will be returned
    Examples:
      | title   | n |
      | Class E | 0 | 

