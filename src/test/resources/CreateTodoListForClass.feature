Feature: Create todo list for class
  As a student,
  I create a to do list for a new class I am taking,
  so I can manage course work.

  Background:
    Given the Todo Manager Rest API server is running

  Scenario Outline: Normal Flow - Create a new class amd specify all fields (a.k.a. project)
    Given the project with title "<title>" does not exist in the system
    When the user attempts to create a new class with title "<title>" and description "<description>" and completed status "<completed>" and active status "<active>"
    Then the project with title "<title>" and description "<description>" shall be created in th system
    Then the number of projects in the system shall be increased by 1

    Examples:
    | title    | description           | completed | active |
    | ECSE 429 | software validation   | false     | true   |
    | COMP 251 | algorithms & data     | true      | true   |
    | MATH 240 | discrete math.        | false     | false  |

  Scenario: Alternate flow - Create a new class (a.k.a. project) without specifying any fields
    When the user attempts to create a new class without specifying any fields
    Then the project with title "" and description "" and completed status true and active status true shall be created in the system
    Then the number of projects in the system shall be increased by 1

  Scenario Outline: Error flow - Create a new class (a.k.a. project) and specify an id
    When the user attempts to create a new class with title "<title>" and id "<id>"
    Then the project with title "<title>" and id "<id>" shall not be created in the system
    Then the number of projects in the system shall be the same

    Examples:
    | title    | id |
    | ESCE 420 | 69 |
    | ECSE 429 | 0  |
    | COMP 251 | 25 |

