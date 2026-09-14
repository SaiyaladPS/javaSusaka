package applicationForm;

import org.mindrot.jbcrypt.BCrypt;


public class PasswordTest {

    public static void main(String[] args) {
        String password = "1234";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed);
    }
}
