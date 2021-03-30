package bb;

import java.util.LinkedList;
import java.util.List;

import aa.Course;
import aa.MScholarship;
import aa.SScholarship;
import aa.Scholarship;
import cc.DBServer;
import cc.SearchSystem;

public class FManager extends Staff{
	
	private List<String> schList = new LinkedList<>();
	
	public void addCredit(String courseID, int credit) {
		Course course = SearchSystem.searchCourse(courseID);
		course.setCredit(credit);		
	}
	
	public void updateCredit(String courseID, int credit) {
		addCredit(courseID, credit);
	}
	
	public void createScholarship(String id, String benefit, String type, String condition) {
		Scholarship ss = null;
		if(type.equals("status")) {
			ss = new SScholarship();
			((SScholarship)ss).setId(id);
			if(benefit.contains("%")) {
				((SScholarship)ss).setRate(benefit);
			}else {
				((SScholarship)ss).setAmount(benefit);
			}
			((SScholarship)ss).setStatus(condition);
		}else {
			ss = new MScholarship();
			((MScholarship)ss).setId(id);
			if(benefit.contains("%")) {
				((MScholarship)ss).setRate(benefit);
			}else {
				((MScholarship)ss).setAmount(benefit);
			}
			((MScholarship)ss).setMerit(condition);
		}
		schList.add(id);
		DBServer.addScholarship(ss);
	}
	
	//attribute scholarship to student
	public void attribute(String studentID, String schID) {
		Student stu = (Student)SearchSystem.searchStaff(studentID);
		stu.getSch(schID);
	}
	
}
