package cn.javar.demo.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Administrator
 * 
 * 
 * V1.0.0
 *
 */
public class JTool {
	
	/**
     * 验证手机号码格式
     */
    public static boolean isMatchesMobile(String mobile){
    	String rule = "^1[34578][0-9]{9}$";
    	return mobile.matches(rule);
    }
    
    /**
     * 验证邮箱格式
     */
    public static boolean isMatchesEmail(String email){
    	String rule = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    	return email.matches(rule);
    }
    
    /**
     * 验证密码格式
     */
    public static boolean isMatchesPassword(String password) {
        String rule = "^[a-zA-Z0-9_][a-zA-Z0-9_]{5,17}$";//由字母、数字、下划线组成，6-18位
        return password.matches(rule);
    }
	
	/**
	 * 获取IP
	 */
	public static String getIP(HttpServletRequest request) {
		//获取IP
		String realIp = request.getHeader("x-real-ip");
		String forwarded = request.getHeader("x-forwarded-for");
		String remoteAddr = request.getRemoteAddr();
		String ip;
		if(realIp!=null && !realIp.equals("")) {
			ip = realIp;
		} else if(forwarded!=null && !forwarded.equals("")) {
			ip = forwarded;
		} else if(remoteAddr!=null && !remoteAddr.equals("")) {
			ip = remoteAddr;
		} else {
			ip = "0.0.0.0";
		}

		return ip;
	}
	
	/**
	 * 根据出生年份计算年龄
	 * 虚岁
	 */
	public static int getAgeByBirth(int year) {
		Calendar calendar = Calendar.getInstance();
		int thisYear = calendar.get(Calendar.YEAR);
		return (thisYear + 1 - year);
	}
	
	/**
	  * 生成随机短信验证码
	  * 6位随机数字
	  */
	public static String generateSMSCode() {
		int code = (int)((Math.random()*9+1)*100000);
		return String.valueOf(code);
	}
	
	/**
	 * 取得md5值
	 */
    public static String getMd5(String plainText) {  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(plainText.getBytes());  
            byte b[] = md.digest();  
  
            int i;  
  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < b.length; offset++) {  
                i = b[offset];  
                if (i < 0)  
                    i += 256;  
                if (i < 16)  
                    buf.append("0");  
                buf.append(Integer.toHexString(i));  
            }  
            //32位加密  
            return buf.toString().toUpperCase();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return null;  
        }  
  
    }  
	
	
}
