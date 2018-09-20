package cn.com.sparknet.branchRecord.common.basedao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;

import cn.com.sparknet.branchRecord.common.json.ListJson;
import cn.com.sparknet.branchRecord.common.util.PlatformUtil;


/**
 * 
 * 定义所有dao的基类
 * 
 * @author zhangxd
 * 
 */
public class BaseJdbcDao extends JdbcDaoSupport {

    private static Logger log = Logger.getLogger(BaseJdbcDao.class);

    @Resource
    public void setMyJdbcTemplate(@Qualifier("jdbcTemplate") JdbcTemplate jdbcTemplate) {
        super.setJdbcTemplate(jdbcTemplate);
    }
    
    /**
     * 获取jdbcTemplate对象
     * */
    public JdbcTemplate getNewJdbcTemplate(){
        return this.getJdbcTemplate();
    }

    /**
     * Int查询
     */
    public int queryForInt(String sql) {
        log.debug(sql);
        return this.getJdbcTemplate().queryForInt(sql);
    }
    
    /**
     * Int查询(预编译)
     * 
     * @author zhangxd
     */
    public int queryForInt(String sql,Object[] args) {
        log.debug(sql);
        return this.getJdbcTemplate().queryForInt(sql, args);
    }

    /**
     * Long查询
     */
    public Long queryForLong(String sql) {
        log.debug(sql);
        return this.getJdbcTemplate().queryForLong(sql);
    }
    
    /**
     * Long查询(预编译)
     * 
     * @author zhangxd
     */
    public Long queryForLong(String sql,Object[] args) {
        log.debug(sql);
        return this.getJdbcTemplate().queryForLong(sql, args);
    }

    /**
     * map查询
     */
    public Map<String, Object> queryForMap(String sql) {
        List<Map<String, Object>> list = queryForList(sql);
        Map<String, Object> resultMap = null;
        for (Map<String, Object> map : list) {
            resultMap = map;
            break;
        }
        return resultMap;
    }
    
    /**
     * map查询(预编译)
     */
    public Map<String, Object> queryForMap(String sql,Object[] args) {
        List<Map<String, Object>> list = queryForList(sql,args);
        Map<String, Object> resultMap = null;
        for (Map<String, Object> map : list) {
            resultMap = map;
            break;
        }
        return resultMap;
    }

    /**
     * List查询
     */
    public List<Map<String, Object>> queryForList(String sql) {
        log.debug(sql);
        List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
        return list;
    }
    
