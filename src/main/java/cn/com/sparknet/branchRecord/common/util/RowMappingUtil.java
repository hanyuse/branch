package cn.com.sparknet.branchRecord.common.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Spring通用RowMapping类
 * @author chenxy
 *
 */
public final class RowMappingUtil implements RowMapper<Object>{
	
    private Object className;
    
    public RowMappingUtil(Object className){
        this.className=className;
    }

    public Object mapRow(ResultSet rs, int index) throws SQLException {
        try {
            className=className.getClass().newInstance();
        }catch(Exception e){
        	throw new RuntimeException(e.getMessage());
        }
        ResultSetMetaData rsmd=rs.getMetaData();
        int cc=rsmd.getColumnCount();
        String columnName="";
        Object cellValue="";
        Field  field=null;
        String cellType=null;
        for(int i=1;i<=cc;i++){
            columnName=rsmd.getColumnName(i);
            try{
                field=className.getClass().getDeclaredField(columnName);
                cellType=field.getType().getSimpleName();
                if(cellType.equalsIgnoreCase("String")){    
                    cellValue=rs.getString(columnName);    
                }else if(cellType.equalsIgnoreCase("Integer")){    
                    cellValue=rs.getInt(columnName);    
                }else if(cellType.equalsIgnoreCase("Float")){    
                    cellValue=rs.getFloat(columnName);    
                }else if(cellType.equalsIgnoreCase("Double")){    
                    cellValue=rs.getDouble(columnName);    
                }else if(cellType.equalsIgnoreCase("Long")){    
                    cellValue=rs.getLong(columnName);    
                }else if(cellType.equalsIgnoreCase("Short")){    
                    cellValue=rs.getShort(columnName);    
                }else if(cellType.equalsIgnoreCase("Boolean")){    
                    cellValue=rs.getBoolean(columnName);    
                }else if(cellType.equalsIgnoreCase("Date")){    
                    cellValue=rs.getDate(columnName);    
                }else if(cellType.equalsIgnoreCase("Byte")){    
                    cellValue=rs.getByte(columnName);    
                }else if(cellType.equalsIgnoreCase("BigDecimal")){    
                    cellValue=rs.getBigDecimal(columnName);    
                }else if(cellType.equalsIgnoreCase("InputStream")){    
                    cellValue=rs.getBinaryStream(columnName);    
                }else if(cellType.equalsIgnoreCase("Blob")){    
                    cellValue=rs.getBlob(columnName);    
                }else if(cellType.equalsIgnoreCase("Clob")){    
                    cellValue=rs.getClob(columnName);    
                }else if(cellType.equalsIgnoreCase("Time")){    
                    cellValue=rs.getTime(columnName);    
                }else if(cellType.equalsIgnoreCase("Timestamp")){    
                    cellValue=rs.getTimestamp(columnName);   
                }else if(cellType.equalsIgnoreCase("URL")){
                    cellValue=rs.getURL(columnName);
                }else{
                    cellValue=rs.getObject(columnName);
                }
                className.getClass().getDeclaredMethod("set"+(columnName.charAt(0)+"").toUpperCase()+columnName.substring(1,columnName.length()),new Class[]{field.getType()}).invoke(className, new Object[]{cellValue});    
            }catch(Exception ex){
                ex.printStackTrace();
                break;
            }    
        }    
        return this.className;    
    }    
        
}
