package com.gyp.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.gyp.chatapp.util.ConfigReader;

public class Server {
	
	ServerSocket serverSocket;
	
	public Server() throws IOException
	{
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket=new ServerSocket(PORT);
		System.out.println("Server Started, connection from client awaited...");
		Socket socket=serverSocket.accept();	///handshaking
		System.out.println("Client joins server...");
		InputStream in=socket.getInputStream();
		byte arr[]=in.readAllBytes();
		String message=new String(arr);
		System.out.println("Message from Client:"+message);
		in.close();
		socket.close();
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Client client=new Client();

	}

}
