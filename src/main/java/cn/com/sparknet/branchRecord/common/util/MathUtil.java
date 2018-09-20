package cn.com.sparknet.branchRecord.common.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Random;

/**
 * 简单的数学运算
 * @author zhangxd
 *
 */
public final class MathUtil {
	
	private MathUtil(){
	}
	
	/**
	 * 加法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 减法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 乘法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 除法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double div(double v1, double v2) {
		if (v2 == 0.0D) {
			return 0.0D;
		} else {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return b1.divide(b2, 4).doubleValue();
		}
	}
	
	/**
	 * 除法
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return
	 */
	public static double div(double v1, double v2, int scale) {
		if (v2 == 0.0D)
			return 0.0D;
		if (scale < 0) {
			throw new IllegalArgumentException("精度范围必须是大于等于零的正整数！");
		} else {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return b1.divide(b2, scale, 4).doubleValue();
		}
	}

	/**
	 * 四舍五入
	 * @param v
	 * @param scale
	 * @return
	 * @throws ParseException
	 */
	public static double roundToDouble(double v, int scale) throws ParseException {
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setGroupingUsed(false);
		formatter.setMaximumFractionDigits(scale);
		return formatter.parse(formatter.format(v)).doubleValue();
	}

	/**
	 * 四舍五入
	 * @param v
	 * @param scale
	 * @return
	 */
	public static String roundToString(double v, int scale) {
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setGroupingUsed(false);
		formatter.setMaximumFractionDigits(scale);
		formatter.setMinimumFractionDigits(scale);
		return formatter.format(v);
	}

	/**
	 * 将1000生成1,000格式
	 * @param v
	 * @return
	 */
	public static String thousandth(double v) {
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setGroupingUsed(true);
		return formatter.format(v);
	}

	/**
	 * 随机数
	 * @param range
	 * @return
	 */
	public static int random(int range) {
		return (new Random()).nextInt(range);
	}
	
}