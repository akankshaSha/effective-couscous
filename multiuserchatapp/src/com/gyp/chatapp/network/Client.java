package com.gyp.chatapp.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.gyp.chatapp.util.ConfigReader;

public class Client {
	Socket socket;
	public Client() throws UnknownHostException, IOException
	{
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket=new Socket(ConfigReader.getValue("SERVER_IP"), PORT);
		System.out.println("Client Arrives...");
		System.out.println("Enter the message send to the Server");
		Scanner scanner=new Scanner(System.in);
		String message=scanner.nextLine();
		OutputStream out=socket.getOutputStream();
		out.write(message.getBytes()); //Write Bytes on network
		System.out.println("Message sent");
		scanner.close();
		out.close();
		socket.close();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server=new Server();
	}

}
