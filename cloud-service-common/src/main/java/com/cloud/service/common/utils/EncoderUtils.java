package com.cloud.service.common.utils;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

@SuppressWarnings("restriction")
public class EncoderUtils {

	/* Base64加密 */
	public static byte[] base64Encode(byte[] sourceData){
		return Base64.encodeBase64(sourceData);
	}
	
	/* Base64加密返回String */
	public static String base64EncodeToString(byte[] sourceData){
		return new BASE64Encoder().encode(sourceData);
	}
	
	/* Base64加密 String */
	public static String base64StringEncode(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}
	
	/* Base64解密  */
	public static byte[] base64Decode(byte[] encryptedData){
		return Base64.decodeBase64(encryptedData);
	}
	
	/* Base64解密 String */
	public String base64StringDecode(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
