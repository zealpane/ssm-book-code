package org.mybatis.first;

import static org.junit.Assert.*;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Dom4jTest {

	@Test
	public void test() {
		// 1、读取并解析xml文档
		try {
			Document xmlDom = readXml("s");
		} catch (DocumentException e) {
			log.error("读取xml文件内容失败");
		}
		
		fail("Not yet implemented");
	}
	
	/**
	 * 返回xml文档
	 * @param fileName 文件路径
	 * @return 文档Document
	 * @throws DocumentException
	 */
	public static Document readXml(String fileName) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(fileName));
		return document;
	}

}
