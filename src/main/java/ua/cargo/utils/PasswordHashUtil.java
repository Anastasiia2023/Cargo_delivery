package ua.cargo.utils;

import ua.cargo.exceptions.IncorrectPasswordException;
import org.mindrot.jbcrypt.BCrypt;

public final class PasswordHashUtil {

    private static final BCrypt bcrypt = new BCrypt();

    private PasswordHashUtil() {
    }

    public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static void verify(String password, String hash) throws IncorrectPasswordException {
        if (!BCrypt.checkpw(password, hash)) {
            throw new IncorrectPasswordException();
        }
    }
}