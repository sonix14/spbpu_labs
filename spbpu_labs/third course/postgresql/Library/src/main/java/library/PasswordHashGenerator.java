package autopark;

import java.security.NoSuchAlgorithmException;

public class PasswordHashGenerator {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SecurityManager securityManager = new SecurityManager();
        String password = "user1pwd";
        System.out.println(securityManager.getHash(password));

        System.out.println(securityManager.logIn("user1", "user1pwd"));
        System.out.println(securityManager.logIn("user2", "user2pwd"));

    }
}
