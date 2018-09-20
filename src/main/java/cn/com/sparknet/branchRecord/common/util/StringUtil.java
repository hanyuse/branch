package cn.com.sparknet.branchRecord.common.util;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import cn.com.sparknet.branchRecord.common.propety.ReadProperties;



/**
 * 字符工具类
 * @author zhangxd
 */
public final class StringUtil {
    public static String logForInsert="I";
    public static String logForUpdate="U";
    public static String logForDelete="D";

    private StringUtil() {
    }


    /**
     * 判断是否不为null
     * @param string
     * @return boolean
     */
    public static final boolean isNotEmpty(String string) {
        return string != null && (!string.equals("")) && string.length() > 0;
    }


    /**
     * 判断是否为null
     * @param string
     * @return boolean
     */
    public static final boolean isEmpty(String string) {
        return string == null || string.length() == 0 || "null".equals(string);
    }


    /**
     * null转换成""
     * @param string
     * @return String
     */
    public static final String nullToEmpty(String str) {
        if (str == null || "null".equals(str)) {
            return "";
        } else {
            return str.toString().trim();
        }
    }


    /**
     * 转换各种类型为String类型，若为null则默认返回空字符串""
     * @author caikun 2016-06-02
     * @param obj
     * @return String
     */
    public static final String getStr(Object obj){
        String rtnStr = "";
        if(obj == null){
            return rtnStr;
        }
        try{
            rtnStr = obj.toString().trim();
        }catch (Exception e){
            System.out.println("getStr转换String格式出错！");
            rtnStr = "";
        }
        return rtnStr.length() == 0 ? "" : rtnStr;
    }

    /**
     * null转换成0.0
     * @param string
     * @return String
     */
    public static double nullToDouble(String str) {
        if (str == null || "null".equals(str)) {
            return 0.0;
        } else {
            return Double.parseDouble(str.toString().trim());
        }
    }

