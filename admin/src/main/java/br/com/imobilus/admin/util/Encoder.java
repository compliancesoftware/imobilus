package br.com.imobilus.admin.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Codifica e decodifica senhas.
 * @author douglas.f.filho
 *
 */
public class Encoder {
	private static final String key = "ImobilusImobilus";
	
	public static String encode(String normalText) {
		String encrypted = null;
		
		try {
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
	        byte[] encryptedBytes = cipher.doFinal(normalText.getBytes());
	        encrypted = Base64.encodeBase64String(encryptedBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		
        return encrypted;
	}
	
	public static void main(String[] args) {
		String text = "admin";
		
		String encodedText = Encoder.encode(text);
		System.out.println("Encrypted: "+encodedText);
	}
	
}
