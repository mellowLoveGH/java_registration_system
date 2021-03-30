package bb;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import aa.Assignment;
import aa.ChoiceAssignment;
import aa.Course;
import aa.EssayAssignment;
import cc.FileOperation;
import cc.SearchSystem;

public class Student extends Staff{

	private List<String> courseList;
	private Map<String, Integer> assignmentGrade = new HashMap<String,Integer>();
	private Map<String, Character> courseGrade = new HashMap<String,Character>();
	private List<String> scholarshipList = new LinkedList<>();
	
	public Student() {
		// TODO Auto-generated constructor stub
		courseList = new LinkedList<String>();
	}
	
	//scholarship
	public void getSch(String schID) {
		scholarshipList.add(schID);
	}
	
	//sign up
	public void signUp(String courseID) {
		Course course = SearchSystem.searchCourse(courseID);
		boolean flag = course.signUp(super.getId());
		if(flag) {
			courseList.add(courseID);
		}else {
			System.out.println("cannot sign up!");
		}
	}
	
	//
	public void submit(String assignmentID, String content) {
		Assignment assignment = SearchSystem.searchAssignment(assignmentID);
		if(assignment instanceof EssayAssignment) {
			((EssayAssignment)assignment).submit(getId(), content);
		}else {
			char[] con = content.toCharArray();
			((ChoiceAssignment)assignment).submit(getId(), con);
		}
		String path = "ast/" + getId() + "-" + assignmentID + ".txt";
		FileOperation.write(path, content);
	}

	//assignment grade
	public void amtGrade(String assignmentID, int point) {
		assignmentGrade.put(assignmentID, point);
	}
	
	//course grade
	public void crsGrade(String courseID, Character grade) {
		courseGrade.put(courseID, grade);
	}
	
	public List<String> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<String> courseList) {
		this.courseList = courseList;
	}

	public Map<String, Integer> getAssignmentGrade() {
		return assignmentGrade;
	}

	public void setAssignmentGrade(Map<String, Integer> assignmentGrade) {
		this.assignmentGrade = assignmentGrade;
	}

	public Map<String, Character> getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(Map<String, Character> courseGrade) {
		this.courseGrade = courseGrade;
	}

	public List<String> getScholarshipList() {
		return scholarshipList;
	}

	public void setScholarshipList(List<String> scholarshipList) {
		this.scholarshipList = scholarshipList;
	}
	
	//
	public List<String> assignments() {
		List<String> list = new LinkedList<>();
		for (int i = 0; i < courseList.size(); i++) {
			Course crs = SearchSystem.searchCourse(courseList.get(i));
			List<String> astlist = crs.getAssignmentList();
			for (int j = 0; j < astlist.size(); j++) {
				list.add(astlist.get(j));
			}
		}
		
		return list;
	}
	
}
