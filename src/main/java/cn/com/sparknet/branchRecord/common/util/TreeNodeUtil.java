package cn.com.sparknet.branchRecord.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
Description:     // 树形字典节点操作工具      
History:         // 历史修改记录
    <author>  <time>   <version >   <desc>
    David    96/10/12     1.0     build this moudle
 */
public final class TreeNodeUtil {

    private TreeNodeUtil() {

    }


    /**
     * list 转成treeNo的节点形式
     * @param string
     * @return boolean
     */
    public static final List<Map<String, Object>> list2TreeNote(List<Map<String, Object>> list) {
        List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
        Map<String, Object> resultMap = null;
        for (Map<String, Object> listMap : list) {
            resultMap = new HashMap<String, Object>();
            resultMap.put("id", listMap.get("ID"));
            resultMap.put("pId", listMap.get("PARENT_ID"));
            resultMap.put("itemCode", listMap.get("ITEM_CODE"));
            resultMap.put("name", listMap.get("ITEM_NAME"));
            String choose = StringUtil.nullToEmpty(listMap.get("CHOOSE"));
            if (choose.equals("X")) {
                resultMap.put("nocheck", true);
            }
            resultlist.add(resultMap);
        }
        return resultlist;
    }
    
    /**
     * list 转成treeNo的节点形式
     * @param string
     * @return boolean
     */
    public static final List<Map<String, Object>> list2MenuTree(HttpServletRequest request,List<Map<String, Object>> list) {
        List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
        Map<String, Object> resultMap = null;
        for (Map<String, Object> listMap : list) {
            resultMap = new HashMap<String, Object>();
            resultMap.put("id", listMap.get("ID"));
            resultMap.put("pId", listMap.get("PARENT_ID"));
            resultMap.put("pageUrl", listMap.get("PAGE_URL"));
            resultMap.put("name", listMap.get("ITEM_NAME")); 
            resultMap.put("iconSkin", listMap.get("ICONSKIN")); 
//            resultMap.put("target", "mainFrame"); 
//            resultMap.put("url", request.getContextPath()+listMap.get("PAGE_URL")+"?privilegeId="+listMap.get("ID")); 
            if(!StringUtil.nullToEmpty(listMap.get("CHAIL")).equals("0")){
                resultMap.put("isParent", true); 
            }
            resultlist.add(resultMap);  
        }
        return resultlist;
    }
    
    
    /**
     * list 转成treeNo的节点形式
     * @param string
     * @return boolean
     */
    public static final List<Map<String, Object>> list2TreeCorp(List<Map<String, Object>> list) {
        List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
        Map<String, Object> resultMap = null;
        for (Map<String, Object> listMap : list) {
            resultMap = new HashMap<String, Object>();
            resultMap.put("id", listMap.get("ID"));
            //resultMap.put("pId", listMap.get("PARENT_ID"));
            resultMap.put("name", listMap.get("CORP_NAME"));
           /* String choose = StringUtil.nullToEmpty(listMap.get("CHOOSE"));
            if (choose.equals("X")) {
                resultMap.put("nocheck", true);
            }*/
            resultlist.add(resultMap);
        }
        return resultlist;
    }
}
