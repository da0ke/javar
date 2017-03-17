package cn.javar.file;

import java.io.FileWriter;
import java.io.IOException;

public class TxtTool {
	
	public static void main(String[] args) {
		String file = "f:/tt.txt";
		
		TxtTool tool = new TxtTool();
		tool.file(file);
	}
	
	
	private void file(String file) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			StringBuffer sb = new StringBuffer();
			sb.append("hello");
			sb.append("\r\n");
			sb.append("welcome to china");
			sb.append("\r\n");
			sb.append("bye");
			sb.append("\r\n");

			fw.write(sb.toString());
			
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
	
}
