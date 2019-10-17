package com.app.util;

import org.springframework.util.DigestUtils;

public class MD5Util {
	//盐，用于混交md5
	private static final String slat = "&%5123***&&%%$$#@";
	/**
	 * 生成md5
	 * @param seckillId
	 * @return
	 */
	public static String getMD5(String str) {
		String base = str +"/"+slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	
	public static void main(String[] args) {
		System.out.println(getMD5("1234"));
		System.out.println(getMD5("1234"));
		System.out.println(getMD5("1234"));
		System.out.println(getMD5("1234"));
	}
}
