package cn.javar.file;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlTool {
	
	public static void main(String[] args) {
		
		XmlTool tool = new XmlTool();
		List<Opt_M> list = tool.getDataFromXml();
		for(Opt_M m : list) {
			if(m.getTypeId()==1) {
				System.out.println(m.getName());
			}
			
		}
	}
	
	private List<Opt_M> getDataFromXml() {
		List<Opt_M> list = new ArrayList<>();
		
		int p_id = 101;
		int c_id = 2001;
		int p_paixu = 1;

		try {
			Document document = parse(this.getClass().getResource("area.xml"));
			Element root = document.getRootElement();

			//one
			for (Iterator i = root.elementIterator(); i.hasNext(); ) {
				Element p_element = (Element) i.next();
				
				int provinceId = p_id++;
				Opt_M p = new Opt_M();
				p.setTypeId(1);

				p.setId(provinceId);
				p.setPaixu(p_paixu++);
				p.setName(p_element.attributeValue("value"));
				
				list.add(p);
				
				//two
				int c_paixu = 1;
				for(Iterator j = p_element.elementIterator();j.hasNext();) {
					Element c_element = (Element) j.next();
					Opt_M c = new Opt_M();
					c.setTypeId(2);
					c.setId(c_id++);
					c.setParentId(provinceId);
					c.setPaixu(c_paixu++);
					c.setName(c_element.attributeValue("value"));
					
					list.add(c);
				}
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
        return document;
    }
	
}
