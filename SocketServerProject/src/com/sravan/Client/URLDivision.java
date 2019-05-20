package com.sravan.Client;

public class URLDivision {
	private String IPADDRESS;
	private String PORT;
	private String URL;
	private String outPut;
	public URLDivision(String IPADDRESS,String PORT,String URL){
		this.IPADDRESS=IPADDRESS;
		this.PORT=PORT;
		this.URL=URL;
		devideURL();
	}
public static void main(String[] args) {
	
}
public void devideURL(){
try {
	chatClient cc=new chatClient(IPADDRESS,PORT,URL);
	outPut=cc.getOutPut();
} catch (Exception e) {
	e.printStackTrace();
}
}
public String getOutPut(){
	return outPut;
}
}
