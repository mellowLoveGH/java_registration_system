package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import bb.Student;
import cc.DBServer;
import cc.SearchSystem;

public class StudentView {

	public StudentView(String studentID) {

		//
		Student std = (Student) SearchSystem.searchStaff(studentID);

		// TODO Auto-generated constructor stub
		JFrame f = new JFrame();
		f.setTitle("student");
		//
		// 设置窗口的大小和位置
		f.setSize(800, 400);
		f.setLocation(200, 200);

		Container con = f.getContentPane();// 生成一个容器
		con.setLayout(null);

		// 表头（列名）
		Object[] columnName02 = { "index", "ID", "name", "capacity", "type", "credit", "teacherID" };
		// 表格所有行数据
		Object[][] rowData02 = DBServer.CourseData();
		// 创建一个表格，指定 所有行数据 和 表头
		JTable table02 = new JTable(rowData02, columnName02);
		table02.getColumnModel().getColumn(0).setPreferredWidth(5);
		table02.getColumnModel().getColumn(3).setPreferredWidth(5);
		table02.getColumnModel().getColumn(4).setPreferredWidth(5);
		table02.getColumnModel().getColumn(5).setPreferredWidth(250);
		table02.setBounds(50, 30, 700, 120);
		con.add(table02);
		//
		int number = rowData02.length;
		ButtonGroup btnGroup = new ButtonGroup();
		JRadioButton[] jbarray = new JRadioButton[number];
		for (int i = 0; i < number; i++) {
			jbarray[i] = new JRadioButton();
			jbarray[i].setBounds(30, 30 + i * 16, 15, 15);
			btnGroup.add(jbarray[i]);
			con.add(jbarray[i]);
		}

		// notification
		JLabel label01 = new JLabel("notification:");
		label01.setBounds(50, 200, 100, 30);
		con.add(label01);
		JTextField name = new JTextField(30);
		name.setBounds(160, 200, 300, 60);
		con.add(name);

		//
		JButton signup = new JButton("signup");
		signup.setBounds(120, 160, 100, 30);
		con.add(signup);
		signup.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int num = 0;
				for (int i = 0; i < number; i++) {
					if (jbarray[i].isSelected()) {
						num = i;
					}
				}
				String crsID = rowData02[num][1].toString();
				if(!std.getCourseList().contains(crsID)) {
					std.getCourseList().add(crsID);
				}
				
				name.setText(courses(studentID));
			}
		});
		
		//
		JButton drop = new JButton("drop");
		drop.setBounds(320, 160, 100, 30);
		con.add(drop);
		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int num = 0;
				for (int i = 0; i < number; i++) {
					if (jbarray[i].isSelected()) {
						num = i;
					}
				}
				String crsID = rowData02[num][1].toString();
				if(std.getCourseList().contains(crsID)) {
					std.getCourseList().remove(crsID);
				}
				
				name.setText(courses(studentID));
			}
		});
		
		//
		JButton assignment = new JButton("assignment");
		assignment.setBounds(520, 160, 120, 30);
		con.add(assignment);
		assignment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("go to submit");
				new SubmitView(studentID);
			}
		});
		

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	//
	public String courses(String studentID) {
		String str = "";
		Student std = (Student) SearchSystem.searchStaff(studentID);
		for (int i = 0; i < std.getCourseList().size(); i++) {
			str = str + std.getCourseList().get(i) + "\n";
		}
		
		return str;
	}

	public static void main(String[] args) {
		DBServer.initialize();
		new StudentView("ST001");
	}

}
