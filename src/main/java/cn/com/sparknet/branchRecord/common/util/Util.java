package cn.com.sparknet.branchRecord.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import oracle.sql.CLOB;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

import sun.misc.BASE64Encoder;

public class Util {

	/**
	 * 空值转换成空避免空值异常的发生,和填写数据库的时候使用
	 * 
	 * @param str
	 * @return String
	 */
	public static final String nullToEmpty(String str) {
		if (null == str || "".equals(str) || "null".equals(str)) {
			return "";
		} else {
			return str.toString().trim();
		}
	}
	
	/**
	 * 空值转换成空避免空值异常的发生,和填写数据库的时候使用

	 * 
	 * @param str
	 * @return String
	 */
	public static final String nullToEmpty(Object obj) {
		if(null==obj){
			return "";
		}else{
			return obj.toString();
		}
	}

	/**
	 * 空值转换成空避免空值异常的发生,和填写数据库的时候使用
	 * 
	 * @param str
	 * @return String
	 */
	public static final String nullToZero(String str) {
		if (null == str || "".equals(str) || "null".equals(str)) {
			return "0";
		} else {
			return str.toString().trim();
		}
	}

	/**
	 * 空值转换成格避免插入非空字段时报错
	 * 
	 * @param str
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
	 * 判断字符串是否为空串(null或只有空格).是则返回true，否则返回false.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return str == null || str.trim().equals("");
	}

	/**
	 * 如果字符串为空,转化为指定内容.
	 * 
	 * @param str
	 *            给定字符串
	 * 
	 * 
	 * 
	 * @param replaceContent
	 *            替换内容
	 * @return 替换内容
	 */
	public static String NVLToStr(Object obj, String replaceContent) {
		return obj == null ? replaceContent : obj + "";
	}

	/**
	 * 如果字符串为空,返回空. 把时间2012-03转为201203
	 * 
	 * @param str
	 *            给定字符串
	 * 
	 * 
	 */
	public static String TimeToStr(Object obj, String replaceContent) {
		return obj == null ? replaceContent : (obj.toString()).replace("-", "");
	}

	/**
	 * 如果字符串为空,转化为指定内容.
	 * 
	 * @param str
	 *            给定字符串
	 * 
	 * 
	 * 
	 * @param replaceContent
	 *            替换内容
	 * @return 替换内容
	 */
	public static int NVLToInt(Object obj, int replaceContent) {
		String str = "" + obj;
		if (str != null)
			str = str.trim();
		return str == null || str.length() == 0 ? replaceContent : Integer
				.parseInt(str);
	}

	/**
	 * 如果字符串为空,转化为指定内容.
	 * 
	 * @param obj
	 *            给定字符串
	 * 
	 * 
	 * 
	 * @param replaceContent
	 *            替换内容
	 * @return 替换内容
	 */
	public static long NVLToLong(Object obj, long replaceContent) {
		String str = (String) obj;
		if (str != null)
			str = str.trim();
		return str == null || str.length() == 0 ? replaceContent : Long
				.parseLong(str);
	}

	/**
	 * 如果字符串为空,转化为指定内容.
	 * 
	 * @param obj
	 *            给定字符串
	 * 
	 * 
	 * 
	 * @param replaceContent
	 *            替换内容
	 * @return 替换内容
	 */
	public static float NVLToFloat(Object obj, float replaceContent) {
		String str = (String) obj;
		if (str != null)
			str = str.trim();
		return str == null || str.length() == 0 ? replaceContent : Float
				.parseFloat(str);
	}

	/**
	 * 如果字符串为空,转化为指定内容.
	 * 
	 * @param obj
	 *            给定字符串
	 * 
	 * 
	 * 
	 * @param replaceContent
	 *            替换内容
	 * @return 替换内容
	 */
	public static double NVLToDouble(Object obj, double replaceContent) {
		String str = (String) obj;
		if (str != null)
			str = str.trim();
		return str == null || str.length() == 0 ? replaceContent : Double
				.parseDouble(str);
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

	public static String getNowTime1() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String nowTime = sdf.format(now);
		return nowTime;
	}

