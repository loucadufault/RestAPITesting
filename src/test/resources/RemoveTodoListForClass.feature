Feature: Remove todo list for class
  As a student,
  I remove a to do list for a class which I am no longer taking,
  to declutter my schedule.

  Background:
    Given the Todo Manager Rest API server is running

  Scenario Outline: Normal Flow - Remove a class (i.e. project) with no related tasks (a.k.a. todos)
    Given the project with title "<title>" exists in the system
    Given the project with title "<title>" has no related tasks
    When the user attempts to delete the project with title "<title>"
    Then the project with title "<title>" shall be removed from the system
    Then there shall be 1 less project in the system
    Then the the number of tasks in the system shall remain the same

    Examples:
      | title    |
      | ECSE 429 |
      | COMP 251 |

  Scenario Outline: Alternate flow - Remove a class (a.k.a. project) with a related task (a.k.a. todo)
    Given the project with title "<projectTitle>" exists in the system
    Given the project with title "<projectTitle>" has the related todo with title "<todoTitle>" and done status "<todoDoneStatus>" and description "<todoDescription>"
    When the user attempts to remove the project with title "<projectTitle>"
    Then the project with title "<title>" shall be removed from the system
    Then there shall be 1 less project in the system
    Then the task with title "<taskTitle>" shall be removed from the system
    Then there shall be 1 less todo in the system

    Examples:
      | projectTitle | todoTitle | todoDoneStatus | todoDescription |
      | ECSE 429     | project B | false          | pickle          |
      | COMP 252     | Ass 2     | false          | greedy          |

  Scenario Outline: Error flow - Remove a class (a.k.a. project) that does not exist
    Given the project with title "<title>" does not exist in the system
    When the user attempts to remove the project with title "<title>"
    Then the system shall report "<error>"
    Then there shall be the same number of projects in the system

    Examples:
      | title    | error |
      | MATH 240 | blah  |
      | ECSE 223 | blah  |




