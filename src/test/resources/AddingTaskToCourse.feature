Feature: Adding task to course
  As a student,
  I add a task to a course to do list,
  so I can remember it.

  Background:
    Given the Todo Manager Rest API server is running
    Given the following todos exist in the system:
      | title             | doneStatus | description |
      | Requirements      | false      | Define reqs |
      | Development       | false      | Create app  |
      | Quality Assurance | false      | Test app    |
    Given the following projects exist in the system:
      | title   | completed | active | description   |
      | ECSE100 | false     | true   | Easy class    |
      | ECSE200 | false     | false  | A bit harder  |
      | ECSE300 | false     | true   | Hardest class |

  Scenario Outline: Normal flow - Add a todo to a existing projects todo list
    Given there exists a todo with title <todoTitle> in the system
    Given there exists a project with title <projectTitle> in the system
    When the user attempts to add todo with title <todoTitle> to project with title <projectTitle>
    Then the todo with title <todoTitle> is added to project with title <projectTitle> todo list

    Examples:
      | todoTitle    | projectTitle |
      | Requirements | ECSE100      |
      | Development  | ECSE200      |

  Scenario Outline: Alternate flow - Create a todo and add to an existing project todo list
    Given there exists a project with title <projectTitle> in the system
    Given there does not exist a todo with title <todoTitle> in the system
    When the user attempts to create a todo with title <todoTitle> and done status <status> and description <description>
    When the user attempts to add todo <todoTitle> to project <projectTitle>
    Then a todo is created with title <todoTitle> and done status <status> and description <description>
    Then the todo with title <todoTitle> is added to project with title <projectTitle> todo list

    Examples:
      | projectTitle | todoTitle  | status | description                |
      | ECSE100      | Deployment | false  | Deploy app to web          |
      | ECSE200      | Financing  | false  | Get funds for app creation |

  Scenario Outline: Error flow - Add a todo to a nonexistent projects todo list
    Given there exists a todo with title <todoTitle> in the system
    Given there does not exist a project with title <projectTitle> in the system
    When the user attempts to add todo with title <todoTitle> to project with title <projectTitle>
    Then the system shall report the error code <errorCode>

    Examples:
      | todoTitle    | projectTitle | errorCode |
      | Requirements | COMP100      | 404       |
      | Development  | FACC100      | 404       |
