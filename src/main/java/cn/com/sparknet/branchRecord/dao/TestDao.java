package cn.com.sparknet.branchRecord.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import cn.com.sparknet.branchRecord.common.basedao.BaseJdbcDao;
import cn.com.sparknet.branchRecord.common.util.SequencesUtil;


@Repository
public class TestDao extends BaseJdbcDao{

    @Resource
    SequencesUtil sequencesUtil;
    
    
    public List<Map<String,Object>> findUser()throws Exception{
        String sql = "SELECT * FROM AM_USER";
        return this.queryForList( sql );
    }
    
    
    public void insertUser()throws Exception{
        final LobHandler lobHandler = new DefaultLobHandler();
        this.getNewJdbcTemplate().execute("INSERT INTO T_BLSP_ZH (ID) VALUES"
                + "(?)"
                , new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
		    protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
		        ps.setLong(1, 10);
		    }
        });
    }
    
    public void insertUser1()throws Exception{
        final LobHandler lobHandler = new DefaultLobHandler();
        this.getNewJdbcTemplate().execute("INSERT INTO T_BLSP_ZH (ID) VALUES"
                + "(?)"
                , new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
    protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
        ps.setLong(1, 11);
    }
});
    }
    
    
    public void deleteUser()throws Exception{
        String sql = "DELETE T_BLSP_ZH WHERE ID IN ('10','11') ";
        this.update( sql );
    }
}
