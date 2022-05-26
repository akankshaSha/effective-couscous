package com.gyp.chatapp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Todo: required fields with specifications

public class Validation {
	public static boolean isValidUserName(String uname)
	{
		if(uname.length()!=0)
			return true;
		return false;
	}
	
	public static boolean isValidPassword(char pass[])
	{
		if(pass.length<8)
			return false;
		return true;
	}
	
	public static boolean isValidEmail(String email)
	{
		if(email==null ||email.length()==0) return true;
		Pattern pattern=Pattern.compile("^(.+)@(.+)$"); //defines pattern to be used in search
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean isValidPhoneNo(String phno)
	{
		if(phno==null ||phno.length()==0) return true; 
		Pattern pattern=Pattern.compile("[0-9]+"); //defines pattern to be used in search
		Matcher matcher = pattern.matcher(phno);
		return matcher.matches() && phno.length()==10;
	}
}
