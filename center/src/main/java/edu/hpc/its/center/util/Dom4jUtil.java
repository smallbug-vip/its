package edu.hpc.its.center.util;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * Dom4j读取配置文件
 * 
 * @timestamp Feb 17, 2016 8:17:23 PM
 * @author smallbug
 */
public class Dom4jUtil {
	private static SAXReader reader = new SAXReader();
	private static Document document = null;

	public static Document getDocument(InputStream input) {
		try {
			document = reader.read(input);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
}
