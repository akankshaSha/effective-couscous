package com.gyp.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import com.gyp.chatapp.util.ConfigReader;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException
	{
		//1. Create connection with the Server
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket=new Socket(ConfigReader.getValue("SERVER_IP"), PORT);
		
		//2. instantiate global variables
		out=socket.getOutputStream();
		in=socket.getInputStream();
		this.textArea=textArea;
		
		//3. this thread reads messages (as soon as an object of the client is created)
		readMessages();
	}
	
	public void sendMessage(String message) throws IOException
	{
		message=message+"\n";
		out.write(message.getBytes());
	}
	
	public void readMessages()
	{
		worker=new ClientWorker(in, textArea);
		worker.start();
	}

}
