package fxengine.datamanagement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public abstract class Serializable {
	
	protected static DocumentBuilderFactory factory; 

	protected static DocumentBuilder docBuilder;
	
	protected static Document doc;
	
	public Serializable()
	{

	     factory = DocumentBuilderFactory.newInstance();
	     
         try {
			docBuilder = factory.newDocumentBuilder();
		 } catch (ParserConfigurationException e) {
			e.printStackTrace();
		 }
         
		 doc = docBuilder.newDocument();

	}
	
	public abstract Element saveState();
	
	public abstract void loadState();
	
}
