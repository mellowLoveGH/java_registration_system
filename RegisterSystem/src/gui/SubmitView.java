package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import aa.Assignment;
import aa.EssayAssignment;
import bb.Student;
import cc.FileOperation;
import cc.SearchSystem;

public class SubmitView {
	
	public SubmitView(String studentID) {
		// TODO Auto-generated constructor stub
		Student std = (Student) SearchSystem.searchStaff(studentID);
		
		JFrame f = new JFrame();
		f.setTitle("assignment");

		//
		// 设置窗口的大小和位置
		f.setSize(800, 400);
		f.setLocation(200, 200);

		Container con = f.getContentPane();// 生成一个容器
		con.setLayout(null);
		// 表头（列名）
        Object[] columnName02 = {"index", "ID", "points", "description", "type"};
        
        // 表格所有行数据
        Object[][] rowData02 = astList(studentID);

        // 创建一个表格，指定 所有行数据 和 表头
        JTable table02 = new JTable(rowData02, columnName02);
        table02.setBounds(100, 0, 600, 100);
        con.add(table02);
        
        
        //
        JLabel label01 = new JLabel("assignment:");
		label01.setBounds(50, 120, 100, 30);
		con.add(label01);
		JTextField astID = new JTextField(30);
		astID.setBounds(180, 120, 100, 30);
		con.add(astID);
		JTextField stdID = new JTextField(30);
		stdID.setBounds(280, 120, 100, 30);
		stdID.setText(studentID);
		con.add(stdID);
		JTextField content = new JTextField(30);
		content.setBounds(180, 160, 200, 60);
		con.add(content);
		
		//
		JButton submit = new JButton("submit");
		submit.setBounds(230, 250, 100, 30);
		con.add(submit);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String s1 = astID.getText();
//				String s2 = stdID.getText();
				String s3 = content.getText();
				std.submit(s1, s3);
			}
		});
        
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
				//
				String id = table02.getValueAt(r, 1).toString();
				System.out.println(id);
				astID.setText(id);
				
				String path = "ast/" + studentID + "-" + astID.getText() + ".txt";
				String ctt = FileOperation.read(path);
				ctt += "";
				System.out.println(path);
				System.out.println(ctt);
				content.setText(ctt);
			}
		});
        
        //
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public Object[][] astList(String studentID){
		Student std = (Student) SearchSystem.searchStaff(studentID);
		List<String> list = std.assignments();
		int number = list.size();
		System.out.println(number);
		Object[][] obj = new Object[number][5];
		for (int i = 0; i < number; i++) {
			Assignment ast = SearchSystem.searchAssignment(list.get(i));
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
		new SubmitView("ST001");
	}
	
}
