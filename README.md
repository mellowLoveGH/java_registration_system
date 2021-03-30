# java_registration_system
java, OOP, database - use file, register system to manage students’ course registration, transfer, etc.

A register system to manage students’ course registration, transfer, etc.

1) Roles:
Student: 
- browse Course, signup Course, drop Course, view Course schedule for each Semester, and view the Transcript for each Semester.
- browse Submission of Assignment, create Submission of Assignment, delete Submission of Assignment, and update Submission of Assignment.

Teacher:
- browse Assignment, create Assignment, delete Assignment, and grade Submission from Student.
- create Evaluation rule for a Course based on Assignment.

Course Manager:
- add Course, delete Course, delete Course, update Course, and browse Course.

Finance Manager
- add Rate for each Course credit, update Rate for each Course credit.
- add Scholarship, delete Scholarship, update Scholarship, and browse Scholarship.
- attach Scholarships to Student, delete Scholarships from Students, and browse Scholarship Assignments.

2) Course:
- have fixed capacity when the signup count of Student reached capacity, student can no longer sign up to that Course.
- have number of credit. Students must sign up between 16 to 20 credits for each semester.
- be either online or offline, for offline Course, there must be a Location associated with it, for online Course, there must be a URL associated with it.
- have three grades: A, B, and C.
- have an Evaluation rules to calculate the final grade for Student. 

3) Assignment:
- have number of points.
- two types of Assignment: Essay & Multiple Choice

4) Scholarship:
- give Student finance benefit for sign up Course.
- two types of Scholarship: Status & Merit based.






