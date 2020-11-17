Feature: Remove task

As a student, 
I remove an unnecessary task from my course to do list,
so I can forget about it

Background:
Given the Todo Manager Rest API server is running
And I am a student
And the following projects exist in the system

      | projectTitle |
      | ecse429 |
      | comp251 |
      
And the following todos are assigned to the project "ecse429"

      | todoTitle |  
      | projectA |  
      | projectB | 
      
And the following todos are assigned to the project "comp251"
      
      | todoTitle |
      | projectC |  
      | projectD |  

Scenario Outline: Remove existent task (Normal Flow)
Given todo with title <todoTitle> exists
When I request to remove todo with title <todoTitle> from the system
Then todo with title <todoTitle> is removed from the system
Examples:

      | todoTitle |
      | projectA |
      | projectC |

Scenario Outline: Remove existent task from a given course (Alternate Flow)
Given project with title <projectTitle> exists
And todo with title <todoTitle> exists and is assigned to a project with title <projectTitle>
When I request to remove todo with title <todoTitle> from the project with title <projectTitle>
Then todo with title <todoTitle> is removed from the project
Examples:
      
      | projectTitle | todoTitle |
      | ecse429 | projectB |
      | comp251 | projectD |

Scenario Outline: Remove nonexistent task (Error Flow)
Given todo with title <todoTitle> does not exist
And project with title <projectTitle> exists
When I request to remove todo with title <todoTitle> from the course
Then I receive an error code <errorCode>
Examples:
      
      | projectTitle | todoTitle | errorCode |
      | ecse429 | projectE | 404 |
      | ecse429 | projectF | 404 |