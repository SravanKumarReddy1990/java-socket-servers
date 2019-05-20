package com.sravan.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.sravan.models.KeyValue;
import com.sravan.models.ServletNamesModel;
import com.xml.Sample.Actions.XMLAction;

public class TClasses {
	private String request;
	TClasses() {

	}
	public TClasses(String request) {
		this.request = request;
	}
	public void m1(StringBuffer sb) {
		Class clazz = null;
		try {
			Hashtable<String, String> ht = new Hashtable<String, String>();
			String[] tol = request.split("\\@@");
			String[] tolDisplayName = null;
			if (request.indexOf("@@") != -1) {
				tolDisplayName = tol[0].split("DATA");
			} else {
				tolDisplayName = request.trim().split("DATA");
			}
			String[] proanddisplayName = tolDisplayName[1].trim().split("/");
			// System.out.println(proanddisplayName[0]+" "+proanddisplayName[1]);
			String pageName = proanddisplayName[1].trim();
			ht.put("pageName", proanddisplayName[1].trim());
			// System.out.println("request : "+request+" "+request.indexOf("@@"));
			if (request.indexOf("@@") != -1) {
				String[] reqTolValue = tol[1].split("\\&");
				for (int i = 0; i < reqTolValue.length; i++) {
					String req[] = reqTolValue[i].split("\\=");
					ht.put(req[0].trim(), req[1].trim());
				}
			}
			String projectsFolder = "";
		//	System.out.println(System.getProperty("user.dir")+"\\bin\\valuesFile.xml"+"  : User Root ");
			File kv = new File(System.getProperty("user.dir")+"\\bin\\valuesFile.xml");
			List<KeyValue> keyV = retrieveXML(kv, "com.sravan.models.KeyValue");
			for (int k = 0; k < keyV.size(); k++) {
				KeyValue keyvalue = keyV.get(k);
				if (keyvalue.getKey().equals("projectFolder")) {
					projectsFolder = keyvalue.getValue();
				}
			}
			//System.out.println(" Project Folder : "+projectsFolder);
			if (projectsFolder != null || projectsFolder.length() != 0) {
				File f = new File(projectsFolder);
				String projectFolder = f.getAbsolutePath() + "\\"
						+ proanddisplayName[0];
				if (pageName.indexOf(".jsp") != -1
						|| pageName.indexOf(".html") != -1) {
					File serviceFile = new File(projectFolder + "\\" + pageName);
					BufferedReader br = new BufferedReader(new FileReader(
							serviceFile));
					String sCurrentLine;
					while ((sCurrentLine = br.readLine()) != null) {
						sb.append(sCurrentLine);
					}

				} else {
					// System.out.println("Request Page : "+pageName);
					doServiceOperation(projectFolder, clazz, pageName, sb, ht);
				}
			} else {
				// System.out.println("Project Folder Doesnt Exist ");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void doServiceOperation(String projectFolder, Class clazz,
			String pageName, StringBuffer sb, Hashtable<String, String> ht) {
		try {
			File f = new File(projectFolder);
			ArrayList<String> alh = null;
			List<ServletNamesModel> listOfServices = null;
			
			
//System.out.println(alh+"  ===  ");
			String objectName = "";

			if (!f.exists()) {
				// System.out.println("Tomcat Project Folder Structure Doesnt exist");
				sb.append("Tomcat Project Folder Structure Doesnt exist");
			} else {
				alh = getAllIconSetFolders(f);
				if (alh.contains(projectFolder + "\\WEB-INF")) {
					File webxmlFile = new File(projectFolder
							+ "\\WEB-INF\\web.xml");
					listOfServices = retrieveXML(webxmlFile,
							"com.sravan.models.ServletNamesModel");
					int count = 0;
					for (int i = 0; i < listOfServices.size(); i++) {
						ServletNamesModel snM = (ServletNamesModel) listOfServices
								.get(i);
						if (snM.getServiceName().equals(pageName)) {
							count++;
							objectName = snM.getServicepath();
							loadClass(projectFolder, sb, ht, clazz, objectName);
						}
					}
					if (count == 0) {
						sb.append("No Such Kind Of Service Found");
					}
				} else {
					sb.append("No Such Kind Of Project Found");
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public <T> List<T> retrieveXML(File webxmlFile, String modelName) {
		try {
			XMLAction xmlActoion = new XMLAction();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(webxmlFile);
			doc.getDocumentElement().normalize();
			return xmlActoion.readData(modelName, doc);
		} catch (Exception e) {
			System.out.println();
		}
		return null;
	}
	public void loadClass(String projectFolder, StringBuffer sb,
			Hashtable<String, String> ht, Class clazz, String objectName) {
		try {
			File root = new File(projectFolder + "\\WEB-INF\\classes");
			URLClassLoader classLoader = URLClassLoader
					.newInstance(new URL[] { root.toURI().toURL() });
			clazz = Class.forName(objectName, true, classLoader);
			TClass t = (TClass) clazz.newInstance();
			t.doGetMethod(sb, ht);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private ArrayList<String> getAllIconSetFolders(File path) {
		ArrayList<String> pathes=null;
		try{
		 pathes = new ArrayList<String>();
		File[] listOfFiles = path.listFiles();
		for (File file : listOfFiles) {
			if (file != null && file.isDirectory()) {
				pathes.add(file.getAbsolutePath());
			}
		}
		}catch (Exception e) {
		System.out.println(e);
		pathes=new ArrayList();
		pathes.add("no-thimgn saf");
		}
		return pathes;
	}
	public static void main(String[] args) {
		TClasses tc = new TClasses("");
		StringBuffer sb = new StringBuffer();
		tc.m1(sb);
	}
	public int runProcess(String command) throws Exception {
		Process pro = Runtime.getRuntime().exec(command);
		pro.waitFor();
		return pro.exitValue();
	}
}