package com.carfinance.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	private static String bytetoString(byte[] digest) {
		String str = "";
		String tempStr = "";
		for (int i = 0 ; i < digest.length ; i++) {
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1) {
				str = str + "0" + tempStr;
			} else {
				str = str + tempStr;
			}
		}
		return str.toUpperCase();
	}
	
	/**
	 * MD5加密
	 * @param inStr
	 * @return
	 */
	public static String MD5Encrypt(String inStr) {
		MessageDigest md = null;
		String outStr = null;
		try {
			md = MessageDigest.getInstance("MD5"); // 可以选中其他的算法如SHA
			byte[] digest = md.digest(inStr.getBytes()); // 返回的是byet[]，要转化为String存储比较方便
			outStr = bytetoString(digest);
		}
		catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		return outStr;
	}
	

	public static void main(String args[]) {
		System.out.println(MD5Util.MD5Encrypt("!q@w#e$r"));

	}
}