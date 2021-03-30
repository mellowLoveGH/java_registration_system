package gui;

import java.util.Scanner;

import aa.Assignment;
import aa.Course;
import bb.CManager;
import bb.Staff;
import cc.DBServer;

public class CMView {

	public static void tip(int num) {
		if (num == 0) {
			System.out.println("please enter 1, 2, 3, 4 to operate on data, " + "ADD, DELETE, UPDATE, BROWSE"
					+ "\nand -1 to quit");
		} else if (num == 1) {
			System.out.println("please enter the information of the course you want to add");
			System.out.println("for example:\nCS001(id), Java(name), 11(capacity)"
					+ "online(type), www.google.com(url), TE001(teacherID)");
		} else if (num == 2) {
			System.out.println("please enter the id of the course you want to delete");
		} else if (num == 3) {
			System.out.println("please enter the information of the course you want to update");
			System.out.println("for example:\nCS001(id), Java(name), 11(capacity)"
					+ "online(type), www.google.com(url), TE001(teacherID)");
		} else {
			System.out.println("browse courses");
			Object[][] obj = DBServer.CourseData();
			DBServer.print(obj);
			// obj = assignmentData();
			// DBServer.print(obj);
			// obj = staffData();
			// DBServer.print(obj);
		}
	}

	public static void main(String[] args) {

		DBServer.initialize();

		CManager cm = new CManager();

		Scanner in = new Scanner(System.in);
		tip(0);
		String typein = in.nextLine();
		while (!typein.trim().equals("-1")) {
			//
			if (typein.trim().equals("1")) {
				tip(1);
				typein = in.nextLine();
				DBServer.addC(typein, cm);
				// CS001,java,12,online,www.google.com,TE001
			} else if (typein.trim().equals("2")) {
				tip(2);
				typein = in.nextLine();
				DBServer.deleteC(typein.trim());
			} else if (typein.trim().equals("3")) {
				tip(3);
				typein = in.nextLine();
				DBServer.updateC(typein, cm);
			} else if (typein.trim().equals("4")) {
				tip(4);
			}

			tip(0);
			DBServer.store();
			typein = in.nextLine();
		}

	}

}
