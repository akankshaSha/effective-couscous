package com.gyp.chatapp.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JWindow {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private Timer timer;
	private void runProgressBar() {
		timer=new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				progressBar.setValue(count);
				count ++;
				if(count>100) {
					timer.stop();
					SplashScreen.this.setVisible(false);
					SplashScreen.this.dispose();
					UserScreen.main(null);
				}				
			}
			
		});
		timer.start();
	}
	private int count=0;
	
	JProgressBar progressBar = new JProgressBar();

	/**
	 * Create the frame.
	 */
	public SplashScreen() {
		setBounds(100, 100, 328, 395);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SplashScreen.class.getResource("/images/chit-chat.png")));
		contentPane.add(lblNewLabel, BorderLayout.CENTER);
		
		
		progressBar.setFont(new Font("Times New Roman", Font.BOLD, 18));
		progressBar.setStringPainted(true);
		contentPane.add(progressBar, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
	}

}
