Feature:
  As a student,
  I categorize tasks as HIGH, MEDIUM or LOW priority,
  so i can better manage my time.

  Background:
    Given the Todo Manager Rest API server is running
    And I am a student
    And the following todos exist in the system:
      | title             | doneStatus | description |
      | Requirements      | false      | Define reqs |
      | Development       | false      | Create app  |
      | Quality Assurance | false      | Test app    |


  Scenario Outline: Assign Priority to Existing Task (Normal Flow)
    Given there exists a todo in the system with title <title>
    When I request to assign priority <priority> to todo <title>
    Then the todo <title> will be assigned priority <priority>
    Examples:
      | title             | priority |
      | Requirements      | LOW      |
      | Quality Assurance | MEDIUM   |

  Scenario Outline: Assign Priority to Non-Existing Task (Error Flow)
    Given there does not exist a todo in the system with title <title>
    When I request to assign priority <priority> to todo <title>
    Then I receive an error code <errorCode>
    Examples:
      | title       | priority | errorCode |
      | Deployment  | HIGH     | 404       |
      | Brain-Storm | MEDIUM   | 404       |

  Scenario Outline: Assign Priority to Existing Task With Already Assigned Priority (Alternate Flow)
    Given there exists a todo in the system with title <title>
    And the todo <title> is assigned priority <priority1>
    When I request to assign priority <priority2> to todo <title>
    Then the priority of todo <title> is updated from <priority1> to <priority2>
    Examples:
      | title             | priority1 | priority2 |
      | Requirements      | LOW       | MEDIUM    |
      | Quality Assurance | MEDIUM    | HIGH      |
        