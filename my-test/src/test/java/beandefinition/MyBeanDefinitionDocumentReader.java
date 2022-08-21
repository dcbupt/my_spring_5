package beandefinition;

import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.w3c.dom.Element;

public class MyBeanDefinitionDocumentReader extends DefaultBeanDefinitionDocumentReader {

	@Override
	protected void preProcessXml(Element root) {
		System.out.println("preProcessXml " + root.getNodeName());
		super.preProcessXml(root);
	}

	@Override
	protected void postProcessXml(Element root) {
		System.out.println("postProcessXml " + root.getNodeValue());
		super.postProcessXml(root);
	}
}
