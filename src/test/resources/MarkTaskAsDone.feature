Feature: Mark a task as done

As a student,
I mark a task as done on my course to do list,
so I can track my accomplishments.

Background:
Given the Todo Manager Rest API server is running
And I am a student
And the following todos exist in the system:

      | title | doneStatus |
      | project | false |
      | assignmentA | false |
      | assignmentB | true |
      | midterm | true |

Scenario Outline: Mark uncompleted task as done (Normal Flow)
Given todo with title <title> exists
And todo with title <title> has doneStatus set to false
When I request to set doneStatus of todo with title <title> to true
Then doneStatus of todo with title <title> is set to true
Examples:

      | title | doneStatus |
      | assignmentA | false |
      | project | false |

Scenario Outline: Mark completed task as done (Alternate Flow)
Given todo with title <title> exists
And todo with title <title> has doneStatus set to true
When I request to set doneStatus of todo with title <title> to true
Then todo with title <title> will not be modified
Examples:

      | title | doneStatus |
      | assignmentB | true |
      | midterm | true |

Scenario Outline: Mark nonexistent task as done (Error Flow)
Given todo with title <title> does not exist
When I request to set doneStatus of todo with title <title> to true
Then I will receive an error code <errorCode>
Examples:

      | title | errorCode |
      | assignmentC | 404 |
      | final | 404 |
