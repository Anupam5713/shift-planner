# shift-planner
An app for creating shift plan for employees

You can create a shift plan for a specific number of employees by providing number of emplyees, starting month and year by using GET endpoint.
The shift plan includes leaves as well as scores for a shift miss or for doing someone else's shift.
You can retrieve the shift plan created as well using a GET endpoint. 

You can change the shift of an employee on a specific date if required.

To build the app in local:-
./gradlew clean build

To run in local:-
./gradlew bootRun 

To run junits in local:-
./gradlew test
