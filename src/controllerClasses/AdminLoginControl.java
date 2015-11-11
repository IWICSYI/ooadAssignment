package controllerClasses;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import data.LoginData;
import dataController.LoginDataControl;

public class AdminLoginControl {

	/**
	 * attribute for secret message
	 */
	private static MessageDigest md;

	/**
	 * Encrypt password
	 * 
	 * @param pass
	 *            inputed password
	 * @return
	 */
	private String encrypt(String pass) {
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] passBytes = pass.getBytes();
			md.reset();
			byte[] digested = md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digested.length; i++) {
				sb.append(Integer.toHexString(0xff & digested[i]));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(AdminLoginControl.class.getName()).log(
					Level.SEVERE, null, ex);
		}
		return null;

	}

	/**
	 * Check if user exist or not
	 * 
	 * @param uN
	 * @param pW
	 * @return
	 * @throws IOException
	 */
	public boolean checkExist(String uN, String pW) throws IOException {
		boolean result = false;
		ArrayList<LoginData> adminList = new ArrayList<LoginData>();
		adminList = LoginDataControl.readLogin();
		pW = encrypt(pW);
		// System.out.println(pW+"\n"+adminList.get(0).getPassword());
		for (int i = 0; i < adminList.size(); i++) {
			if (uN.equals(adminList.get(i).getUsername())
					&& pW.equals(adminList.get(i).getPassword())) {
				result = true;
				break;
			}
		}

		return result;

	}

}
