package com.gyp.chatapp.views;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Dashboard {

	private JFrame frame;
	final String message;
	
	public Dashboard(String message) {
		initialize();
		this.message=message;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 762, 419);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setTitle(message);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu chatMenu = new JMenu("Chat");
		menuBar.add(chatMenu);
		
		JMenuItem startChat = new JMenuItem("Start Chat");
		startChat.setIcon(new ImageIcon(Dashboard.class.getResource("/images/chat.png")));
		startChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new ChatScreen();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		startChat.setSelectedIcon(new ImageIcon(Dashboard.class.getResource("/images/chat.png")));
		chatMenu.add(startChat);
		
		JMenu options = new JMenu("Options");
		menuBar.add(options);
		
		JMenuItem changePassword = new JMenuItem("Change Password");
		changePassword.setIcon(new ImageIcon(Dashboard.class.getResource("/images/about.png")));
		options.add(changePassword);
		changePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassword changePassword=new ChangePassword(message);
			}
		});
		
		JMenuItem logout = new JMenuItem("Logout");
		logout.setIcon(new ImageIcon(Dashboard.class.getResource("/images/logout.png")));
		logout.setSelectedIcon(new ImageIcon(Dashboard.class.getResource("/images/logout.png")));
		options.add(logout);
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				UserScreen.main(null);
			}
		});
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Dashboard.class.getResource("/images/ChatBackgroung.png")));
		frame.getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}
