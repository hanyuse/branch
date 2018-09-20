package cn.com.sparknet.branchRecord.common.util;

import java.beans.Expression;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


/**
 * NumberUtil.java
 *
 * @author Guoxp
 * @desc 数字计算工具
 * @datatime Apr 7, 2013 3:52:29 PM
 */
public class NumberUtil {

    /**
     * 生成不重复随机数
     * 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组
     *
     * @param begin 最小数字（包含该数）
     * @param end   最大数字（不包含该数）
     * @param size  指定产生随机数的个数
     */
    public int[] generateRandomNumber(int begin, int end, int size) {
        // 加入逻辑判断，确保begin<end并且size不能大于该表示范围
        if (begin >= end || (end - begin) < size) {
            return null;
        }
        // 种子你可以随意生成，但不能重复
        int[] seed = new int[end - begin];

        for (int i = begin; i < end; i++) {
            seed[i - begin] = i;
        }
        int[] ranArr = new int[size];
        Random ran = new Random();
        // 数量你可以自己定义。
        for (int i = 0; i < size; i++) {
            // 得到一个位置
            int j = ran.nextInt(seed.length - i);
            // 得到那个位置的数值
            ranArr[i] = seed[j];
            // 将最后一个未用的数字放到这里
            seed[j] = seed[seed.length - 1 - i];
        }
        return ranArr;
    }


    /**
     * 生成不重复随机数
     * 根据给定的最小数字和最大数字，以及随机数的个数，产生指定的不重复的数组
     *
     * @param begin 最小数字（包含该数）
     * @param end   最大数字（不包含该数）
     * @param size  指定产生随机数的个数
     */
    public Integer[] generateBySet(int begin, int end, int size) {
        // 加入逻辑判断，确保begin<end并且size不能大于该表示范围
        if (begin >= end || (end - begin) < size) {
            return null;
        }

        Random ran = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < size) {
            set.add(begin + ran.nextInt(end - begin));
        }

        Integer[] ranArr = new Integer[size];
        ranArr = set.toArray(new Integer[size]);
        //ranArr = (Integer[]) set.toArray();

