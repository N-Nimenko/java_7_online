package homework7.util;

import java.util.UUID;

public final class AppUtil {
    public static String FIND_BY_ID_MESSAGE_PAINTING = "Enter painting id:";
    public static String FIND_BY_ID_MESSAGE_ARTIST = "Enter artist's id:";

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
