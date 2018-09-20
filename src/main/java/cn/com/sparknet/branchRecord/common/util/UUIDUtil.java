package cn.com.sparknet.branchRecord.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * UUID生成类(32位)
 * @author zhangxd
 *
 */
public final class UUIDUtil {
	
	private UUIDUtil(){
	}
	
	private static byte serverIP[] = null;
	private static SecureRandom secureRand;

	static {
		secureRand = new SecureRandom();
		try {
			serverIP = InetAddress.getLocalHost().getAddress();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	   /**
     * 获取数字ID
     * @return
     */
    public static final String getNextIntValue() {
        int totalLength=32;
        Random random=new Random(); 
        StringBuffer num=new StringBuffer();
        String time=String.valueOf(System.currentTimeMillis());
        int timeLength=time.length();
        int cycleLength=totalLength-timeLength;
        for(int i=0;i<cycleLength;i++){
            num.append(random.nextInt(10));
        }
        return time+num.toString();
    }
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static final String getNextValue() {
		String hexServerIP = hexFormat(getInt(serverIP), 8);
		String hexThis = hexFormat(System.identityHashCode(UUIDUtil.class), 8);
		long timeNow = System.currentTimeMillis();
		int timeLow = (int) timeNow & -1;
		String hexTime = hexFormat(timeLow, 8);
		int rand = secureRand.nextInt();
		String hexRand = hexFormat(rand, 8);
		StringBuffer guid = new StringBuffer(32);
		guid.append(hexTime);
		guid.append(hexServerIP);
		guid.append(hexRand);
		guid.append(hexThis);
		return guid.toString();
	}
	private static int getInt(byte bytes[]) {
		int i = 0;
		int j = 24;
		for (int k = 0; j >= 0; k++) {
			int l = bytes[k] & 0xff;
			i += l << j;
			j -= 8;
		}
		return i;
	}
	private static String hexFormat(int i, int j) {
		String s = Integer.toHexString(i);
		return padHex(s, j) + s;
	}
	private static String padHex(String s, int i) {
		StringBuffer tmpBuffer = new StringBuffer();
		if (s.length() < i) {
			for (int j = 0; j < i - s.length(); j++){
				tmpBuffer.append('0');
			}
		}
		return tmpBuffer.toString();
	}
	
}
