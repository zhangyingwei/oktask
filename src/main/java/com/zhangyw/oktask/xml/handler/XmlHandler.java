package com.zhangyw.oktask.xml.handler;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.zhangyw.oktask.exception.TaskTimeException;
import com.zhangyw.oktask.util.StringUtil;
import com.zhangyw.oktask.xml.entity.XmlInfo;

public class XmlHandler {
	private SAXReader reader;
	private Document document;
	public XmlHandler(){
		
	}
	public XmlHandler init(String path){
		if(StringUtil.isEmpty(path)){
			throw new NullPointerException("path is null");
		}
		File xmlFile = new File(path);
		this.reader = new SAXReader();
		try {
			this.document = this.reader.read(xmlFile);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public XmlInfo buildXmlInfo(){
		XmlInfo xmlInfo = null;
		try {
			xmlInfo = new XmlInfo().build(this.document.getRootElement());
		} catch (TaskTimeException e) {
			e.printStackTrace();
		}
		return xmlInfo;
	}
}
