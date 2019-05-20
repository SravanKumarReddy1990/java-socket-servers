package com.sravan.models;

import com.xml.Sample.XMLAnn.XMLColumn;
import com.xml.Sample.XMLAnn.XMLReport;

@XMLReport(reportName = "servlet")
public class ServletNamesModel {
private String serviceName;
private String servicepath;
@XMLColumn(label = "urlName")
public String getServiceName() {
	return serviceName;
}
public void setServiceName(String serviceName) {
	this.serviceName = serviceName;
}
@XMLColumn(label = "className")
public String getServicepath() {
	return servicepath;
}
public void setServicepath(String servicepath) {
	this.servicepath = servicepath;
}

}
