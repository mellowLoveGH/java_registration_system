package aa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bb.Student;
import cc.SearchSystem;

public class Course {
	
	private String id = "";
	private String name = "";
	private int capacity = 0;
	private int credit = 0;
	private CourseType courseType;
	
	private String teacherID = "";
	private List<String> studentList;
	private List<String> assignmentList;
	private Map<String, String> grade;
	
	private boolean rule = true;
	
	public Course() {
		// TODO Auto-generated constructor stub
		studentList = new LinkedList<String>();
		assignmentList = new LinkedList<String>();
		grade = new HashMap<String,String>();
	}
	
	//final grade
	public void grade(String studentID) {
		char grade = evaluation(studentID, isRule());
		Student student = (Student)SearchSystem.searchStaff(studentID);
		student.crsGrade(getId(), grade);
	}
	
	
	public char evaluation(String studentID, boolean rule) {
		
		if(rule) {
			int point = SearchSystem.calculateGrade(getId(), studentID);
			if(point > 70) {
				return 'A';
			}else if(point <= 70 && point >50) {
				return 'B';
			}else {
				return 'C';
			}
			
		}else {
			
		}
		
		return 'A';
	}
	
	//student sign up
	public boolean signUp(String studentID) {
		if(capacity > 0) {
			studentList.add(studentID);
			capacity--;
			System.out.println("sign up " + this.id + " successfully!");
			
			return true;
		}else{
			System.out.println("cannot sign up! "  + this.id);
			return false;
		}
	}
	
	//
	public void addAssignment(String assignmentID) {
		assignmentList.add(assignmentID);
	}
	
	//
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

	public List<String> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<String> studentList) {
		this.studentList = studentList;
	}

	public List<String> getAssignmentList() {
		return assignmentList;
	}

	public void setAssignmentList(List<String> assignmentList) {
		this.assignmentList = assignmentList;
	}

	public Map<String, String> getGrade() {
		return grade;
	}

	public void setGrade(Map<String, String> grade) {
		this.grade = grade;
	}

	public boolean isRule() {
		return rule;
	}

	public void setRule(boolean rule) {
		this.rule = rule;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "";
		for (int i = 0; i < assignmentList.size(); i++) {
			if(i == (assignmentList.size() - 1)) {
				str = str + assignmentList.get(i);
			}else {
				str = str + assignmentList.get(i) + ",";
			}
		}
		
		return id + "," + name + "," + capacity + "," + credit + ","
				+ courseType + "," + teacherID + "," + str;
	}
}
