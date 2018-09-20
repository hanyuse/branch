package cn.com.sparknet.branchRecord.common.util;

import sun.misc.BASE64Decoder;     
import sun.misc.BASE64Encoder;     
    
/**   
 * BASE64加密解密(适用于信息查询)
 * 
 * @author zhangxd
 * 
 */    
public class Base64Encrypt     
{     
    
    /**    
     * BASE64解密   
   * @param key          
     * @return          
     * @throws Exception          
     */              
    public static byte[] decryptBASE64(String key) throws Exception {               
        return (new BASE64Decoder()).decodeBuffer(key);               
    }
                  
    /**         
     * BASE64加密   
   * @param key          
     * @return          
     * @throws Exception          
     */              
    public static String encryptBASE64(byte[] key) throws Exception {               
        return (new BASE64Encoder()).encodeBuffer(key);               
    }
    
    public static void main(String[] args) throws Exception     
    {
        /*String data = Base64Encrypt.encryptBASE64( Base64Encrypt.encryptBASE64( Base64Encrypt.encryptBASE64("江苏<<现代面粉工业>>杂志社有限公司".getBytes()).getBytes()).getBytes());     
        System.out.println("加密前："+data);   */  
        
        byte[] byteArray = Base64Encrypt.decryptBASE64(new String(Base64Encrypt.decryptBASE64(new String( Base64Encrypt.decryptBASE64("TmxsUGREVTBTMk09")))));     
        System.out.println("解密后："+new String(byteArray,"UTF-8"));     
    }     
}  
