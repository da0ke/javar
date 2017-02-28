package cn.javar.area;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class AreaTool {
	
	public static void main(String[] args) {
		AreaTool tool = new AreaTool();
		List<Area_M> temp = tool.initData();
		
		
		
		List<Area_M> list = new ArrayList<>();
		for(Area_M m : temp) {
			if(m.getTypeId() == 1) {
				list.add(m);
			}
		}
		for(Area_M m : temp) {
			if(m.getTypeId() == 2) {
				list.add(m);
			}
		}
		
		
		
		String newFile = "f:/sql.txt";
		FileWriter fw = null;
		try {
			fw = new FileWriter(newFile);
			for(Area_M m : list) {
				StringBuffer sb = new StringBuffer();
				sb.append("insert into sys_class_area(id,name,typeId,parentId,paixu)");
				sb.append(" values(");
				sb.append(m.getId());
				sb.append(",'"+m.getName()+"'");
				sb.append(","+m.getTypeId());
				sb.append(","+m.getParentId());
				sb.append(","+m.getPaixu());
				sb.append(");");
				sb.append("\r\n");
				
				fw.write(sb.toString());
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	private List<Area_M> initData() {
		List<Area_M> list = new ArrayList<>();
		
		int p_id = 101;
		int c_id = 2001;
		int p_paixu = 1;
		
		try {
			Document document = parse(this.getClass().getResource("area.xml"));
			Element root = document.getRootElement();

			//省份
			for (Iterator i = root.elementIterator(); i.hasNext(); ) {
				Element p_element = (Element) i.next();
				
				int provinceId = p_id++;
				
				Area_M p = new Area_M();
				p.setTypeId(1);
				p.setId(provinceId);
				p.setPaixu(p_paixu++);
				p.setName(p_element.attributeValue("value"));
				
				list.add(p);
				
				//城市
				int c_paixu = 1;
				for(Iterator j = p_element.elementIterator();j.hasNext();) {
					Element c_element = (Element) j.next();
					Area_M c = new Area_M();
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
