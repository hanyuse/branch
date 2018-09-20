package cn.com.sparknet.branchRecord.common.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.stereotype.Repository;

import cn.com.sparknet.branchRecord.common.basedao.BaseJdbcDao;




@Repository("sequencesUtil")
public class SequencesUtil extends BaseJdbcDao{
	
	/**
	 * 获取库的SEQ，用于用户表的表数据插入
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Long getNextVal(){
		List list = this.getJdbcTemplate().queryForList("SELECT BLSP_SEQ.NEXTVAL AS VALUE FROM DUAL");
		String value = "0";
		if(list.size() >0){
			value = ObjectUtils.toString(((Map)list.get(0)).get("VALUE"));
		}
		return Long.parseLong(value);
	}
	
	/**
	 * 一照一码seq
	 * YZYM_SEQ
	 * @return
	 */
	public Long getYZYMNextVal(){
        List list = this.getJdbcTemplate().queryForList("SELECT YZYM_SEQ.NEXTVAL AS VALUE FROM DUAL");
        String value = "0";
        if(list.size() >0){
            value = ObjectUtils.toString(((Map)list.get(0)).get("VALUE"));
        }
        return Long.parseLong(value);
    }
	
	/**
     * 获取库的ORG
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Long getORGVal(){
        List list = this.getJdbcTemplate().queryForList("SELECT PARAMETER_VALUE FROM G$PARAMETER T WHERE T.PARAMETER_NAME='ORG'");
        String value = "0";
        if(list.size() >0){
            value = ObjectUtils.toString(((Map)list.get(0)).get("PARAMETER_VALUE"));
        }
        return Long.parseLong(value);
    }
} 
