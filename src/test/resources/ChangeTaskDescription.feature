Feature: Change a task description

As a student, 
I want to change a task description,
to better represent the work to do.

Background:
Given the todo manager rest API is running
And I am a student
And the following todos exist in the system

      | title | description |
      | project | "Use Gherkin!" |
      | midterm1 | "Very hard midterm!" |
      | midterm2 |  |
      | assignment |  |

Scenario Outline: Add the description for an existent task (Normal Flow)
Given todo with title <title> exists
And todo with title <title> does not have a description
When I request to add a description <newDescription> to the todo with title <title>
Then the description <newDescription> is added to the todo with title <title>
Examples:

      | title | newDescription |
      | midterm2 | "Chapters 1 to 6" |
      | assignment | "On unit testing" |

Scenario Outline: Change the description for an existent task (Alternate Flow)
Given todo with title <title> exists
And todo with title <title> has a description <description1>
When I request to change the description of todo with title <title> from <description1> to <description2>
Then the description of todo with title <title> is changed to <description2>
Examples:

      | title | description1 | description2 |
      | project | "Use Gherkin!" | "Use Cucumber!" |
      | midterm1 | "Very hard midterm!" | "Study earlier!" |

Scenario Outline: Change the description for a nonexistent task (Error Flow)
Given todo with title <title> does not exist
When I request to change the description of todo with title <title>
Then I receive an error code <errorCode>
Examples:

      | title | errorCode |
      | assignment2 | 404 |
      | final | 404 |