	public static String getNowTime2() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String nowTime = sdf.format(now);
		return nowTime;
	}

	public static String getNowTime3() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowTime = sdf.format(now);
		return nowTime;
	}

	/**
	 * 获取指定的日期（按照日）
	 * 
	 * @param sign
	 *            操作符（+/-）
	 * @param day
	 *            天数
	 */
	public static String getPointTime(String sign, int day) {
		Calendar curr = Calendar.getInstance();
		if ("+".equals(sign)) {
			curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH)
					+ day);
		} else {
			curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH)
					- day);
		}

		Date date = curr.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = sdf.format(date);
		return dateTime;
	}

	/**
	 * 当前日期往前或者往后推指定月分
	 * 
	 * @param sign
	 *            操作符（+/-）
	 * @param month
	 *            月数
	 */
	public static String getPointTimeOfMonth(String sign, int month) {
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
				dateTime = v_intYear + "-" + "0" + v_intMonth + "-" + "0" + v_intDAY;
			}else{
				dateTime = v_intYear + "-" + "0" + v_intMonth + "-" + v_intDAY;
			}
			
		} else {
			if(v_intDAY<10){
				dateTime = v_intYear + "-" + v_intMonth + "-" + "0" + v_intDAY;
			}else{
				dateTime = v_intYear + "-" + v_intMonth + "-" + v_intDAY;
			}
			
		}

		return dateTime;
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
				// 正对试图的日期20100101
				if (value.length() == 8) {
					value = value.substring(0, 4) + "-" + value.substring(4, 6)
							+ "-" + value.substring(6, 8);
				}
			}
			if (value.indexOf(".") == -1 && value.length() > 0) {
				if (value.length() <= 10) {
					format = "yyyy-MM-dd";
				} else {
					format = "yyyy-MM-dd HH:mm:ss";
				}
			}
			return new java.sql.Timestamp(DateUtils.parseDate(value,
					new String[] { format }).getTime());
		} catch (Exception e) {
			e.printStackTrace();
			// Log.error(this.getClass(), "StringToTimestamp", e.toString(), e);
			return null;
		}
	}

	public static int getYear() {
		GregorianCalendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		return year;
	}

	public static int getMonth() {
		GregorianCalendar calendar = new GregorianCalendar();
		int month = calendar.get(Calendar.MONTH) + 1;
		return month;
	}

	public static int getDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		int date = calendar.get(Calendar.DATE);
		return date;
	}

	public static int getDay() {
		GregorianCalendar calendar = new GregorianCalendar();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	public static int getHours() {
		GregorianCalendar calendar = new GregorianCalendar();
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		return hours;
	}

	public static int getMinutes() {
		GregorianCalendar calendar = new GregorianCalendar();
		int min = calendar.get(Calendar.MINUTE);
		return min;
	}

	public static int getSeconds() {
		GregorianCalendar calendar = new GregorianCalendar();
		int sec = calendar.get(Calendar.SECOND);
		return sec;
	}

	public static int getMilliSeconds() {
		GregorianCalendar calendar = new GregorianCalendar();
		int millisec = calendar.get(Calendar.MILLISECOND);
		return millisec;
	}

	/**
	 * Map中的null处理
	 * 
	 * @param Map
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public static final Map nullToEmptyForMap(Map map) {
		Map convertedMap = null;
		Set mapset = map.entrySet();
		String EMPTYSTR = "";
		Entry entry = null;
		if (map != null) {
			convertedMap = new HashMap();
			Iterator it = null;
			for (it = mapset.iterator(); it.hasNext();) {
				entry = (Entry) it.next();
				if (entry.getValue() == null) {
					convertedMap.put(entry.getKey(), EMPTYSTR);
				} else {
					convertedMap.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return convertedMap;
	}

	/**
	 * 取seq
	 * 
	 * @param jdbcTemplate
	 * @return
	 */
	public static long getSeq(JdbcTemplate jdbcTemplate) {
		long id = 0;
		String sql = "SELECT BLSP_SEQ.nextval FROM DUAL";
		id = jdbcTemplate.queryForLong(sql);
		return id;
	}

	/**
	 * @function <font color=red>获取CLOB值</font>
	 * @author <font color=red>wangyl</font>
	 * @time <font color=red>2012-03-06 21:29:21</font>
	 * @param <font
	 *            color=red>jdbcTemplate</font>
	 * @param <font
	 *            color=red>sql 要获取CLOB值的SQL</font>
	 * @param <font
	 *            color=red>key 要获取CLOB对应的SQL中的字段项</font>
	 * @return
	 */
	public static Map selClob(JdbcTemplate jdbcTemplate, String sql,
			final String key) {
		final Map result = new HashMap();// 存放对像的hashmap
		jdbcTemplate.query(sql, new RowMapper() {
			@SuppressWarnings("unchecked")
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				String str = "";
				CLOB clob = null;
				try {
					clob = (oracle.sql.CLOB) rs.getClob(key);// content字段属性为clob,转成clob对象
					str = clobToString(clob);// 将clob对象转为String
					result.put(key, str);
				} catch (Exception e) {
				}
				return clob;
			}

			// 此方法将clob转为String
			@SuppressWarnings("deprecation")
			public String clobToString(CLOB clob) throws SQLException,
					IOException {
				String reString = "";
				if (clob == null || clob.getCharacterOutputStream() == null)
					return "";
				Reader is = clob.getCharacterStream();// 得到流

				BufferedReader br = new BufferedReader(is);
				String s = br.readLine();
				StringBuffer sb = new StringBuffer();
				while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
					sb.append(s);
					s = br.readLine();
				}
				reString = sb.toString();
				return reString;
			}
		});
		return result;
	}
	
	
	
	/**
     * 查询待clob 和blob字段
     * @param sql
     * @param clobFields
     * @param blobFields
     * @return 
     * lixp update 2015831 将sql执行方式改为预编译的形式(未验证)
     */
    public static List<Map> findBySql(String sql, String[] clobFields,String[] blobFields,Object[] obj,JdbcTemplate jdbcTemplate) {
        // clob
        final String[] upperClobFields = new String[clobFields.length];
        for (int i = 0; i < clobFields.length; i++) {
            upperClobFields[i] = clobFields[i].toString().toUpperCase();
        }
        // blob
        final String[] upperBlobFields = new String[blobFields.length];
        for (int i = 0; i < blobFields.length; i++) {
            upperBlobFields[i] = blobFields[i].toString().toUpperCase();
        }
        final List<Map> result = new ArrayList<Map>();
        final LobHandler lobHandler = new DefaultLobHandler();
        List list = jdbcTemplate.query(sql,obj, new RowMapper() {
            Map map = null;
            int colCount = 0;
            String colName = "";
            ResultSetMetaData metaData = null;
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                map = new LinkedHashMap();
                metaData = rs.getMetaData();
                colCount = metaData.getColumnCount();
                for (int c = 1; c <= colCount; c++) {
                    colName = metaData.getColumnName(c);
                    if (ArrayUtils.contains(upperClobFields, colName)) {// clob
                        map.put(colName, lobHandler.getClobAsString(rs, c));
                    } else if (ArrayUtils.contains(upperBlobFields, colName)) {// blob
                        map.put(colName,lobHandler.getBlobAsBinaryStream(rs, c));
                    } else {// other
                        map.put(colName, rs.getString(colName));
                    }
                }
                result.add(map);
                return result;
            }
        });
        return result;
    }
	
	
