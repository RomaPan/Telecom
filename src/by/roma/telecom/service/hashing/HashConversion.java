package by.roma.telecom.service.hashing;

import org.apache.commons.codec.digest.DigestUtils;


public class HashConversion {

	private final static String salt = "Roma";

	public static String cipher(String userPass, String salt) {
		return DigestUtils.sha256Hex(userPass + salt);
	}

	public static String cipher(String pass) {

		String generatedHash = DigestUtils.sha256Hex(pass + salt);
		return generatedHash;
	}

	public String getSalt() {
		return salt;
	}

}
