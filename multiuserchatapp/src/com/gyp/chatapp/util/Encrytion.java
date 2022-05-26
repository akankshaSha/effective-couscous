package com.gyp.chatapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encrytion {
	public static String passwordEncrypt(String plainPassword) throws NoSuchAlgorithmException {
		String encryption=null;
		MessageDigest messageDigest=MessageDigest.getInstance("MD5");
		messageDigest.update(plainPassword.getBytes());
		byte [] encrypt=messageDigest.digest();
		StringBuffer sb=new StringBuffer();
		for(byte b: encrypt) {
			sb.append(b);
		}
		encryption=sb.toString();
		return encryption;
	}
}
