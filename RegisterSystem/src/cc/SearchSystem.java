package cc;

import java.util.LinkedList;
import java.util.List;

import aa.Assignment;
import aa.ChoiceAssignment;
import aa.Course;
import aa.EssayAssignment;
import aa.Scholarship;
import bb.Staff;
import bb.Student;

public class SearchSystem {
	
	public static Course searchCourse(String courseID) {
		int length = DBServer.courseList.size();
		for (int i = 0; i < length; i++) {
			Course course = DBServer.courseList.get(i);
			if(course.getId().equals(courseID)) {
				return course;
			}
		}
		
		return null;
	}
	
	public static Assignment searchAssignment(String assignmentID) {
		int length = DBServer.assignmentList.size();
		for (int i = 0; i < length; i++) {
			Assignment assignment = DBServer.assignmentList.get(i);
			if(assignment.getId().equals(assignmentID)) {
				return assignment;
			}
		}
		
		return null;
	}
	
	public static Staff searchStaff(String staffID) {
		int length = DBServer.staffList.size();
		for (int i = 0; i < length; i++) {
			Staff staff = DBServer.staffList.get(i);
			if(staff.getId().equals(staffID)) {
				return staff;
			}
		}
		
		return null;
	}
	
	public static Scholarship searchSch(String schID) {
		int length = DBServer.scholarshipList.size();
		for (int i = 0; i < length; i++) {
			Scholarship ss = DBServer.scholarshipList.get(i);
			if(ss.getId().equals(schID)) {
				return ss;
			}
		}
		
		
		return null;
	}
	
	//get all the courses of a certain teacher by ID
	public static List<Course> forTeacher(String teacherID){
		List<Course> list = new LinkedList<Course>();
		
		int length = DBServer.courseList.size();
		for (int i = 0; i < length; i++) {
			Course course = DBServer.courseList.get(i);
			if(course.getTeacherID().equals(teacherID)) {
				list.add(course);
			}
		}
		
		return list;
	}
	
	//find the submission of a certain student for a certain assignment
	public static String findSubmission(String assignmentID, String studentID) {
		Assignment assignment = searchAssignment(assignmentID);
		String str = "";
		if(assignment instanceof EssayAssignment) {
			str = ((EssayAssignment)assignment).getSubmission().get(studentID);
		}else {
			char[] ch = ((ChoiceAssignment)assignment).getSubmission().get(studentID);
			str = new String(ch);
		}
		
		return str;
	}
	
	//get the sum of assignment points of a student for a certain course
	public static int calculateGrade(String courseID, String studentID) {
		Course course = searchCourse(courseID);
		List<String> assignmentList = course.getAssignmentList();
		Student student = (Student)searchStaff(studentID);
		
		int sum = 0;
		for (int i = 0; i < assignmentList.size(); i++) {
			String aID = assignmentList.get(i);
			int point = student.getAssignmentGrade().get(aID);
			sum += point;
		}
		
		return sum;
	}
	
	
}