    /**
     * List查询(预编译)
     * 
     * @author zhangxd
     */
    public List<Map<String, Object>> queryForList(String sql,Object[] args) {
        log.debug(sql);
        List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql, args);
        return list;
    }

    /**
     * 添加、修改、删除
     */
    public int update(String sql) {
        log.debug(sql);
        return this.getJdbcTemplate().update(sql);
    }

    /**
     * 添加、修改、删除(预编译)
     */
    public int update(String sql, Object[] obj) {
        log.debug(sql);
        return this.getJdbcTemplate().update(sql, obj);
    }

    /**
     * 添加、修改、删除和DDL
     */
    public void execute(String sql) {
        log.debug(sql);
        this.getJdbcTemplate().execute(sql);
    }

    /**
     * 分页查询
     */
    public ListJson queryForPage(String sql, int start, int limit) {
        ListJson json = new ListJson();
        try {
            int countPage = 1;
            int recordCount = this.queryForInt("SELECT COUNT(*) FROM (" + sql + ")");
            if (recordCount > 0) {
                if (start != -1 && limit != -1) {
                    countPage = limit;
                }
            }
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT * FROM (SELECT TMP.*,ROWNUM RN FROM (").append(sql).append(") TMP) ");
            if (start != -1 && limit != -1) {
                int cpage = start / limit + 1;
                sb.append("WHERE RN >").append((cpage - 1) * countPage).append(" AND RN<= ").append(cpage * countPage);
            }
            // 记录数
            json.setTotal(recordCount);
            // 结果集
            json.setItems(this.queryForList(sb.toString()));
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return json;
    }
    
    /**
     * 分页查询
     */
    public ListJson queryForPage(String sql, int start, int limit,Object[] args) {
        ListJson json = new ListJson();
        try {
            int countPage = 1;
            int recordCount = this.queryForInt("SELECT COUNT(*) FROM (" + sql + ")",args);
            if (recordCount > 0) {
                if (start != -1 && limit != -1) {
                    countPage = limit;
                }
            }
            StringBuffer sb = new StringBuffer();
            sb.append("SELECT * FROM (SELECT TMP.*,ROWNUM RN FROM (").append(sql).append(") TMP) ");
            if (start != -1 && limit != -1) {
                int cpage = start / limit + 1;
                sb.append("WHERE RN >").append((cpage - 1) * countPage).append(" AND RN<= ").append(cpage * countPage);
            }
            // 记录数
            json.setTotal(recordCount);
            // 结果集
            json.setItems(this.queryForList(sb.toString(),args));
            
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return json;
    }

    /**
     * 保存Clob
     */
    public boolean updateForClob(String sql, final byte[] b) {
        log.debug(sql);
        boolean result = true;
        try {
            final ByteArrayInputStream bis = new ByteArrayInputStream(b);
            final InputStreamReader clobReader = new InputStreamReader(bis);
            final LobHandler lobHandler = new DefaultLobHandler();
            this.getJdbcTemplate().execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                protected void setValues(PreparedStatement ps, LobCreator lobCreator) {
                    try {
                        lobCreator.setClobAsCharacterStream(ps, 1, clobReader, (int) b.length);
                    } catch (SQLException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
            });
            if (null != clobReader) {
                clobReader.close();
            }
            if (null != bis) {
                bis.close();
            }
        } catch (Exception e) {
            result = false;
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

    /**
     * 保存Blob
     */
    public boolean updateForBlob(String sql, final File file, final InputStream inputStream) {
        log.debug(sql);
        boolean result = true;
        try {
            final LobHandler lobHandler = new DefaultLobHandler();
            this.getJdbcTemplate().execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                protected void setValues(PreparedStatement ps, LobCreator lobCreator) {
                    try {
                        lobCreator.setBlobAsBinaryStream(ps, 1, inputStream, (int) file.length());
                    } catch (SQLException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            result = false;
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }

    /**
     * 保存Blob
     */
    public boolean updateForBlob(String sql, final int length, final InputStream inputStream) {
        log.debug(sql);
        boolean result = true;
        try {
            final LobHandler lobHandler = new DefaultLobHandler();
            this.getJdbcTemplate().execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                protected void setValues(PreparedStatement ps, LobCreator lobCreator) {
                    try {
                        lobCreator.setBlobAsBinaryStream(ps, 1, inputStream, length);
                    } catch (SQLException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            result = false;
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }

    /**
    *
    * @param sql
    * @param blobFields
    * @return
    */
    @SuppressWarnings ( "unchecked" )
    public List<Map> queryBlobAsInputStreamList(String sql, String[] blobFields) {
            log.debug(sql);
            final String[] upperBlobFields=new String[blobFields.length];
            for(int i=0;i<blobFields.length;i++){
                upperBlobFields[i]=blobFields[i].toString().toUpperCase();
            }
            final List<Map> result=new ArrayList<Map>();
            final LobHandler lobHandler=new DefaultLobHandler();
            List list = this.getJdbcTemplate().query(sql,
                new RowMapper() {
                    Map map=null;
                    int colCount=0;
                    String colName="";
                    ResultSetMetaData metaData=null;
                    public Object mapRow(ResultSet rs, int i) throws SQLException {
                        map=new HashMap();
                        metaData = rs.getMetaData();
                        colCount=metaData.getColumnCount();
                        for (int c = 1; c <= colCount; c++) {
                            colName=metaData.getColumnName(c);
                            if(ArrayUtils.contains(upperBlobFields, colName)){
                                map.put(colName, lobHandler.getBlobAsBytes(rs,c));
                            }else{
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
     * 
     * @param sql
     * @param blobFields
     * @return
     */
    public InputStream queryBlobAsInputStream(String sql, String[] blobFields) {
        log.debug(sql);
        final String[] upperBlobFields = new String[blobFields.length];
        for (int i = 0; i < blobFields.length; i++) {
            upperBlobFields[i] = blobFields[i].toString().toUpperCase();
        }
        final List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        final LobHandler lobHandler = new DefaultLobHandler();
        sql = new StringBuffer("select * from (").append(sql).append(") where rownum < 2").toString();

        this.getJdbcTemplate().query(sql, new RowMapper<Object>() {
            Map<String, Object> map = null;
            int colCount = 0;
            String colName = "";
            ResultSetMetaData metaData = null;

            public Object mapRow(ResultSet rs, int i) throws SQLException {
                map = new HashMap<String, Object>();
                metaData = rs.getMetaData();
                colCount = metaData.getColumnCount();
                for (int c = 1; c <= colCount; c++) {
                    colName = metaData.getColumnName(c);
                    if (ArrayUtils.contains(upperBlobFields, colName)) {
                        map.put(colName, lobHandler.getBlobAsBytes(rs, c));
                    } else {
                        map.put(colName, rs.getString(colName));
                    }
                }
                result.add(map);
                return result;
            }
        });
        if (result.size() > 0) {
            return (InputStream) result.get(0);
        }
        return null;
    }

    // private Map syncMap=null;
    // private Map asyncMap=null;

    /**
     * 同步Tree的Json，一次性加载所有节点数据
     * 
     * @param sql 查询sql
     * @param idField id字段名
     * @param parentIdField 对应的存储父节点id的字段名
     * @param nameField 用来显示的，对应text的字段名
     * @param parentIdValue 对应的父节点的Id
     * @param checkedIdValue 需要选中的节点id，使用“,”分隔
     * @return
     */
    public String getSyncTreeJson(String sql, String idField, String parentIdField, String nameField,
            String parentIdValue, String checkedIdValue) {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("select tmp.* from ( ").append(sql).append(" ) tmp where 1=1 ");
        if (StringUtils.isBlank(parentIdValue)) {
            sqlBuffer.append(" and tmp." + parentIdField + " is null ");
        } else {
            if (parentIdValue.indexOf(",") > -1) {
                sqlBuffer.append(" and tmp." + parentIdField + " in ('" + parentIdValue.replaceAll(",", "','") + "') ");
            } else {
                sqlBuffer.append(" and tmp." + parentIdField + " = '" + parentIdValue + "' ");
            }
        }
        List<Map<String, Object>> data = this.queryForList(sqlBuffer.toString());
        StringBuffer jsonStr = new StringBuffer();
        jsonStr.append("[");
        String id = "";
        String name = "";
        Map syncMap = null;
        for (int i = 0; i < data.size(); i++) {
            syncMap = (Map) data.get(i);
            id = ObjectUtils.toString(syncMap.get(idField), "");
            name = ObjectUtils.toString(syncMap.get(nameField), "");
            jsonStr.append("{id:'" + id + "',text:'" + name + "',");
            for (Iterator iterator = syncMap.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                if (!key.toUpperCase().equals(idField.toUpperCase())
                        && !key.toUpperCase().equals(nameField.toUpperCase())) {
                    jsonStr.append(formatFieldName(key) + ":'" + ObjectUtils.toString(syncMap.get(key), "") + "',");
                }
            }
            jsonStr.append(getTreeCheckedJson(syncMap.get(idField).toString(), checkedIdValue));
            String childJson = getSyncTreeChildJson(jsonStr, sql, idField, parentIdField, nameField,
                    syncMap.get(idField).toString(), checkedIdValue);
            jsonStr.delete(0, jsonStr.length());
            jsonStr.append(childJson);
            if (i != data.size()) {
                jsonStr.append("}");
            }
            if (i + 1 != data.size()) {
                jsonStr.append(",");
            }
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }

    private String getSyncTreeChildJson(StringBuffer jsonStr, String sql, String idField, String parentIdField,
            String nameField, String idValue, String checkedIdValue) {
        List<Map<String, Object>> data = this.queryForList("select tmp.* from ( " + sql + " ) tmp where tmp."
                + parentIdField + "='" + idValue + "' ");
        if (data.size() > 0) {
            jsonStr.append("children:[");
            String id = "";
            String name = "";
            Map syncMap = null;
            for (int i = 0; i < data.size(); i++) {
                syncMap = (Map) data.get(i);
                id = ObjectUtils.toString(syncMap.get(idField), "");
                name = ObjectUtils.toString(syncMap.get(nameField), "");
                jsonStr.append("{id:'" + id + "',text:'" + name + "',");
                for (Iterator iterator = syncMap.keySet().iterator(); iterator.hasNext();) {
                    String key = (String) iterator.next();
                    if (!key.toUpperCase().equals(idField.toUpperCase())
                            && !key.toUpperCase().equals(nameField.toUpperCase())) {
                        jsonStr.append(formatFieldName(key) + ":'" + ObjectUtils.toString(syncMap.get(key), "") + "',");
                    }
                }
                jsonStr.append(getTreeCheckedJson(syncMap.get(idField).toString(), checkedIdValue));
                String childJson = getSyncTreeChildJson(jsonStr, sql, idField, parentIdField, nameField,
                        syncMap.get(idField).toString(), checkedIdValue);
                jsonStr.delete(0, jsonStr.length());
                jsonStr.append(childJson);
                if (i != data.size()) {
                    jsonStr.append("}");
                }
                if (i + 1 != data.size()) {
                    jsonStr.append(",");
                }
            }
            jsonStr.append("]");
        } else {
            jsonStr.append("leaf:true");
        }
        return jsonStr.toString();
    }

    /**
     * 异步Tree的Json，仅加载当前层的数据
     * @param idValue 指定的需要加载的节点的id
     * @param sql 查询sql
     * @param idField id字段名
     * @param parentIdField 对应的存储父节点id的字段名
     * @param nameField 用来显示的，对应text的字段名
     * @param parentIdValue 对应的父节点的Id
     * @param checkedIdValue 需要选中的节点id，使用“,”分隔
     * @return
     */
    public String getAsyncTreeJson(String idValue, String sql, String idField, String parentIdField, String nameField,
            String parentIdValue, String checkedIdValue) {
        StringBuffer sqlBuf = new StringBuffer();
        sqlBuf.append("select tmp.* from ( ").append(sql).append(" ) tmp where 1=1 ");
        if (StringUtils.isBlank(idValue) || "-1".equals(idValue)) {
            if (StringUtils.isBlank(parentIdValue)) {
                sqlBuf.append(" and tmp." + parentIdField + " is null ");
            } else {
                if (parentIdValue.indexOf(",") > -1) {
                    sqlBuf.append(" and tmp." + parentIdField + " in ('" + parentIdValue.replaceAll(",", "','") + "') ");
                } else {
                    sqlBuf.append(" and tmp." + parentIdField + " ='" + parentIdValue + "' ");
                }
            }
        } else {
            sqlBuf.append(" and tmp." + parentIdField + " ='" + idValue + "' ");
        }
        List<Map<String, Object>> data = this.queryForList(sqlBuf.toString());
        StringBuffer jsonStr = new StringBuffer();
        jsonStr.append("[");
        String id = "";
        String name = "";
        for (int i = 0; i < data.size(); i++) {
            Map asyncMap = (Map) data.get(i);
            id = ObjectUtils.toString(asyncMap.get(idField), "");
            name = ObjectUtils.toString(asyncMap.get(nameField), "");
            jsonStr.append("{id:'" + id + "',text:'" + name + "',");
            for (Iterator iterator = asyncMap.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                if (!key.toUpperCase().equals(idField.toUpperCase())
                        && !key.toUpperCase().equals(nameField.toUpperCase())) {
                    jsonStr.append(formatFieldName(key) + ":'" + ObjectUtils.toString(asyncMap.get(key), "") + "',");
                }
            }
            jsonStr.append(getTreeCheckedJson(asyncMap.get(idField).toString(), checkedIdValue));
            if (getSyncTreeChildCount(sql, parentIdField, asyncMap.get(idField).toString()) > 0) {
                jsonStr.append("leaf:false");
            } else {
                jsonStr.append("leaf:true");
            }
            if (i == data.size() - 1) {
                jsonStr.append("}");
            } else {
                jsonStr.append("},");
            }
        }
        jsonStr.append("]");
        return jsonStr.toString();
    }

    private int getSyncTreeChildCount(String sql, String parentIdField, String idValue) {
        return this.queryForInt("select count(*) from ( " + sql + " ) tmp where tmp." + parentIdField + "='" + idValue
                + "' ");
    }

    private String getTreeCheckedJson(String idValue, String checkIdValue) {
        String result = "";
        if (StringUtils.isNotBlank(checkIdValue)) {
            result = "checked:false,";
            if (checkIdValue.indexOf(",") > -1) {
                for (int j = 0; j < checkIdValue.split(",").length; j++) {
                    if (idValue.equals(checkIdValue.split(",")[j].toString())) {
                        result = "checked:true,";
                    }
                }
            } else {
                if (idValue.equals(checkIdValue)) {
                    result = "checked:true,";
                }
            }
        }
        return result;
    }

    private String formatFieldName(String fieldName) {
        String[] fieldNames = fieldName.toLowerCase().split("_");
        int length = fieldNames.length;
        if (length > 0) {
            String newFieldName = "";
            String[] newFieldNames = new String[fieldNames.length];
            for (int i = 0; i < length; i++) {
                newFieldNames[i] = fieldNames[i].substring(0, 1).toUpperCase()
                        + fieldNames[i].substring(1, fieldNames[i].length());
            }
            for (int i = 0; i < newFieldNames.length; i++) {
                newFieldName += newFieldNames[i];
            }
            newFieldName = newFieldName.substring(0, 1).toLowerCase()
                    + newFieldName.substring(1, newFieldName.length());
            return newFieldName;
        } else {
            return fieldName;
        }
    }
    
    /**
     * 分页查询(返回Map类型)
     * 
     * @author zhangxd
     */
    public Map queryDataForPage(String sql,String page,String size) {
        int start = ((Integer.parseInt(page) - 1) * Integer.parseInt(size)) + 1;
        int limit = Integer.parseInt(size);
        Map map = null;
        try{
            int pageCount=0;
            int countPage=1;
            int recordCount = this.queryForInt("SELECT COUNT(*) FROM ("+sql+")");
            if (recordCount > 0) {
                if(start!=-1&&limit!=-1){
                    countPage=limit;
                }
                if (recordCount % countPage == 0) {
                    pageCount = recordCount / countPage;
                } else {
                    pageCount = recordCount / countPage + 1;
                }
            }
            sql = "SELECT * FROM (SELECT TMP.*,ROWNUM RN FROM (" + sql + ") TMP) ";
            if(start!=-1&&limit!=-1){
                int cpage=start/limit+1;
                sql+="WHERE RN >" + (cpage - 1) * countPage + " AND RN<= " + cpage * countPage;
            }
            map = new HashMap();
            //记录数
            map.put("success", true);
            map.put("totalRows", recordCount);
            map.put("curPage", page);
            //总页数
            map.put("pageCount", pageCount);
            //结果集
            map.put("data", this.queryForList(sql));
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return map;
    }
    
    /**
     * 分页查询(返回Map类型)预编译
     * 
     * @author zhangxd
     */
    @SuppressWarnings ( { "unchecked", "rawtypes" } )
    public Map queryDataForPage(String sql,String page,String size,Object[] args) {
        int start = ((Integer.parseInt(page) - 1) * Integer.parseInt(size)) + 1;
        int limit = Integer.parseInt(size);
        Map map = null;
        try{
            int pageCount=0;
            int countPage=1;
            int recordCount = this.queryForInt("SELECT COUNT(*) FROM ("+sql+")",args);
            if (recordCount > 0) {
                if(start!=-1&&limit!=-1){
                    countPage=limit;
                }
                if (recordCount % countPage == 0) {
                    pageCount = recordCount / countPage;
                } else {
                    pageCount = recordCount / countPage + 1;
                }
            }
            sql = "SELECT * FROM (  SELECT * FROM (SELECT TMP.*,ROWNUM RN FROM (" + sql + ") TMP) ";
            if(start!=-1&&limit!=-1){
                int cpage=start/limit+1;
                sql+="WHERE  RN<= " + cpage * countPage+" ) where  RN >" + (cpage - 1) * countPage + " ";
            }
            map = new HashMap();
            //记录数
            map.put("success", true);
            map.put("totalRows", recordCount);
            map.put("curPage", page);
            //总页数
            map.put("pageCount", pageCount);
            //结果集
            map.put("data", this.queryForList(sql,args));
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return map;
    }
    
    /**
     * 不分页查询(返回Map类型)预编译
     * 
     * @author lixp
     */
    public Map queryNoPage(String sql,int count,Object[] args) {
        Map map = null;
        try{
            sql = "SELECT * FROM (  SELECT * FROM (SELECT TMP.*,ROWNUM RN FROM (" + sql + ") TMP)) ";
            map = new HashMap();
//            {"success": true, "totalRows": 26, "curPage": 1, "data": 
            //记录数
            map.put("success", true);
            map.put("totalRows", count);
            map.put("curPage", 1);
            //总页数
            map.put("pageCount", 1);
            //结果集
            map.put("data", this.queryForList(sql,args));
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return map;
    }
    
    
    /**
     * 分页查询(返回Map类型)预编译
     * 
     * @author chends
     */
    public Map queryForPageNoCount(String sql,int recordCount,String page,String size,Map<String, Object> mapObj) {
        int start = ((Integer.parseInt(page) - 1) * Integer.parseInt(size)) + 1;
        int limit = Integer.parseInt(size);
        Map map = null;
        try{
            int pageCount=0;
            int countPage=1;
            if (recordCount > 0) {
                if(start!=-1&&limit!=-1){
                    countPage=limit;
                }
                if (recordCount % countPage == 0) {
                    pageCount = recordCount / countPage;
                } else {
                    pageCount = recordCount / countPage + 1;
                }
            }
            sql = "SELECT * FROM (  SELECT * FROM (SELECT TMP.*,ROWNUM RN FROM (" + sql + ") TMP) ";
            if(start!=-1&&limit!=-1){
                int cpage=start/limit+1;
                sql+="WHERE  RN<= ?) where  RN > ? ";
                mapObj.put( "RN1", cpage * countPage );
                mapObj.put( "RN2",  (cpage - 1) * countPage );
            }else{
                sql+=")"; 
            }
            map = new HashMap();
//            {"success": true, "totalRows": 26, "curPage": 1, "data": 
            //记录数
            map.put("success", true);
            map.put("totalRows", recordCount);
            map.put("curPage", page);
            //总页数
            map.put("pageCount", pageCount);
            Object[] objArray = PlatformUtil.statementObjectArray(mapObj);
            //结果集
            map.put("data", this.queryForList(sql,objArray));
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return map;
    }
    
    
    /**
     * 分页查询(返回Map类型)预编译
     * 
     * @author chends
     */
    public Map queryForPageNoCount(String sql,int recordCount,String page,String size,Object[] args) {
        int start = ((Integer.parseInt(page) - 1) * Integer.parseInt(size)) + 1;
        int limit = Integer.parseInt(size);
        Map map = null;
        try{
            int pageCount=0;
            int countPage=1;
            if (recordCount > 0) {
                if(start!=-1&&limit!=-1){
                    countPage=limit;
                }
                if (recordCount % countPage == 0) {
                    pageCount = recordCount / countPage;
                } else {
                    pageCount = recordCount / countPage + 1;
                }
            }
            sql = "SELECT * FROM (  SELECT * FROM (SELECT TMP.*,ROWNUM RN FROM (" + sql + ") TMP) ";
            if(start!=-1&&limit!=-1){
                int cpage=start/limit+1;
                sql+="WHERE  RN<= " + cpage * countPage+" ) where  RN >" + (cpage - 1) * countPage + " ";
            }else{
                sql+=")"; 
            }
            map = new HashMap();
//            {"success": true, "totalRows": 26, "curPage": 1, "data": 
            //记录数
            map.put("success", true);
            map.put("totalRows", recordCount);
            map.put("curPage", page);
            //总页数
            map.put("pageCount", pageCount);
            //结果集
            map.put("data", this.queryForList(sql,args));
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return map;
    }
    
    /**
     * 读取Blob
     */
    @SuppressWarnings ( "rawtypes" )
    public List<Map> queryForBlob(String sql,String[] blobFields){
        log.debug(sql);
        final String[] upperBlobFields=new String[blobFields.length];
        for(int i=0;i<blobFields.length;i++){
            upperBlobFields[i]=blobFields[i].toString().toUpperCase();
        }
        final List<Map> result=new ArrayList<Map>();
        final LobHandler lobHandler=new DefaultLobHandler();
        List list = this.getJdbcTemplate().query(sql,
            new RowMapper() {
                Map map=null;
                int colCount=0;
                String colName="";
                ResultSetMetaData metaData=null;
                public Object mapRow(ResultSet rs, int i) throws SQLException {
                    map=new HashMap();
                    metaData = rs.getMetaData();
                    colCount=metaData.getColumnCount();
                    for (int c = 1; c <= colCount; c++) {
                        colName=metaData.getColumnName(c);
                        if(ArrayUtils.contains(upperBlobFields, colName)){
                            map.put(colName, lobHandler.getBlobAsBytes(rs,c));
                        }else{
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
     * 分页查询，查询不同条数，但是没有总数 预编译 
     * 
     * @author zhangxd
     */
    public List<Map<String,Object>> queryForLimit(String sql,String page,String size,Object[] args) {
        int start = ((Integer.parseInt(page) - 1) * Integer.parseInt(size)) + 1;
        int limit = Integer.parseInt(size);
        try{
            sql = "SELECT * FROM (  SELECT * FROM (SELECT TMP.*,ROWNUM RN FROM (" + sql + ") TMP) ";
            if(start!=-1&&limit!=-1){
                int cpage=start/limit+1;
                sql+="WHERE  RN<= " + cpage * limit+" ) where  RN >" + (cpage - 1) * limit + " ";
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return  this.queryForList(sql,args);
    }
    
    /**
     * 分页查询，查询不同条数，但是没有总数 预编译 
     * 
     * @author zhangxd
     */
    public List<Map<String,Object>> queryForLimit(String sql,String page,String size) {
        int start = ((Integer.parseInt(page) - 1) * Integer.parseInt(size)) + 1;
        int limit = Integer.parseInt(size);
        try{
            sql = "SELECT * FROM (  SELECT * FROM (SELECT TMP.*,ROWNUM RN FROM (" + sql + ") TMP) ";
            if(start!=-1&&limit!=-1){
                int cpage=start/limit+1;
                sql+="WHERE  RN<= " + cpage * limit+" ) where  RN >" + (cpage - 1) * limit + " ";
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return  this.queryForList(sql);
    }
    
    /**
     * 查询待clob 和blob字段
     * @param sql
     * @param clobFields
     * @param blobFields
     * @return 
     * lixp update 2015831 将sql执行方式改为预编译的形式(未验证)
     */
    @SuppressWarnings ( "unchecked" )
    public List<Map> findBySql(String sql, String[] clobFields,String[] blobFields,Object[] obj) {
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
        List list = this.getJdbcTemplate().query(sql,obj, new RowMapper() {
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
}
