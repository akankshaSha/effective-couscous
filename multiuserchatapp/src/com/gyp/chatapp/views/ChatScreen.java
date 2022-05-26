package com.gyp.chatapp.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gyp.chatapp.network.Client;
import com.gyp.chatapp.util.UserInfo;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ChatScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea ;
	private Client client ;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		
//					try {
//						ChatScreen frame = new ChatScreen();
//						frame.setVisible(true);
//					} catch (UnknownHostException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					
//	}
	private void sendIt() {
			String message = textField.getText();
			try {
				client.sendMessage(UserInfo.USER_NAME+"-"+message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ChatScreen() throws UnknownHostException, IOException {
		textArea = new JTextArea();
		client = new Client(textArea);
		setTitle("Chit Chat");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 6, 768, 313);
		contentPane.add(scrollPane);
		
		
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textArea.setBounds(10, 24, 713, 280);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		textField.setBounds(10, 338, 602, 52);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton sendIt = new JButton("Send");
		sendIt.setBackground(Color.GREEN);
		sendIt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sendIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendIt();
			}
		});
		sendIt.setBounds(622, 340, 132, 50);
		contentPane.add(sendIt);
		setVisible(true);
	}
	
}
