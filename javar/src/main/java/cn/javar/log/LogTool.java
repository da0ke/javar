package cn.javar.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 日志分析
 * @author Administrator
 *
 */
public class LogTool {
	
	
	public static void main(String[] args) {
		{
			/**
			 * 日志提取
			 */
//			String src = "F:/temp/ftp/localhost_access_log.2017-02-04.txt";
//			String rule = "/api/sms4JX?type";
//			getLogContainsRule(src,rule);
		}
		
		{
			/**
			 * 批量日志提取
			 */
//			String dir = "F:/temp/ftp";
//			String rule = "/api/sms4JX?type";
//			batchGetLogContainsRule(dir,rule);
		}
		

	}
	
	/**
	 * 日志提取
	 * 以行为单位匹配
	 */
	public static void getLogContainsRule(String src,String rule) {
		System.out.println("操作开始");
		File file = new File(src);
		if(file.exists()) {
			String newFile = file.getParent()+"/new_"+file.getName();
			
			BufferedReader reader = null;
			FileWriter fw = null;
			try {
				reader = new BufferedReader(new FileReader(file));
				fw = new FileWriter(newFile);
				String tempString = null; 
				while ((tempString = reader.readLine()) != null) {  
					if(tempString.contains(rule)) {
						fw.write(tempString+"\r\n");
					}
					
	            }
				reader.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(fw != null) {
					try {
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("文件不存在");
		}
		System.out.println("操作结束");
	}
	
	/**
	 * 批量日志提取
	 * 以行为单位匹配
	 */
	public static void batchGetLogContainsRule(String dir,String rule) {
		System.out.println("操作开始");
		File dirFile = new File(dir);
		if(dirFile.exists() && dirFile.isDirectory()) {
			File[] files = dirFile.listFiles();
			if(files != null && files.length > 0) {
				File newDirFile = new File(dirFile.getParent()+"/new/");
				if(!newDirFile.exists()) {
					newDirFile.mkdir();
				}
				for(File file : files) {
					String newFile = newDirFile+"/"+file.getName();
					BufferedReader reader = null;
					FileWriter fw = null;
					try {
						reader = new BufferedReader(new FileReader(file));
						fw = new FileWriter(newFile);
						String tempString = null; 
						while ((tempString = reader.readLine()) != null) {  
							if(tempString.contains(rule)) {
								fw.write(tempString+"\r\n");
							}
							
			            }
						reader.close();
						fw.close();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if(reader != null) {
							try {
								reader.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if(fw != null) {
							try {
								fw.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
				
			} else {
				System.out.println("文件不存在");
			}
			
		} else {
			System.out.println("目录不存在");
		}
		System.out.println("操作结束");
	}
}
