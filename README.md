#####Klarity Health Technical Interview

Thanks for participating in our interview. In this session, you will be given a problem which we encounter in our daily
work. Assuming you are our only backend technical leader, please design and implement the backend service, according to
the given requirements.

##Problem Statement

Klarity Health provides services to different hospitals, to help them coordinate different tasks with patients and other
hospitals. The goal of this interview is to design and implement a task management service.

##Background
Klarity Health serves multiple hospitals, and each hospital has multiple employees.
Tasks have their title, description, priority, status, and exactly one owner.
Tasks status could be OPEN (require an employee to take action), FAILED (task can’t be done), or COMPLETED (task
success).
Tasks priority could be URGENT, HIGHT, and LOW.
When tasks are created, employees need to provide title, description, priority, and assign it to one employee in the
hospital.
Any employee in the same hospital could update task title, description, and change the status.
Any employee in the same hospital could reassign a task to another employee.
Requirement
Please design and implement the backend service to support these functionalities:
Register new hospitals in the service.
Register new employees into a particular hospital.
Create new tasks.
Update existing tasks.
Reassign tasks to different employees.
Return a list of tasks owned by a particular employee.
Return a list of tasks created under a particular company.

The service should include
Proper data storage mechanism, and corresponding data IO logic.
Proper implementation to handle business logic.
Proper APIs definition, and how to interact with frontend.

Note. There is NO need to implement any UI, as long as there is a way to call the APIs is good. For example, it can be
simple Curl via Console, Postman recipes, GraphQL playground, and any other ways best fit your solution.
Detail
Feel free to choose technologies / toolings that fit your solution best.

Please create a github.com repo, and provide necessary procedures to run your service from the interviewer's device.
Including but not limit to
If it is required to compile the code, please provide a proper compile command.
If it is required to run the code in a server, please provide guideline about how to setup, a Docker file or equivalent
is preferred.
If it is required to initialize databases with certain DB schemas, please provide that.

Please provide the github repo link at the time you book the interview via calendly.
Follow up
Please be prepared for a 10 - 15 minutes demo with your interviewer, about your technical choice, and architecture
design. It could be a pre-recorded video, or live demo.

After your demo, the interviewer will discuss with you regarding your technical decisions and potential extensions. For
example:
Why choose a certain data storage mechanism?
How to recover if a certain component crashes / runs into bugs?
How to extend the service for new requirements?

Thanks again for interviewing with us, looking forward to working with you.
Klarity Health
