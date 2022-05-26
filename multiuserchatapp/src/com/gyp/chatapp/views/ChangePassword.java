package com.gyp.chatapp.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gyp.chatapp.dao.UserDAO;
import com.gyp.chatapp.dto.UserDTO;
import com.gyp.chatapp.util.Validation;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JPasswordField newPassword;
	private JPasswordField currentPassword;
	private String userid;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChnagePassword frame = new ChnagePassword();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	private void authenticate() {
		String userid=this.userid;
		char[] password=currentPassword.getPassword();
		UserDAO userDAO=new UserDAO();
		UserDTO userDTO=new UserDTO(userid, password);
		try
		{
			boolean result=userDAO.isLogin(userDTO);
			if(!result)
			{
				String message="Current Password not authenticated";
				JOptionPane.showMessageDialog(this, message);
				this.setVisible(false);
				Dashboard dashboard=new Dashboard(userid);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DB Exception...");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void change() {
		String userid=this.userid;
		char[] password=currentPassword.getPassword();
		char[] np=newPassword.getPassword();
		UserDAO userDAO=new UserDAO();
		UserDTO userDTO=new UserDTO(userid, password);
		try
		{
			boolean result=userDAO.updatePassword(userDTO, np);
			if(!result)
			{
				String message="password not updated";
				JOptionPane.showMessageDialog(this, message);
				this.setVisible(false);
				Dashboard dashboard=new Dashboard(userid);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DB Exception...");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the frame.
	 */
	public ChangePassword(String userid) {
		this.userid=userid;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		newPassword = new JPasswordField();
		newPassword.setBounds(148, 132, 262, 33);
		contentPane.add(newPassword);
		
		JButton btnNewButton = new JButton("Change");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//1. authenticate(currentPassword)
				authenticate();
				//2. check if new password is valid
				if(!Validation.isValidPassword(newPassword.getPassword())) 
				{
					JOptionPane.showMessageDialog(btnNewButton, "password should atleast be 8 charecters in length");
					return;
				}
				//3. change password in database
				change();
				//4. display success or failure message
				JOptionPane.showMessageDialog(btnNewButton, "Password Updated");
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(148, 189, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(321, 189, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Current Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(24, 90, 114, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(24, 142, 89, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Reset Password");
		lblNewLabel_2.setForeground(Color.ORANGE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(148, 11, 140, 23);
		contentPane.add(lblNewLabel_2);
		
		currentPassword = new JPasswordField();
		currentPassword.setBounds(148, 88, 262, 33);
		contentPane.add(currentPassword);
		
		this.setVisible(true);
	}
}
