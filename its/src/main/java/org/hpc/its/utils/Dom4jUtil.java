package org.hpc.its.utils;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

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
