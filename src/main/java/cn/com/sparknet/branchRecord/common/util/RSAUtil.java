package cn.com.sparknet.branchRecord.common.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * RSA非对称算法
 * @author chenxy
 * 
 */
public final class RSAUtil {
	
	/**
	 * 初始化密钥
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put("PublicKey", publicKey);
		keyMap.put("PrivateKey", privateKey);
		return keyMap;
	}
	
	/**
	 * 获取公钥
	 * @param keyMap 密钥Map
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get("PublicKey");
        return new BASE64Encoder().encode(key.getEncoded());
    }
	
	/**
	 * 获取私钥
	 * @param keyMap 密钥Map
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get("PrivateKey");
        return new BASE64Encoder().encode(key.getEncoded());
    }
	
	/**
	 * 公钥加密
	 * @param data 需加密的数据
	 * @param publicKey 公钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data,String publicKey)throws Exception{
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key key = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }
	
	/**
	 * 公钥解密
	 * @param data 需解密的数据
	 * @param publicKey 公钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data,String publicKey)throws Exception{
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key key = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }
	
	/**
	 * 私钥加密
	 * @param data 需加密的数据
	 * @param privateKey 私钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data,String privateKey)throws Exception{
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key key = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }
	
	/**
	 * 私钥解密
	 * @param data 需解密的数据
	 * @param privateKey 私钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data,String privateKey)throws Exception{
		byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key key = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }
	
	/**
	 * 私钥签名
	 * @param data 需签名的数据
	 * @param privateKey 私钥
	 * @return
	 * @throws Exception
	 */
	public static String signByPrivateKey(byte[] data,String privateKey)throws Exception{
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKey);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data);
        return new BASE64Encoder().encode(signature.sign());
    }
	
	/**
	 * 公钥校验
	 * @param data 需校验的数据
	 * @param publicKey 公钥
	 * @param sign 签名
	 * @return
	 * @throws Exception
	 */
	public static boolean verifyByPublicKey(byte[] data,String publicKey,String sign)throws Exception{
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKey);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(x509EncodedKeySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(data);
        return signature.verify(new BASE64Decoder().decodeBuffer(sign));
    }
	
	public static void main(String[] args) throws Exception {
        Map keyMap=RSAUtil.initKey();
        String privateKey=RSAUtil.getPrivateKey(keyMap);
        String publicKey=RSAUtil.getPublicKey(keyMap);
        
        /*******************对方********************/
        //1、将公钥字符先用Base64解码，然后进行使用公钥对用户名或密码进行加密
        byte[] b1=RSAUtil.encryptByPublicKey("用户名或密码".getBytes(), publicKey);
        //2、将加密后的字节数组进行Base64编码传递给我们
        String str=new BASE64Encoder().encode(b1);
        System.out.println("\n对方给我们加密后的字符："+str);
        
        /*******************我们********************/
        //1、我们拿到对方给的密文后先进行Base64解码
        byte[] b2 = new BASE64Decoder().decodeBuffer(str);
        //2、使用密钥进行解码
        String result=new String(RSAUtil.decryptByPrivateKey(b2, privateKey));
        System.out.println("\n我们通过解码得到的密文："+result);
}
}
