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
    And the following todos are saved under 'Class A'
      | todoTitle            | todoDoneStatus | todoDescription             |
      | todo A High prio 1   | false          | todo A High prio 1 descr    |
      | todo A Medium prio 1 | false          | todo A Medimum prio 1 descr |
      | todo A Medium prio 2 | true           | todo A Medimum prio 2 descr |
      | todo A Low prio 1    | false          | todo A Low prio 1 descr     |
      | todo A Low prio 2    | true           | todo A Low prio 2 descr     |
    And the following todos are saved under 'Class B1'
      | todoTitle             | todoDoneStatus | todoDescription              |
      | todo B1 High prio 1   | false          | todo B1 High prio 1 descr    |
      | todo B1 High prio 2   | true           | todo B1 High prio 2 descr    |
      | todo B1 Medium prio 1 | false          | todo B1 Medimum prio 1 descr |
      | todo B1 Medium prio 2 | true           | todo B1 Medimum prio 2 descr |
    And the following todos are saved under 'Class B2'
      | todoTitle             | todoDoneStatus | todoDescription              |
      | todo B2 High prio 1   | false          | todo B2 High prio 1 descr    |
      | todo B2 High prio 2   | false          | todo B2 High prio 2 descr    |
      | todo B2 Medium prio 1 | false          | todo B2 Medimum prio 1 descr |
      | todo B2 Medium prio 2 | true           | todo B2 Medimum prio 2 descr |
    And the following todos are saved under 'Class C'
      | todoTitle            | todoDoneStatus | todoDescription             |
      | todo C High   prio 1 | true           | todo C High prio 1 descr    |
      | todo C Medium prio 1 | true           | todo C Medimum prio 1 descr |
      | todo C Medium prio 2 | true           | todo C Medimum prio 2 descr |
      | todo C Low prio 1    | true           | todo C Low prio 1 descr     |
      | todo C Low prio 2    | true           | todo C Low prio 2 descr     |
    And the following todos are saved under 'Class D'
      | todoTitle | todoDoneStatus | todoDescription |

  Scenario Outline: Queury imcomplete tasks from a project (class) with incomplete tasks (Normal Flow)
    Given that <title> is a project in memory
    And that <title> has active tasks
    When I request the incomplete High priority tasks of the project titled <projectTitle>
    Then <n> todos will be returned
    Examples:
      | title    | n |
      | Class B1 | 1 |
      | Class B2 | 2 |

  Scenario Outline: Queury imcomplete tasks from a project (class) with no incomplete tasks (Alternate Flow)
    Given that <title> is a project in memory
    And that <title> has no active tasks
    When I request the incomplete High priority tasks of the project titled <projectTitle>
    Then <n> todos will be returned
    Examples:
      | title   | n |
      | Class C | 0 |

  Scenario Outline: Queury imcomplete tasks from an unactive project (class) with incomplete tasks (Alternate Flow)
    Given that <title> is an unactive project in memory
    When I request the incomplete tasks of the project titled <projectTitle>
    Then <n> todos will be returned
    Examples:
      | title   | n |
      | Class A | 0 |

  Scenario Outline: Queury imcomplete tasks from a project (class) with no tasks (Alternate Flow)
    Given that <title> is a project in memory
    And that <title> has no tasks
    When I request the incomplete tasks of the project titled <projectTitle>
    Then <n> todos will be returned
    Examples:
      | title   | n |
      | Class D | 0 |

  Scenario Outline: Queury imcomplete tasks from a non-existant project (class) (Error Flow)
    Given that <title> is not a project in memory
    When I request the incomplete tasks of the project titled <projectTitle>
    Then <n> todos will be returned
    Examples:
      | title   | n |
      | Class E | 0 | 
  
  
