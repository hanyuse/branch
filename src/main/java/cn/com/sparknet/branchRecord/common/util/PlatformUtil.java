package cn.com.sparknet.branchRecord.common.util;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 *业务模块处理工具类

 * 
 * @author zhangxd
 * 
 */

public class PlatformUtil {

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
					map.put(strList.get(i).split("\\#")[0].toUpperCase(),
							strList.get(i).split("\\#")[1]);
				} else {
					map.put(strList.get(i).split("\\#")[0].toUpperCase(), "");
				}

			}
		} catch (Exception ex) {
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

		} catch (Exception ex) {
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
	public static String subLength(String value,int number) {
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
				if(valueLength>number){
					return value.substring(0,number<valueLength?number/2:value.length())+"...";
				}
			} else {
				// 其他字节长度为1
				valueLength += 1;
                if(valueLength>number){
                	return value.substring(0,number<valueLength?number/2:value.length())+"...";
				}
			}
		}
		// 进位取整
		return value;
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

			boolean isStr = Boolean.parseBoolean("".equals(strValue) ? "false"
					: "true");

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
	
	
	public static Object[] statementObjectArray1(Map<String, Object> map) {

        int arrayLength = 0;

        Set setKeys = map.keySet();

        List listValue = new ArrayList();

        Iterator iteKeys = setKeys.iterator();

        while (iteKeys.hasNext()) {

            String keyName = iteKeys.next().toString();

            String strValue = map.get(keyName).toString();
                listValue.add(keyName);
                arrayLength++;
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
	 *  计算两个日期（月份）之间的月数，并且返回所有的日期
	 *  
	 *  @param startDate 开始日期（yyyy-MM）
	 *  @param endDate 开始日期（yyyy-MM）
	 *  @param type 格式形式（1：yyyy-MM；2：yyyy-M；3：yyyyMM；4：3：yyyyM）
	 *  
	 *  @return
	 * */
	public static String[] getAllDateBetweenTwoDate(String startDate,String endDate,String type){
	
		String[] allDate=null;
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		
			
			Date sDate=sdf.parse(startDate);
			Date eDate=sdf.parse(endDate);
			
			int difference = 0; //相差月份
			int sYear=sDate.getYear(); //起始日期年
			int eYear=eDate.getYear();//结束日期年
			int sMonth=sDate.getMonth()+1;//起始日期月份
			int eMonth=eDate.getMonth()+1;//结束日期月份

			if(sDate.getTime()<=eDate.getTime()){
				
				difference = (eYear - sYear) * 12;
				difference = difference > 0 ? (difference - (12 - eMonth)) + 1 : difference
						- (12 - eMonth);
				difference = difference + (12 - sMonth);
				
				if(sYear<eYear){
					difference=difference-1;
				}
				
				allDate=new String[difference+1];
				
				int recursiveNumber=11;
				
				for(int i=0,num=allDate.length;i<num;i++){
					int year=Integer.parseInt(startDate.split("-")[0]);
					int month=Integer.parseInt(startDate.split("-")[1]) + i;
					
					if(month>12){
						int startMonth=Integer.parseInt(startDate.split("-")[1]);
						month = (startMonth + 12) - (startMonth + recursiveNumber);
						year++;
						recursiveNumber--;
					}
					if("1".equals(type)){
						if(month<10){
							allDate[i]=year+"-"+"0"+month;
						}else{
							allDate[i]=year+"-"+month;
						}
						
					}else if("2".equals(type)){
						allDate[i]=year+""+month;
					}else if("3".equals(type)){
						if(month<10){
							allDate[i]=year+"0"+month;
						}else{
							allDate[i]=year+""+month;
						}
					}else{
						allDate[i]=year+""+month;
					}
					
				}
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return allDate;
		
	}
	
	/**
	 * 获取指定的日期（按照月）
	 * 
	 * @param sign
	 *            操作符（+/-）
	 * @param month
	 *            月份
	 * @param type
	 *            时间格式化类型
	 */
	public static String getPointTimeOfMonth(String sign, int month, String type) {
		Calendar curr = Calendar.getInstance();
		if ("+".equals(sign)) {
			curr.set(Calendar.MONTH, (curr.get(Calendar.MONTH)) + month);
		} else {
			curr.set(Calendar.MONTH, (curr.get(Calendar.MONTH)) - month);
		}

		Date date = curr.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		String dateTime = sdf.format(date);
		return dateTime;
	}
	
	/**
	 * 获取指定key名称
	 * 
	 * @param map map集合
	 * @param separator 分隔符 （比如："_"）
	 * @param keyPartName key部分名称
	 * 
	 * */
	public static String getMapKey(Map map,String keyPartName,String separator){
		
		String key="";
		
		Iterator iter = map.entrySet().iterator();
		
		Map.Entry<String, String> param = null;
		
		while(iter.hasNext())
		  {
			  param = (Entry<String, String>) iter.next();
		      if(param.getKey().indexOf(keyPartName) !=-1){
		    	  key=param.getKey().split("\\"+separator)[1];
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
	public static boolean existenceOfStr(String str,String str1,String type){
		
        boolean isSame=false;

		if(str.lastIndexOf(type) !=-1){
			str=str.substring(0,str.length()-1);
		}
		
		String[] strArray=str.split("\\"+type);
		
		for(int i=0,num=strArray.length;i<num;i++){
			if(str1.equals(strArray[i])){
				isSame=true;
				break;
			}
		}
		
		return isSame;
		
	}
	
	/**
	 * @function 获取捕获时路径
	 * @return
	 */
	public static String getFileCatchPath(){
		String filePath = "";
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);//获取年份
		int month=ca.get(Calendar.MONTH)+1;//获取月份
		int day=ca.get(Calendar.DATE);//获取月份
		String pMonth = "";
		if(month <=9){
			pMonth = "0"+month;
		}else{
			pMonth = ObjectUtils.toString(month);
		}
		if(day<=10){ 
			filePath = year+"\\"+pMonth+"01";
		}else if(day>10 && month<=20){
			filePath =  year+"\\"+pMonth+"11";
		}else{
			filePath =  year+"\\"+pMonth+"21";
		}
		return filePath;
	}

	/**
	 * StringToTimestamp
	 * 
	 * @param value
	 * @return
	 */
	public static Timestamp StringToTimestamp(String value) {
		try {
			 String format = "";
			if (value == null || "".equals(value)|| "null".equals(value)) {
				return null;
			} 
			if(value.indexOf(".")!=-1){
				 value=value.substring(0, 19);
				 format="yyyy-MM-dd HH:mm:ss";
			 }else{
				 //正对试图的日期20100101
				 if(value.length()==8){
					 value=value.substring(0,4)+"-"+value.substring(4,6)+"-"+value.substring(6,8);
				 }
			 }
			 if(value.indexOf(".")==-1 && value.length()>0){
				 if(value.length()<=10){
					 format="yyyy-MM-dd";
				 }else{
					 format="yyyy-MM-dd HH:mm:ss";
				 }
			 }  
			return new java.sql.Timestamp(DateUtils.parseDate(value,
					new String[] { format }).getTime());
		} catch (Exception e) {
			e.printStackTrace();
			//Log.error(this.getClass(), "StringToTimestamp", e.toString(), e);
			return null;
		}
	}
	/**
	 * 取得当前时间
	 */
	public static String getNowTime() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(now);
		return nowTime;
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
			return str;
		}
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&#x3C;");
		str = str.replaceAll(">", "&#x3E;");
		str = str.replaceAll("\"", "");
		str = str.replaceAll("\'", "");
		str = str.replaceAll("%", "");
		str = str.replaceAll("eval", "");
		str = str.replaceAll("expression", "");
		str = str.replaceAll("unescape", "");
		str = str.replaceAll(",", "，");
		str = str.replaceAll(";", "；");
		str = str.replaceAll(":", "：");

		// str = str.replaceAll(";", "");
		// str = str.replaceAll("(", "");
		// str = str.replaceAll(")", "");
		// str = str.replaceAll("&", "");
		// str = str.replaceAll("+", "");
		return str;
	}
	
	/**
	 * 创建文件夹
	 * 
	 * @param completeFilePath 完整路径
	 * @param notCapTheLastE 不截取最后一个元素，默认为false
	 * 
	 * */
	public static String generateTmpFolder(String completeFilePath){
		
		String rootFolder = System.getProperty("user.dir");
		
        String localAddr = rootFolder+"\\"+completeFilePath;
		
        localAddr = localAddr.replaceAll("\\\\", "/");
        
		//创建文件夹
		File folderAddr = new File(localAddr);
		if (!folderAddr.exists()) {
			folderAddr.mkdirs();
		}
		return localAddr;
	}
	
	
}
