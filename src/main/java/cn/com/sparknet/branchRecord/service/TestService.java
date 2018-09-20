package cn.com.sparknet.branchRecord.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.com.sparknet.branchRecord.common.exception.BusinessException;
import cn.com.sparknet.branchRecord.common.exception.ExceptionEnum;
import cn.com.sparknet.branchRecord.common.json.EditJson;
import cn.com.sparknet.branchRecord.common.json.ListJson;
import cn.com.sparknet.branchRecord.dao.TestDao;

@Service
public class TestService {

    @Resource
    private TestDao testDao;
    
    
    public ListJson findUser(){
        ListJson listJson = new ListJson();
        List<Map<String,Object>> resultlist = new ArrayList<Map<String,Object>>();
        try {
            resultlist = testDao.findUser();
            listJson.setItems( resultlist );
            listJson.setTotal( resultlist.size() );
        }
        catch ( Exception e ) {
            throw new BusinessException( e, ExceptionEnum.NULL, "查询列表数据失败" );
        }
        return listJson;
    }
    
    public EditJson insertUser( ) {
        EditJson editJson = new EditJson();
        try {
            testDao.insertUser();
            testDao.insertUser1();
            editJson.setSuccess( true );
        }
        catch ( Exception e ) {
            editJson.setSuccess( false );
            throw new BusinessException( e, ExceptionEnum.NULL, "数据添加失败！" );
        }
        return editJson;
    }
    
    public EditJson deleteUser( ) { 
        EditJson editJson = new EditJson();
        try {
            testDao.deleteUser();
            editJson.setSuccess( true );
        }
        catch ( Exception e ) {
            editJson.setSuccess( false );
            throw new BusinessException( e, ExceptionEnum.NULL, "数据删除失败！" );
        }
        return editJson;
    }
    
    
}
