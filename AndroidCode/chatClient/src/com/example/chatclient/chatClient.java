package com.example.chatclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

class chatClient {
	
	Socket soc;
	String sendTo;
	String LoginName;
private chatClientT chT;
	chatClient(String LoginName, String chatwith,Activity a,TextView td) throws Exception {
		this.LoginName = LoginName;
		sendTo = chatwith;
		soc = new Socket("192.168.1.74", 5217);
		 chT = new chatClientT(soc, LoginName, chatwith,a,td);
	}

	public static void main(String args[]) throws Exception {
		//@SuppressWarnings("unused")
	//	chatClient Client1 = new chatClient("sravan", "8");

	}
	public String getMSG(){
		return chT.getOutput();
	}
}
class chatClientT extends Thread {
	private String replayMSG="";
	Thread t = null;
	DataOutputStream dout;
	DataInputStream din;
	String sendTo;
	String LoginName;
	private Activity activity;
private TextView tv;
	chatClientT(Socket soc, String LoginName, String sendTo,Activity a,TextView t) {
		try {
			this.LoginName = LoginName;
			this.sendTo = sendTo;
			this.activity=a;
			this.tv=t;
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
					+ " ProjectName/ServiceClass1@@name=sravan&message=sfaewfegf");
			//System.out.println(din.readUTF() + " --");
		//	replayMSG=din.readUTF();
			tv.setText(din.readUTF());
			//Toast.makeText(this.activity, din.readUTF() +" --dfgdfgh- ", Toast.LENGTH_SHORT).show();
			
			dout.writeUTF(LoginName + " LOGOUT");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	public String getOutput(){
		return replayMSG;
	}
}