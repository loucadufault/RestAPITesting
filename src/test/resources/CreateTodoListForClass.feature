Feature: Create todo list for class
  As a student,
  I create a to do list for a new class I am taking,
  so I can manage course work.

  Background:
    Given the Todo Manager Rest API server is running

  Scenario Outline: Normal Flow - Create a new class and specify all fields (a.k.a. project)
    Given there does not exist a project with title "<title>" in the system
    When the user attempts to create a new project with title "<title>" and description "<description>" and completed status "<completed>" and active status "<active>"
    Then the project with title "<title>" and description "<description>" and completed status "<completed>" and active status "<active>" shall be created in the system
    Then there shall be one more project in the system

    Examples:
      | title    | description         | completed | active |
      | ECSE 429 | software validation | false     | true   |
      | COMP 251 | algorithms & data   | true      | true   |
      | MATH 240 | discrete math.      | false     | false  |

  Scenario Outline: Alternate flow - Create a new class (a.k.a. project) without specifying any fields
    When the user attempts to create a new project without specifying any fields
    Then the project with title "<title>" and description "<description>" and completed status "<completed>" and active status "<completed>" shall be created in the system
    Then there shall be one more project in the system

    Examples:
      | title | description | completed | active |
      |       |             | false     | true   |

  Scenario Outline: Error flow - Create a new class (a.k.a. project) and specify an id
    When the user attempts to create a new project with title "<title>" and id "<id>"
    Then the project with title "<title>" and id "<id>" shall not be created in the system
    Then there shall be the same number of projects in the system

    Examples:
      | title    | id |
      | ESCE 420 | 69 |
      | ECSE 429 | 0  |
      | COMP 251 | 25 |

