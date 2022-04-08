package com.gyp.chatapp.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.gyp.chatapp.dao.UserDAO;
import com.gyp.chatapp.dto.UserDTO;

public class UserScreen {

	private JFrame frmLogin;
	private JTextField uidTxtf;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserScreen window = new UserScreen();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	
//previous version Register
	
//	private void register() {
//		final String userid=uidTxtf.getText();
//		final char[] password=passwordField.getPassword();
//		final UserDAO userDAO=new UserDAO();
//		final UserDTO userDTO=new UserDTO(userid, password);
//		try {
//			boolean result=userDAO.add(userDTO);
//			if(result)
//			{
//				JOptionPane.showMessageDialog(frmLogin, "Succesful Registry");
//				//System.out.println("record added");
//			}
//			else
//			{
//				JOptionPane.showMessageDialog(frmLogin, "Error Occured");
//				//System.out.println("Failure");
//			}
//		} catch (ClassNotFoundException | SQLException e) {
//			System.out.println("DB Exception...");
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	private void doLogin() {
		String userid=uidTxtf.getText();
		char[] password=passwordField.getPassword();
		UserDAO userDAO=new UserDAO();
		UserDTO userDTO=new UserDTO(userid, password);
		try
		{
			boolean result=userDAO.isLogin(userDTO);
			if(result)
			{
				String message="Welcome "+userid;
				JOptionPane.showMessageDialog(frmLogin, message);
				frmLogin.setVisible(false);
				Dashboard dashboard=new Dashboard(message);
				
			}
			else
			{
				JOptionPane.showMessageDialog(frmLogin, "Authentication failed");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DB Exception...");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setTitle("LOGIN");
		frmLogin.getContentPane().setBackground(Color.WHITE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel Loginlbl = new JLabel("Login");
		Loginlbl.setHorizontalAlignment(SwingConstants.CENTER);
		Loginlbl.setFont(new Font("Tahoma", Font.BOLD, 40));
		Loginlbl.setBounds(137, 11, 161, 56);
		frmLogin.getContentPane().add(Loginlbl);
		
		uidTxtf = new JTextField();
		uidTxtf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		uidTxtf.setBounds(126, 91, 247, 32);
		frmLogin.getContentPane().add(uidTxtf);
		uidTxtf.setColumns(10);
		
		JLabel uidLbl = new JLabel("User Id");
		uidLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		uidLbl.setBounds(27, 93, 76, 22);
		frmLogin.getContentPane().add(uidLbl);
		
		JLabel pwdLbl = new JLabel("Password");
		pwdLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pwdLbl.setBounds(27, 149, 76, 22);
		frmLogin.getContentPane().add(pwdLbl);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(126, 147, 247, 32);
		frmLogin.getContentPane().add(passwordField);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginBtn.setBackground(Color.WHITE);
		loginBtn.setForeground(new Color(51, 153, 0));
		loginBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loginBtn.setBounds(126, 219, 103, 32);
		frmLogin.getContentPane().add(loginBtn);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmLogin.setVisible(false);
				UserRegistration register=new UserRegistration();
			}
		});
		registerBtn.setBackground(Color.WHITE);
		registerBtn.setForeground(new Color(0, 51, 153));
		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerBtn.setBounds(270, 219, 103, 32);
		frmLogin.getContentPane().add(registerBtn);
		frmLogin.setBounds(100, 100, 445, 314);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
