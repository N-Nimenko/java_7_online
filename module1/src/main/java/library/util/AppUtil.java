package library.util;

import java.util.UUID;

public final class AppUtil {
    public static String FIND_BY_ID_MESSAGE_BOOK = "Enter book's id:";
    public static String FIND_BY_ID_MESSAGE_AUTHOR = "Enter author's id:";

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}

