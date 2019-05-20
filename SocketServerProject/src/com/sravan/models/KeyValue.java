package com.sravan.models;

import com.xml.Sample.XMLAnn.XMLColumn;
import com.xml.Sample.XMLAnn.XMLReport;

@XMLReport(reportName="d")
public class KeyValue {
private String key;
private String value;
@XMLColumn(label="key")
public String getKey() {
	return key;
}
public void setKey(String key) {
	this.key = key;
}
@XMLColumn(label="value")
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}

}
