Feature: Remove task
  As a student,
  I remove an unnecessary task from my course to do list,
  so I can forget about it

  Background:
    Given the Todo Manager Rest API server is running
    #And I am a student
    And the following projects exist in the system
      | projectTitle |
      | ecse429      |
      | comp251      |
    And the following todos are assigned to the project "ecse429"
      | todoTitle |
      | projectA  |
      | projectB  |
    And the following todos are assigned to the project "comp251"
      | todoTitle |
      | projectC  |
      | projectD  |

  Scenario Outline: Remove existent task (Normal Flow)
    Given there exists a todo with title "<todoTitle>" in the system
    When the user attempts to remove todo with title "<todoTitle>" from the system
    Then todo with title "<todoTitle>" shall be removed from the system
    Then there shall be 1 less todo in the system

    Examples:
      | todoTitle     |
      | projectA  |
      | projectC  |

  Scenario Outline: Remove existent task from a given course (Alternate Flow)
    Given there exists a todo with title "<todoTitle>" in the system
    And the todo with title "<todoTitle>" is assigned to a project with title "<projectTitle>"
    When the user attempts to remove the todo with title "<todoTitle>" from the project with title "<projectTitle>"
    Then todo with title "<todoTitle>" shall no longer be associated with the project with title "<projectTitle>"

    Examples:
      | projectTitle | todoTitle |
      | ecse429      | projectB  |
      | comp251      | projectD  |

  Scenario Outline: Remove nonexistent task (Error Flow)
    Given there does not exist a todo with title "<todoTitle>" in the system
    Given there exists a project with title "<projectTitle>" in the system
    When the user attempts to remove the todo with title "<todoTitle>" from the project with title "<projectTitle>"
    Then the system shall report the error code "<errorCode>"
    Then there shall be the same number of todos in the system

    Examples:

      | projectTitle | todoTitle | errorCode |
      | ecse429      | projectE  | 404       |
      | ecse429      | projectF  | 404       |