/*
 * XT Software Technologies Co., Ltd. Copyright 1998-2009, All rights reserved.
 * 文件名  :DESPlus72.java
 * 创建人  :xiangtao
 * 创建时间:Sep 22, 2011
 */

package com.carfinance.utils;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;

/**
 * [简要描述]: <br/>[详细描述]:
 * 
 * @author xiangtao
 * @version [revision],Sep 22, 2011
 * @since 版本号
 */
public class DESPlus72 {

    private static String strDefaultKey = "AHXXT72CH";

    private Cipher encryptCipher = null;

    private Cipher decryptCipher = null;

    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程
     * 
     * @param arrB
     *            需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception
     *             本方法不处理任何异常，所有异常全部抛出
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        // for (int i = 0; i < iLen-8; i++) {
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            // log.info("=====:"+intTmp);
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
        /*
         * StringBuffer sb = new StringBuffer(arrB.length); String sTemp; for
         * (int i = 0; i < arrB.length; i++) { sTemp = Integer.toHexString(0xFF &
         * arrB[i]); if (sTemp.length() < 2) sb.append(0);
         * sb.append(sTemp.toUpperCase()); } return sb.toString();
         */

    }

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
     * 互为可逆的转换过程
     * 
     * @param strIn
     *            需要转换的字符串
     * @return 转换后的byte数组
     * @throws Exception
     *             本方法不处理任何异常，所有异常全部抛出
     * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
     */
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        // strIn=strIn+"6e8662b6d7f37c27";
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }

        return arrOut;
    }

    /**
     * 默认构造方法，使用默认密钥
     * 
     * @throws Exception
     */
    public DESPlus72() throws Exception {
        this(strDefaultKey);
    }

    /**
     * 指定密钥构造方法
     * 
     * @param strKey
     *            指定的密钥
     * @throws Exception
     */
    public DESPlus72(String strKey) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        Key key = getKey(strKey.getBytes());

        encryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        decryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
        decryptCipher.init(Cipher.DECRYPT_MODE, key);
    }

    /**
     * 加密字节数组
     * 
     * @param arrB
     *            需加密的字节数组
     * @return 加密后的字节数组
     * @throws Exception
     */
    public byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    /**
     * 加密字符串
     * 
     * @param strIn
     *            需加密的字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public String encrypt(String strIn) throws Exception {
        return byteArr2HexStr(encrypt(padStr(strIn).getBytes()));

    }

    /**
     * 解密字节数组
     * 
     * @param arrB
     *            需解密的字节数组
     * @return 解密后的字节数组
     * @throws Exception
     */
    public byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    /**
     * 解密字符串
     * 
     * @param strIn
     *            需解密的字符串
     * @return 解密后的字符串
     * @throws Exception
     */
    public String decrypt(String strIn) throws Exception {
        return new String(decrypt(hexStr2ByteArr(strIn))).trim();
    }

    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     * 
     * @param arrBTmp
     *            构成该字符串的字节数组
     * @return 生成的密钥
     * @throws java.lang.Exception
     */
    private Key getKey(byte[] arrBTmp) throws Exception {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];

        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

        return key;
    }

    /**
     * 补位
     */
    public String padStr(String sourceData) {
        int n = (8 - (sourceData.getBytes().length) % 8);
        if (n > 0 && n != 8) {
            for (int i = 0; i < n; i++)
                sourceData = sourceData + " ";
        }
        return sourceData;
    }

    // 测试程序 Test.java

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            String test = "/72chMT";
            DESPlus72 des = new DESPlus72("AHXXT72CH");// 默认密钥
            System.out.println("加密前的字符：" + test);
            System.out.println("加密后的字符：" + des.encrypt(test));
            System.out
                    .println("解密后的字符===："
                            + des
                                    .decrypt("FWont6uQcplK-5iyqyo2oFl3HWcbN_tUaAVt9mLFXZQu6I24bMNdSHeoSxoIskKLtEMv5NctG1raBk9Bp-86RA.."));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
    private String RunDes8(byte[] data) throws Exception {
		int len = data.length >> 3;
		String outBuffer = "";
		for (int i = 0; i < len; i++) {
			outBuffer += byteArr2HexStr(encrypt(subBytes(data, i * 8, 8)));
		}
		return outBuffer;

	}
	
	private byte[] subBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		for (int i = begin; i < begin + count; i++)
			bs[i - begin] = src[i];
		return bs;
	}
    
    /**
	 * 分散密钥
	 * 
	 * @param data
	 *            3130 + keytype + keyid + yyyyMMdd
	 * @return
	 * @throws Exception
	 */
	public String Diversify(String data) throws Exception {
		byte[] db = hexStr2ByteArr(data);
		for (int i = 0; i < db.length; i++) {
			db[i] = (byte) (~db[i]);
		}
		return RunDes8(db);
	}
	
	/**
	 * 生成母key
	 * 
	 * @param keyA
	 *            必须是长度为8的密钥因子A
	 * @param keyB
	 *            必须是长度为8的密钥因子A
	 * @return 生成的母key
	 * @throws Exception
	 */
	public static String XOR(String keyA, String keyB) throws Exception {
		String keyAStr = byteArr2HexStr(byteArr2HexStr(keyA.getBytes())
				.getBytes());

		DESPlus72 des = new DESPlus72(keyB);
		return des.Diversify(keyAStr);

	}
}
