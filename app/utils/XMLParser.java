package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import models.Country;
import models.Indicator;
import models.Observation;

import org.xml.sax.InputSource;

public class XMLParser {

	public static void main(String[] args) {
		try {
			lectorXML();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String lectorXML() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(
				"app/utils/xml/health.xml"));
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
		String[] parts = str.split("</row>");
		for (int i = 0; i < parts.length; i++) {
			parts[i] += "</row>";
			analizadorPais(parts[i]);
		}
		return code;

	}

	private static void analizadorPais(String code) {
		String xml = code;
		String[] partes = xml
				.split("[<_][0123456789][0123456789][0123456789][0123456789]_");
		String[] indicatorName = xml.split("</");

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		InputSource source = new InputSource(new StringReader(xml));
		InputSource source2 = new InputSource(new StringReader(xml));
		InputSource source3 = new InputSource(new StringReader(xml));

		String name = null;
		String abreviacion = null;

		String[] indicador = null;
		try {
			int k = 5;
			name = xpath.evaluate("/row/name", source);
			abreviacion = xpath.evaluate("/row/abbreviation", source2);
			for (int i = 4; i < partes.length - 1; i += 2) {
				indicador = partes[i].split("><");

				for (int x = 0; x < indicador.length; x++) {
					String fechaza = indicatorName[k++].substring(0, 6);
					String consulta = fechaza + indicador[x];
					String indicationTitle = "";
					String indicationValue = "";
					String[] aux = indicador[x].split("_");
					for (int j = 0; j < aux.length; j++) {
						indicationTitle += aux[j] + " ";
					}
					indicationValue = xpath.evaluate("/row/" + consulta,
							source3);
					addDatos(name, abreviacion, indicationTitle,
							indicationValue);
					source3 = new InputSource(new StringReader(xml));
				}
			}
		} catch (XPathExpressionException e) {
			// e.printStackTrace();
		}
	}

	public static void addDatos(String name, String abreviacion,
			String indicador, String valor) {

		System.out.println(name);
		System.out.println(abreviacion);
		System.out.println(indicador);
		System.out.println(valor);
		System.out.println();
		Double valorFinal;
		try {
			valorFinal = Double.parseDouble(valor);
		} catch (NumberFormatException e) {
			return;
		}
		if (Country.findByName(name) == null)
			Country.create(new Country(abreviacion, name));
		if (Indicator.findByCode(indicador) == null)
			Indicator.create(new Indicator(indicador, indicador));
		;
		Observation.create(abreviacion, indicador, valorFinal);

	}
}
