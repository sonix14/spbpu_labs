package autopark;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SecurityManager {

    private static final Map<String, String> passwords = new HashMap<>();
    private final MessageDigest md;

    static {
        passwords.put("user1", "~��r��ڽ��\u000BW`��R");
        passwords.put("user2", "=(\u001D!ĝy���,�D\u0019��");
    }

    public SecurityManager() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("MD5");
    }

    public boolean logIn(String username, String password) {
        String passwordHash = passwords.get(username);
        if (passwordHash == null) return false;
        return  passwordHash.equals(getHash(password));
    }

    public String getHash(String toHash){
        md.update(toHash.getBytes());
        return new String(md.digest());
    }
}