/**
 *@anthor lixp
 *@time 2015831
 *@function 获取clob,传一个Object数组，将sql执行的方式改为预编译的形式
 */
	public static Map queryClob(JdbcTemplate jdbcTemplate, String sql,
			final String key,Object[] obj) {
		final Map result = new HashMap();// 存放对像的hashmap
		jdbcTemplate.query(sql,obj, new RowMapper() {
			@SuppressWarnings("unchecked")
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				String str = "";
				CLOB clob = null;
				try {
					clob = (oracle.sql.CLOB) rs.getClob(key);// content字段属性为clob,转成clob对象
					str = clobToString(clob);// 将clob对象转为String
					result.put(key, str);
				} catch (Exception e) {
				}
				return clob;
			}

			// 此方法将clob转为String
			@SuppressWarnings("deprecation")
			public String clobToString(CLOB clob) throws SQLException,
					IOException {
				String reString = "";
				if (clob == null || clob.getCharacterOutputStream() == null)
					return "";
				Reader is = clob.getCharacterStream();// 得到流


				BufferedReader br = new BufferedReader(is);
				String s = br.readLine();
				StringBuffer sb = new StringBuffer();
				while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
					sb.append(s);
					s = br.readLine();
				}
				reString = sb.toString();
				return reString;
			}
		});
		return result;
	}
	/**
	 * 取blob字段
	 * 
	 * @param ds
	 * @param sql
	 * @param key
	 * @return
	 */
	public static Blob getBlob(DataSource ds, String sql, String key) {
		Connection conn = null;
		Blob blob = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			Statement statement = conn.createStatement();

			rs = statement.executeQuery(sql);
			if (rs.next()) {
				blob = (java.sql.Blob) rs.getBlob(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成 catch 块


				e.printStackTrace();
			}
		}
		return blob;

	}

	/**
	 * BLOB转为byte[]
	 * 
	 * @param blob
	 * @return
	 * @throws Exception
	 */
	public static byte[] getBlob(Blob blob) throws Exception {
		if (blob == null) {
			return new byte[0];
		}

		InputStream input = null;
		byte[] bytes = null;
		try {
			input = blob.getBinaryStream();
			int size = 1024;
			byte[] buf = new byte[size];
			int readBytes = 0;
			int length = (int) blob.length();
			bytes = new byte[length];
			int i = 0;
			while (i < length) {
				if (i + size > length) {
					readBytes = (int) (length - i);
				} else {
					readBytes = size;
				}

				input.read(buf, 0, readBytes);
				System.arraycopy(buf, 0, bytes, i, readBytes);
				i += size;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			input.close();
		}
		return bytes;
	}

	/**
	 * @function 插入blob字段 此方法不好写成公共的方法，在此作参考使用
	 * @param jdbcTemplate
	 * @param b
	 */
	private void insBlob(JdbcTemplate jdbcTemplate, final byte b[]) {
		final LobHandler lobHandler = new DefaultLobHandler();
		jdbcTemplate.execute("INSERT INTO TABLE_NAME(BLOB) VALUES (?)",
				new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
					@Override
					protected void setValues(PreparedStatement ps, LobCreator lc)
							throws SQLException, DataAccessException {
						// TODO Auto-generated method stub
						lc.setBlobAsBytes(ps, 2, b);
					}

				});
	}

	/**
	 * @function 插入clob字段 此方法不好写成公共的方法，在此作参考使用
	 * @param jdbcTemplate
	 * @param is
	 */
	private void insClob(JdbcTemplate jdbcTemplate, final InputStream is) {
		final LobHandler lobHandler = new DefaultLobHandler();
		jdbcTemplate.execute("INSERT INTO TABLE_NAME(CLOB) VALUES (?)",
				new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
					@Override
					protected void setValues(PreparedStatement ps, LobCreator lc)
							throws SQLException, DataAccessException {
						// TODO Auto-generated method stub
						try {
							lc.setClobAsAsciiStream(ps, 1, is, is.available());// 存储图片
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
	}

	/**
	 * 将byte数组转成base64编码
	 * 
	 * @param data
	 * @return
	 */
	public static String byteToBase64(byte[] data) {
		BASE64Encoder encoder = new BASE64Encoder();
		String encodeString = encoder.encode(data);
		return encodeString;
	}

	/**
	 * 将base64转为byte数组
	 * 
	 * @param base64
	 * @return
	 */
	public static byte[] base64Tobyte(String base64) {
		try {
			byte[] data = new sun.misc.BASE64Decoder().decodeBuffer(base64);

			return data;
		} catch (IOException e) {
			// TODO 自动生成 catch 块

			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 取Sequences名称
	 * 
	 * @param jdbcTemplate
	 * @param SequencesName
	 * @return
	 */
	public static String getNextVal(JdbcTemplate jdbcTemplate,
			String SequencesName) {
		String sql = "select " + SequencesName + ".nextval value from dual";
		List list = jdbcTemplate.queryForList(sql);
		if (list.size() == 0) {
			throw new RuntimeException("无效的Sequences的名称");
		} else {
			return ((Map) list.get(0)).get("value").toString();
		}

	}

	// 替换特殊字符
	public static String connvertJobDesc(String jobDesc) {
		if (jobDesc == null) {
			return "";
		} else {
			return jobDesc.replaceAll("_\\|：\\|_", "：").replaceAll("_\\|\\|_",
					" ");
		}
	}

	/**
	 * 取数据库当前时间
	 * 
	 * @param jdbcTemplate
	 * @param dateType
	 *            "yyyy-mm-dd" "yyyy-mm-dd hh24:mi:ss"
	 * @return
	 */
	public static String getDbSysdate(JdbcTemplate jdbcTemplate, String dateType) {
		String sql = "select to_char(sysdate,'" + dateType
				+ "') value from dual";
		return jdbcTemplate.queryForMap(sql).get("value") + "";
	}

	/**
	 * 取数据库当前时间
	 * 
	 * @param jdbcTemplate
	 * @param dateType
	 *            "yyyy-mm-dd" "yyyy-mm-dd hh24:mi:ss"
	 * @return
	 */
	public static Timestamp getDbSysdateTime(JdbcTemplate jdbcTemplate) {
		String sql = "select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') value from dual";
		return Timestamp.valueOf(jdbcTemplate.queryForMap(sql).get("value")
				+ "");
	}


	public static String jiaMi(String s) {
		String sp = ",";
		String result = "";

		if (s == null || "".equals(s) || s == "null") {
			return "";
		}
		byte[] b = s.getBytes();
		for (int i = 0; i < b.length; i++) {
			result += b[i] + sp;
		}
		return result.substring(0, result.length() - sp.length());
	}

	public static String jieMi(String s) {
		String sp = ",";
		if (s == null || "".equals(s) || s == "null") {
			return "";
		}
		String[] s1 = s.split(sp);
		byte[] b = new byte[s1.length];
		for (int i = 0; i < s1.length; i++) {
			b[i] = Byte.parseByte(s1[i]);
		}
		return new String(b);
	}

	// 不足5位补位
	public static String getFiveNumer(String p_num) {
		// 如果大于5位 就不处理
		if (p_num.length() > 5) {
			return p_num;
		}

		int len = p_num.length();

		for (int i = len; i < 5; i++) {
			p_num = "0" + p_num;
		}
		return p_num;
	}

	// 不足4位补位
	public static String get4Numer(String p_num) {
		// 如果大于4位 就不处理
		if (p_num.length() > 4) {
			return p_num;
		}

		int len = p_num.length();

		for (int i = len; i < 4; i++) {
			p_num = "0" + p_num;
		}
		return p_num;
	}
	
	//sql时间到时分秒yyyy-MM-dd HH:mm:ss
	   public static java.sql.Date StringToDate(String date) {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        java.util.Date dDate = null;
	        try {
	            dDate = sdf.parse(date.trim());
	        } catch (ParseException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        java.sql.Date sDate = new java.sql.Date(dDate.getYear(), dDate
	                .getMonth(), dDate.getDate());
	        return sDate;
	    }

	// 字符型转成sql.Date
	public static java.sql.Date string_to_sqlDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dDate = null;
		try {
			dDate = sdf.parse(date.trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sDate = new java.sql.Date(dDate.getYear(), dDate
				.getMonth(), dDate.getDate());
		return sDate;
	}

	// 删除文件
	public static Boolean deleteFile(String filePath) {
		try {
			File f = new File(filePath);
			if (f.isFile()) {
				if (f.delete()) {
					return true;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// 字节流读取文件
	public static byte[] parseFile(String fileName) {
		byte a[] = null;
		FileInputStream fin = null;
		File f1 = new File(fileName);
		try {
			fin = new FileInputStream(f1);
			int flength = (int) f1.length();// **读入文件的字节长度
			a = new byte[flength];
			int i = 0;
			int itotal = 0;
			// 将文件读入字节数组
			for (; itotal < flength; itotal = i + itotal) {
				i = fin.read(a, itotal, flength - itotal);
			}
			fin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return a;
	}

	// 模糊查询map中的key
	public static List<String> likeString(String key, Map<String, String> map) {
		List<String> list = new ArrayList<String>();
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it
					.next();
			if (entry.getKey().indexOf(key) != -1) {
				list.add(entry.getValue());
			}
		}
		return list;
	}

	// 封装对象
	public static List copyObject(List<Map> leadList, Object obj) {
		List list = new ArrayList();
		for (Map map : leadList) {
			try {
				BeanUtils.copyProperties(obj, map);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			list.add(obj);
		}
		return list;
	}

	public static boolean isAfterDate(String date1, Date date2) throws Exception{
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date d1 = format.parse(date1);
		if (d1.after(date2)) {
			return true;
		}else{
			return false;
		}
	}
	
	public static Date typeToDate(String date1) throws Exception{
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date d1 = format.parse(date1);
		return d1;
	}
	
	
	/**
     * 危险字符过滤
     * @param str
     * @return
     */
       public static String strFiltrate(String str) {
            if(str==null || str.equals("")){
                return str;
            }
            str = str.replaceAll("&", "&amp;");
            str = str.replaceAll("<", "&#x3C;");
            str = str.replaceAll(">", "&#x3E;");
            str = str.replaceAll("\"", "");
            str = str.replaceAll("'", "");
//          str = str.replaceAll("%", "");
            str = str.replaceAll("eval", "");
            str = str.replaceAll("expression", "");
            str = str.replaceAll("unescape", "");
//            str = str.replaceAll(",", "，");
            str = str.replaceAll(":", "：");
            
            str = str.replaceAll(";", "");
//            str = str.replaceAll("\\(", "");
//            str = str.replaceAll("\\)", "");
            str = str.replaceAll("&", "");
            str = str.replaceAll("\\+", "");
            str = str.replaceAll("=", " ");
            str = str.replaceAll("having", " ");
            str = str.replaceAll("group", " ");
//            str = str.replaceAll(".*([';]+|(--)+).*", " ");
            return str;
        }
       
       public static String getWondowPan(HttpServletRequest request){
           String Agent = request.getHeader("User-Agent"); 
           StringTokenizer st = new StringTokenizer(Agent,";"); 
           st.nextToken(); 
           String userbrowser = "";
           try{
                userbrowser = st.nextToken().trim().toUpperCase();   
           }catch(Exception e){
               userbrowser = request.getHeader( "USER-AGENT" ).toLowerCase();
               System.out.println("不是IE浏览器");
           }
            
           System.out.println("userbrowser="+userbrowser);
         //得到用户的浏览器名
//           String userbrowser = st.nexttoken();
//           //得到用户的操作系统名
//           String useros = st.nexttoken();
//           取得本机的信息也可以这样：
//           操作系统信息
//           String version =  System.getProperty("os.version");
//           System.out.println("version="+version);
//           String arch = System.getProperty("os.arch");
//           System.out.println("arch="+arch);
//           String num = request.getHeader( "USER-AGENT" ).toLowerCase();
//           System.out.println("num="+num);
////           getheader(string name)：获得http协议定义的传送文件头信息，
//           request. getMethod();//：获得客户端向服务器端传送数据的方法有get、post、put等类型
//           request. getRequestURI();///：获得发出请求字符串的客户端地址
//           request. getServletPath();//：获得客户端所请求的脚本文件的文件路径
//           request. getServerName();//：获得服务器的名字
//           request.getServerName();//：获得服务器的端口号
//           request.getRemoteAddr();//：获得客户端的ip地址
//           request.getRemoteAddr();//：获得客户端电脑的名字，若失败，则返回客户端电脑的ip地址
//           request.getProtocol();//：
//           request.getHeaderNames();//：返回所有request header的名字，结果集是一个enumeration（枚举）类的实例
           
           return userbrowser;
       }
       
       /**
        * 数组去重
        * @param array
        * @return
        */
       public static String[] array_unique(String[] array) {  
           List<String> list = new LinkedList<String>();  
           for(int i = 0; i < array.length; i++) {  
               if(!list.contains(array[i])) {  
                   list.add(array[i]);  
               }  
           }  
           return (String[])list.toArray(new String[list.size()]);  
       }  
       
       
}
