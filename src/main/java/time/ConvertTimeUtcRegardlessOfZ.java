package time;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ConvertTimeUtcRegardlessOfZ {
    private static final DateTimeFormatter UTC_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private long parseTime(String timeStr) {
        return toTimestamp(UTC_FORMAT.print(DateTime.parse(timeStr)));
    }

    public static long toTimestamp(String utc) {
        return DateTime.parse(utc).getMillis();
    }
}
