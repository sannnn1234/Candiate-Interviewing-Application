package com.hr.config;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class PasswordEncryption {
	
	private final String ALGORITHM = "AES";
	private String key = "TimesheetPasswor";

	public void setKey(String key) {
		this.key = key;
	}

	public String encrypt(String valueToEncrypt) throws Exception {
		String pws = "";
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			byte[] encryptedBytes = cipher.doFinal(valueToEncrypt.getBytes());
			pws = Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
		}
		return pws;
	}

	public String decrypt(String encryptedValue) {
		String pws = "";
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			byte[] decodedBytes = Base64.getDecoder().decode(encryptedValue);
			byte[] decryptedBytes = cipher.doFinal(decodedBytes);
			pws = new String(decryptedBytes);
		} catch (Exception e) {
		}
		return pws;
	}

	public static void main(String[] args) throws Exception {
		PasswordEncryption pe = new PasswordEncryption();
		String pass = "September@2023";
		System.out.println(pe.encrypt(pass));
		System.out.println(pe.decrypt(pe.encrypt(pass)));

	}

}
