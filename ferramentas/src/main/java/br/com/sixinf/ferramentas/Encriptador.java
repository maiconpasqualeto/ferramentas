/*
 * Created on 24/11/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.com.sixinf.ferramentas;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * @author maicon
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Encriptador {
	
	        Cipher ecipher;
	        Cipher dcipher;
	    
	        // 8-byte Salt
	        byte[] salt = {
	            (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
	            (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03
	        };
	    
	        // Iteration count
	        int iterationCount = 19;
	    
	        
	        
	        public Encriptador() {}

			public Encriptador(String passPhrase) {
	            try {
	                // Create the key
	                PBEKeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
	                SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
	                ecipher = Cipher.getInstance(key.getAlgorithm());
	                dcipher = Cipher.getInstance(key.getAlgorithm());
	    
	                // Prepare the parameter to the ciphers
	                AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);	                
	    
	                // Create the ciphers
	                ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
	                dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
	            } catch (java.security.InvalidAlgorithmParameterException e) {
	            } catch (java.security.spec.InvalidKeySpecException e) {
	            } catch (javax.crypto.NoSuchPaddingException e) {
	            } catch (java.security.NoSuchAlgorithmException e) {
	            } catch (java.security.InvalidKeyException e) {
	            }
	        }
	    
	        public String encrypt(String str) {
	            try {
	                // Encode the string into bytes using utf-8
	                byte[] utf8 = str.getBytes("UTF8");
	    
	                // Encrypt
	                byte[] enc = ecipher.doFinal(utf8);
	    
	                // Encode bytes to base64 to get a string
	                return new sun.misc.BASE64Encoder().encode(enc);
	            } catch (javax.crypto.BadPaddingException e) {
	            } catch (IllegalBlockSizeException e) {
	            } catch (UnsupportedEncodingException e) {
	            }
	            return null;
	        }
	    
	        public String decrypt(String str) {
	            try {
	                // Decode base64 to get bytes
	                byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
	    
	                // Decrypt
	                byte[] utf8 = dcipher.doFinal(dec);
	    
	                // Decode using utf-8
	                return new String(utf8, "UTF8");
	            } catch (javax.crypto.BadPaddingException e) {
	            } catch (IllegalBlockSizeException e) {
	            } catch (UnsupportedEncodingException e) {
	            } catch (java.io.IOException e) {
	            }
	            return null;
	        }
	        
	        public String getHashOf(String source){
	    		try {
	    			MessageDigest md = MessageDigest.getInstance("SHA");
	    			
	    			md.reset();
	    			md.update(source.getBytes());
	    			
	    			BigInteger bi = new BigInteger(md.digest());	    	
	    			
	    			return bi.toString(32);
	    			
	    		}catch(NoSuchAlgorithmException e){
	    			return null;
	    		}catch(Exception e){
	    			return null;
	    		}
	    	}
}