        return ranArr;
    }

    /**
     * 判断String是否是整数
     */
    public boolean isInteger(String s) {
        if ((s != null) && (s != ""))
            return s.matches("^[0-9]*$");
        else
            return false;
    }

    /**
     * 判断字符串是否是浮点数
     */
    public boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains("."))
                return true;
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是数字
     */
    public boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }

    //排序方法
    public static void sort(int[] array) {// 小到大的排序
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 是否是质数
     */
    public static boolean isPrimes(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 阶乘
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    /**
     * 平方根算法
     *
     * @param x
     * @return
     */
    public static long sqrt(long x) {
        long y = 0;
        long b = (~Long.MAX_VALUE) >>> 1;
        while (b > 0) {
            if (x >= y + b) {
                x -= y + b;
                y >>= 1;
                y += b;
            } else {
                y >>= 1;
            }
            b >>= 2;
        }
        return y;
    }


    private int math_subnode(int selectNum, int minNum) {
        if (selectNum == minNum) {
            return 1;
        } else {
            return selectNum * math_subnode(selectNum - 1, minNum);
        }
    }

    private int math_node(int selectNum) {
        if (selectNum == 0) {
            return 1;
        } else {
            return selectNum * math_node(selectNum - 1);
        }
    }

    /**
     * 可以用于计算双色球、大乐透注数的方法
     * selectNum：选中了的小球个数
     * minNum：至少要选中多少个小球
     * 比如大乐透35选5可以这样调用processMultiple(7,5);
     * 就是数学中的：C75=7*6/2*1
     */
    public int processMultiple(int selectNum, int minNum) {
        int result;
        result = math_subnode(selectNum, minNum)
                / math_node(selectNum - minNum);
        return result;
    }

    /**
     * 求m和n的最大公约数
     */
    public static int gongyue(int m, int n) {
        while (m % n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return n;
    }

    /**
     * 求两数的最小公倍数
     */
    public static int gongbei(int m, int n) {
        return m * n / gongyue(m, n);
    }

    /**
     * 递归求两数的最大公约数
     */
    public static int divisor(int m, int n) {
        if (m % n == 0) {
            return n;
        } else {
            return divisor(n, m % n);
        }
    }

    public static void main(String[] args) {
        NumberUtil util = new NumberUtil();
        System.out.println(util.sqrt(100));
    }

    private static final int FourDecimalMedian = 4;  //小数位数

    /**
     * 将object转换成整型，当传入的对象是null时返回指定的值
     *
     * @param o
     * @param dv
     * @return
     */
    public static Integer safeToInteger(Object o, Integer dv) {
        Integer r = dv;
        if (o != null) {
            try {
                r = new Integer(String.valueOf(o));
            } catch (Exception ex) {
            }
        }
        return r;
    }

    /**
     * 将object转换成Long，当传入的对象是null时返回指定的值
     *
     * @param o
     * @param dv
     * @return
     */
    public static Long safeToLong(Object o, Long dv) {
        Long r = dv;
        if (o != null) {
            try {
                r = new Long(String.valueOf(o));
            } catch (Exception ex) {
            }
        }
        return r;
    }
    /**
     * 将object转换成整型，当传入的对象是null时返回指定的值
     *
     * @param o
     * @param dv
     * @return
     */
    public static Double safeToDouble(Object o, Double dv) {
        Double r = dv;
        if (o != null) {
            try {
                r = new Double(String.valueOf(o));
            } catch (Exception ex) {
            }
        }
        return r;
    }

    /**
     * 将object转换成整型，当传入的对象是null时返回指定的值
     *
     * @param o
     * @param dv
     * @return
     */
    public static Float safeToFloat(Object o, Float dv) {
        Float r = dv;
        if (o != null) {
            try {
                r = new Float(String.valueOf(o));
            } catch (Exception ex) {
            }
        }
        return r;
    }

    /**
     * String类型 转 BigDecimal类型
     *
     * @return paraValue
     */
    public static BigDecimal stringToBigDecimal(String paraValue) {
        try {
            BigDecimal bDecimal = null;
            if (paraValue.indexOf("%") == -1) {
                return new BigDecimal(Double.valueOf(paraValue.trim().replace(",", ""))).setScale(4, BigDecimal.ROUND_HALF_UP);
            } else {
                return new BigDecimal(Double.valueOf(paraValue.trim().replace("%", "")) / 100).setScale(FourDecimalMedian, BigDecimal.ROUND_HALF_UP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Double strToDouble(String paraValue) {

        Double d = 0.0;
        if (paraValue.indexOf("%") == -1) {
            d = (Double.valueOf(paraValue.trim().replace(",", "")));
        } else {
            d = Double.valueOf(paraValue.trim().replace("%", "")) / 100;
        }
        return d;
    }
    /**
     * String类型 转 BigDecimal类型
     *
     * @return paraValue
     */
    public static BigDecimal stringToBigDecimalTwoSpilt(String paraValue) {
        try {
            BigDecimal bDecimal = null;
            if (paraValue.indexOf("%") == -1) {
                return new BigDecimal(Double.valueOf(paraValue.trim().replace(",", ""))).setScale(2, BigDecimal.ROUND_HALF_UP);
            } else {
                return new BigDecimal(Double.valueOf(paraValue.trim().replace("%", "")) / 100).setScale(FourDecimalMedian, BigDecimal.ROUND_HALF_UP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * aParaValue值 - bParaValue值
     *
     * @return BigDecimal
     */
    public static BigDecimal aSubtractB(BigDecimal aParaValue, BigDecimal bParaValue) {
        try {
            return aParaValue.subtract(bParaValue).setScale(FourDecimalMedian, BigDecimal.ROUND_HALF_UP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * aParaValue值 / bParaValue值
     *
     * @return BigDecimal
     */
    public static BigDecimal aDivideB(BigDecimal aParaValue, BigDecimal bParaValue,int numLimt) {
        if (numLimt < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b3 = aParaValue.divide(bParaValue, numLimt, BigDecimal.ROUND_HALF_UP);

//        try {
//            return aParaValue.divide(bParaValue, numLimt).setScale(numLimt, BigDecimal.ROUND_HALF_UP);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return b3;
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal b3 = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
        Double d = b3.doubleValue();
//        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }



    public static double avg(double[] sequenceArr) {
        if (sequenceArr != null && sequenceArr.length > 0) {
            double sum = 0.00;
            int T = sequenceArr.length;
            for (int i = 0; i < T; i++) {
                sum += sequenceArr[i];
            }
            return sum / T;
        }
        return 0.00;
    }

    public static double round(double value, int count) {
        int t = (int) Math.pow(10.0, (double) (count + 1));
        return ((double) Math.round(value * t)) / t;
    }


    public static Double getFourDecimalNumber(Number number) {
        if (number == null || (0.00 == number.doubleValue())) {
            return 0.0000;
        }
        NumberFormat format = new DecimalFormat("##0.0000");
        return Double.valueOf(format.format(number));
    }

    /**
     * 从指定Map中获取指定Key的 值，并将期值转为Integer型，若Map中指定Key不存在，则直接返回默认值
     *
     * @param map          Map
     * @param key          Key
     * @param defaultValue 默认值
     * @return 将Map值转换后的Integer值，若不存在则返回默认值
     */
    public static Integer convertMapKeyToInt(Map map, String key, Integer defaultValue) {
        return safeToInteger(map.get(key), defaultValue);
    }

    /**
     * 从指定Map中获取指定Key的值，并将期值转为Integer型，若Map中指定Key不存在，则直接返回0
     *
     * @param map Map
     * @param key Key
     * @return 将Map值转换后的Integer值，若不存在则返回0
     */
    public static Integer convertMapKeyToInt(Map map, String key) {
        return convertMapKeyToInt(map, key, 0);
    }

    /**
     * 将int数组转换成以逗号分隔的字符串
     *
     * @param intArray
     * @return
     */
    public static String convertIntArrayToString(int[] intArray) {
        if (intArray.length <= 0)
            return "";
        String _string = "";
        for (int _int : intArray) {
            _string += _string.equals("") ? new Integer(_int).toString() : "," + new Integer(_int).toString();
        }
        return _string;
    }

    /**
     * 获取百分比 整形
     * @param num1
     * @param num2
     * @return
     */
    public static String getPercent(Integer num1, Integer num2) {
        String rtnPercent = "0.00%";
        Double p3 = 0.0;
        if(num1 ==0){
            p3 = 0.0;
        }else{
            p3 = num1 * 1.0/num2;
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);//控制保留小数点后几位，2：表示保留2位小数点
        rtnPercent = nf.format(p3);
        return rtnPercent;
    }

    /**
     * 获取百分比 long
     * @param num1
     * @param num2
     * @return
     */
    public static String getPercent(Long num1, Long num2) {
        String rtnPercent = "0.00%";
        Double p3 = 0.0;
        if(num1 ==0){
            p3 = 0.0;
        }else{
            p3 = num1 * 1.0/num2;
        }
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2);//控制保留小数点后几位，2：表示保留2位小数点
        rtnPercent = nf.format(p3);
        return rtnPercent;
    }


}