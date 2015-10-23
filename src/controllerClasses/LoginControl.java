package controllerClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import data.User;

public class LoginControl  {
	
	
	private static MessageDigest md;
	
	public LoginControl() {
		// TODO Auto-generated constructor stub
	}
	private static String cryptWithMD5(String pass){
	    try {
	        md = MessageDigest.getInstance("MD5");
	        byte[] passBytes = pass.getBytes();
	        md.reset();
	        byte[] digested = md.digest(passBytes);
	        StringBuffer sb = new StringBuffer();
	        for(int i=0;i<digested.length;i++){
	            sb.append(Integer.toHexString(0xff & digested[i]));
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException ex) {
	        Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
	    }
	        return null;


	   }

	public boolean checkExist(String uN,String pW) throws IOException
	{
		boolean result=false;
		DataControl dS=new DataControl();
		ArrayList<User> adminList=new ArrayList<User>();
		adminList=dS.readLogin();
		pW=cryptWithMD5(pW);
		//System.out.println(pW+"\n"+adminList.get(0).getPassword());
		for(int i=0;i<adminList.size();i++)
		{
			if(uN.equals(adminList.get(i).getUsername())&&pW.equals(adminList.get(i).getPassword()))
					{
						result=true;
						break;
					}
		}
		
		return result;
		
	}
	
	
}
