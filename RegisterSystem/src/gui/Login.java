package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login {

	public Login() {
		// TODO Auto-generated constructor stub
		JFrame f = new JFrame();
		f.setTitle("系统登录界面");

		//
		// 设置窗口的大小和位置
		f.setSize(400, 400);
		f.setLocation(200, 200);

		Container con = f.getContentPane();// 生成一个容器
		con.setLayout(null);

		//
		JLabel label01 = new JLabel("name:");
		label01.setBounds(50, 50, 100, 30);
		con.add(label01);
		JTextField name = new JTextField(30);
		name.setBounds(100, 50, 160, 30);
		con.add(name);

		//
		JLabel label02 = new JLabel("pswd:");
		label02.setBounds(50, 100, 100, 30);
		con.add(label02);
		JPasswordField jp = new JPasswordField();
		jp.setBounds(100, 100, 160, 30);
		con.add(jp);

		//
		JButton login = new JButton("Login");
		login.setBounds(120, 150, 100, 60);
		con.add(login);

		//
		ButtonGroup btnGroup = new ButtonGroup();
		JRadioButton rb01 = new JRadioButton("CM");
		JRadioButton rb02 = new JRadioButton("FM");
		JRadioButton rb03 = new JRadioButton("TE");
		JRadioButton rb04 = new JRadioButton("ST");		
		rb01.setBounds(20, 250, 60, 60);
		rb02.setBounds(120, 250, 60, 60);
		rb03.setBounds(220, 250, 60, 60);
		rb04.setBounds(320, 250, 60, 60);
		btnGroup.add(rb01);
		btnGroup.add(rb02);
		btnGroup.add(rb03);
		btnGroup.add(rb04);
		con.add(rb01);
		con.add(rb02);
		con.add(rb03);
		con.add(rb04);
		
		
		//
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str = name.getText();
				System.out.println(name.getText());
				System.out.println(jp.getPassword());
				if(rb01.isSelected()) {
					System.out.println("CM");
					new CMView().main(null);;
				}else if(rb02.isSelected()) {
					System.out.println("FM");
					new FMView().main(null);
				}else if(rb03.isSelected()) {
					System.out.println("TE");
					new TeacherView(str);
				}else {
					System.out.println("ST");
					new StudentView(str);
				}
			}
		});
		
		
		
		
		//
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new Login();

	}

}
