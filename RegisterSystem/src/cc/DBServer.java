package cc;

import java.util.LinkedList;
import java.util.List;

import aa.Assignment;
import aa.ChoiceAssignment;
import aa.Course;
import aa.CourseType;
import aa.EssayAssignment;
import aa.Offline;
import aa.Online;
import aa.Scholarship;
import bb.CManager;
import bb.FManager;
import bb.Staff;
import bb.Student;
import bb.Teacher;

public class DBServer {

	public static List<Staff> staffList = new LinkedList<>();
	public static List<Course> courseList = new LinkedList<>();
	public static List<Assignment> assignmentList = new LinkedList<Assignment>();
	public static List<Scholarship> scholarshipList = new LinkedList<Scholarship>();

	// public static Map<String, Character> courseGrade = new HashMap<>();
	public static void initialize() {

		// staff
		String str = FileOperation.read("staff.txt");
		String[] staff = str.trim().split("\n");
		for (int i = 0; i < staff.length; i++) {
			String[] info = staff[i].trim().split(",");
			Staff sta = null;
			if (info[0].startsWith("CM")) {
				sta = new CManager();
			} else if (info[0].startsWith("FM")) {
				sta = new FManager();
			} else if (info[0].startsWith("ST")) {
				sta = new Student();
			} else if (info[0].startsWith("TE")) {
				sta = new Teacher();
			}

			sta.setId(info[0]);
			sta.setName(info[1]);
			addSatff(sta);
		}
		System.out.println("load staff");

		// course
		str = FileOperation.read("course.txt");
		String[] course = str.trim().split("\n");
		for (int i = 0; i < course.length; i++) {
			String[] info = course[i].trim().split(",");
			if (info.length >= 7) {
				Course crs = new Course();
				crs.setId(info[0]);
				crs.setName(info[1]);
				crs.setCapacity(Integer.parseInt(info[2]));
				crs.setCredit(Integer.parseInt(info[3]));
				CourseType ct = null;
				if (info[4].trim().equals("online")) {
					ct = new Online();
					((Online) ct).setUrl(info[5]);
				} else {
					ct = new Offline();
					((Offline) ct).setLocation(info[5]);
				}
				crs.setCourseType(ct);
				crs.setTeacherID(info[6]);
				for (int j = 7; j < info.length; j++) {
					crs.getAssignmentList().add(info[j]);
				}
				addCourse(crs);
			}
		}
		System.out.println("load course");

		// assignment
		str = FileOperation.read("assignment.txt");
		String[] assignment = str.trim().split("\n");
		for (int i = 0; i < assignment.length; i++) {
			String[] info = assignment[i].trim().split(",");
			Assignment ast = null;
			if (info[2].equals("essay")) {
				ast = new EssayAssignment();
			} else {
				ast = new ChoiceAssignment();
			}
			ast.setId(info[0]);
			ast.setPoints(Integer.parseInt(info[1]));
			ast.setDescription(info[3]);
			addAssignment(ast);
		}

	}

	public static void store() {

		String str = "";

		str = listToStr(staffList);
		FileOperation.write("staff.txt", str);
		//
		str = listToStr(courseList);
		FileOperation.write("course.txt", str);
		//
		str = listToStr(assignmentList);
		FileOperation.write("assignment.txt", str);

	}

	// list to string
	public static String listToStr(List list) {
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			str = str + list.get(i) + "\n";
		}

		return str;
	}

	//
	public static void addSatff(Staff staff) {
		staffList.add(staff);
	}

	public static void addCourse(Course course) {
		courseList.add(course);
	}

	public static void addAssignment(Assignment assignment) {
		assignmentList.add(assignment);
	}

	public static void addScholarship(Scholarship scholarship) {
		scholarshipList.add(scholarship);
	}

	//
	public static void print(Object[][] obj) {

		for (int i = 0; i < obj.length; i++) {
			for (int j = 0; j < obj[i].length; j++) {
				System.out.print("" + obj[i][j] + "\t\t");
			}
			System.out.println();
		}

	}

	// add course
	public static void addC(String str, CManager cm) {
		String[] info = str.trim().split(",");
		if (info.length == 6) {
			// Course course = new Course();
			CourseType type = null;
			if (info[3].trim().equals("online")) {
				type = new Online();
				((Online) type).setUrl(info[4]);
			} else {
				type = new Offline();
				((Offline) type).setLocation(info[4]);
			}
			// System.out.println(type);
			cm.create(info[0], info[1], Integer.parseInt(info[2]), type, info[5]);

		} else {
			System.out.println("your entered information is not correct!");
		}
	}

	public static void deleteC(String courseID) {
		Course crs = SearchSystem.searchCourse(courseID);
		courseList.remove(crs);
	}

	public static void updateC(String str, CManager cm) {
		String[] info = str.trim().split(",");
		String courseID = info[0].trim();
		deleteC(courseID);
		addC(str, cm);
	}

	// data for course
	public static Object[][] CourseData() {

		int length = DBServer.courseList.size();
		Object[][] obj = new Object[length][7];
		for (int i = 0; i < length; i++) {
			Course course = DBServer.courseList.get(i);
			obj[i][0] = i;
			obj[i][1] = course.getId();
			obj[i][2] = course.getName();
			obj[i][3] = course.getCapacity();
			obj[i][4] = course.getCredit();
			obj[i][5] = course.getCourseType();
			obj[i][6] = course.getTeacherID();
		}

		return obj;
	}

	// data for assignment
	public static Object[][] assignmentData() {

		int length = DBServer.assignmentList.size();
		Object[][] obj = new Object[length][4];
		for (int i = 0; i < length; i++) {
			Assignment ast = DBServer.assignmentList.get(i);
			obj[i][0] = i;
			obj[i][1] = ast.getId();
			obj[i][2] = ast.getPoints();
			obj[i][3] = ast.getDescription();
		}

		return obj;
	}

	// data for staff
	public static Object[][] staffData() {

		int length = DBServer.staffList.size();
		Object[][] obj = new Object[length][3];
		for (int i = 0; i < length; i++) {
			Staff st = DBServer.staffList.get(i);
			obj[i][0] = i;
			obj[i][1] = st.getId();
			obj[i][2] = st.getName();
		}

		return obj;
	}

}
