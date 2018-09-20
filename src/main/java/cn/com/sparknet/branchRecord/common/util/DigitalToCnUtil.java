package cn.com.sparknet.branchRecord.common.util;

/**
 * 数字转中文
 * @author zhangxd
 * 
 */
public class DigitalToCnUtil {

	// 阿拉伯数字,字符串类型
	public String number;
	
	// 阿拉伯数字,int类型
	public int inumber;
	
	// 阿拉伯数字的位数
	public int size;

	// 构造函数
	public DigitalToCnUtil(String value) {
		number = value;
		inumber = Integer.parseInt(value);
		size = DigitalToCnUtil.getLength(inumber);
	}

	// 阿拉伯数字每一位
	public static final String[] num = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	
	// 中文数字每一位
	public static final String[] china = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	
	// 中文数字进位度量
	public static final String[] degree = { "十", "百", "千", "万", "亿" };
	

	// 计算输入数据长度
	public static int getLength(int value) {
		return (int) Math.floor((int) (Math.log10(value) + 1));
	}

	// 将输入数字转换成相应文字,不添加度量
	public static String convertAtoW(String value) {
		for (int i = 0; i < num.length; i++) {
			value = value.replaceAll(num[i], china[i]);
		}
		return value;
	}

	// 度量处理方法,处理到最高位为九位的数字,如果处理更高位的数字则需要做微小改动
	public String degree(String value) {
		StringBuffer temp = new StringBuffer(value);
		// 添加"十"
		if (size > 1) {
			temp = temp.insert(temp.length() - 1, degree[0]);
		}
		// 添加"百"
		if (size > 2) {
			temp = temp.insert(temp.length() - 3, degree[1]);
		}
		// 添加"千"
		if (size > 3) {
			temp = temp.insert(temp.length() - 5, degree[2]);
		}
		// 添加"万"
		if (size > 4) {
			temp = temp.insert(temp.length() - 7, degree[3]);
		}
		// 添加"十万"
		if (size > 5) {
			temp = temp.insert(temp.length() - 9, degree[0]);
		}
		// 添加"百万"
		if (size > 6) {
			temp = temp.insert(temp.length() - 11, degree[1]);
		}
		// 添加"千万"
		if (size > 7) {
			temp = temp.insert(temp.length() - 13, degree[2]);
		}
		// 添加"亿"
		if (size > 8) {
			temp = temp.insert(temp.length() - 15, degree[4]);
		}
		// 添加"十亿"以后处理类似,需要用BigInteger
		return temp.toString();
	}

	// 处理多零情况
	public String degreezero(String value) {
		String temp = new String(value);
		while ((temp.indexOf("零千") != -1) || (temp.indexOf("零百") != -1)
				|| (temp.indexOf("零十") != -1) || (temp.indexOf("零零") != -1)) {
			if (temp.indexOf("零千") != -1) {
				temp = temp.replaceAll("零千", "零");
			}
			if (temp.indexOf("零百") != -1) {
				temp = temp.replaceAll("零百", "零");
			}
			if (temp.indexOf("零十") != -1) {
				temp = temp.replaceAll("零十", "零");
			}
			if (temp.indexOf("零零") != -1) {
				temp = temp.replaceAll("零零", "零");
			}
		}
		if ((temp.indexOf("零万") != -1)) {
			temp = temp.replaceAll("零万", "万");
		}
		if ((temp.indexOf("亿万") != -1)) {
			temp = temp.replaceAll("万", "");
		}

		// 末尾不能有零
		if (temp.lastIndexOf("零") == temp.length() - 1) {
			temp = temp.substring(0, temp.length() - 1);
		}
		return temp;
	}

	public static void main(String[] args) {
		DigitalToCnUtil digitalToCn = new DigitalToCnUtil("9");
		String result = digitalToCn.degreezero(digitalToCn.degree(DigitalToCnUtil.convertAtoW(digitalToCn.number)));
//		System.out.println(result);
	}

}
