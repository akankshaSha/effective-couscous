package com.gyp.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerWorker extends Thread{
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	
	public ServerWorker(Socket clientSocket, Server server) throws IOException
	{
		this.clientSocket=clientSocket;
		in=clientSocket.getInputStream();
		out=clientSocket.getOutputStream();
		this.server=server;
	}
	
	@Override
	public void run() {
		//Read data from client and broadcast to all
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String line;
		try {
		try {
		while(true) {
			//1. read
				line=br.readLine();
				System.out.println(line);
				if(line.equalsIgnoreCase("quit"))
				{
					break;
				}
				//2. broadcast
				for(ServerWorker serverWorker:server.workers)
				{
					line=line+"\n";
					serverWorker.out.write(line.getBytes());
				}
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(br!=null) br.close();
			if(in!=null) in.close();
			if(out!=null) out.close();
			if(clientSocket!=null) clientSocket.close();
		}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
