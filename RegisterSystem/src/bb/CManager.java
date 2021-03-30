package bb;

import java.util.LinkedList;
import java.util.List;

import aa.Course;
import aa.CourseType;
import cc.DBServer;
import cc.SearchSystem;

public class CManager extends Staff{
	

	private List<String> courseList;
	
	public CManager() {
		// TODO Auto-generated constructor stub
		courseList = new LinkedList<String>();
	}
	
	public Course create(String id, String name, int capacity, CourseType type, String teacherID) {
		Course course = SearchSystem.searchCourse(id);
		if(course == null) {
			course = new Course();
			course.setId(id);
			course.setName(name);;
			course.setCapacity(capacity);
			course.setCourseType(type);
			course.setTeacherID(teacherID);
			//
			DBServer.addCourse(course);	
			((Teacher)SearchSystem.searchStaff(teacherID)).updateCourseList();
			courseList.add(id);
		}
		
		return course;
	}
	
}
