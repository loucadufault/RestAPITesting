Feature: 
	As a student, 
    I add a task to a course to do list, 
    so I can remember it.
    
Background:
    	Given the Todo Manager Rest API server is running
        	And I am a student
            And the following todos exist in the system:
            |title | doneStatus | description |
            |Requirements | false | Define reqs |
			|Development | false | Create app |
            |Quality Assurance | false | Test app |
            And the following projects exist in the system:
            |title | completed | active | description |
            |ECSE100      | false          |  true |  Easy class  |
            |ECSE200      | false          |  false| A bit harder |
            |ECSE300      | false          |  true | Hardest class|
            
Scenario Outline: Add a Todo to a Existing Projects Todo List (Normal Flow)
        Given there exists a todo in the system with title <todoTitle>
            And there exists a project in the system with title <projectTitle>
            When I request to add todo <todoTitle> to project <projectTitle>
            Then the todo <todoTitle> is added to project <projectTitle> todo list
            
            Examples:
            
      |todoTitle  |projectTitle |
      |Requirements  |ECSE100  |
      |Development  |ECSE200  |

Scenario Outline: Add a Todo to a Non-existent Projects Todo List (Error Flow)
        Given there exists a todo in the system with title <todoTitle>
            And there does not exist a project in the system with title <projectTitle>
            When I request to add todo <todoTitle> to project <projectTitle>
			Then I receive an error code <errorCode>
            
            Examples:
            |todoTitle  |projectTitle | errorCode |
      		|Requirements  |COMP100  |404 |
      		|Development  |FACC100  |404 |
            
Scenario Outline: Create a Todo and Add to an Existing Projects Todo List (Alternate Flow)
        	Given there exists a project in the system with title <projectTitle>
            And there does not exist a todo in the system with title <todoTitle>
            When I request to create a todo with title <todoTitle> done status <status> and description <description>
            And I request to add todo <todoTitle> to project <projectTitle>
            Then a todo is created with title <todoTitle> done status <status> and description <description>
            And the todo <todoTitle> is added to project <projectTitle> todo list
            
            Examples:
            
      |projectTitle  |todoTitle  |status  |description  |
      |ECSE100  |Deployment  |false  |Deploy app to web  |
      |ECSE200  |Financing  |false  |Get funds for app creation  |

