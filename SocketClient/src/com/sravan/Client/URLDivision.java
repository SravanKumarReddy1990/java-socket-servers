package com.sravan.Client;

public class URLDivision {
	private String IPADDRESS;
	private String PORT;
	private String URL;
	private String outPut;
	private StringBuffer sb;
	public URLDivision(){
		
	}
	public URLDivision(String IPADDRESS,String PORT,String URL,StringBuffer sb){
		this.IPADDRESS=IPADDRESS;
		this.PORT=PORT;
		this.URL=URL;
		this.sb=sb;
		devideURL();
	}
	public void setParams(String IPADDRESS,String PORT,String URL,StringBuffer sb){
		this.IPADDRESS=IPADDRESS;
		this.PORT=PORT;
		this.URL=URL;
		this.sb=sb;
	}
public static void main(String[] args) {
	URLDivision uv=new URLDivision("192.168.1.74", "5217", "ProjectName/ServiceClass1@@name=jsah&message=ahjsvfc",new StringBuffer());
	
	System.out.println("uv.getOutPut() : "+uv.getOutPut());
}
public void devideURL(){
	try {
		System.out.println(IPADDRESS+"  --  "+PORT+" -- "+URL);
		chatClient cc=new chatClient(IPADDRESS,PORT,URL,sb);
		waitToOutput();
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	public String getOutPut(){
		return outPut;
	}
	public void waitToOutput(){
		if(chatClientT.outPutFlag==false){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			waitToOutput();
		}
	}
}
