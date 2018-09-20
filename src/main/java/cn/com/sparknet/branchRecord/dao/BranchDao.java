package cn.com.sparknet.branchRecord.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.com.sparknet.branchRecord.common.basedao.BaseJdbcDao;

@Repository
public class BranchDao extends BaseJdbcDao{
	
	public int pushbrinfo(Map<String,Object> map)throws Exception{
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		return this.update( sql.toString(),list.toArray());
	}
	
	/**
	 * 保存母公司信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int saveSubentInfo(Map<String,Object> map)throws Exception{
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		return this.update( sql.toString(),list.toArray());
	}
	
	/**
	 * 保存分公司信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int SavebrentInfo(Map<String,Object> map)throws Exception{
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		return this.update( sql.toString(),list.toArray());
	}
	
}
