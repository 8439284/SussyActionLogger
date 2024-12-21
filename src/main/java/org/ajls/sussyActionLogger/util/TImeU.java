package org.ajls.sussyActionLogger.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TImeU {
    public static String getTime() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(now);
    }
}
