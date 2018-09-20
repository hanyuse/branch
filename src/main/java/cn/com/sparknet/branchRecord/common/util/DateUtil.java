package cn.com.sparknet.branchRecord.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.lang.time.DateUtils;


public class DateUtil {

    /**
     * 获取系统日期
     * @return String
     */
    public static final String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date());
        return sdf.format(cd.getTime());
    }


    /**
     * 获取系统日期
     * @return String
     */
    public static final String getCNDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date());
        return sdf.format(cd.getTime());
    }


    /**
     * 获取本年一月
     * 
     * @return String
     */
    public static final String getJanuary() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cd = Calendar.getInstance();
        cd.set(Calendar.DAY_OF_YEAR, 1);
        return sdf.format(cd.getTime());
    }
    
    /**
     * 获取当前年份的一月一号 没有“-”
     */
    public static final String getJanuaryDateNomat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cd = Calendar.getInstance();
        cd.set(Calendar.DAY_OF_YEAR, 1);
        return sdf.format(cd.getTime());
    }
    /**
     * 当前日期往前或者往后推指定月分
     * 格式： yyMMdd
     * @param sign
     *            操作符（+/-）

     * @param month
     *            月数
     */
    public static String getPointTimeOfTime(String sign, int month) {
        Calendar curr = Calendar.getInstance();
        curr.setTime(new Date());
        Calendar returnCal = Calendar.getInstance();
        

        if ("+".equals(sign)) {
            returnCal.add(curr.MONTH, +month);
        } else {
            returnCal.add(curr.MONTH, -month);
        }

        
        int v_intYear = returnCal.get(Calendar.YEAR);
        int v_intMonth = returnCal.get(Calendar.MONTH) + 1;
        int v_intDAY = returnCal.get(Calendar.DAY_OF_MONTH);

        String dateTime ="";
        if (v_intMonth < 10) {
            if(v_intDAY<10){
                dateTime = v_intYear + "" + "0" + v_intMonth + "" + "0" + v_intDAY;
            }else{
                dateTime = v_intYear + "" + "0" + v_intMonth + "" + v_intDAY;
            }
            
        } else {
            if(v_intDAY<10){
                dateTime = v_intYear + "" + v_intMonth + "" + "0" + v_intDAY;
            }else{
                dateTime = v_intYear + "" + v_intMonth + "" + v_intDAY;
            }
            
        }

        return dateTime;
    }
    
    /**
     * 获取系统的月份
     */
    public static final String getMonthMate() {
        String dateTime ="";
        int mon =  calendar.get(Calendar.MONTH) + 1;
        if (mon < 10) {
                dateTime =  "0" + mon ;
        } else {
                dateTime ="" + mon ;
            
        }
        return dateTime;
    }
    
    /**
     * 获取任意时间的任意个月的时间 
     */
    public static final String getTopTimes(String timeStr,int month,String sign ){
         String resTime = timeStr;
         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
             try {
                 Date date = sdf.parse(resTime);
                 Calendar calendar = Calendar.getInstance();
                 calendar.setTime(date);
                 if ("+".equals(sign)) {
                     calendar.add(Calendar.MONTH, +month);
                 } else {
                     calendar.add(Calendar.MONTH, -month);
                 }
                
                 date = calendar.getTime();
                 resTime = sdf.format(date);
            } catch (ParseException e) {
                 e.printStackTrace();
            }
         return resTime; 
    }
    /**
     * 获取系统时间
     * @return String
     */
    public static final String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date());
        return sdf.format(cd.getTime());
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
     * 获取系统时间
     * */
    public static final String getSystemDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        String current_date = sdf.format(date);
        return current_date;
    }


    /**
     * 获取系统前一天日期 
     * 
     * @return String
     */
    public static final String getYestedayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        String yestedayDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

        return yestedayDate;
    }

    /**
     * 获取系统前一天日期 没有“-”
     *
     * @return String
     */
    public static final String getYestedayDateNomat() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateStr = dateFormat.format(date);

        return dateStr;
    }

    private static GregorianCalendar calendar = new GregorianCalendar();


    public static final int getYear() {
        return calendar.get(Calendar.YEAR);
    }


    public static final int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }


    public static final int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    public static final int getCurrentDay() {
        return calendar.get(Calendar.DATE);
    }


    public static final int getHours() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    public static final int getMinutes() {
        return calendar.get(Calendar.MINUTE);
    }


    public static final int getSeconds() {
        return calendar.get(Calendar.SECOND);
    }


    public static final int getMilliSeconds() {
        return calendar.get(Calendar.MILLISECOND);
    }


    public static final String getMorningValue() {
        if (getHours() >= 1 && getHours() <= 12) {
            return "上午好";
        } else if (getHours() >= 13 && getHours() <= 18) {
            return "下午好";
        } else {
            return "晚上好";
        }
    }


    /**
     * 判断date1是否在date2之后
     */
    public static boolean isAfterDate(String date1, String date2) throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = format.parse(date1);
        Date d2 = format.parse(date2);
        if (d1.after(d2)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 计算时间间隔多少秒
     * @throws Exception 
     * */
    public static long intervalSecond(String oldDate, String newDate) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date doldDate = null;
        Date dnewDate = null;
        try {
            doldDate = format.parse(oldDate);
            dnewDate = format.parse(newDate);
        } catch ( ParseException e ) {
            e.printStackTrace();
        }


        return (dnewDate.getTime() - doldDate.getTime()) / 1000;

    }


    /**
     * 判断date1是否在date2之前
     */
    public static boolean isBeforeDate(String date1, String date2) throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = format.parse(date1);
        Date d2 = format.parse(date2);
        if (d1.before(d2)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断date1是否与date2相等
     */
    public static boolean isEqualsDate(String date1, String date2) throws Exception {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = format.parse(date1);
        Date d2 = format.parse(date2);
        if (d1.equals(d2)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取日期区间
     */
    public static String[] getRangeDate(String startDateStr, String endDateStr) throws Exception {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate = myFormatter.parse(startDateStr);
        Date date1 = myFormatter.parse(startDateStr);
        Date date2 = myFormatter.parse(endDateStr);
        long day = (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000) + 1;
        String[] date = new String[(int)day];
        date[0] = startDateStr;
        long startDate = 0;
        for (int i = 1; i < (int)day; i++) {
            startDate = (tempDate.getTime() / 1000) + 60 * 60 * 24;
            tempDate.setTime(startDate * 1000);
            date[i] = myFormatter.format(tempDate);
            if (tempDate.getTime() == date2.getTime()) {
                break;
            }
        }
        return date;
    }


    // 字符型转成sql.Date
    public static java.sql.Date string_to_sqlDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dDate = null;
        try {
            dDate = sdf.parse(date.trim());
        } catch ( ParseException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        java.sql.Date sDate = new java.sql.Date(dDate.getYear(), dDate.getMonth(), dDate.getDate());
        return sDate;
    }


    /**
     * 获取从fromDate到toDate的日期列表

     * 
     * 
     * 
     * @param fromDate
     * @param toDate
     * @return
     */
    public static List getDateDiff(String fromDate, String toDate) throws Exception {
        int yearFirst = Integer.parseInt(fromDate.split("-")[0]);
        int yearSecond = Integer.parseInt(toDate.split("-")[0]);
        int monthFirst = Integer.parseInt(fromDate.split("-")[1]);
        int monthSecond = Integer.parseInt(toDate.split("-")[1]);
        int monthsub = (yearSecond - yearFirst) * 12 + (monthSecond - monthFirst);
        List list = new ArrayList();
        while (yearFirst < yearSecond) {

            if (monthFirst > 9) {
                list.add(yearFirst + "" + monthFirst);
            } else {
                list.add(yearFirst + "0" + monthFirst);
            }
            if (monthFirst == 12) {
                yearFirst++;
                monthFirst = 1;
            } else {
                monthFirst++;
            }
        }
        if (yearFirst == yearSecond) {

            while (monthFirst <= monthSecond) {
                if (monthFirst > 9) {
                    list.add(yearFirst + "" + monthFirst);
                } else {
                    list.add(yearFirst + "0" + monthFirst);
                }

                monthFirst++;

            }
        }
        return list;
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
            if (value == null || "".equals(value) || "null".equals(value)) {
                return null;
            }
            if (value.indexOf(".") != -1) {
                value = value.substring(0, 19);
                format = "yyyy-MM-dd HH:mm:ss";
            } else {
                //正对试图的日期20100101
                if (value.length() == 8) {
                    value = value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6, 8);
                }
            }
            if (value.indexOf(".") == -1 && value.length() > 0) {
                if (value.length() <= 10) {
                    format = "yyyy-MM-dd";
                } else {
                    format = "yyyy-MM-dd HH:mm:ss";
                }
            }
            return new java.sql.Timestamp(DateUtils.parseDate(value, new String[] {format}).getTime());
        } catch ( Exception e ) {
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
     *  计算两个日期（月份）之间的月数，并且返回所有的日期
     *  
     *  @param startDate 开始日期（yyyy-MM）
     *  @param endDate 开始日期（yyyy-MM）
     *  @param type 格式形式（1：yyyy-MM；2：yyyy-M；3：yyyyMM；4：3：yyyyM）
     *  
     *  @return
     * */
    public static String[] getAllDateBetweenTwoDate(String startDate, String endDate, String type) {

        String[] allDate = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");


            Date sDate = sdf.parse(startDate);
            Date eDate = sdf.parse(endDate);

            int difference = 0; //相差月份
            int sYear = sDate.getYear(); //起始日期年
            int eYear = eDate.getYear();//结束日期年
            int sMonth = sDate.getMonth() + 1;//起始日期月份
            int eMonth = eDate.getMonth() + 1;//结束日期月份

            if (sDate.getTime() <= eDate.getTime()) {

                difference = (eYear - sYear) * 12;
                difference = difference > 0 ? (difference - (12 - eMonth)) + 1 : difference - (12 - eMonth);
                difference = difference + (12 - sMonth);

                if (sYear < eYear) {
                    difference = difference - 1;
                }

                allDate = new String[difference + 1];

                int recursiveNumber = 11;

                for (int i = 0, num = allDate.length; i < num; i++) {
                    int year = Integer.parseInt(startDate.split("-")[0]);
                    int month = Integer.parseInt(startDate.split("-")[1]) + i;

                    if (month > 12) {
                        int startMonth = Integer.parseInt(startDate.split("-")[1]);
                        month = (startMonth + 12) - (startMonth + recursiveNumber);
                        year++;
                        recursiveNumber--;
                    }
                    if ("1".equals(type)) {
                        if (month < 10) {
                            allDate[i] = year + "-" + "0" + month;
                        } else {
                            allDate[i] = year + "-" + month;
                        }

                    } else if ("2".equals(type)) {
                        allDate[i] = year + "" + month;
                    } else if ("3".equals(type)) {
                        if (month < 10) {
                            allDate[i] = year + "0" + month;
                        } else {
                            allDate[i] = year + "" + month;
                        }
                    } else {
                        allDate[i] = year + "" + month;
                    }

                }

            }

        } catch ( Exception ex ) {
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
     * 获取指定的日期（按照月）
     * 
     * @param sign
     *            操作符（+/-）
     * @param year
     *            年
     * @param type
     *            时间格式化类型
     */
    public static String getPointTimeOfYear(String sign, int year, String type) {
        Calendar curr = Calendar.getInstance();
        if ("+".equals(sign)) {
            curr.set(Calendar.YEAR, (curr.get(Calendar.YEAR)) + year);
        } else {
            curr.set(Calendar.YEAR, (curr.get(Calendar.YEAR)) - year);
        }

        Date date = curr.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        String dateTime = sdf.format(date);
        return dateTime;
    }


    /**
     * @throws ParseException 
     */
    public static String getDateAfterYearAndMonth(String DEAL_DATE, String year, String month) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = dateFormat.parse(DEAL_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (StringUtil.isNotEmpty(year)) {
            calendar.add(Calendar.YEAR, Integer.parseInt(year));
        }
        if (StringUtil.isNotEmpty(month)) {
            calendar.add(Calendar.MONTH, Integer.parseInt(month));
        }
        Date changAfterDate = calendar.getTime();
        String time = dateFormat.format(changAfterDate);
        return time;
    }
    
    
    /**
     * 获取一个日期后一定天数的日期
     * @param dateStr
     * @param num
     * @return
     * @throws ParseException
     */
    public static String getNextDay(String dateStr,int num){
        if(Util.isNull( dateStr )){
            return "";
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(sdf.parse(dateStr));
            }
            catch ( ParseException e ) {
                e.printStackTrace();
            }
            cal.add(Calendar.DATE, num);
            String result = (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
            return result;  
        }
    }
    
    
    /**
     * 获取一个时间后一定天数的时间
     * @param dateStr
     * @param num
     * @return
     * @throws ParseException
     */
    public static String getNextDayss(String dateStr,int num){
        if(Util.isNull( dateStr )){
            return "";
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            try {
                cal.setTime(sdf.parse(dateStr));
            }
            catch ( ParseException e ) {
                e.printStackTrace();
            }
            cal.add(Calendar.DATE, num);
            String result = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(cal.getTime());
            return result;  
        }
    }
    /**
     * 获取年份集合
     * @param
     * @param falg  false是之前 true是之后
     * @return
     */
    public static List<String> getYearArray(int num,boolean falg){
        Calendar cl = Calendar.getInstance(); 
        cl.setTime(new Date()); 
        int year = cl.get(Calendar.YEAR); 
        List<String> list = new LinkedList<String>();
        for(int i = 0; i <=num;i++){
            if(falg==true){
                list.add( String.valueOf( year+i ) );
            }else{
                list.add( String.valueOf( year-i ) ); 
            }
        }
        return list;
    }

    /**
     * 获取过去或者未来 任意天内的日期数组
     * @param intervals      intervals天内
     * @return              日期数组
     * 大于0 是当前时间之后， 小于0 是当前时间之前。
     */
    public static ArrayList<String> getPastDateArray(int intervals ,String formateStr) {
        ArrayList<String> pastDaysList = new ArrayList();
        if(intervals<0){
            intervals = -intervals;
            for (int i = 0; i <intervals; i++) {
                pastDaysList.add(getPastDate(-i,formateStr));
            }
        }else {
            for (int i = 0; i <intervals; i++) {
                pastDaysList.add(getPastDate(i,formateStr));
            }
        }

        return pastDaysList;
    }
    /**
     * 获取 当前日期 指定日期（当前日期前后 加减） 的 未来或过去任意天内的日期数组
     * @param intervals      intervals天内
     * @return              日期数组
     * 大于0 是当前时间之后， 小于0 是当前时间之前。
     * 进行格式转换，并截取月日，不要年
     */
    public static ArrayList<String> getPastDateMateArray(int intervals ,int thisDate ,String formateStr) {
        ArrayList<String> pastDaysList = new ArrayList();
        if(intervals<0){
            intervals = -intervals;
            for (int i = 0; i <intervals; i++) {
                pastDaysList.add(getPastDate(-i+thisDate,formateStr));
            }
        }else {
            for (int i = 0; i <intervals; i++) {
                pastDaysList.add(getPastDate(-i+thisDate,formateStr));
            }
        }

        return pastDaysList;
    }
    /**
     * 获取 当前日期 指定日期（当前日期前后 加减） 的 未来或过去任意天内的日期数组
     * @param intervals      intervals天内
     * @return              日期数组
     * 大于0 是当前时间之后， 小于0 是当前时间之前。
     * 进行格式转换，并截取月日，不要年
     */
    public static ArrayList<Map> getPastDateMateArray(int intervals , int thisDate , String formateKey,String formateVal) {
        ArrayList<Map> pastDaysList = new ArrayList();
        if(intervals<0){
            intervals = -intervals;
            for (int i = 0; i <intervals; i++) {
                Map tempMap = new HashMap();
                tempMap.put("dateKey",getPastDate(i-(intervals-1)+thisDate,formateKey));
                tempMap.put("dateVal",getPastDate(i-(intervals-1)+thisDate,formateVal));
                pastDaysList.add(tempMap);
            }
        }else {
            for (int i = 0; i <intervals; i++) {
                Map tempMap = new HashMap();
                tempMap.put("dateKey",getPastDate(i+thisDate,formateKey));
                tempMap.put("dateVal",getPastDate(i+thisDate,formateVal));
                pastDaysList.add(tempMap);
            }
        }

        return pastDaysList;
    }
    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past,String formateStr) {
        if(null==formateStr||"".equals(formateStr)){
            formateStr = "yyyy-MM-dd";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(formateStr);
        String result = format.format(today);
        return result;
    }

    /**
     * 获取过去 第几个月
     *
     * @param past
     * @return
     */
    public static String getPastMonth(int past,String formateStr) {

        if(null==formateStr||"".equals(formateStr)){
            formateStr = "yyyy-MM";
        }
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, past);
        Date wantday = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(formateStr);
        String result = format.format(wantday);
        return result;
    }

    /**
     * 获取 当前日期 指定日期（当前日期前后 加减） 的 未来或过去任意天内的月 数组
     * @param intervals      intervals天内
     * @return              月数组
     * 大于0 是当前时间之后， 小于0 是当前时间之前。
     * 进行格式转换，并截取年月，不要日
     */
    public static ArrayList<Map> getPastMonthMateArray(int intervals , int thisDate , String formateKey,String formateVal) {
        ArrayList<Map> pastDaysList = new ArrayList();
        if(intervals<0){
            intervals = -intervals;
            for (int i = 0; i <intervals; i++) {
                Map tempMap = new HashMap();
                tempMap.put("dateKey",getPastMonth(i-(intervals-1)+thisDate,formateKey));
                tempMap.put("dateVal",getPastMonth(i-(intervals-1)+thisDate,formateVal));
                pastDaysList.add(tempMap);
            }
        }else {
            for (int i = 0; i <intervals; i++) {
                Map tempMap = new HashMap();
                tempMap.put("dateKey",getPastMonth(i+thisDate,formateKey));
                tempMap.put("dateVal",getPastMonth(i+thisDate,formateVal));
                pastDaysList.add(tempMap);
            }
        }

        return pastDaysList;
    }

    /**
     * 获取 当前日期 指定日期（当前日期前后 加减） 的 未来或过去任意天内的日期数组
     * @param intervals      intervals天内
     * @return              日期数组
     * 大于0 是当前时间之后， 小于0 是当前时间之前。
     * 进行格式转换，并截取月日，不要年
     */
    public static ArrayList<String> getPastMonthMateArray(int intervals ,int thisDate ,String formateStr) {
        ArrayList<String> pastDaysList = new ArrayList();
        if(intervals<0){
            intervals = -intervals;
            for (int i = 0; i <intervals; i++) {
                pastDaysList.add(getPastMonth(-i+thisDate,formateStr));
            }
        }else {
            for (int i = 0; i <intervals; i++) {
                pastDaysList.add(getPastMonth(-i+thisDate,formateStr));
            }
        }

        return pastDaysList;
    }
    
    /**
     * 获取某年的几个月
     */
    public static List<String> getYearEachMon(String year,int mons){
        List monList = new ArrayList();
        for(int i = 1; i<=mons; i ++){
            Map map = new HashMap();
            if(i<10){
                map.put( "eachMon", year+"0"+i );
                monList.add( map );
            }else{
                map.put( "eachMon", year +""+i );
                monList.add( map );
            }
        }
        return monList;
    }
    
    
}
