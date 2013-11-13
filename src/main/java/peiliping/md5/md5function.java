package peiliping.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class md5function {
	
	public String md5(String text){
        
        byte[] bytes = md5Bytes(text);

        String md5Str = new String();
        byte   tb;
        char   low;
        char   high;
        char   tmpChar;
        
        for (int i = 0; i < bytes.length; i++) {
            tb = bytes[i];

            tmpChar = (char) ((tb >>> 4) & 0x000f);
            if (tmpChar >= 10) {
                high = (char) (('a' + tmpChar) - 10);
            } else {
                high = (char) ('0' + tmpChar);
            }
            md5Str += high;
            
            tmpChar = (char) (tb & 0x000f);
            if (tmpChar >= 10) {
                low = (char) (('a' + tmpChar) - 10);
            } else {
                low = (char) ('0' + tmpChar);
            }
            md5Str += low;
        }

        return md5Str;
	}
	
    public static byte[] md5Bytes(String text) {
        if (null == text || "".equals(text)) {
            return new byte[0];
        }
        
        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }

        msgDigest.update(text.getBytes());
        byte[] bytes = msgDigest.digest();
        return bytes;
    }

}
