package com.gyp.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.gyp.chatapp.dao.UserDAO;
import com.gyp.chatapp.dto.UserDTO;
import com.gyp.chatapp.util.Validation;

public class UserRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField useridField;
	private JPasswordField passwordField;
	private JTextField emailField;
	private JTextField phnoField;
	private JTextField cityField;

	public UserRegistration() {
		setVisible(true);
		setTitle("REGISTER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 487);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		useridField = new JTextField();
		useridField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		useridField.setColumns(10);
		useridField.setBounds(116, 113, 247, 32);
		contentPane.add(useridField);
		
		JLabel registerLbl = new JLabel("Register");
		registerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		registerLbl.setFont(new Font("Tahoma", Font.BOLD, 40));
		registerLbl.setBounds(133, 11, 180, 56);
		contentPane.add(registerLbl);
		
		JLabel uidLbl = new JLabel("User Id");
		uidLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		uidLbl.setBounds(19, 117, 76, 22);
		contentPane.add(uidLbl);
		
		JLabel pwdLbl = new JLabel("Password");
		pwdLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pwdLbl.setBounds(19, 173, 76, 22);
		contentPane.add(pwdLbl);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(116, 169, 247, 32);
		contentPane.add(passwordField);
		
		JLabel emailLbl = new JLabel("Email");
		emailLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		emailLbl.setBounds(19, 230, 48, 22);
		contentPane.add(emailLbl);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailField.setColumns(10);
		emailField.setBounds(116, 226, 247, 32);
		contentPane.add(emailField);
		
		phnoField = new JTextField();
		phnoField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phnoField.setColumns(10);
		phnoField.setBounds(116, 282, 247, 32);
		contentPane.add(phnoField);
		
		JLabel phnoLbl = new JLabel("Phone No.");
		phnoLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		phnoLbl.setBounds(19, 286, 87, 22);
		contentPane.add(phnoLbl);
		
		cityField = new JTextField();
		cityField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cityField.setColumns(10);
		cityField.setBounds(116, 339, 247, 32);
		contentPane.add(cityField);
		
		JLabel cityLbl = new JLabel("City");
		cityLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cityLbl.setBounds(19, 339, 87, 22);
		contentPane.add(cityLbl);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.setForeground(new Color(0, 51, 153));
		registerBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerBtn.setBackground(Color.WHITE);
		registerBtn.setBounds(116, 392, 103, 32);
		contentPane.add(registerBtn);
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registration();
			}
		});
		
		JButton refresh = new JButton("Refresh");
		refresh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		refresh.setBackground(Color.WHITE);
		refresh.setForeground(new Color(0, 128, 0));
		refresh.setBounds(276, 393, 87, 30);
		contentPane.add(refresh);
		
		JLabel lblNewLabel = new JLabel("*");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(85, 122, 10, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(96, 180, 10, 14);
		contentPane.add(lblNewLabel_1);
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
	}
	
	//1. register
	private void registration()
	{
		final String userid=useridField.getText();
		final char[] password=passwordField.getPassword();
		String emial=emailField.getText();
		String phno=phnoField.getText();
		String city=cityField.getText();
		final UserDAO userDAO=new UserDAO();
		
		//Validations
		if(!Validation.isValidUserName(userid)) {
			JOptionPane.showMessageDialog(this, "Username is a required Field");
			return;
		}
		if(!Validation.isValidPassword(password)) {
			JOptionPane.showMessageDialog(this, "Password must be atleast 8 charecters long");
			return;
		}
		if(!Validation.isValidEmail(emial)) JOptionPane.showMessageDialog(this, "Invalid Email");
		if(!Validation.isValidPhoneNo(phno)) JOptionPane.showMessageDialog(this, "Invalid Phone Number");
		
		//Converting new entry to a DTO object
		final UserDTO userDTO=new UserDTO(userid, password);
		userDTO.setCity(city);
		userDTO.setEmail(emial);
		userDTO.setPhno(phno);
		
		try {
			boolean result=userDAO.add(userDTO);
			if(result)
			{
				JOptionPane.showMessageDialog(this, "Succesful Registry");
				this.setVisible(false);
				UserScreen.main(null);
				//System.out.println("record added");
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Error Occured");
				//System.out.println("Failure");
			}
		}catch(SQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(this, "username taken");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DB Exception...");
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	//2. refresh
	private void refresh()
	{
		useridField.setText("");
		passwordField.setText("");
		emailField.setText("");
		phnoField.setText("");
		cityField.setText("");
	}
}
