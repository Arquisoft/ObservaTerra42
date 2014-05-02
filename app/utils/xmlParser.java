package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

public class xmlParser {

	public static void main(String[] args) {
		try {
			String xml = lectorXML();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// test1();
	}

	private static String lectorXML() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(
				"app/utils/health.xml"));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();

		while ((line = reader.readLine()) != null) {

			stringBuilder.append(line);
		}
		stringBuilder.delete(stringBuilder.length() - 17,
				stringBuilder.length());
		stringBuilder.delete(stringBuilder.length() - stringBuilder.length(),
				stringBuilder.length() - stringBuilder.length() + 15);

		separadaor(stringBuilder.toString());
		return stringBuilder.toString();
	}

	private static String separadaor(String code) {

		String str = code;
		String part = "";
		int index = 0;
		int end = 0;
		String[] parts = str.split("</row>");
		for(int i = 0; i<parts.length; i++)
		{
			parts[i] += "</row>";
			test1(parts[i]);
		}
		return code;

	}

	private static void test1(String code) {
		String xml = code;
		//System.out.println(xml);

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		InputSource source = new InputSource(new StringReader(xml));
		InputSource source2 = new InputSource(new StringReader(xml));

		String msg = null;
		String status = null;
		try {
			msg = xpath.evaluate("/row/name", source);
			status = xpath.evaluate("/row/abbreviation", source2);
		} catch (XPathExpressionException e) {
			//e.printStackTrace();
		}

		System.out.println("msg = " + msg + ";" + "status = " + status);
	}
}
