package dd;

import java.util.Map;

import aa.ChoiceAssignment;
import aa.CourseType;
import aa.EssayAssignment;
import aa.Offline;
import aa.Online;
import bb.CManager;
import bb.FManager;
import bb.Staff;
import bb.Student;
import bb.Teacher;
import cc.DBServer;
import cc.SearchSystem;

public class Tester01 {

	public static void main(String[] args) {
		
		//create staff
		Staff staff01 = new CManager();
		staff01.setId("CM001");
		staff01.setName("David01");
		DBServer.addSatff(staff01);
		
		Staff staff02 = new FManager();
		staff02.setId("FM001");
		staff02.setName("David02");
		DBServer.addSatff(staff02);
		
		Staff staff03 = new Student();
		staff03.setId("ST001");
		staff03.setName("David03");
		DBServer.addSatff(staff03);
		
		Staff staff04 = new Teacher();
		staff04.setId("TE001");
		staff04.setName("David04");
		DBServer.addSatff(staff04);
		//		
		System.out.println(SearchSystem.searchStaff("TE001").getName());
		
		
		//create courses
		CourseType type01 = new Online();
		((Online)type01).setUrl("www.google.com");
		((CManager)staff01).create("CS001", "algorithm", 20, type01, "TE001");
		CourseType type02 = new Offline();
		((Offline)type02).setLocation("teaching building 4");
		((CManager)staff01).create("CS002", "java", 30, type02, "TE001");
		//
		System.out.println(SearchSystem.searchCourse("CS001").getCourseType().toString());
		System.out.println(((Teacher)staff04).getCourseList().get(0));
		System.out.println(((Teacher)staff04).getCourseList().get(1));
		
		
		//add credit
		((FManager)staff02).addCredit("CS001", 5);
		((FManager)staff02).addCredit("CS002", 3);
		System.out.println(SearchSystem.searchCourse("CS001").getCredit());
		System.out.println(SearchSystem.searchCourse("CS002").getCredit());
		
		
		//create assignment
		((Teacher)staff04).createAssignment("CS001", "CS001-01", 36, "write an essay", "essay");
		((Teacher)staff04).createAssignment("CS001", "CS001-02", 30, "finish the questions", "choice");
		System.out.println(SearchSystem.searchAssignment("CS001-01").getDescription());
		System.out.println(SearchSystem.searchAssignment("CS001-02").getClass());
		
		
		//student sign up
		((Student)staff03).signUp("CS001");
		((Student)staff03).signUp("CS002");
		System.out.println(((Student)staff03).getCourseList().get(0));
		System.out.println(((Student)staff03).getCourseList().get(1));
		System.out.println(SearchSystem.searchCourse("CS001").getCapacity());
		
		
		//student submit assignment
		((Student)staff03).submit("CS001-01", "to be mellow");
		((Student)staff03).submit("CS001-02", "ABCDEFGH");
		Map<String, String> submission01 = ((EssayAssignment)SearchSystem.searchAssignment("CS001-01")).getSubmission();
		System.out.println(submission01.get("ST001"));
		Map<String, char[]> submission02 = ((ChoiceAssignment)SearchSystem.searchAssignment("CS001-02")).getSubmission();
		System.out.println(submission02.get("ST001"));
		
		
		//teacher grade the submission of a certain student
		System.out.println(SearchSystem.findSubmission("CS001-01", "ST001"));
		System.out.println(SearchSystem.findSubmission("CS001-02", "ST001"));
		((Teacher)staff04).grade("CS001-01", "ST001", 36);
		((Teacher)staff04).grade("CS001-02", "ST001", 11);
		System.out.println(((Student)staff03).getAssignmentGrade().get("CS001-02"));
		System.out.println(((Student)staff03).getAssignmentGrade().get("CS001-01"));
		
		
		//
		System.out.println(SearchSystem.calculateGrade("CS001", "ST001"));
		
		
		//create scholarship
		((FManager)staff02).createScholarship("SC001", "10%", "status", "all Chinese students");
		((FManager)staff02).createScholarship("SC002", "2000$", "merit", "top ten students");
		System.out.println(SearchSystem.searchSch("SC001"));
		System.out.println(SearchSystem.searchSch("SC002"));
		
		
		//attribute scholarship
		((FManager)staff02).attribute("ST001", "SC001");
		((FManager)staff02).attribute("ST001", "SC002");
		System.out.println(((Student)staff03).getScholarshipList().get(0));
		System.out.println(((Student)staff03).getScholarshipList().get(1));
		
		
	}

}