    /**
     * null转换成""
     * @param string
     * @return String
     */
    public static final String nullToEmpty(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return (obj + "").trim();
        }
    }


    /**
     * null转换成"0"
     * @param string
     * @return String
     */
    public static final String nullToZero(Object object) {
        if (object == null || "null".equals(object) || "".equals(object)) {
            return "0";
        } else {
            return object.toString().trim();
        }
    }


    /**
     * null转换成"0"
     * @param string
     * @return String
     */
    public static final boolean nullToBoolean(String str) {
        if (str == null || "null".equals(str) || "".equals(str)) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * null转换成" "
     * @param string
     * @return String
     */
    public static final String nullToSpace(String str) {
        if (str == null || "".equals(str)) {
            return " ";
        } else {
            return str.toString().trim();
        }
    }


    /**
     * null转换成 null
     * @param string
     * @return String
     */
    public static final String nullToNull(String str) {
        if (str == null || "null".equals(str) || "".equals(str)) {
            return null;
        } else {
            return str.toString().trim();
        }
    }
    /**
     * null转换成 null 
     * @param Object
     * @return String
     */
    public static final String nullToNull(Object str) {
        if (str == null || "null".equals(str) || "".equals(str)) {
            return null;
        } else {
            return (str+"").trim();
        }
    }


    /**
    * null转换成null
    * @param string
    * @return String
    */
    public static final Double nullToDoubleNull(String str) {
        if (str == null || "null".equals(str) || "".equals(str)) {
            return null;
        } else {
            return Double.valueOf(str.toString().trim());
        }
    }


    /**
    * null转换成null
    * @param string
    * @return String
    */
    public static final Double nullToDoubleNull(Object str) {
        if (str == null || "null".equals(str) || "".equals(str)) {
            return null;
        } else {
            return Double.valueOf((str+"").trim());
        }
    }
    
    /**
    * str转换成boolean
    * @param string
    * @return boolean
    */
    public static final boolean strToBoolean(String str) {
        if (str == null || "null".equals(str) || "".equals(str)) {
            return false;
        } else {
            return Boolean.parseBoolean(str);
        }
    }


    /**
     * 对象为null时返回其他自定义对象
     * @param obj
     * @param replaceContent
     * @return
     */
    public static Object nullToObject(Object sourceObj, Object targetObj) {
        if (sourceObj == null) {
            return targetObj;
        }
        if (sourceObj.toString().equals("null")) {
            return targetObj;
        }
        String str = sourceObj.toString().trim();
        return str.length() == 0 ? targetObj : str;
    }


    /**
     * 
     * 说明：将字符串数组{"str1","str2","str3"}转换成'str1','str2','str3'
     * @param string[]
     * @return String
     *
     */
    public static final String arrToStr(String[] strings) {
        StringBuffer buf = new StringBuffer();
        buf.append("'");
        buf.append(join("','", strings));
        buf.append("'");
        return buf.toString();
    }


    /**
     *
     * 说明：将字符串"str1,str2,str3"转换成"'str1','str2','str3'"
     * @param String
     * @return String
     *
     */
    public static final String strToStr(String str) {
        StringBuffer buf = new StringBuffer();
        buf.append("'");
        buf.append(replace(str, ",", "','"));
        buf.append("'");
        return buf.toString();
    }


    public static final String join(String seperator, String[] strings) {
        int length = strings.length;
        if (length == 0) {
            return "";
        }
        StringBuffer buf = new StringBuffer(length * strings[0].length()).append(strings[0]);
        for (int i = 1; i < length; i++) {
            buf.append(seperator).append(strings[i]);
        }
        return buf.toString();
    }


    /**
     *
     * 说明：替换字符串
     * @param String
     * @return String
     *
     */
    public static final String replace(String template, String placeholder, String replacement) {
        return replace(template, placeholder, replacement, false);
    }


    public static final String replace(String template, String placeholder, String replacement, boolean wholeWords) {
        int loc = template.indexOf(placeholder);
        if (loc < 0) {
            return template;
        } else {
            final boolean actuallyReplace =
                    !wholeWords || loc + placeholder.length() == template.length()
                            || !Character.isJavaIdentifierPart(template.charAt(loc + placeholder.length()));
            String actualReplacement = actuallyReplace ? replacement : placeholder;
            return new StringBuffer(template.substring(0, loc)).append(actualReplacement)
                    .append(replace(template.substring(loc + placeholder.length()), placeholder, replacement, wholeWords)).toString();
        }
    }


    /**
     * 去除换行、回车、制表符
     * @param str
     * @return
     */
    public static String replaceTRN(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    /**
     * 初始化war包中文件路径
     * */
    public static String initSysPath() {
        String sysPath = StringUtil.class.getClassLoader().getResource("/").getPath();
        if (sysPath.startsWith("/")) {
            sysPath = sysPath.substring(1, sysPath.length());
        }
        return sysPath;
    }


    /**
     * 说明：获取Map中的所有键的集合
     * @return List<String>
     */
    public static final List<String> getMapKey(Map map) {
        List<String> list = new ArrayList<String>();
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        Map.Entry mapEntry = null;
        while (iterator.hasNext()) {
            mapEntry = (Map.Entry)iterator.next();
            list.add(mapEntry.getKey().toString());
        }
        return list;
    }


    /**
     * 替换非法字符
     * 
     * @param str 字符串
     * 
     * @return
     * */
    public static String strFiltrate(String str) {
        if (str == null || str.equals("")) {
            return "";
        }

        Object o = ReadProperties.getContextProperty("passSpecial");
//        if (o != null) {
            //			str = str.replaceAll("&", "&amp;");
//            str = str.replaceAll("<", "&#x3C;");
//            str = str.replaceAll(">", "&#x3E;");
//            str = str.replaceAll("\"", "");
//            str = str.replaceAll("\'", "");
//            str = str.replaceAll("%", "");
//            str = str.replaceAll("eval", "");
//            str = str.replaceAll("expression", "");
//            str = str.replaceAll("unescape", "");
//            str = str.replaceAll(",", "，");
//            if (str.indexOf("&#x3E;") == -1 && str.indexOf("&#x3E;") == -1) {
//                str = str.replaceAll(";", "；");
//            }
//            str = str.replaceAll(":", "：");

//        } else {
//            str = str.replaceAll("&", "&amp;");
            str = str.replaceAll("<", "&#x3C;");
            str = str.replaceAll(">", "&#x3E;");
//            str = str.replaceAll("\"", "");
//            str = str.replaceAll("\'", "");
            str = str.replaceAll("%", "");
            str = str.replaceAll("eval", "");
            str = str.replaceAll("expression", "");
            str = str.replaceAll("unescape", "");
            str = str.replaceAll("\\(", "");
            str = str.replaceAll("\\)", "");
//            str = str.replaceAll(",", "，");
//            str = str.replaceAll(":", "：");
//        }

        return str;
    }
    
    
    public static String strNewFiltrate(String str) {
        if(str==null || str.equals("")){
            return str;
        }
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&#x3C;");
//        str = str.replaceAll(">", "&#x3E;");
        str = str.replaceAll("\"", "");
        str = str.replaceAll("\'", "");
        str = str.replaceAll("'", "");
        str = str.replaceAll("%", "");
        str = str.replaceAll("eval", "");
        str = str.replaceAll("expression", "");
        str = str.replaceAll("unescape", "");
        str = str.replaceAll(",", "，");
        str = str.replaceAll(":", "：");
        
        str = str.replaceAll(";", "；");
        str = str.replaceAll("\\(", "（");
        str = str.replaceAll("\\)", "）");
        str = str.replaceAll("&", "");
        str = str.replaceAll("\\+", "");
        str = str.replaceAll("=", " ");
        str = str.replaceAll("having", " ");
        str = str.replaceAll("group", " ");
        str = str.replaceAll(".*([';]+|(--)+).*", " ");
        return str;
    }


    /**
     * 创建文件夹
     * 
     * @param completeFilePath 完整路径
     * @param notCapTheLastE 不截取最后一个元素，默认为false
     * 
     * */
    public static String generateTmpFolder(String completeFilePath) {

        String rootFolder = System.getProperty("user.dir");

        String localAddr = rootFolder + "\\" + completeFilePath;

        localAddr = localAddr.replaceAll("\\\\", "/");

        //创建文件夹
        File folderAddr = new File(localAddr);
        if (!folderAddr.exists()) {
            folderAddr.mkdirs();
        }

        return localAddr;

    }


    /**
     * 获取指定key名称
     * 
     * @param map map集合
     * @param separator 分隔符 （比如："_"）
     * @param keyPartName key部分名称
     * 
     * */
    public static String getMapKey(Map map, String keyPartName, String separator) {

        String key = "";

        Iterator iter = map.entrySet().iterator();

        Map.Entry<String, String> param = null;

        while (iter.hasNext()) {
            param = (Entry<String, String>)iter.next();
            if (param.getKey().indexOf(keyPartName) != -1) {
                key = param.getKey().split("\\" + separator)[1];
                break;
            }

        }

        return key;

    }


    /**
     * 判断字符串中是否存在指定的indexOf
     * 
     * @param  str  被比较字符串（比如："1,2,3,4"）
     * @param  str1 比较字符串（比如："1"）
     * @param  type 分隔符（比如："," 只能是单个分隔符）
     * 
     * @return
     * */
    public static boolean existenceOfStr(String str, String str1, String type) {

        boolean isSame = false;

        if (str.lastIndexOf(type) != -1) {
            str = str.substring(0, str.length() - 1);
        }

        String[] strArray = str.split("\\" + type);

        for (int i = 0, num = strArray.length; i < num; i++) {
            if (str1.equals(strArray[i])) {
                isSame = true;
                break;
            }
        }

        return isSame;

    }


    /**
     * 将传递的参数转换成Map
     * 
     * @param strList
     *            存放String类型数据的变量名集合（集合存储方式：key#value）

     * @return
     */
    public static Map<String, String> strTransformToMap(List<String> strList) {

        Map<String, String> map = new HashMap<String, String>();

        try {
            for (int i = 0, num = strList.size(); i < num; i++) {
                int strLength = strList.get(i).split("\\#").length;

                if (strLength > 1) {
                    map.put(strList.get(i).split("\\#")[0].toUpperCase(), strList.get(i).split("\\#")[1]);
                } else {
                    map.put(strList.get(i).split("\\#")[0].toUpperCase(), "");
                }

            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        return map;

    }


    /**
     * 功能说明：将参数转换为适合SQL查询的字符

     * 
     * 
     * 
     * 
     * 
     * 比如：将String str="1,2,3,4"转化成 String str="'1','2','3'";
     * 
     * @param str
     *            以某种类型分隔的存放数字的字符串
     * 
     * @param type
     *            分隔符号类型
     * 
     * @return 返回字符串

     * 
     * 
     * 
     * 
     */
    public static String transNumberToChar(String str, String type) {
        String strTrans = "";
        String[] strArray = null;
        try {
            String transBefore = str;
            strArray = transBefore.split(type);

            for (int i = 0; i < strArray.length; i++) {
                String strEach = "'" + strArray[i] + "'";
                strTrans += strEach + ",";
            }

            if (strTrans.lastIndexOf(",") != -1) {
                strTrans = strTrans.substring(0, strTrans.length() - 1);
            }

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return strTrans;
    }


    /**
     * 获取字符串的长度，中文占两个字节,英文数字占一个字节

     * 
     * @param value
     *            指定的字符串
     * @return 字符串的长度
     */
    public static double length(String value) {
        double valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < value.length(); i++) {
            // 获取一个字符

            String temp = value.substring(i, i + 1);
            // 判断是否为中文字符

            if (temp.matches(chinese)) {
                // 中文字节长度为2
                valueLength += 2;
            } else {
                // 其他字节长度为1
                valueLength += 1;
            }
        }
        // 进位取整
        return Math.ceil(valueLength);
    }


    /**
     * 截取字符串，超出部分以“...”代替

     * 
     * @param value
     *            指定的字符串
     *            
     * @param number 需要展示长度     
     *            
     * @return 字符串的长度
     */
    public static String subLength(String value, int number) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < value.length(); i++) {
            // 获取一个字符

            String temp = value.substring(i, i + 1);
            // 判断是否为中文字符

            if (temp.matches(chinese)) {
                // 中文字节长度为2
                valueLength += 2;
                if (valueLength > number) {
                    return value.substring(0, number < valueLength ? number / 2 : value.length()) + "...";
                }
            } else {
                // 其他字节长度为1
                valueLength += 1;
                if (valueLength > number) {
                    return value.substring(0, number < valueLength ? number / 2 : value.length()) + "...";
                }
            }
        }
        // 进位取整
        return value;
    }


    /**
     * 获取IP地址
     * @param request
     * @return
     */
    public static final String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 定义Object类型数组
     * 
     * @param map
     *            参数集合
     * 
     * @return
     */
    public static Object[] statementObjectArray(Map<String, Object> map) {

        int arrayLength = 0;

        Set setKeys = map.keySet();

        List listValue = new ArrayList();

        Iterator iteKeys = setKeys.iterator();

        while (iteKeys.hasNext()) {

            String keyName = iteKeys.next().toString();

            String strValue = map.get(keyName).toString();

            boolean isStr = Boolean.parseBoolean("".equals(strValue) ? "false" : "true");

            if (isStr) {
                listValue.add(keyName);
                arrayLength++;
            }

        }

        Object[] objArray = new Object[arrayLength];

        for (int i = 0, num = listValue.size(); i < num; i++) {
            String keyName = listValue.get(i).toString();
            String strValue = map.get(keyName).toString();
            objArray[i] = strValue;
        }

        return objArray;

    }


    /**
     * 获取系统日期 时间
     * @return String
     */
    public static final String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date());
        return sdf.format(cd.getTime());
    }


    /**
     * 判断系统时间是否在3月1日到7月31日之间
     * @return boolean
     */
    public static boolean isBefore0731() {
        //当前时间
        Calendar dateNow = Calendar.getInstance();

        //当前年3月1日
        Calendar date0301 = Calendar.getInstance();
        date0301.set(dateNow.get(Calendar.YEAR), Calendar.MARCH, 1);
        //当前年7月31日
        Calendar date0731 = Calendar.getInstance();
        date0731.set(dateNow.get(Calendar.YEAR), Calendar.JULY, 31);

        //当前时间在3月1日到7月31日之间
        if (dateNow.after(date0301) && dateNow.before(date0731)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 针对企业的数据进行转换，我去年买了个表
     * */
    public static String converStrBase(String val) {
        val = val.replaceAll("\\$1", "=");
        byte[] byteArray = null;
        String base = "";
        try {
            byteArray = Base64Encrypt.decryptBASE64(new String(Base64Encrypt.decryptBASE64(new String(Base64Encrypt.decryptBASE64(val)))));
            base = new String(byteArray, "UTF-8");
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return base;
    }


    /**
     * 针对企业的数据进行转换
     * */
    public static String baseToStr(String val) {
        val = val.replaceAll("\\$1", "=");
        byte[] byteArray = null;
        String base = "";
        try {
            byteArray = Base64Encrypt.decryptBASE64(val);
            base = new String(byteArray, "UTF-8");
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return base;
    }


    /**
     * 针对外资企业那帮货的乱七八糟的数据，做数据转换，NMD
     * 
     * */
    public static String converStr(String val) {

        val = val.replaceAll("\\$1", ",");
        val = val.replaceAll("\\$2", "，");
        val = val.replaceAll("\\$3", ".");
        val = val.replaceAll("\\$4", "'");
        val = val.replaceAll("\\$5", "/");
        val = val.replaceAll("\\$6", "(");
        val = val.replaceAll("\\$7", ")");
        val = val.replaceAll("\\$8", "-");
        val = val.replaceAll("\\$9", ";");
        //    	val = val.replaceAll("：",":");

        return val;
    }


    /**
     * 替换字符串中指定字符串
     * 
     * @param completeContent
     *            完整内容
     * @param replaceContent
     *            替换前内容
     * @param replacedContent
     *            替换后内容
     * 
     * */
    public static String replacePointStr(String completeContent, String replaceContent, String replacedContent) {
        Pattern p = Pattern.compile(replaceContent);
        Matcher m = p.matcher(completeContent);
        StringBuffer sb = new StringBuffer();
        int i = 0;
        boolean result = m.find();
        while (result) {
            i++;
            m.appendReplacement(sb, replacedContent);
            result = m.find();
        }
        m.appendTail(sb);
        return sb.toString();
    }


    /**
     * 取出字符串中指定字符串
     * 
     * @param pattern 正则表达式
     * @param str 完整字符串
     * 
     * */
    public static String catchPointStr(String pattern, String str) {
        String pointStr = "";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        while (m.find()) {
            pointStr = pointStr.concat(m.group());
        }
        return pointStr;
    }


    /**
     * 将全角字符转成半角字符
     * 
     * @param str 全角字符串
     * 
     * */
    public static String doubleTosingle(String str) {
        Pattern pattern = Pattern.compile("[\u3000\uff01-\uff5f]{1}");

        Matcher m = pattern.matcher(str);
        StringBuffer s = new StringBuffer();
        while (m.find()) {
            char c = m.group(0).charAt(0);
            char replacedChar = c == '　' ? ' ' : (char)(c - 0xfee0);
            m.appendReplacement(s, String.valueOf(replacedChar));
        }

        m.appendTail(s);

        return s.toString();
    }
    
    /**
     * 格式化Double
     * 
     * @param val Double类型数据
     * @param type 精确的小数
     * 
     * */
    public static String formatDouble(double val,String type){
//    	String type = "#.000000";
    	DecimalFormat df = new DecimalFormat(type);
		String st=df.format(val);
		return st;
    }
    
    /**
     * 取数组中日期最大值
     * 
     * @param dateArray 日期数组
     * 
     * */
    public static String maxDate(String[] dateArray){
    	
    	int number = dateArray.length;
    	String maxDate = "";
    	for(int i = 0;i<number;i++){
    		String date1 = dateArray[i];
    		boolean isMax = false;
    		for(int j = 0;j<number;j++){
    			String date2 = dateArray[j];
    			
    			try {
					if(!DateUtil.isBeforeDate(date1, date2)){
						isMax = true;
					}else{
						isMax = false;
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
    			
    		}
    		
    		if(isMax){
    			maxDate = date1;
    		}
    		
    	}
    	
    	if(!"".equals(maxDate)){
    		String[] maxDateArray = maxDate.split("-");
    		maxDate = maxDateArray[0]+"年";
    		maxDate += maxDateArray[1]+"月";
    		maxDate += maxDateArray[2]+"日";
    	}
    	
    	return maxDate;
    	
    }
    
    public static boolean checkNumber(double value){  
        String str = String.valueOf(value);  
        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";  
        return str.matches(regex);  
    }  
      
    public static boolean checkNumber(int value){  
        String str = String.valueOf(value);  
        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";  
        return str.matches(regex);  
    }  
      
    public static boolean checkNumber(String value){  
        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";  
        return value.matches(regex);  
    }  
    
}
