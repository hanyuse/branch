package cn.com.sparknet.branchRecord.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * Base64加密与解密
 * @author zhangxd
 *
 */
@SuppressWarnings ( "restriction" )
public final class Base64Util {
	
	private Base64Util(){
	}
	
	/**
	 * 加密
	 * @param text
	 * @return
	 */
    public static String encode(String text) {
		return new BASE64Encoder().encode(text.getBytes());
	}

	/**
	 * 解密
	 * @param text
	 * @return
	 */
	public static String decode(String text) {
		byte[] buffer = new byte[0];
		try {
			buffer = new BASE64Decoder().decodeBuffer(text);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		String s = "";
		try {
			s = new String(buffer, "GBK");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
		return s;
	}
}
