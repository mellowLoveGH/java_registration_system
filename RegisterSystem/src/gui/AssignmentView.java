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
import aa.ChoiceAssignment;
import aa.Course;
import aa.EssayAssignment;
import cc.DBServer;
import cc.FileOperation;
import cc.SearchSystem;

public class AssignmentView {
	
	public AssignmentView(String courseID) {
		// TODO Auto-generated constructor stub
		
		JFrame f = new JFrame();
		f.setTitle("系统登录界面");

		//
		// 设置窗口的大小和位置
		f.setSize(800, 600);
		f.setLocation(200, 200);

		Container con = f.getContentPane();// 生成一个容器
		con.setLayout(null);
		
		//
		//
		Object[] columnName01 = {"index", "ID", "points", "decription", "type"};
        //
        Object[][] rowData01 = {{"index", "ID", "points", "decription", "type"}};
        // 创建一个表格，指定 所有行数据 和 表头
        JTable table01 = new JTable(rowData01, columnName01);
        table01.setBounds(100, 160, 600, 60);
        con.add(table01);
		
		
		
		//
		Object[] columnName02 = {"index", "ID", "points", "decription", "type"};
        
        // 表格所有行数据
        Object[][] rowData02 = astData(courseID);

        // 创建一个表格，指定 所有行数据 和 表头
        JTable table02 = new JTable(rowData02, columnName02);
        table02.setBounds(100, 0, 600, 100);
        con.add(table02);
        
        //
        JLabel label01 = new JLabel("assignmentID:");
		label01.setBounds(380, 120, 100, 30);
		con.add(label01);
		JTextField name = new JTextField(30);
		name.setBounds(500, 120, 160, 30);
		con.add(name);
        
        //
        JButton update = new JButton("update");
        update.setBounds(120, 120, 100, 30);
		con.add(update);
		
		JButton delete = new JButton("delete");
		delete.setBounds(250, 120, 100, 30);
		con.add(delete);
		
		JButton add = new JButton("add");
		add.setBounds(100, 230, 100, 30);
		con.add(add);
		
		JButton search = new JButton("search");
		search.setBounds(380, 230, 100, 30);
		con.add(search);
		
		JButton grade = new JButton("grade");
		grade.setBounds(600, 230, 100, 30);
		con.add(grade);
		
		JLabel label02 = new JLabel("studentID:");
		label02.setBounds(100, 270, 100, 30);
		con.add(label02);
		JTextField name1 = new JTextField(30);
		name1.setBounds(220, 270, 160, 30);
		con.add(name1);
		JTextField name2 = new JTextField(30);
		name2.setBounds(110, 320, 260, 160);
		con.add(name2);
		JLabel label03 = new JLabel("grade:");
		label03.setBounds(450, 270, 100, 30);
		con.add(label03);
		JTextField name3 = new JTextField(30);
		name3.setBounds(520, 270, 160, 30);
		con.add(name3);
		
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
				name.setText(id);
			}
		});
		
		//
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int r = table01.getSelectedRow();
				String id = table01.getValueAt(r, 1).toString();
				String points = table01.getValueAt(r, 2).toString();
				String description = table01.getValueAt(r, 3).toString();
				String type = table01.getValueAt(r, 4).toString();
				System.out.println(id);
				System.out.println(points);
				System.out.println(description);
				System.out.println(type);
				
				if(type.trim().equals("essay")) {
					EssayAssignment ea = new EssayAssignment();
					ea.setId(id);
					ea.setPoints(Integer.parseInt(points));
					ea.setDescription(description);
					DBServer.addAssignment(ea);
				}else {
					ChoiceAssignment ca = new ChoiceAssignment();
					ca.setId(id);
					ca.setPoints(Integer.parseInt(points));
					ca.setDescription(description);
					DBServer.addAssignment(ca);
				}
			}
		});
        
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String path = "ast/" + name1.getText() + "-" + name.getText() + ".txt";
				System.out.println(path);
				String str = FileOperation.read(path);
				name2.setText(str);
			}
		});
		
		grade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s1 = name.getText();
				String s2 = name1.getText();
				String s3 = name3.getText();
				System.out.println(s1 + "," + s2 + "," + s3);
			}
		});
		
        //
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public Object[][] astData(String courseID){
		
		Course crs = SearchSystem.searchCourse(courseID);
		int number = crs.getAssignmentList().size();
		Object[][] obj = new Object[number][5];
		for (int i = 0; i < number; i++) {
			Assignment ast = SearchSystem.searchAssignment(crs.getAssignmentList().get(i));
			obj[i][0] = i;
			obj[i][1] = ast.getId();
			obj[i][2] = ast.getPoints();
			obj[i][3] = ast.getDescription();
			if(ast instanceof EssayAssignment) {
				obj[i][4] = "essay";
			}else {
				obj[i][4] = "choice";
			}
		}
		
		return obj;		
	}

	public static void main(String[] args) {
		DBServer.initialize();
		new AssignmentView("CS001");
	}
	
}
