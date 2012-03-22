package mila.tags;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public final class Helpers {
	private Helpers() {}
	
	public static java.lang.String URLDecode(java.lang.String value) {
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unsupported encoding WTF");
		}
	}
	
	public static java.lang.String URLEncode(java.lang.String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Unsupported encoding WTF");
		}
	}
}
