package bb;

import java.util.LinkedList;
import java.util.List;

import aa.Assignment;
import aa.ChoiceAssignment;
import aa.Course;
import aa.EssayAssignment;
import cc.DBServer;
import cc.SearchSystem;

public class Teacher extends Staff{

	private List<String> courseList;
	private List<String> assignmentList;
	
	public Teacher() {
		// TODO Auto-generated constructor stub
		courseList = new LinkedList<String>();
		assignmentList = new LinkedList<String>();
		//updateCourseList();
	}
	
	//
	public void updateCourseList() {
		courseList.clear();
		List<Course> list = SearchSystem.forTeacher(getId());
		int length = list.size();
		for (int i = 0; i < length; i++) {
			courseList.add(list.get(i).getId());
//			System.out.println(list.get(i).getId()+")))))))))))))");
		}
		
	}
	
	public void createAssignment(String courseID, String id, int points, String description, String type) {
		type = type.trim();
		Assignment ea;
		if(type.equals("essay")) {
			ea = new EssayAssignment();
			ea.setId(id);
			ea.setPoints(points);
			ea.setDescription(description);
		}else {
			ea = new ChoiceAssignment();
			ea.setId(id);
			ea.setPoints(points);
			ea.setDescription(description);
		}
		
		DBServer.addAssignment(ea);
		assignmentList.add(id);
		Course course = SearchSystem.searchCourse(courseID);
		course.addAssignment(id);
	}
	
	//
	public String grade(String assignmentID, String studentID, int point) {
		String str = SearchSystem.findSubmission(assignmentID, studentID);
		Student student = (Student)SearchSystem.searchStaff(studentID);
		student.amtGrade(assignmentID, point);
		return str;
	}
	
	//
	
	public List<String> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<String> courseList) {
		this.courseList = courseList;
	}

	public List<String> getAssignmentList() {
		return assignmentList;
	}

	public void setAssignmentList(List<String> assignmentList) {
		this.assignmentList = assignmentList;
	}
	
	
	
}
