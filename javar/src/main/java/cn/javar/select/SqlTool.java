package cn.javar.select;

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

public class SqlTool {
	
//	private static String sys_class = "area";
//	private static String sys_class = "job";
	private static String sys_class = "area2";

	public static void main(String[] args) {
		String newFile = "f:/sys_class_"+sys_class+".sql";
		
		SqlTool tool = new SqlTool();
		List<Opt_M> list = tool.initData();
		
		FileWriter fw = null;
		try {
			fw = new FileWriter(newFile);
			for(Opt_M m : list) {
				StringBuffer sb = new StringBuffer();
				sb.append("insert into sys_class_");
				if("area2".equals(sys_class)) {
					sb.append("area");
				} else {
					sb.append(sys_class);
				}
				
				sb.append("(id,name,typeId,parentId,paixu)");
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
	
	private List<Opt_M> initData() {
		List<Opt_M> temp = getDataFromXml();

		List<Opt_M> list = new ArrayList<>();
		for(Opt_M m : temp) {
			if(m.getTypeId() == 1 || m.getTypeId()==3) {
				list.add(m);
			}
		}
		for(Opt_M m : temp) {
			if(m.getTypeId() == 2 || m.getTypeId()==4) {
				list.add(m);
			}
		}
		
		return list;
	}
	
	private List<Opt_M> getDataFromXml() {
		List<Opt_M> list = new ArrayList<>();
		
		int p_id = 101;
		int c_id = 2001;
		int p_paixu = 1;
		
		if("area2".equals(sys_class)) {
			p_id = 301;
			c_id = 4001;
		}
		
		try {
			Document document = parse(this.getClass().getResource(sys_class+".xml"));
			Element root = document.getRootElement();

			//one
			for (Iterator i = root.elementIterator(); i.hasNext(); ) {
				Element p_element = (Element) i.next();
				
				int provinceId = p_id++;
				
				Opt_M p = new Opt_M();
				if("area2".equals(sys_class)) {
					p.setTypeId(3);
				} else {
					p.setTypeId(1);
				}
				p.setId(provinceId);
				p.setPaixu(p_paixu++);
				p.setName(p_element.attributeValue("value"));
				
				list.add(p);
				
				//two
				int c_paixu = 1;
				for(Iterator j = p_element.elementIterator();j.hasNext();) {
					Element c_element = (Element) j.next();
					Opt_M c = new Opt_M();
					if("area2".equals(sys_class)) {
						c.setTypeId(4);
					} else {
						c.setTypeId(2);
					}
					
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
