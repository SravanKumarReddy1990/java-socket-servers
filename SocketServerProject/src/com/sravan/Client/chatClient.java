package com.sravan.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

class chatClient {
	Socket soc;
	String sendTo;
	String LoginName;
	chatClientT chT=null;
	chatClient(String IpAddress, String Port,String URL) throws Exception {
		this.LoginName = "sravan";
		sendTo = "8";
		soc = new Socket(IpAddress, Integer.parseInt(Port));
		 chT = new chatClientT(soc, LoginName, sendTo,URL);
	}

	public static void main(String args[]) throws Exception {
		@SuppressWarnings("unused")
		 chatClient Client1 = new chatClient("192.168.1.74", "5217","");

	}
	public String getOutPut(){
		return chT.getOutPut();
	}
}
class chatClientT extends Thread {
	Thread t = null;
	DataOutputStream dout;
	DataInputStream din;
	String sendTo;
	String LoginName;
	private String URL;
	private String responceString="";
	chatClientT(Socket soc, String LoginName, String sendTo,String URL) {
		try {
			this.LoginName = LoginName;
			this.sendTo = sendTo;
			this.URL=URL;
			din = new DataInputStream(soc.getInputStream());
			dout = new DataOutputStream(soc.getOutputStream());
			dout.writeUTF(LoginName);
			start();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void run() {
		try {
			dout.writeUTF(sendTo + " " + "DATA"
					+ " "+URL);
			System.out.println(din.readUTF() + " --");
			responceString=din.readUTF();
			dout.writeUTF(LoginName + " LOGOUT");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	public String getOutPut(){
		return responceString;
	}
}