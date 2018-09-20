package cn.com.sparknet.branchRecord.common.util;

import java.security.*;

/**
 * MD5 加密
 * @author zhangxd
 *
 */
public final class MD5Util {
	
	private MD5Util(){
	}
	
	/**
	 * 生成MD5值
	 * @param s
	 * @return
	 */
	public static String encrypt(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes("UTF-8");
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String KL(String inStr) {   
	    // String s = new String(inStr);   
	    char[] a = inStr.toCharArray();   
	    for (int i = 0; i < a.length; i++) {   
	     a[i] = (char) (a[i] ^ 't');   
	    }   
	    String s = new String(a);   
	    return s;   
	   }   
	    
	   // 加密后解密   
	   public static String JM(String inStr) {   
	    char[] a = inStr.toCharArray();   
	    for (int i = 0; i < a.length; i++) {   
	     a[i] = (char) (a[i] ^ 't');   
	    }   
	    String k = new String(a);   
	    return k;   
	   }   
	      
	   // 测试主函数   
	   public static void main(String args[]) {   
	    String s = new String("a");   
	    System.out.println("原始：" + s);   
	    System.out.println("MD5后：" + encrypt(s));   
	    System.out.println("MD5后再加密：" + KL(encrypt(s)));   
	    System.out.println("解密为MD5后的：" + JM(KL(encrypt(s))));   
	   }   
}
