package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import aa.Assignment;
import aa.Course;
import aa.EssayAssignment;
import bb.Teacher;
import cc.DBServer;
import cc.SearchSystem;

public class TeacherView {
	
	public TeacherView(String teacherID) {
		// TODO Auto-generated constructor stub
		Teacher teacher = (Teacher)SearchSystem.searchStaff(teacherID);
		
		JFrame f = new JFrame();
		f.setTitle("teacher");

		//
		// 设置窗口的大小和位置
		f.setSize(400, 400);
		f.setLocation(200, 200);

		Container con = f.getContentPane();// 生成一个容器
		con.setLayout(null);
		
		//
		JLabel label01 = new JLabel("courseID:");
		label01.setBounds(50, 110, 100, 30);
		con.add(label01);
		JTextField name = new JTextField(30);
		name.setBounds(110, 110, 100, 30);
		con.add(name);
		
		//
		Object[] columnName02 = {"index", "ID", "name"};
        
        // 表格所有行数据
        Object[][] rowData02 = crsData(teacherID);

        // 创建一个表格，指定 所有行数据 和 表头
        JTable table02 = new JTable(rowData02, columnName02);
        table02.setBounds(36, 0, 300, 100);
        con.add(table02);
        
        //
        table02.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int r = table02.getSelectedRow();
				int c = table02.getSelectedColumn();
				
				String id = table02.getValueAt(r, 1).toString();
//				rowData01 = astData(id);
				name.setText(id);
			}
		});
		
		//
        JButton assignment = new JButton("assignment");
        assignment.setBounds(110, 150, 100, 30);
		con.add(assignment);
		assignment.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new AssignmentView(name.getText());
			}
		});
		
        //
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	//
	public Object[][] crsData(String teacherID){
		//
		Teacher teacher = (Teacher)SearchSystem.searchStaff(teacherID);
		int number = DBServer.courseList.size();
		
		teacher.getCourseList().clear();
		for (int i = 0; i < number; i++) {
			Course crs = DBServer.courseList.get(i);
			if(crs.getTeacherID().equals(teacherID)) {
				teacher.getCourseList().add(crs.getId());
			}
		}
		
		number = teacher.getCourseList().size();
		Object[][] obj = new Object[number][3];
		for (int i = 0; i < number; i++) {
			Course crs = SearchSystem.searchCourse(teacher.getCourseList().get(i));
			obj[i][0] = i;
			obj[i][1] = crs.getId();
			obj[i][2] = crs.getName();		
		}
		
		return obj;
	}
	
	
	public static void main(String[] args) {
		DBServer.initialize();
		new TeacherView("TE001");
	}
	
}
