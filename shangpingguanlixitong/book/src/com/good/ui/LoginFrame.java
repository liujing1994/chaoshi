package com.good.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.good.service.UserService;

/**
 * ��¼��JFrame

 */
public class LoginFrame extends JFrame {
	
	//����"�û���"��"����"�ı�ǩ
	private JLabel acc = new JLabel("�û� ");
	private JLabel pass = new JLabel("���� ");
	
	//�������û��û�����������ı���
	private JTextField accText = new JTextField();
	private JPasswordField passText = new JPasswordField();

	//�����¼�����Box�������Ա�ʹ��BoxLayout������
	private Box up = Box.createHorizontalBox();
	private Box center = Box.createHorizontalBox();
	private Box upCenter = Box.createVerticalBox();
	private Box down = Box.createHorizontalBox();
	
	UserService userService;
    
	//�����¼��ť
	private JButton login = new JButton("��¼");
	private JButton cancel = new JButton("ȡ��");
	
	public LoginFrame(UserService userService)
	{
		this.userService = userService;
		//���ָ������������ø�������ˮƽ�ʹ�ֱ���
		up.add(Box.createHorizontalStrut(50));
		up.add(acc);
		up.add(Box.createHorizontalStrut(10));
		up.add(accText);
		up.add(Box.createHorizontalStrut(100));
				
		center.add(Box.createHorizontalStrut(50));
		center.add(pass);
		center.add(Box.createHorizontalStrut(10));
		center.add(passText);
		center.add(Box.createHorizontalStrut(100));

		upCenter.add(Box.createVerticalStrut(20));
		upCenter.add(up);
		upCenter.add(Box.createVerticalStrut(20));
		upCenter.add(center);
		upCenter.add(Box.createVerticalStrut(20));

		down.add(Box.createHorizontalStrut(80));
		down.add(login, BorderLayout.EAST);
		down.add(Box.createHorizontalStrut(20));
		down.add(Box.createVerticalStrut(10));
		
		down.add(Box.createHorizontalStrut(80));
		down.add(cancel, BorderLayout.EAST);
		down.add(Box.createHorizontalStrut(20));
		down.add(Box.createVerticalStrut(10));

		this.add(upCenter, BorderLayout.CENTER);
		this.add(down, BorderLayout.SOUTH);
		this.setBounds(300, 250, 350, 200);
		this.pack();
		this.setVisible(true);
		super.setTitle("��Ʒ����������¼");  //���ñ���
		initListeners();
	}
	public void initListeners() {
		this.login.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				login();
			}
			
		});
	}
	public void cancelListeners() {
		this.cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);              //�ر�
			}
		});
	}
	public void login() {
		String name = this.accText.getText().trim();
		char[] passes = this.passText.getPassword();
		StringBuffer password = new StringBuffer();
		for (char c : passes) {
			password.append(c);
		}
		try {
			userService.login(name, password.toString());
			new MainFrame();
			this.setVisible(false);
		} catch (Exception e) {
			showWarn(e.getMessage());
		}	
	}
	//��ʾ����
	protected int showWarn(String message) {
		return JOptionPane.showConfirmDialog(this, message, "����", 
				JOptionPane.OK_CANCEL_OPTION);
	}
}
