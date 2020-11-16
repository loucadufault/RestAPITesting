Feature: 
	As a student,
    I want to adjust the priority of a task,
    to better manage my time.
    
    Background: 
    	Given the Todo Manager Rest API server is running
        	And I am a student
            And the following todos exist in the system:
            |title | doneStatus | description |
            |Requirements | false | Define reqs |
			|Development | false | Create app |
            |Quality Assurance | false | Test app |
            
	Scenario Outline: Update Priority of Existing Todo with Assigned Priority (Normal Flow)
    	Given there exists a todo in the system with title <title>
        And the todo <title> is assigned priority <priority1>
        When I request to update todo <title> priority to priority <priority2>
		Then the priority of todo <title> is updated from <priority1> to <priority2>
        
        Examples:
        |title | priority1 | priority2 |
        |Requirements | LOW | MEDIUM |
		|Quality Assurance | MEDIUM | HIGH |
        
	Scenario Outline: Update Priority of Non-Existing Todo (Error Flow)
      	Given there does not exist a todo in the system with title <title>
		When I request to update todo <title> priority to priority <priority>
        Then I recieve an error code <errorCode>
        
        Examples:
        |title | priority | errorCode |
        |Deployment | HIGH | 404 |
		|Brain-Storm | MEDIUM | 404 |
        
	Scenario Outline: Update Priority of Existing Todo without Assigned Priority (Alternate Flow)
    	Given there exists a todo in the system with title <title>
        And the todo <title> is not assigned to a priority
        When I request to update todo <title> priority to priority <priority>
		Then the todo <title> will be assigned priority <priority>
        
        Examples:
      		|title | priority |
            |Requirements | LOW |
			|Quality Assurance | MEDIUM |